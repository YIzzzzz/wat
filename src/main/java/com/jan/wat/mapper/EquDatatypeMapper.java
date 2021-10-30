package com.jan.wat.mapper;

import com.jan.wat.pojo.EquDatatype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface EquDatatypeMapper extends BaseMapper<EquDatatype> {
    @Select("select dt.*\n" +
            "from equ_equipmentrealdata as erd,equ_datatype dt\n" +
            "where erd.datatype_Id=dt.Id and erd.IfCurve=true and erd.Ifrecord=true and erd.equipment_Id = #{id}\n" +
            "order by erd.Position;")
    public List<EquDatatype> getEquDataType(String id);

    @Select("select dt.*\n" +
            "from equ_equipmentrealdata as erd,equ_datatype dt\n" +
            "where erd.datatype_Id=dt.Id and erd.IfCurve=true and erd.Ifrecord=true and erd.equipment_Id in (${ids})\n" +
            "order by erd.Position")
    public List<EquDatatype> getMulDataType(String ids);
}
