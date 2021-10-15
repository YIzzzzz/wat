package com.jan.wat.mapper;

import com.jan.wat.pojo.EquEquipmentgroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
public interface EquEquipmentgroupMapper extends BaseMapper<EquEquipmentgroup> {

    @Select("     select A.*\n" +
            "        from equ_user_equipmentgroup_map B, equ_equipmentgroup A\n" +
            "        where A.ID = B.EquipmentGroup_ID\n" +
            "        and UserCode = #{userCode}")
    public List<EquEquipmentgroup> getListByUser(String userCode);


    @Select("select max(id) from equ_equipmentgroup")
    public Integer getMaxId();
}
