package com.jan.wat.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.EquCommand;
import com.jan.wat.pojo.EquPara;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.EquParaQuery;
import com.jan.wat.pojo.vo.LimitQuery;
import com.jan.wat.pojo.vo.MulEquipparaQuery;
import com.jan.wat.pojo.vo.SigEuipementparaQuery;
import org.apache.ibatis.annotations.Mapper;
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
 * @since 2021-07-08
 */
@Mapper
@Repository
public interface EquParaMapper extends BaseMapper<EquPara> {

    @Select("        select\n" +
            "            ep.ID as id,\n" +
            "            ep.Name as name,\n" +
            "            ep.ParaGroup_ID as paragroup_id,\n" +
            "            epg.Name as paraname,\n" +
            "            ep.Type as type,\n" +
            "            ep.ReadOnly as readonly\n" +
            "        from equ_para ep, equ_paragroup epg\n" +
            "        where ep.ParaGroup_ID = epg.ID\n" +
            "        order by id")
    @Results(id = "Para",value = {
            @Result(column = "paragroup_id",property = "paragroup_id")
    })
    List<EquParaQuery> queryParaList();

    @Select("select   ep.ID as id, ep.Name as name, ep.ParaGroup_ID as paragroup_id, epg.Name as paraname, ep.Type as type, ep.ReadOnly as readonly \n" +
            "from equ_para ep, equ_paragroup epg  \n" +
            "where ep.ParaGroup_ID = #{id} and ep.ParaGroup_ID = epg.ID order by id")
    @Results(id = "Para1",value = {
            @Result(column = "paragroup_id",property = "paragroup_id")
    })
    List<EquParaQuery> querybyparagroupid(int id);

    @Select("        select\n" +
            "            ep.ID as id,\n" +
            "            ep.Name as name,\n" +
            "            epg.Name as paraname,\n" +
            "            ep.Type as type,\n" +
            "            ep.ReadOnly as readonly\n" +
            "        from equ_para ep, equ_paragroup epg\n" +
            "        where ep.ParaGroup_ID = epg.ID\n" +
            "        order by id")
    IPage<EquParaQuery> selectByPage(Page<EquParaQuery> page);

    @Select("select (select Name from equ_unit u where u.ID=a.unit_ID) as unit_Name," +
"(case when b.Type=2 then (select Name from equ_paravalue pv where pv.ID=a.paravalue and pv.para_ID=a.para_ID) else a.paravalue end) as paravalue,\n" +
            "(case when b.Type=2 then a.paravalue else NuLL end) as paravalue_ID," +
            "a.equipment_ID as equipment_ID,a.para_ID as para_ID,CONveRT(a.uploadTime,DATeTIMe) as uploadTime," +
            "a.upLimit,a.DownLimit,b.Type as Type,b.Name as para_Name,b.ReadOnly as ReadOnly\n" +
            "from equ_equipmentpara as a,equ_para b\n" +
            "where a.para_ID=b.ID and a.equipment_ID=#{equipment_ID}")
    @Results(id = "getSigEquipmentPara",value = {
            @Result(column = "unit_Name",property = "unit_Name"),
            @Result(column = "paravalue",property = "paravalue"),
            @Result(column = "paravalue_ID",property = "paravalue_ID"),
            @Result(column = "equipment_ID",property = "equipment_ID"),
            @Result(column = "para_ID",property = "para_ID"),
            @Result(column = "uploadTime",property = "uploadTime"),
            @Result(column = "upLimit",property = "upLimit"),
            @Result(column = "DownLimit",property = "DownLimit"),
            @Result(column = "Type",property = "Type"),
            @Result(column = "para_Name",property = "para_Name"),
            @Result(column = "ReadOnly",property = "ReadOnly")
    })
    List<SigEuipementparaQuery> getSigEquipmentPara(String equipment_ID);

    @Select("select distinct p.ID as para_ID,p.Type as pType,p.Name as para_Name,'' as UpLimit,'' as DownLimit\n" +
            "from equ_para p ${from}\n" +
            "where t0.para_ID=p.ID and p.ReadOnly=0")
    @Results(id="MulEquipmentParaMap", value={
            @Result(column="para_ID", property="para_ID"),
            @Result(column="pType", property="pType"),
            @Result(column="para_Name", property="para_Name"),
            @Result(column="UpLimit", property="UpLimit"),
            @Result(column="DownLimit", property="DownLimit")
    })
    List<MulEquipparaQuery> getMulEquipmentPara(String from);


    @Select("select min(UpLimit) as upLimit,max(DownLimit) as downLimit\n" +
            "from equ_equipmentpara a\n" +
            "where a.Para_ID = ${paraId} and a.Equipment_ID in ${ids}")
    List<LimitQuery> getLimit(int paraId, String ids);

    @Select("select * from equ_command where Equipment_ID = #{id} and CommandType = #{para} and UserCode = #{usercode};\n")
    public List<EquCommand> selectParaCommand(String id, String usercode, Integer para);
}
