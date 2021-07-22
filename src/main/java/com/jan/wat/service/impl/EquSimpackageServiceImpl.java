package com.jan.wat.service.impl;

import com.jan.wat.pojo.EquSimpackage;
import com.jan.wat.mapper.EquSimpackageMapper;
import com.jan.wat.service.IEquSimpackageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Service("EquSimpackageServiceImpl")
@Transactional
public class EquSimpackageServiceImpl extends ServiceImpl<EquSimpackageMapper, EquSimpackage> implements IEquSimpackageService {

}
