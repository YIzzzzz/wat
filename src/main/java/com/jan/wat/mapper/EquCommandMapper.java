package com.jan.wat.mapper;

import com.jan.wat.pojo.EquCommand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.EquUncheckcommandQuery;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
            "and egm.Equipment_ID=c.Equipment_ID and uem.EquipmentGroup_ID=egm.EquipmentGroup_ID and uem.EquipmentGroup_ID in (${set})" +
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

}
