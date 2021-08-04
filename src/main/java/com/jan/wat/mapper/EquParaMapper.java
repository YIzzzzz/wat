package com.jan.wat.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.EquPara;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.EquParaQuery;
import com.jan.wat.pojo.vo.SigEuipementparaQuery;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
@Repository
public interface EquParaMapper extends BaseMapper<EquPara> {

    @Select("        select\n" +
            "            ep.ID as id,\n" +
            "            ep.Name as name,\n" +
            "            epg.Name as paraname,\n" +
            "            ep.Type as type,\n" +
            "            ep.ReadOnly as readonly\n" +
            "        from rdas_eng.equ_para ep, rdas_eng.equ_paragroup epg\n" +
            "        where ep.ParaGroup_ID = epg.ID\n" +
            "        order by id")
    List<EquParaQuery> queryParaList();

    @Select("        select\n" +
            "            ep.ID as id,\n" +
            "            ep.Name as name,\n" +
            "            epg.Name as paraname,\n" +
            "            ep.Type as type,\n" +
            "            ep.ReadOnly as readonly\n" +
            "        from rdas_eng.equ_para ep, rdas_eng.equ_paragroup epg\n" +
            "        where ep.ParaGroup_ID = epg.ID\n" +
            "        order by id")
    IPage<EquParaQuery> selectByPage(Page<EquParaQuery> page);

    @Select("select (select Name from equ_unit u where u.ID=a.unit_ID) as unit_Name,(case when b.Type=2 then (select Name from equ_paravalue pv where pv.ID=a.paravalue and pv.para_ID=a.para_ID) else a.paravalue end) as paravalue,\n" +
            "(case when b.Type=2 then a.paravalue else NuLL end) as paravalue_ID,a.equipment_ID,a.para_ID,CONveRT(a.uploadTime,DATeTIMe) as uploadTime,a.upLimit,a.DownLimit,b.Type,b.Name as para_Name,b.ReadOnly\n" +
            "from equ_equipmentpara as a,equ_para b\n" +
            "where a.para_ID=b.ID and a.equipment_ID=#{equipment_ID}")
    List<SigEuipementparaQuery> getSigEquipmentPara(String equipment_ID);

}
