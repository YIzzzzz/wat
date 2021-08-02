package com.jan.wat.service.impl;

import com.jan.wat.pojo.SysRole;
import com.jan.wat.mapper.SysRoleMapper;
import com.jan.wat.pojo.vo.RoleTree;
import com.jan.wat.pojo.vo.SysRoleeditQuery;
import com.jan.wat.service.ISysRoleService;
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
@Service("SysRoleServiceImpl")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public List<RoleTree> selectByRolecode(String rolecode) {
        List<SysRoleeditQuery> list = sysRoleMapper.selectByRolecode(rolecode);

        List<RoleTree> tree = new ArrayList<>();
        RoleTree.createTree(list, tree);
        return tree;
    }
}
