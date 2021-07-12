package com.jan.wat.service.impl;

import com.jan.wat.pojo.SysPermission;
import com.jan.wat.mapper.SysPermissionMapper;
import com.jan.wat.service.ISysPermissionService;
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
@Service("SysPermissionServiceImpl")
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

}
