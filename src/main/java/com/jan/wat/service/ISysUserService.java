package com.jan.wat.service;

import com.jan.wat.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.RoleUserItem;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
public interface ISysUserService extends IService<SysUser> {

    List<RoleUserItem> getRoleUserItem();
}
