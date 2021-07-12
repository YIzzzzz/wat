package com.jan.wat.service.impl;

import com.jan.wat.pojo.EquDatatype;
import com.jan.wat.mapper.EquDatatypeMapper;
import com.jan.wat.service.IEquDatatypeService;
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
@Service("EquDatatypeServiceImpl")
public class EquDatatypeServiceImpl extends ServiceImpl<EquDatatypeMapper, EquDatatype> implements IEquDatatypeService {

}
