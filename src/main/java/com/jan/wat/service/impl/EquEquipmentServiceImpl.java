package com.jan.wat.service.impl;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        if(all.isEmpty())
            return "";
        Set<Integer> set = new TreeSet<>();
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Equipmentdata item : all){
            String xml = item.getData();
//            System.out.println(xml);

            List<RealValue> realValues = encodeXML(xml);
//            System.out.println(realValues);

            JSONObject tmp = new JSONObject();
            tmp.put("Collecttime",DateTime.format(item.getCollecttime()));
            tmp.put("Uploadtime",DateTime.format(item.getUploadtime()));
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
        LambdaQueryWrapper<EquEquipment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EquEquipment::getId, id);
        List<EquEquipment> list = iEquEquipmentService.list(queryWrapper);
        object.put("name",list.get(0).getAname());
        return object.toJSONString();
    }
    @Override
    public List<String> getMonths(String time1, String time2){
        String startDate = time1.substring(0,4)+time1.substring(4,6);
        String endDate = time2.substring(0,4)+time2.substring(4,6);
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


}
