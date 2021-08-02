package com.jan.wat.service;

import com.jan.wat.pojo.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.RoleTree;
import com.jan.wat.pojo.vo.SysRoleeditQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
public interface ISysRoleService extends IService<SysRole> {

    List<RoleTree> selectByRolecode(String rolecode);

}
