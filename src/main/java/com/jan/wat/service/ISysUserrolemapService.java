package com.jan.wat.service;

import com.jan.wat.pojo.SysUserrolemap;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.RoleQuery;
import com.jan.wat.pojo.vo.RoleUserItem;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
public interface ISysUserrolemapService extends IService<SysUserrolemap> {
    public RoleQuery getRoleUserhelp(String usercode);

    public boolean updateRolecode(String usercode, String rolecode);

    public String getroleseqbyusercode(String usercode);
}
