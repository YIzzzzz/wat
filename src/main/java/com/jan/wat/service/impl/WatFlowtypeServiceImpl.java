package com.jan.wat.service.impl;

import com.jan.wat.pojo.WatFlowtype;
import com.jan.wat.mapper.WatFlowtypeMapper;
import com.jan.wat.service.IWatFlowtypeService;
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
@Service("WatFlowtypeServiceImpl")
public class WatFlowtypeServiceImpl extends ServiceImpl<WatFlowtypeMapper, WatFlowtype> implements IWatFlowtypeService {

}
