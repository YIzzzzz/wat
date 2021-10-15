package com.jan.wat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jan.wat.pojo.SysUserrolemap;
import com.jan.wat.mapper.SysUserrolemapMapper;
import com.jan.wat.pojo.vo.RoleQuery;
import com.jan.wat.pojo.vo.RoleUserItem;
import com.jan.wat.service.ISysUserService;
import com.jan.wat.service.ISysUserrolemapService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Service("SysUserrolemapServiceImpl")
public class SysUserrolemapServiceImpl extends ServiceImpl<SysUserrolemapMapper, SysUserrolemap> implements ISysUserrolemapService {

    @Autowired
    SysUserrolemapMapper sysUserrolemapMapper;
    @Autowired
    ISysUserService iSysUserService;
    @Override
    public RoleQuery getRoleUserhelp(String usercode) {
        RoleQuery querie = new RoleQuery();
        querie.setRole(sysUserrolemapMapper.getRoleUserItem(usercode));
        querie.setAll(iSysUserService.getRoleUserItem());
        return querie;
    }

    @Override
    public boolean updateRolecode(String usercode, String rolecode) {
        return sysUserrolemapMapper.updateRolecode(usercode, rolecode);
    }

    @Override
    public String getroleseqbyusercode(String usercode) {
        return sysUserrolemapMapper.getroleseqbyusercode(usercode);
    }
}
