package com.jan.wat.mapper;

import com.jan.wat.pojo.Equipmentdata;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Repository
public interface EquipmentdataMapper extends BaseMapper<Equipmentdata> {

    @Insert("insert into ${dataName}.equipmentdata values(#{equipmentdata.collecttime},#{equipmentdata.equipmentId}," +
            "#{equipmentdata.uploadtime},#{equipmentdata.data},#{equipmentdata.str})")
    public void insertData(String dataName, Equipmentdata equipmentdata);



}
