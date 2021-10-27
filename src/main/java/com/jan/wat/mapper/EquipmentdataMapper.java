package com.jan.wat.mapper;

import com.jan.wat.pojo.Equipmentdata;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
 * @since 2021-07-08
 */
@Repository
public interface EquipmentdataMapper extends BaseMapper<Equipmentdata> {

    @Insert("insert into ${dataName}.equipmentdata values(#{equipmentdata.collecttime},#{equipmentdata.equipmentId}," +
            "#{equipmentdata.uploadtime},#{equipmentdata.data},#{equipmentdata.str})")
    public void insertData(String dataName, Equipmentdata equipmentdata);

    @Select("SELECT count(SCHEMA_NAME) FROM information_schema.SCHEMATA \n" +
            "WHERE SCHEMA_NAME = #{database};")
    public int isExit(String database);

    @Select("select *\n" +
            "from ${dataName}.equipmentdata a\n" +
            "where CollectTime between #{startTime} and #{endTime} and a.Equipment_ID = #{id}")
    public List<Equipmentdata> getData(String dataName, String startTime, String endTime, String id);
}
