package com.jan.wat.mapper;

import com.jan.wat.pojo.EquEquipment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

}
