package com.jan.wat.mapper;

import com.jan.wat.pojo.EquCommand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.EquUncheckcommandQuery;
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
            "<if test='equipmentgroup_id != \"0\"'>" +
            "" +
            "</script>")
    public List<EquUncheckcommandQuery> getEquUncheckcommand(String usercode);

}
