package com.jan.wat.mapper;

import com.jan.wat.pojo.WatAlarmtype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Repository
public interface WatAlarmtypeMapper extends BaseMapper<WatAlarmtype> {

    @Select("select a.id\n" +
            "from equ_equipment a, equ_equipmentgroup_equipment_map b, wat_alarmtype_flow_map c\n" +
            "where a.ID = b.Equipment_ID and b.EquipmentGroup_ID = #{equipmentgroup_id}  and c.AlarmType_ID = #{alarmtype_id} and c.Equipment_ID = a.id")
    public List<String> getEquipment(Integer alarmtype_id, Integer equipmentgroup_id);

}
