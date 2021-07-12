package com.jan.wat.service.impl;

import com.jan.wat.pojo.EquCommand;
import com.jan.wat.mapper.EquCommandMapper;
import com.jan.wat.service.IEquCommandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-06
 */
@Service("EquCommandServiceImpl")
public class EquCommandServiceImpl extends ServiceImpl<EquCommandMapper, EquCommand> implements IEquCommandService {

}
