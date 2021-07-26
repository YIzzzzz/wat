package com.jan.wat.mapper;

import com.jan.wat.pojo.EquPara;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.EquParaQuery;
import org.apache.ibatis.annotations.Mapper;
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

    List<EquParaQuery> queryParaList();

}
