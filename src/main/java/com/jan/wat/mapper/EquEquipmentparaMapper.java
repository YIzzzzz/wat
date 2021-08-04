package com.jan.wat.mapper;

import com.jan.wat.pojo.EquEquipmentpara;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.EquParaQuery;
import com.jan.wat.pojo.vo.ReadEquipmentparaQuery;
import org.apache.ibatis.annotations.Insert;
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
public interface EquEquipmentparaMapper extends BaseMapper<EquEquipmentpara> {

//    @Select("<script>" +
//            "select distinct 'false' as checked, A.ID, A.Name, A.EquipmentType_ID, A.PowerType\n" +
//            "from equ_equipment A, equ_equipmentgroup_equipment_map egm, equ_user_equipmentgroup_map uem\n" +
//            "where A.id = egm.Equipment_ID and egm.EquipmentGroup_ID = uem.EquipmentGroup_ID and uem.UserCode = #{usercode}" +
//            "<trim prefix=\"\" prefixOverrides=\"and\" suffix=\"\" suffixOverrides=\"and\">" +
//            "<where test = 'equipmentgroupid !=\"\"'>" +
//            "and A.ID in (select Equipment_ID from equ_EquipmentGroup_Equipment_Map where EquipmentGroup_ID in (select ID from ))" +
//            "</script>")
//    List<ReadEquipmentparaQuery> readEquipmentpara(String usercode, String equipmentgroupid, String equipment_id);

}
