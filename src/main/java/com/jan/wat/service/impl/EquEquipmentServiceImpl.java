package com.jan.wat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jan.wat.EquServer.helper.DateTime;
import com.jan.wat.EquServer.helper.Tools;
import com.jan.wat.mapper.EquipmentdataMapper;
import com.jan.wat.pojo.EquDatatype;
import com.jan.wat.pojo.EquEquipment;
import com.jan.wat.mapper.EquEquipmentMapper;
import com.jan.wat.pojo.EquEquipmentrealdata;
import com.jan.wat.pojo.Equipmentdata;
import com.jan.wat.pojo.vo.*;
import com.jan.wat.service.IEquDatatypeService;
import com.jan.wat.service.IEquEquipmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jan.wat.service.IEquEquipmentgroupService;
import com.jan.wat.service.IEquEquipmentrealdataService;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-06-25
 */
@Service("EquEquipmentServiceImpl")
public class EquEquipmentServiceImpl extends ServiceImpl<EquEquipmentMapper, EquEquipment> implements IEquEquipmentService {

    @Autowired
    EquEquipmentMapper equEquipmentMapper;

    @Autowired
    IEquEquipmentrealdataService iEquEquipmentrealdataService;

    @Autowired
    IEquDatatypeService iEquDatatypeService;

    @Autowired
    IEquEquipmentgroupService iEquEquipmentgroupService;

    @Autowired
    EquipmentdataMapper equipmentdataMapper;

    @Autowired
    IEquEquipmentService iEquEquipmentService;

    @Override
    public List<EuipmentsQuery> getEuipments(String euipmentGroupID, String equipmentId, String userCode) {
        StringBuilder where = new StringBuilder("");
//        System.out.println("=============="+euipmentGroupID);
        if(!euipmentGroupID.equals("")){
            List<String> childrenGroupId = iEquEquipmentgroupService.getChildrenId(userCode, euipmentGroupID);
            System.out.println(childrenGroupId);
            if(childrenGroupId.size()>0){
                where.append(" and A.ID in (");
                int count = 0;
                int length = childrenGroupId.size();
                for(String i : childrenGroupId){
                    where.append(i);
                    count++;
                    if(count == length)
                        continue;
                    where.append(",");
                }
                where.append(")");
            }else{
                return new ArrayList<EuipmentsQuery>();
            }
        }
        if(!equipmentId.equals("0") && !equipmentId.equals("")){
            where.append(String.format(" and A.ID=%s",equipmentId));
        }

        return equEquipmentMapper.getEuipments(userCode, where.toString());
    }

    @Override
    public String getRealDataQuery(String equipmentGroupId, String equipmentId, String usercode) {
        StringBuilder where = new StringBuilder();

        if(!equipmentGroupId.equals("0")){
            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentGroupId);
            StringBuilder set = new StringBuilder();
            int count = 0;
            set.append(equipmentGroupId);
            for(int i : childrengroupId){
                count++;
                set.append(String.valueOf(i));
                if(count != childrengroupId.size())
                    set.append(",");
            }
            where.append(String.format("and uem.EquipmentGroup_ID in (%s)",set.toString()));
        }

        if(!equipmentId.equals("0"))
            where.append(String.format(" and e.ID = %s",equipmentId));

        List<EquDatatype> datatypes = iEquDatatypeService.list();
        Map<Integer, EquDatatype> hashTable = new HashMap<Integer, EquDatatype>();
        for(EquDatatype item : datatypes){
            hashTable.put(item.getId(), item);
        }
        Set<Integer> hash = new HashSet<Integer>();

        List<RealDataQuery> realDataQuery = equEquipmentMapper.getRealDataQuery(usercode,where.toString());
        JSONArray array = new JSONArray();
        for(RealDataQuery realData : realDataQuery){
            JSONObject json = new JSONObject();
            json.put("equipmentalarm",realData.getEquipmentalarm());
            json.put("id",realData.getId());
            json.put("lastcollecttime", DateTime.format(realData.getLastcollecttime()));
            json.put("n",realData.getN());
            json.put("outLinealarm",realData.getOutLinealarm());
            LambdaQueryWrapper<EquEquipmentrealdata> wrapper = new LambdaQueryWrapper<>();
            wrapper.orderByAsc(EquEquipmentrealdata::getPosition);
            wrapper.eq(EquEquipmentrealdata::getEquipmentId,realData.getId());
            List<EquEquipmentrealdata> list = iEquEquipmentrealdataService.list(wrapper);
            for(EquEquipmentrealdata equEquipmentrealdata : list){
                if(!hash.contains(equEquipmentrealdata.getDatatypeId()))
                    hash.add(equEquipmentrealdata.getDatatypeId());
                json.put(String.format("%s",equEquipmentrealdata.getDatatypeId().toString()),equEquipmentrealdata.getValue());
            }
            array.add(json);
        }
        JSONArray head = new JSONArray();
        Iterator<Integer> iterator = hash.iterator();

        while(iterator.hasNext()){
            JSONObject json = new JSONObject();
            int i = iterator.next();
            if(i == 0)
                continue;
            EquDatatype item =  hashTable.get(i);
            json.put("label",item.getName());
            json.put("key",item.getId().toString());
            head.add(json);
        }
        JSONObject body = new JSONObject();

        body.put("tableInfo", head);
        body.put("list", array);


        return body.toString();
    }

    @Override
    public String getEquipmentValue(String usercode, String equipmentId) {
        StringBuilder where = new StringBuilder();
        where.append(String.format(" and e.ID = %s",equipmentId));
        List<EquDatatype> datatypes = iEquDatatypeService.list();
        Map<Integer, EquDatatype> hashTable = new HashMap<Integer, EquDatatype>();
        for(EquDatatype item : datatypes){
            hashTable.put(item.getId(), item);
        }
        JSONObject object = new JSONObject();
        LambdaQueryWrapper<EquEquipmentrealdata> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(EquEquipmentrealdata::getPosition);
        wrapper.eq(EquEquipmentrealdata::getEquipmentId, equipmentId);
        List<EquEquipmentrealdata> list = iEquEquipmentrealdataService.list(wrapper);
        for(EquEquipmentrealdata equEquipmentrealdata : list){
            if(equEquipmentrealdata.getDatatypeId() == 0)
                continue;
            String name = hashTable.get(equEquipmentrealdata.getDatatypeId()).getName();
            object.put(name, equEquipmentrealdata.getValue());
        }
        return object.toJSONString();
    }

    @Override
    public List<EquEquipment> getEquEquipment(String usercode, String equipment_id, String equipmentgroup_id) {
        StringBuilder set = new StringBuilder();
        set.append(equipmentgroup_id);
        if(equipmentgroup_id != "0"){
            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentgroup_id);
            for(int i : childrengroupId){

                set.append(",");
                set.append(String.valueOf(i));

            }
        }
        return equEquipmentMapper.getEquEquipment(usercode,set.toString(),equipment_id,equipmentgroup_id);
    }

    @Override
    public List<EquipmentQuery> getEquipmentbyequipmentgroupid(Integer equipmentgroup_id) {
        return equEquipmentMapper.getEquipmentbyequipmentgroupid(equipmentgroup_id);
    }

    @Override
    public List<String> getEquipmentid() {
        return equEquipmentMapper.getEquipmentid();
    }

    @Override
    public List<EquEquipment> getEquEquipmentByUsercode(String usercode) {
        return equEquipmentMapper.getEquEquipmentByUsercode(usercode);
    }


    @Override
    public List<RealValue> encodeXML(String xml){
//        String xml = "<D><V i=\"0\">-0.787</V><V i=\"1\">-0.626</V><V i=\"2\">2.78</V><V i=\"3\">3.0</V><V i=\"4\">9019740.1</V><V i=\"5\">9000957.3</V><V i=\"218\">18782.8</V><V i=\"14\">0.0</V><V i=\"219\">0.0</V><V i=\"10\">0.0</V><V i=\"12\">0.0</V><V i=\"13\">0.0</V><V i=\"220\">0.0</V><V i=\"11\">0.0</V><V i=\"36\">0.0</V><V i=\"34\">2485.0</V><V i=\"20\">0.0</V><V i=\"21\">1.0</V><V i=\"22\">0.0</V><V i=\"23\">0.0</V><V i=\"43\">0.0</V><V i=\"224\">0.0</V><V i=\"213\">0.0</V><V i=\"17\">100.0</V><V i=\"228\">0.0</V><V i=\"225\">0.0</V><V i=\"18\">100.0</V><V i=\"227\">0.0</V><V i=\"19\">24.0</V></D>";
        System.out.println(xml);
        Iterator<Element> iter = Tools.XML2iter(xml);
        List<RealValue> list = new ArrayList<>();
        while(iter.hasNext()){
            Element element= iter.next();
            List<Attribute> attributes = element.attributes();
            RealValue realValue = new RealValue();
            realValue.setKey(Integer.parseInt(attributes.get(0).getValue()));
            realValue.setValue(Double.parseDouble(element.getStringValue()));
            list.add(realValue);
        }
        return list;
    }

    @Override
    public String getValue(String usercode, String id, String startTime, String endTime) {

        List<String> databases = getMonths(startTime, endTime);
        Map<Integer, String> map = iEquDatatypeService.getMap();
        List<Equipmentdata> all = new ArrayList<>();
        for(String database : databases){
            if(equipmentdataMapper.isExit(database) == 0)
                continue;
            List<Equipmentdata> data = equipmentdataMapper.getData(database, startTime, endTime, id);
            all.addAll(data);
        }
        LambdaQueryWrapper<EquEquipment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EquEquipment::getId, id);
        List<EquEquipment> list = iEquEquipmentService.list(queryWrapper);


        Set<Integer> set = new TreeSet<>();
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        if(all.isEmpty())
            return object.toJSONString();
        for(Equipmentdata item : all){
            String xml = item.getData();
//            System.out.println(xml);

            List<RealValue> realValues = encodeXML(xml);
//            System.out.println(realValues);

            JSONObject tmp = new JSONObject();
            tmp.put("Collecttime",DateTime.format(item.getCollecttime()));
            tmp.put("Uploadtime",DateTime.format(item.getUploadtime()));
            tmp.put("name",list.get(0).getAname());
            tmp.put("id",id);
            for(RealValue value : realValues){
                if(!set.contains(value.getKey()))
                    set.add(value.getKey());
                    tmp.put(String.valueOf(value.getKey()),value.getValue());
            }
            array.add(tmp);
        }

        object.put("data", array);
        JSONArray head = new JSONArray();
        Iterator<Integer> iterator = set.iterator();
        while(iterator.hasNext()){
            JSONObject tmp = new JSONObject();
            Integer next = iterator.next();
            tmp.put("key",next);
            tmp.put("value", map.get(next) == null ? "" : map.get(next));
//            System.out.println(next+" "+map.get(next));
            head.add(tmp);
        }
        object.put("head",head);


        return object.toJSONString();
    }

    @Override
    public List<String> getMonths(String time1, String time2){
        String startDate = time1.substring(0,4)+time1.substring(5,7);
        String endDate = time2.substring(0,4)+time2.substring(5,7);
//        System.out.println(startDate+"=============="+endDate);
        ArrayList<String> result = new ArrayList<String>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");//格式化为年月

            Calendar min = Calendar.getInstance();
            Calendar max = Calendar.getInstance();
            min.setTime(sdf.parse(startDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(sdf.parse(endDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

            Calendar curr = min;
            while (curr.before(max)) {
                result.add("rdasdata"+sdf.format(curr.getTime()));
                curr.add(Calendar.MONTH, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getMonth(String time){
        String timeDate = time.substring(0,4)+time.substring(5,7);
        return "rdasdata" + timeDate;
    }

    @Override
    public List<MapQuery> getJW(String usercode) {
        return equEquipmentMapper.getMaps(usercode);
    }

    @Override
    public String getAccumulateData(String equipmentId, String startTime, String endTime, int interval) {
        JSONObject json = new JSONObject();
        JSONArray data = new JSONArray();
        LocalDateTime start = DateTime.getTime(startTime);
        LocalDateTime end = DateTime.getTime(endTime);

        int diffDays = (int) Duration.between(start, end).toDays();

        AccumulateDataQuery tmpValue = new AccumulateDataQuery();
        AccumulateDataQuery endValue = new AccumulateDataQuery();
        List<AccumulateDataQuery> lists = new ArrayList<>();
        int h_add = interval;
        for(int h = 0; h < diffDays; h = h + h_add){
            if((h + interval) >= diffDays){
                h_add = diffDays- h;
                if(h_add == 0) break;
            }
            else h_add = interval;
            LocalDateTime rangeStartTime = start.plusDays(h);
            LocalDateTime rangeEndTime = rangeStartTime.plusDays(h_add-1);
            String databaseName = getMonth(rangeStartTime
            .format(DateTime.getPattern()));
            if(equipmentdataMapper.isExit(databaseName) == 0)
                continue;
            if(h == 0){
                List<AccumulateDataQuery> accumulateData = equEquipmentMapper.getAccumulateData(databaseName
                        , rangeStartTime.format(DateTime.getPattern())
                        , endTime, equipmentId);
                if(accumulateData.size() < 1)
                        continue;
                tmpValue = accumulateData.get(0);
            }
            if(Duration.between(rangeStartTime.plusDays(h_add),LocalDateTime.now()).toDays() < 0)break;

            databaseName = getMonth(rangeStartTime.plusDays(h_add)
                    .format(DateTime.getPattern()));
            if(equipmentdataMapper.isExit(databaseName) == 0)
                continue;
            List<AccumulateDataQuery> accumulateData = equEquipmentMapper.getAccumulateData(databaseName
                    , rangeStartTime.plusDays(h_add).format(DateTime.getPattern())
                    , endTime, equipmentId);
            if(accumulateData.size() < 1)
                continue;
            endValue = accumulateData.get(0);

            JSONObject object = new JSONObject();
            object.put("equipment_ID",tmpValue.getEquipment_ID());
            object.put("equipment_Name",tmpValue.getEquipment_ID());
            object.put("collectTime",rangeStartTime.toString().substring(0,10) +
                    "--" + rangeEndTime.toString().substring(0,10));
            object.put("cold",endValue.getCold()-tmpValue.getCold());
            object.put("diff",endValue.getDiff()-tmpValue.getDiff());
            object.put("heat",endValue.getHeat()-tmpValue.getHeat());
            object.put("negative",endValue.getNegative()-tmpValue.getNegative());
            object.put("positive",endValue.getPositive()-tmpValue.getPositive());
            AccumulateDataQuery obj = new AccumulateDataQuery();
            obj.setEquipment_ID(tmpValue.getEquipment_ID());
            obj.setEquipment_Name(rangeStartTime.toString().substring(0,10) +
                    "--" + rangeEndTime.toString().substring(0,10));
            obj.setCollectTime(rangeStartTime);
            obj.setCold(endValue.getCold()-tmpValue.getCold());
            obj.setDiff(endValue.getDiff()-tmpValue.getDiff());
            obj.setHeat(endValue.getHeat()-tmpValue.getHeat());
            obj.setNegative(endValue.getNegative()-tmpValue.getNegative());
            obj.setPositive(endValue.getPositive()-tmpValue.getPositive());
            data.add(object);
            lists.add(obj);
            tmpValue = endValue;
        }
        List<Integer> index = new ArrayList<>();
        index.add(lists.size());
        json.put("data",data);
        json.put("index",index);
        json.put("Acc", getFooter_HistoryAccumulate(lists, lists.size()));
        return json.toString();
    }

    @Override
    public String getAccumulateDataMonth(List<String> equipmentIds, String startTime, String endTime) {
        JSONObject json = new JSONObject();
        JSONArray data = new JSONArray();
        LocalDateTime start = DateTime.getTime(startTime);
        LocalDateTime end = DateTime.getTime(endTime);
        int month1 = start.getMonthValue();
        int month2 = end.getMonthValue();
        int year1 = start.getYear();
        int year2 = end.getYear();
        int diffMonths = (year2 - year1)*12 + month2 -month1;
        List<AccumulateDataQuery> lists = new ArrayList<>();
        List<Integer> index = new ArrayList<>();
        int count = 0;
        for(int m = 0; m < equipmentIds.size(); ++m){
            String equipment_id = equipmentIds.get(m);
            AccumulateDataQuery tmpValue = new AccumulateDataQuery();
            AccumulateDataQuery endValue = new AccumulateDataQuery();
            int monthStep = 0;
            int index_item = 0;
            for(int h = 0; h < (diffMonths + 2); h++){

                LocalDateTime readTime = start.plusMonths(h);
                String databaseName = getMonth(readTime
                        .format(DateTime.getPattern()));
                if(equipmentdataMapper.isExit(databaseName) == 0)
                    continue;
                List<AccumulateDataQuery> accumulateData = equEquipmentMapper.getAccumulateData(databaseName
                        , readTime.format(DateTime.getPattern())
                        , end.plusMonths(diffMonths+5).format(DateTime.getPattern()), equipment_id);

                if(accumulateData.size() < 1){
                    if(h > 0) monthStep++;
                    continue;
                }

                if(h == 0){
                    tmpValue = accumulateData.get(0);
                }else{
                    endValue = accumulateData.get(0);
                    JSONObject object = new JSONObject();
                    object.put("equipment_ID",endValue.getEquipment_ID());
                    object.put("equipment_Name",endValue.getEquipment_ID());
                    object.put("collectTime",readTime.plusMonths(-1-monthStep).toString().substring(0,7));
                    object.put("cold",endValue.getCold()-tmpValue.getCold());
                    object.put("diff",endValue.getDiff()-tmpValue.getDiff());
                    object.put("heat",endValue.getHeat()-tmpValue.getHeat());
                    object.put("negative",endValue.getNegative()-tmpValue.getNegative());
                    object.put("positive",endValue.getPositive()-tmpValue.getPositive());
                    AccumulateDataQuery obj = new AccumulateDataQuery();
                    obj.setEquipment_ID(tmpValue.getEquipment_ID());
                    obj.setEquipment_Name(readTime.plusMonths(-1-monthStep).toString().substring(0,7));
                    obj.setCollectTime(readTime);
                    obj.setCold(endValue.getCold()-tmpValue.getCold());
                    obj.setDiff(endValue.getDiff()-tmpValue.getDiff());
                    obj.setHeat(endValue.getHeat()-tmpValue.getHeat());
                    obj.setNegative(endValue.getNegative()-tmpValue.getNegative());
                    obj.setPositive(endValue.getPositive()-tmpValue.getPositive());
                    data.add(object);
                    lists.add(obj);
                    index_item++;
                    tmpValue = endValue;
                    while (monthStep != 0){
                        monthStep--;
                        JSONObject o = new JSONObject();
                        o.put("equipment_ID",tmpValue.getEquipment_ID());
                        o.put("equipment_Name",tmpValue.getEquipment_ID());
                        o.put("collectTime",readTime.plusMonths(-1-monthStep).toString().substring(0,7));
                        o.put("cold",0);
                        o.put("diff",0);
                        o.put("heat",0);
                        o.put("negative",0);
                        o.put("positive",0);
                        data.add(o);
                        index_item++;
                    }
                }
            }
            index.add(index_item);
            count += index_item;
        }
        json.put("data",data);
        json.put("index",index);
        json.put("Acc", getFooter_HistoryAccumulate(lists,count));
        return json.toJSONString();
    }

    @Override
    public String getAccumulateDataYear(List<String> equipmentIds, String startTime, String endTime) {
        JSONObject json = new JSONObject();
        JSONArray data = new JSONArray();
        LocalDateTime start = DateTime.getTime(startTime);
        LocalDateTime end = DateTime.getTime(endTime);

        int year1 = start.getYear();
        int year2 = end.getYear();
        int diffYears = year2 - year1;
        List<AccumulateDataQuery> lists = new ArrayList<>();
        List<Integer> index = new ArrayList<>();
        int count = 0;
        for(int m = 0; m < equipmentIds.size(); ++m){
            String equipment_id = equipmentIds.get(m);
            String equipment_name = new String();
            AccumulateDataQuery tmpValue = new AccumulateDataQuery();
            AccumulateDataQuery endValue = new AccumulateDataQuery();
            int yearStep = 0;
            int index_item = 0;
            TreeMap<String, AccumulateDataQuery> hash = new TreeMap<>();
            for(int h = 0; h < (diffYears + 2); h++){
                for(int a = 1; a < 13; a++) {
                    LocalDateTime readTime = start.plusYears(h).plusMonths(a);
                    String databaseName = getMonth(readTime
                            .format(DateTime.getPattern()));
                    if(equipmentdataMapper.isExit(databaseName) == 0)
                        continue;
                    List<AccumulateDataQuery> accumulateData = equEquipmentMapper.getAccumulateData(databaseName
                            , readTime.format(DateTime.getPattern())
                            , end.plusYears(diffYears+5).format(DateTime.getPattern()), equipment_id);

                    if(accumulateData.size() < 1){
                        if(a == 12 && h > 0) yearStep++;
                        continue;
                    }else {
                        if (h == 0) {
                            tmpValue = accumulateData.get(0);
                        } else {
                            endValue = accumulateData.get(0);
                            if(endValue.getEquipment_Name() != null)
                                equipment_name = endValue.getEquipment_Name();
                            String yearString = readTime.plusYears(-1 - yearStep).toString().substring(0, 4);
                            if(hash.containsKey(yearString)){
                                AccumulateDataQuery accumulateDataQuery = hash.get(yearString);
                                hash.get(yearString).setCold(accumulateDataQuery.getCold() + endValue.getCold() - tmpValue.getCold());
                                hash.get(yearString).setDiff(accumulateDataQuery.getDiff() + endValue.getDiff() - tmpValue.getDiff());
                                hash.get(yearString).setHeat(accumulateDataQuery.getHeat() + endValue.getHeat() - tmpValue.getHeat());
                                hash.get(yearString).setNegative(accumulateDataQuery.getNegative() + endValue.getNegative() - tmpValue.getNegative());
                                hash.get(yearString).setPositive(accumulateDataQuery.getPositive() + endValue.getPositive() - tmpValue.getPositive());
                            }else{
                                AccumulateDataQuery obj = new AccumulateDataQuery();
                                obj.setEquipment_ID(tmpValue.getEquipment_ID());
                                obj.setEquipment_Name(yearString);
                                obj.setCollectTime(readTime);
                                obj.setCold(endValue.getCold() - tmpValue.getCold());
                                obj.setDiff(endValue.getDiff() - tmpValue.getDiff());
                                obj.setHeat(endValue.getHeat() - tmpValue.getHeat());
                                obj.setNegative(endValue.getNegative() - tmpValue.getNegative());
                                obj.setPositive(endValue.getPositive() - tmpValue.getPositive());
                                hash.put(yearString, obj);
                            }

                            tmpValue = endValue;
                            while (yearStep != 0) {
                                yearStep--;
                                String yearString1 = readTime.plusYears(-1 - yearStep).toString().substring(0, 4);
                                if(!hash.containsKey(yearString1)){
                                    AccumulateDataQuery obj = new AccumulateDataQuery();
                                    obj.setEquipment_ID(tmpValue.getEquipment_ID());
                                    obj.setEquipment_Name(yearString1);
                                    obj.setCollectTime(readTime);
                                    obj.setCold(0.0f);
                                    obj.setDiff(0.0f);
                                    obj.setHeat(0.0f);
                                    obj.setNegative(0.0f);
                                    obj.setPositive(0.0f);
                                    hash.put(yearString1, obj);
                                }
                            }
                        }
                    }
                }
            }

            Iterator iter = hash.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry entry = (Map.Entry)iter.next();
                String str = (String) entry.getKey();
                AccumulateDataQuery accumulateDataQuery = (AccumulateDataQuery) entry.getValue();
                JSONObject object = new JSONObject();
                object.put("equipment_ID", equipment_id);
                object.put("equipment_Name", equipment_name);
                object.put("collectTime", str);
                object.put("cold", accumulateDataQuery.getCold());
                object.put("diff", accumulateDataQuery.getDiff());
                object.put("heat", accumulateDataQuery.getHeat());
                object.put("negative", accumulateDataQuery.getNegative());
                object.put("positive", accumulateDataQuery.getPositive());
                lists.add(accumulateDataQuery);
                data.add(object);
                index_item++;
            }
            index.add(index_item);
            count += index_item;
        }
        json.put("data",data);
        json.put("index",index);
        json.put("Acc", getFooter_HistoryAccumulate(lists,count));
        return json.toJSONString();
    }

    @Override
    public JSONArray getFooter_HistoryAccumulate(List<AccumulateDataQuery> dataQueries, int l){
        JSONArray array = new JSONArray();
        int len = dataQueries.size();
        if(len == 0)
            return array;
        int[] mx = new int[5];
        int[] mn = new int[5];
        float[] mean=new float[5];
        for(int i = 0; i < 5; ++i){
            mx[i] = 0;
            mn[i] = 0;
            mean[i]= 0.0F;
        }
        for(int i = 0; i < len; ++i){
            if(dataQueries.get(i).getPositive() > dataQueries.get(mx[0]).getPositive()){
                mx[0] = i;
            }
            if(dataQueries.get(i).getNegative() > dataQueries.get(mx[1]).getNegative()){
                mx[1] = i;
            }
            if(dataQueries.get(i).getHeat() > dataQueries.get(mx[2]).getHeat()){
                mx[2] = i;
            }
            if(dataQueries.get(i).getDiff() > dataQueries.get(mx[3]).getDiff()){
                mx[3] = i;
            }
            if(dataQueries.get(i).getCold() > dataQueries.get(mx[4]).getCold()){
                mx[4] = i;
            }
            if(dataQueries.get(i).getPositive() < dataQueries.get(mn[0]).getPositive()){
                mn[0] = i;
            }
            if(dataQueries.get(i).getNegative() < dataQueries.get(mn[1]).getNegative()){
                mn[1] = i;
            }
            if(dataQueries.get(i).getHeat() < dataQueries.get(mn[2]).getHeat()){
                mn[2] = i;
            }
            if(dataQueries.get(i).getDiff() < dataQueries.get(mn[3]).getDiff()){
                mn[3] = i;
            }
            if(dataQueries.get(i).getCold() < dataQueries.get(mn[4]).getCold()){
                mn[4] = i;
            }
            mean[0] += dataQueries.get(i).getPositive();
            mean[1] += dataQueries.get(i).getNegative();
            mean[2] += dataQueries.get(i).getHeat();
            mean[3] += dataQueries.get(i).getDiff();
            mean[4] += dataQueries.get(i).getCold();
        }

        JSONObject object = new JSONObject();
        object.put("collectTime","最大值");
        object.put("positive",dataQueries.get(mx[0]).getPositive());
        object.put("negative",dataQueries.get(mx[1]).getNegative());
        object.put("heat",dataQueries.get(mx[2]).getHeat());
        object.put("diff",dataQueries.get(mx[3]).getDiff());
        object.put("cold",dataQueries.get(mx[4]).getCold());
        array.add(object);
        object = new JSONObject();
        object.put("collectTime","最大值时间");
        object.put("positive",dataQueries.get(mx[0]).getEquipment_Name());
        object.put("negative",dataQueries.get(mx[1]).getEquipment_Name());
        object.put("heat",dataQueries.get(mx[2]).getEquipment_Name());
        object.put("diff",dataQueries.get(mx[3]).getEquipment_Name());
        object.put("cold",dataQueries.get(mx[4]).getEquipment_Name());
        array.add(object);
        object = new JSONObject();
        object.put("collectTime","最小值");
        object.put("positive",dataQueries.get(mn[0]).getPositive());
        object.put("negative",dataQueries.get(mn[1]).getNegative());
        object.put("heat",dataQueries.get(mn[2]).getHeat());
        object.put("diff",dataQueries.get(mn[3]).getDiff());
        object.put("cold",dataQueries.get(mn[4]).getCold());
        array.add(object);
        object = new JSONObject();
        object.put("collectTime","最小值时间");
        object.put("positive",dataQueries.get(mn[0]).getEquipment_Name());
        object.put("negative",dataQueries.get(mn[1]).getEquipment_Name());
        object.put("heat",dataQueries.get(mn[2]).getEquipment_Name());
        object.put("diff",dataQueries.get(mn[3]).getEquipment_Name());
        object.put("cold",dataQueries.get(mn[4]).getEquipment_Name());
        array.add(object);
        object = new JSONObject();
        object.put("collectTime","平均值");
        object.put("positive",mean[0]/l);
        object.put("negative",mean[1]/l);
        object.put("heat",mean[2]/l);
        object.put("diff",mean[3]/l);
        object.put("cold",mean[4]/l);
        array.add(object);
        object = new JSONObject();
        object.put("collectTime","合计");
        object.put("positive",mean[0]);
        object.put("negative",mean[1]);
        object.put("heat",mean[2]);
        object.put("diff",mean[3]);
        object.put("cold",mean[4]);
        array.add(object);
        return array;
    }
}
