package com.jan.wat.service.impl;

import com.jan.wat.pojo.SysUser;
import com.jan.wat.mapper.SysUserMapper;
import com.jan.wat.service.ISysUserService;
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
@Service("SysUserServiceImpl")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
