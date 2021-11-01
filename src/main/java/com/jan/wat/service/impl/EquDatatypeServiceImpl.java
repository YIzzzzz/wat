package com.jan.wat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jan.wat.EquServer.helper.DateTime;
import com.jan.wat.mapper.EquipmentdataMapper;
import com.jan.wat.pojo.EquDatatype;
import com.jan.wat.mapper.EquDatatypeMapper;
import com.jan.wat.pojo.Equipmentdata;
import com.jan.wat.pojo.vo.RealValue;
import com.jan.wat.service.IEquDatatypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jan.wat.service.IEquEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Service("EquDatatypeServiceImpl")
public class EquDatatypeServiceImpl extends ServiceImpl<EquDatatypeMapper, EquDatatype> implements IEquDatatypeService {

    @Autowired
    EquDatatypeMapper equDatatypeMapper;

    @Autowired
    IEquEquipmentService iEquEquipmentService;

    @Autowired
    EquipmentdataMapper equipmentdataMapper;

    @Override
    public Map<Integer, String> getMap(){
        Map<Integer, String> map = new HashMap<>();
        List<EquDatatype> list = list();
        for(EquDatatype item : list){
            map.put(item.getId(), item.getName());
        }
        return map;
    }

    @Override
    public List<EquDatatype> getEquDataType(String id) {
        return equDatatypeMapper.getEquDataType(id);
    }

    @Override
    public JSONObject getRealCurve(String usercode, String equipment_id, String datatype_id, String startTime, String endTime) {
        JSONObject object = new JSONObject();
        List<String> databases = iEquEquipmentService.getMonths(startTime, endTime);
        List<Equipmentdata> all = new ArrayList<>();
        for(String database : databases){
            if(equipmentdataMapper.isExit(database) == 0)
                continue;
            List<Equipmentdata> data = equipmentdataMapper.getData(database, startTime, endTime, equipment_id);
            all.addAll(data);
        }
        if(all.isEmpty()) {
            JSONArray tmp = new JSONArray();
            object.put("x", tmp);
            object.put("y", tmp);
            return object;
        }
        List<String> time = new ArrayList<>();
        List<String> value = new ArrayList<>();
        for(Equipmentdata item : all){
            String xml = item.getData();
//            System.out.println(xml);

            List<RealValue> realValues = iEquEquipmentService.encodeXML(xml);
//            System.out.println(realValues);
            time.add(DateTime.format(item.getCollecttime()));
            for(RealValue r : realValues){
                if (r.getKey() == Integer.parseInt(datatype_id)) {
                    value.add(String.valueOf(r.getValue()));
                }

            }
        }

        JSONArray t = JSONArray.parseArray(JSON.toJSONString(time));
        JSONArray v = JSONArray.parseArray(JSON.toJSONString(value));
        object.put("x", t);
        object.put("y", v);
        return object;
    }

    @Override
    public List<EquDatatype> getMulDataType(List<String> list) {
        Set<EquDatatype> s = new TreeSet<>();
        StringBuilder where = new StringBuilder();
        where.append(list.get(0));
        for(int i = 1; i < list.size(); ++i){
            where.append(" , ");
            where.append(list.get(i));
        }
        return equDatatypeMapper.getMulDataType(where.toString());
    }

}
