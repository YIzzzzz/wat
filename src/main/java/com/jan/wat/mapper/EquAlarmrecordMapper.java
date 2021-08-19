package com.jan.wat.mapper;

import com.jan.wat.pojo.EquAlarmrecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.EquAlarmQuery;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author January
 * @since 2021-07-06
 */
@Repository
public interface EquAlarmrecordMapper extends BaseMapper<EquAlarmrecord> {

    @Select("<script>" +
            "select distinct a.equipment_name as equipment_name,a.equipment_id as equipment_id,a.alarmrecord_id as alarmrecord_id,a.datatype_id as datatype_id,convert(a.alarmtime, datetime) as alarmtime,a.alarmtype as alarmtype,convert(a.confirmtime, datetime) as confirmtime,a.usercode as usercode,a.recovery as recovery,convert(a.recoverytime, datetime) as recoverytime,a.reason as reason,a.des as des,a.model as model,a.setupperson as setupperson,a.manager as manager,a.telephone as telephone,convert(a.setuptime, datetime) as setuptime,a.setupaddress as setupaddress,a.equipmenttype_name as equipmenttype_name,a.powertype as powertype,a.username as username,a.datatype_name as datatype_name,a.manufacturer_name as manufacturer_name\n" +
            "from equ_alarmrecord_view as a,equ_user_equipmentgroup_map uem,equ_equipmentgroup_equipment_map as egm\n" +
            "where a.recovery='false' and uem.usercode = #{usercode} and uem.equipmentgroup_id = egm.equipmentgroup_id and egm.equipment_id = a.equipment_id" +
            "<if test='equipmentgroup_id != \"0\"'>" +
            "and egm.equipment_id=a.equipment_id and uem.equipmentgroup_id=egm.equipmentgroup_id and uem.EquipmentGroup_ID in (${set}) and uem.usercode=#{usercode} " +
            "</if>" +
            "<if test='equipment_id != \"0\"'>" +
            "and a.equipment_id=#{equipment_id}" +
            "</if>" +
            "</script>")
    @Results(id="getCommand", value={
            @Result(column="equipment_name", property="equipment_name"),
            @Result(column="equipment_id", property="equipment_id"),
            @Result(column="alarmrecord_id", property="alarmrecord_id"),
            @Result(column="datatype_id", property="datatype_id"),
            @Result(column="alarmtime", property="alarmtime"),
            @Result(column="alarmtype", property="alarmtype"),
            @Result(column="confirmtime", property="confirmtime"),
            @Result(column="usercode", property="usercode"),
            @Result(column="recovery", property="recovery"),
            @Result(column="recoverytime", property="recoverytime"),
            @Result(column="reason", property="reason"),
            @Result(column="des", property="des"),
            @Result(column="model", property="model"),
            @Result(column="setupperson", property="setupperson"),
            @Result(column="manager", property="manager"),
            @Result(column="telephone", property="telephone"),
            @Result(column="setuptime", property="setuptime"),
            @Result(column="setupaddress", property="setupaddress"),
            @Result(column="equipmenttype_name", property="equipmenttype_name"),
            @Result(column="powertype", property="powertype"),
            @Result(column="username", property="username"),
            @Result(column="datatype_name", property="datatype_name"),
            @Result(column="manufacturer_name", property="manufacturer_name")
    })
    public List<EquAlarmQuery> getEquUnrecoveryalarm(String usercode, String set, String equipment_id, String equipmentgroup_id);

    @Select("<script>" +
            "select a.equipment_name as equipment_name,a.equipment_id as equipment_id,a.alarmrecord_id as alarmrecord_id,a.datatype_id as datatype_id,convert(a.alarmtime,datetime) as alarmtime,a.alarmtype as alarmtype,convert(a.confirmtime,datetime) as confirmtime,a.usercode as usercode,a.recovery as recovery,convert(a.recoverytime,datetime) as recoverytime,a.reason as reason,a.des as des,a.model as model,a.setupperson as setupperson,a.manager as manager,a.telephone as telephone,convert(a.setuptime,datetime) as setuptime,a.setupaddress as setupaddress,a.equipmenttype_name as equipmenttype_name,a.powertype as powertype,a.username as username,a.datatype_name as datatype_name,a.manufacturer_name as manufacturer_name\n" +
            "from equ_alarmrecord_view as a,equ_user_equipmentgroup_map uem,equ_equipmentgroup_equipment_map as egm\n" +
            "where (a.alarmtime between #{start} and #{end} and uem.usercode = #{usercode}) and uem.equipmentgroup_id = egm.equipmentgroup_id and egm.equipment_id = a.equipment_id" +
            "<if test='equipmentgroup_id != \"0\"'>" +
            "and egm.equipment_id=a.equipment_id and uem.equipmentgroup_id=egm.equipmentgroup_id and uem.equipmentgroup_id in (${set}) and uem.usercode=#{usercode}" +
            "</if>" +
            "<if test='equipment_id != \"0\"'>" +
            "and a.equipment_id=#{huluadmin}" +
            "</if>" +
            "</script>")
    @ResultMap(value="getCommand")
    public List<EquAlarmQuery> getEquYesterdayalarm(String usercode,String start,String end, String set, String equipment_id, String equipmentgroup_id);

}
