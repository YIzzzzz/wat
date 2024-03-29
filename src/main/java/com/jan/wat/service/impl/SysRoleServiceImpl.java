package com.jan.wat.service.impl;

import com.jan.wat.pojo.SysRole;
import com.jan.wat.mapper.SysRoleMapper;
import com.jan.wat.pojo.vo.RoleTree;
import com.jan.wat.pojo.vo.SysRoleeditQuery;
import com.jan.wat.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public List<RoleTree> selectByUsercode(String usercode) {
        List<SysRoleeditQuery> list = sysRoleMapper.selectByUsercode(usercode);

        List<RoleTree> tree = new ArrayList<>();
        RoleTree.createTree(list, tree);

        return tree;
    }

    @Override
    public List<RoleTree> selectByUsercodeDouble(String usercodeOld, String usercodeNew){
        List<SysRoleeditQuery> listOld = sysRoleMapper.selectByUsercode(usercodeOld);
        List<SysRoleeditQuery> listNew = sysRoleMapper.selectByUsercode(usercodeNew);
        Set<String> menus = new HashSet<>();
        for(SysRoleeditQuery query : listNew){
            menus.add(query.getIndex());
        }
        List<RoleTree> tree = new ArrayList<>();
        RoleTree.createTree(listOld, tree);

        return tree;
    }

    @Override
    public List<SysRole> getallrole() {
        return sysRoleMapper.getallrole();
    }

    @Override
    public boolean updateRole(String oldcode, String rolecode, String roleseq, String rolename, String description, String updateperson, LocalDateTime updatedate) {
        return sysRoleMapper.updateRole(oldcode, rolecode, roleseq, rolename, description, updateperson, updatedate);
    }

    @Override
    public String getroleseqbyusercode(String usercode) {
        return sysRoleMapper.getroleseqbyusercode(usercode);
    }


}
