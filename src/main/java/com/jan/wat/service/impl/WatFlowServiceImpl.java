package com.jan.wat.service.impl;

import com.jan.wat.pojo.WatFlow;
import com.jan.wat.mapper.WatFlowMapper;
import com.jan.wat.pojo.vo.FlowViewQuery;
import com.jan.wat.service.IWatFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Service("WatFlowServiceImpl")
public class WatFlowServiceImpl extends ServiceImpl<WatFlowMapper, WatFlow> implements IWatFlowService {

    @Autowired
    WatFlowMapper watFlowMapper;

//    @Override
//    public List<FlowViewQuery> getAllFlowtypeList(String equipmentGroupId, String equipmentId) {
//        return watFlowMapper.getByCondition(equipmentGroupId, equipmentId);
//    }
}
