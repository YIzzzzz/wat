package com.jan.wat.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jan.wat.pojo.WatFlow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.FlowViewQuery;
import org.apache.ibatis.annotations.Param;
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
public interface WatFlowMapper extends BaseMapper<WatFlow> {

//    public List<FlowViewQuery> getByCondition(@Param("EquipmentGroup_ID")String equipmentGroupId,
//                                              @Param("Equipment_ID")String equipmentId);

}
