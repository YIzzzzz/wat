package com.jan.wat.mapper;

import com.jan.wat.pojo.WatAlarmrecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.EquAlarmQuery;
import com.jan.wat.pojo.vo.WatAlarmQuery;
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
public interface WatAlarmrecordMapper extends BaseMapper<WatAlarmrecord> {


    @Select("select a.*\n" +
            "from wat_alarmrecord_view a,equ_user_equipmentgroup_map uem,equ_equipmentgroup_equipment_map as egm\n" +
            "where a.recovery='false' and uem.usercode = #{usercode} and uem.equipmentgroup_id = egm.equipmentgroup_id and egm.equipment_id = a.equipment_id")
    public List<WatAlarmQuery> getWatUnrecoveryalarm(String usrcode);


    @Select("select a.*\n" +
            "from wat_alarmrecord_view a,equ_user_equipmentgroup_map uem,equ_equipmentgroup_equipment_map as egm\n" +
            "where ( a.alarmtime between #{start} and #{end})  and uem.userCode = #{usercode} and uem.equipmentgroup_id = egm.equipmentgroup_id and egm.equipment_id = a.equipment_id ")
    public List<WatAlarmQuery> getWatYesterdayalarm(String usercode, String start, String end);

    @Select("select a.*\n" +
            "from wat_alarmrecord_view a,equ_user_equipmentgroup_map uem,equ_equipmentgroup_equipment_map as egm\n" +
            "where a.recovery='true' and (a.recoverytime between #{start} and #{end}) and uem.usercode = #{usercode} and uem.equipmentgroup_id = egm.equipmentgroup_id and egm.equipment_id = a.equipment_id")
    public List<WatAlarmQuery> getWatYesterdayrecoveryalarm(String usercode, String start, String end);


    @Select("select a.*\n" +
            "from wat_alarmrecord_view a,equ_user_equipmentgroup_map uem,equ_equipmentgroup_equipment_map as egm\n" +
            "where (a.confirmtime between #{start} and #{end}) and uem.usercode = #{usercode} and uem.equipmentgroup_id = egm.equipmentgroup_id and egm.equipment_id = a.equipment_id")
    public List<WatAlarmQuery> getWatYesterdayconfirmalarm(String usercode, String start, String end);


    @Select("select a.*\n" +
            "from wat_alarmrecord_view a,equ_user_equipmentgroup_map uem,equ_equipmentgroup_equipment_map as egm\n" +
            "where a.confirmtime is null and uem.usercode = #{usercode} and uem.equipmentgroup_id = egm.equipmentgroup_id and egm.equipment_id = a.equipment_id")
    public List<WatAlarmQuery> getWatUnconfirmalarm(String usercode);


}
