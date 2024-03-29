package com.jan.wat.service;

import com.jan.wat.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.RoleUserItem;
import com.jan.wat.pojo.vo.SysRegisterQuerry;
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

    public List<RoleUserItem> getRoleUserItem();

    public List<SysRegisterQuerry> getuserbyorganizecode(String organizecode, String roleseq);

    public List<SysRegisterQuerry> getalluser(String roleseq);

    public boolean updatepassword(String usercode, String password);
}
