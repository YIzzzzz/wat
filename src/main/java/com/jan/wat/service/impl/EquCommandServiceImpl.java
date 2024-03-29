package com.jan.wat.service.impl;

import com.jan.wat.pojo.EquCommand;
import com.jan.wat.mapper.EquCommandMapper;
import com.jan.wat.pojo.vo.EquHistoryUpdateProgramRecord;
import com.jan.wat.pojo.vo.EquUncheckcommandQuery;
import com.jan.wat.pojo.vo.FailureAndHistoryCommandQuery;
import com.jan.wat.service.IEquCommandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jan.wat.service.IEquEquipmentgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-06
 */
@Service("EquCommandServiceImpl")
public class EquCommandServiceImpl extends ServiceImpl<EquCommandMapper, EquCommand> implements IEquCommandService {

    @Autowired
    IEquEquipmentgroupService iEquEquipmentgroupService;
    @Autowired
    EquCommandMapper equCommandMapper;

    @Override
    public List<EquUncheckcommandQuery> getEquUncheckcommand(String usercode, String equipmentId, String equipmentgroupId) {
        StringBuilder set = new StringBuilder();
        set.append(equipmentgroupId);
        if(equipmentgroupId != "0"){
            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentgroupId);

            for(int i : childrengroupId){
                set.append(",");

                set.append(String.valueOf(i));
            }
        }

        return equCommandMapper.getEquUncheckcommand(usercode,set.toString(),equipmentId,equipmentgroupId);
    }

    
    @Override
    public List<FailureAndHistoryCommandQuery> getHistoryCommand(String usercode, String equipmentGroupId, String equipmentId, Integer commandtype, String startTime, String endTime) {
        StringBuilder where = new StringBuilder();

        if(!equipmentGroupId.equals("0") && equipmentId.equals("0")){
            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentGroupId);
            StringBuilder set = new StringBuilder();
            int count = 0;
            for(int i : childrengroupId){
                count++;
                set.append(String.valueOf(i));
                if(count != childrengroupId.size())
                    set.append(",");
            }
            where.append(String.format(" and egm.Equipment_ID=c.Equipment_ID and uem.EquipmentGroup_ID=egm.EquipmentGroup_ID and uem.UserCode='%s' and uem.EquipmentGroup_ID in (%s)",usercode,set.toString()));
        }

        if(!equipmentId.equals("0"))
            where.append(String.format(" and c.Equipment_ID='%s'",equipmentId));

        if(commandtype != 0)
            where.append(String.format(" and c.CommandType=%d",commandtype));

        if(!startTime.equals("") && !endTime.equals("")){
            where.append(String.format(" and (c.SettingTime between '%s' and '%s') ",startTime,endTime));
        }
//        ("where = " + where);
        return equCommandMapper.getHistoryCommand(usercode, where.toString());
    }

    @Override
    public List<FailureAndHistoryCommandQuery> getEquFailurecommand(String usercode, String equipmentId, String equipmentgroupId) {
        //config!!!!
        int sendnummaxlimit = 3;
//        StringBuilder set = new StringBuilder();
//        if (equipmentgroupId != "0") {
//            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode, equipmentgroupId);
//            int count = 0;
//            for (int i : childrengroupId) {
//                count++;
//                set.append(String.valueOf(i));
//                if (count != childrengroupId.size())
//                    set.append(",");
//            }
//        }
        StringBuilder set = new StringBuilder();
        set.append(equipmentgroupId);
        if(equipmentgroupId != "0"){
            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentgroupId);

            for(int i : childrengroupId){
                set.append(",");

                set.append(String.valueOf(i));
            }
        }
        return equCommandMapper.getEquFailurecommand(usercode, sendnummaxlimit, set.toString(), equipmentId, equipmentgroupId);

    }

    @Override
    public List<EquHistoryUpdateProgramRecord> getHistoryupdateprogramrecord(String usercode, String status, String equipment_id, String equipmentgroup_id, String start, String end) {
        StringBuilder set = new StringBuilder();
        set.append(equipmentgroup_id);
        if(equipmentgroup_id != "0"){
            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentgroup_id);

            for(int i : childrengroupId){
                set.append(",");

                set.append(String.valueOf(i));
            }
        }
        return equCommandMapper.getHistoryupdateprogramrecord(usercode,set.toString(),status,equipment_id,equipmentgroup_id,start,end);
    }
}
