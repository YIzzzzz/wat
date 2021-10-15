package com.jan.wat.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.EquParavalue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.EquParaQuery;
import com.jan.wat.pojo.vo.EquParavalueQuery;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
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
public interface EquParavalueMapper extends BaseMapper<EquParavalue> {


    @Select("select eqv.ID as paravalue_id, eq.Name as para_name, eq.ID as para_id, eqv.Name as name\n" +
            "from equ_paravalue eqv, equ_para eq\n" +
            "where eq.ID = #{para_id} and eqv.Para_ID = eq.ID")
    @Results(id = "Para2",value = {
            @Result(column = "para_id",property = "para_id"),
            @Result(column = "paravalue_id",property = "paravalue_id"),
            @Result(column = "name",property = "name"),
            @Result(column = "para_name",property = "para_name")
    })
    public List<EquParavalueQuery> selectByPage(Integer para_id);


}
