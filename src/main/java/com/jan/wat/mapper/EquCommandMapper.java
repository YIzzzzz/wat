package com.jan.wat.mapper;

import com.jan.wat.pojo.EquCommand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.EquHistoryUpdateProgramRecord;
import com.jan.wat.pojo.vo.EquUncheckcommandQuery;
import com.jan.wat.pojo.vo.FailureAndHistoryCommandQuery;
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
public interface EquCommandMapper extends BaseMapper<EquCommand> {

    @Select("<script>" +
            "        select e.name as equipment_name,c.des as des,c.equipment_id as equipment_id,c.id as id ,c.commandtype as commandtype,c.sendnum as sendnum,c.responsemessage as responsemessage,CONVERT(c.settingtime,DATETIME) as settingtime,CONVERT(c.sendtime,DATETIME) as sendtime,CONVERT(c.responsetime, DATETIME) as responsetime,\n" +
            "               (case c.status when 1 then '等待发送' when 2 then '发送后未应答' when 3 then '发送达最大次数' when 4 then '成功' when 5 then '应答提示失败' else '其他未定义失败' end) as status,(select username from sys_user u where c.usercode=u.usercode) as username\n" +
            "        from equ_command as c,equ_equipment e,equ_user_equipmentgroup_map uem,equ_equipmentgroup_equipment_map as egm\n" +
            "        where c.equipment_id=e.id and checktime is null and uem.usercode = #{usercode} and uem.equipmentgroup_id = egm.equipmentgroup_id and egm.equipment_id = c.equipment_id" +
            "<if test='equipmentgroupId != \"0\"'>" +
            "and egm.Equipment_ID=c.Equipment_ID and uem.EquipmentGroup_ID=egm.EquipmentGroup_ID and uem.EquipmentGroup_ID in (${set}) and uem.UserCode=#{usercode}" +
            "</if>" +
            "<if test='equipmentId != \"0\"'>" +
            "and c.Equipment_ID=#{equipmentId}" +
            "</if>" +
            "</script>")
    @Results(id="getEquUncheckcommand", value={
            @Result(column="equipment_name", property="equipment_name"),
            @Result(column="des", property="des"),
            @Result(column="equipment_id", property="equipment_id"),
            @Result(column="id", property="id"),
            @Result(column="commandtype", property="commandtype"),
            @Result(column="sendnum", property="sendnum"),
            @Result(column="responsemessage", property="responsemessage"),
            @Result(column="settingtime", property="settingtime"),
            @Result(column="sendtime", property="sendtime"),
            @Result(column="responsetime", property="responsetime"),
            @Result(column="status", property="status"),
            @Result(column="username", property="username")

    })
    public List<EquUncheckcommandQuery> getEquUncheckcommand(String usercode,String set,String equipmentId,String equipmentgroupId);
 @Select("select e.Name as equipmentName,c.Des as des ,c.checkDes as checkDes,c.equipment_ID as equipmentId,c.ID as id,c.commandType as commandType ,c.SendNum as sendNum,c.Responsemessage as responsemessage,cONVeRT(c.SettingTime,Datetime) as settingTime,cONVeRT(c.SendTime,Datetime) as sendTime,cONVeRT(c.ResponseTime,Datetime) as responseTime,cONVeRT(c.checkTime,Datetime) as checkTime\n" +
            ",(case c.Status when 1 then '等待发送' when 2 then '发送后未应答' when 3 then '发送达最大次数' when 4 then '成功' when 5 then '应答提示失败' else '其他未定义失败' end) as s,(select userName from sys_user u where c.usercode=u.usercode) as username,(select userName from sys_user u where u.usercode=c.checkusercode) as checkuserName\n" +
            "from equ_command as c,equ_equipment e,equ_user_equipmentgroup_map uem,equ_equipmentgroup_equipment_map as egm\n" +
            "where c.equipment_Id=e.ID and uem.usercode = #{userCode} and uem.equipmentgroup_ID = egm.equipmentgroup_ID and egm.equipment_ID = c.equipment_ID \n" +
            "${where}")
    @Results(id="getCommand", value={
            @Result(column="equipmentName", property="equipmentName"),
            @Result(column="des", property="des"),
            @Result(column="checkdes", property="checkdes"),
            @Result(column="equipmentId", property="equipmentId"),
            @Result(column="id", property="id"),
            @Result(column="commandtype", property="commandtype"),
            @Result(column="sendnum", property="sendnum"),
            @Result(column="responsemessage", property="responsemessage"),
            @Result(column="settingtime", property="settingtime"),
            @Result(column="sendtime", property="sendtime"),
            @Result(column="responsetime", property="responsetime"),
            @Result(column="s", property="s"),
            @Result(column="checktime", property="checktime"),
            @Result(column="checkuserName", property="checkuserName"),
            @Result(column="username", property="username")

    })
    public List<FailureAndHistoryCommandQuery> getHistoryCommand(String userCode, String where);
    @Select("<script>" +
            "select e.Name as equipmentName,c.Des as des ,c.checkDes as checkDes,c.equipment_ID as equipmentId,c.ID as id,c.commandType as commandType ,c.SendNum as sendNum,c.Responsemessage as responsemessage,cONVeRT(c.SettingTime,Datetime) as settingTime,cONVeRT(c.SendTime,Datetime) as sendTime,cONVeRT(c.ResponseTime,Datetime) as responseTime,cONVeRT(c.checkTime,Datetime) as checkTime\n" +
            ",(case c.Status when 1 then '等待发送' when 2 then '发送后未应答' when 3 then '发送达最大次数' when 4 then '成功' when 5 then '应答提示失败' else '其他未定义失败' end) as s,(select userName from sys_user u where c.usercode=u.usercode) as username,(select userName from sys_user u where u.usercode=c.checkusercode) as checkuserName\n" +
            "from equ_command as c,equ_equipment e,equ_user_equipmentgroup_map uem,equ_equipmentgroup_equipment_map as egm\n" +
            "where c.equipment_id=e.id and (c.status=3 or c.status>=5) and ((c.responsetime is not null) or (c.sendnum>=#{sendnummaxlimit})) and uem.usercode = #{usercode} and uem.equipmentgroup_id = egm.equipmentgroup_id and egm.equipment_id = c.equipment_id" +
            "<if test='equipmentgroupId != \"0\"'>" +
            "and egm.Equipment_ID=c.Equipment_ID and uem.EquipmentGroup_ID=egm.EquipmentGroup_ID and uem.EquipmentGroup_ID in (${set}) and uem.usercode=#{usercode}" +
            "</if>" +
            "<if test='equipmentId != \"0\"'>" +
            "and c.Equipment_ID=#{equipmentId}" +
            "</if>" +
            "</script>")
    @ResultMap(value="getCommand")
    public List<FailureAndHistoryCommandQuery> getEquFailurecommand(String usercode, Integer sendnummaxlimit, String set, String equipmentId, String equipmentgroupId);



    @Select("<script>" +
            "select e.id as equipment_id,e.name as equipment_name,c.vision,c.filepath,c.filelength,c.id,c.usercode,convert(c.settingtime,datetime) as settingtime,convert(c.responsetime,datetime) as responsetime\n" +
            ",(case c.status when 1 then '未应答' when 2 then '开始下载' when 3 then '成功' when 4 then '失败' else '其他未定义失败' end) as status,(select username from sys_user u where c.usercode=u.usercode) as username\n" +
            "from equ_updateprogramrecord c,equ_equipment e,equ_user_equipmentgroup_map uem,equ_equipmentgroup_equipment_map as egm\n" +
            "where c.equipment_id=e.id and uem.usercode = #{usercode} and uem.equipmentgroup_id = egm.equipmentgroup_id and egm.equipment_id = c.equipment_id " +
            "<if test='equipmentgroup_id != \"0\" and equipment_id == \"0\"'>" +
            "and egm.equipment_id=c.equipment_id and uem.equipmentgroup_id=egm.equipmentgroup_id and uem.equipmentgroup_id in (${set})" +
            "</if>" +
            "<if test='equipment_id != \"0\"'>" +
            "and c.equipment_id=#{equipment_id}" +
            "</if>" +
            "<if test='status!=\"0\"'>" +
            "and c.status=#{status}" +
            "</if>" +
            "<if test='start != \"\" and end != \"\"'>" +
            "and (c.settingtime between #{start} and #{end})" +
            "</if>" +
            "</script>")
    public List<EquHistoryUpdateProgramRecord> getHistoryupdateprogramrecord(String usercode, String set, String status, String equipment_id, String equipmentgroup_id, String start, String end);

}
