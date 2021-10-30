package com.jan.wat.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jan.wat.pojo.EquAlarmrecord;
import com.jan.wat.mapper.EquAlarmrecordMapper;
import com.jan.wat.pojo.vo.EquAlarmQuery;
import com.jan.wat.service.IEquAlarmrecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jan.wat.service.IEquEquipmentgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-06
 */
@Service("EquAlarmrecordServiceImpl")
public class EquAlarmrecordServiceImpl extends ServiceImpl<EquAlarmrecordMapper, EquAlarmrecord> implements IEquAlarmrecordService {

    @Autowired
    EquAlarmrecordMapper equAlarmrecordMapper;

    @Autowired
    IEquEquipmentgroupService iEquEquipmentgroupService;

    @Override
    public List<EquAlarmQuery> getEquUnrecoveryalarm(String usercode, String equipment_id, String equipmentgroup_id) {
        StringBuilder set = new StringBuilder();
        set.append(equipmentgroup_id);
        if(equipmentgroup_id != "0"){
            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentgroup_id);

            for(int i : childrengroupId){
                set.append(",");

                set.append(String.valueOf(i));
            }
        }
        return equAlarmrecordMapper.getEquUnrecoveryalarm(usercode,set.toString(),equipment_id,equipmentgroup_id);
    }

    @Override
    public List<EquAlarmQuery> getEquYesterdayalarm(String usercode, String equipment_id, String equipmentgroup_id) {
        StringBuilder set = new StringBuilder();
        set.append(equipmentgroup_id);
        if(equipmentgroup_id != "0"){
            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentgroup_id);

            for(int i : childrengroupId){
                set.append(",");

                set.append(String.valueOf(i));
            }
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime minus = localDateTime.minus(Period.ofDays(1));
        String start = minus.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));
        String end = minus.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59"));
//        String start = "2021-02-01 00:00:00";
//        String end = "2021-02-01 23:59:59";

        return equAlarmrecordMapper.getEquYesterdayalarm(usercode,start,end,set.toString(),equipment_id,equipmentgroup_id);
    }

    @Override
    public List<EquAlarmQuery> getEquYesterdayrecoveryalarm(String usercode, String equipment_id, String equipmentgroup_id) {
        StringBuilder set = new StringBuilder();
        set.append(equipmentgroup_id);
        if(equipmentgroup_id != "0"){
            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentgroup_id);

            for(int i : childrengroupId){
                set.append(",");

                set.append(String.valueOf(i));
            }
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime minus = localDateTime.minus(Period.ofDays(1));
        String start = minus.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));
        String end = minus.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59"));
//        String start = "2018-02-01 00:00:00";
//        String end = "2021-02-01 23:59:59";
        return equAlarmrecordMapper.getEquYesterdayrecoveryalarm(usercode,start,end,set.toString(),equipment_id,equipmentgroup_id);
    }

    @Override
    public List<EquAlarmQuery> getEquYesterdayconfirmalarm(String usercode, String equipment_id, String equipmentgroup_id) {
        StringBuilder set = new StringBuilder();
        set.append(equipmentgroup_id);
        if(equipmentgroup_id != "0"){
            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentgroup_id);

            for(int i : childrengroupId){
                set.append(",");

                set.append(String.valueOf(i));
            }
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime minus = localDateTime.minus(Period.ofDays(1));
        String start = minus.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));
        String end = minus.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59"));
//        String start = "2018-02-01 00:00:00";
//        String end = "2021-02-01 23:59:59";
        return equAlarmrecordMapper.getEquYesterdayconfirmalarm(usercode,start,end,set.toString(),equipment_id,equipmentgroup_id);
    }

    @Override
    public List<EquAlarmQuery> getEquUnconfirmalarm(String usercode, String equipment_id, String equipmentgroup_id) {
        StringBuilder set = new StringBuilder();
        set.append(equipmentgroup_id);
        if(equipmentgroup_id != "0"){
            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentgroup_id);

            for(int i : childrengroupId){
                set.append(",");

                set.append(String.valueOf(i));
            }
        }
        return equAlarmrecordMapper.getEquUnconfirmalarm(usercode,set.toString(),equipment_id,equipmentgroup_id);
    }

    @Override
    public List<EquAlarmQuery> getEquhistoryalarm(String usercode, String equipment_id, String equipmentgroup_id, String alarmtype, String start, String end) {
        StringBuilder set = new StringBuilder();
        set.append(equipmentgroup_id);
        if(equipmentgroup_id != "0"){
            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentgroup_id);

            for(int i : childrengroupId){
                set.append(",");

                set.append(String.valueOf(i));
            }
        }
        return equAlarmrecordMapper.getEquhistoryalarm(usercode, set.toString(),equipment_id,equipmentgroup_id,alarmtype,start,end);
    }

    @Override
    public String statisticsAlarm(String usercode, List<String> groupId, List<String> groupName,String startTime, String endTime) {
        JSONArray array = new JSONArray();
        Integer[] types = new Integer[3];
        types[0] = 1;
        types[1] = 2;
        types[2] = 3;
        for(int i = 0; i < groupId.size(); ++i){
            JSONObject tmp = new JSONObject();
            String set = iEquEquipmentgroupService.getGroupIdSet(usercode, groupId.get(i));
            int total = 0;
            tmp.put("name", groupName.get(i));
            for(int j = 0; j < 3; ++j){
                int unRecovery = equAlarmrecordMapper.getUnRecovery(usercode, startTime, endTime, set, types[j]);
                int recovery = equAlarmrecordMapper.getRecovery(usercode, startTime, endTime, set, types[j]);
                int confirm = equAlarmrecordMapper.getConfirm(usercode, startTime, endTime, set, types[j]);

                total += unRecovery;
                total += recovery;
                total += confirm;

                tmp.put(String.format("unRecovery%d",types[j]),unRecovery);
                tmp.put(String.format("recovery%d",types[j]),recovery);
                tmp.put(String.format("confirm%d",types[j]),confirm);
            }
            tmp.put("total", total);
            array.add(tmp);
        }

        return array.toJSONString();
    }
}
