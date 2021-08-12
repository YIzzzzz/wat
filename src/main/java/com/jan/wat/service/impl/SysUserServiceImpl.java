package com.jan.wat.service.impl;

import com.jan.wat.pojo.SysUser;
import com.jan.wat.mapper.SysUserMapper;
import com.jan.wat.pojo.vo.RoleUserItem;
import com.jan.wat.pojo.vo.SysRegisterQuerry;
import com.jan.wat.service.ISysUserService;
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
@Service("SysUserServiceImpl")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public List<RoleUserItem> getRoleUserItem() {
        return sysUserMapper.getRoleUserItem();
    }

    @Override
    public List<SysRegisterQuerry> getuserbyorganizecode(String organizecode) {
        return sysUserMapper.getuserbyorganizecode(organizecode);
    }
}
