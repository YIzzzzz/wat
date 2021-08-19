package com.jan.wat.service.impl;

import com.jan.wat.pojo.SysUserorganizemap;
import com.jan.wat.mapper.SysUserorganizemapMapper;
import com.jan.wat.service.ISysUserorganizemapService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Service("SysUserorganizemapServiceImpl")
public class SysUserorganizemapServiceImpl extends ServiceImpl<SysUserorganizemapMapper, SysUserorganizemap> implements ISysUserorganizemapService {

    @Autowired
    SysUserorganizemapMapper sysUserorganizemapMapper;

    @Override
    public String getOrganizecodebyusercode(String usercode) {
        return sysUserorganizemapMapper.getOrganizecodebyusercode(usercode);
    }
}
