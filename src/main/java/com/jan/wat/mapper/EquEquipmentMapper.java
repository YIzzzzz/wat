package com.jan.wat.mapper;

import com.jan.wat.pojo.EquEquipment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.EuipmentsQuery;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author January
 * @since 2021-06-25
 */
@Repository
public interface EquEquipmentMapper extends BaseMapper<EquEquipment> {

    List<Object> getRealData();

    @Select("select distinct A.id as id,A.Name as name, A.equipmentType_id as equipmentType_id,A.PowerType as powerType\n" +
            "from equ_equipment A,equ_equipmentgroup_equipment_map egm, equ_user_equipmentgroup_map uem\n" +
            "where A.id = egm.equipment_id and egm.equipmentgroup_id = uem.equipmentgroup_id and uem.userCode = #{userCode} ${where}")
    List<EuipmentsQuery> getEuipments(String userCode, String where);



}
