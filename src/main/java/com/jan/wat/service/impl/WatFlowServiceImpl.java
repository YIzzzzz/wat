package com.jan.wat.service.impl;

import com.jan.wat.pojo.WatFlow;
import com.jan.wat.mapper.WatFlowMapper;
import com.jan.wat.service.IWatFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
