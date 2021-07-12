package com.jan.wat.service.impl;

import com.jan.wat.pojo.WatClassify;
import com.jan.wat.mapper.WatClassifyMapper;
import com.jan.wat.service.IWatClassifyService;
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
@Service("WatClassifyServiceImpl")
public class WatClassifyServiceImpl extends ServiceImpl<WatClassifyMapper, WatClassify> implements IWatClassifyService {

}
