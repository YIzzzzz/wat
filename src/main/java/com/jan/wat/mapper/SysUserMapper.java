package com.jan.wat.mapper;

import com.jan.wat.pojo.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.RoleUserItem;
import com.jan.wat.pojo.vo.SysRegisterQuerry;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select UserCode as usercode, userName as username from sys_user")
    List<RoleUserItem> getRoleUserItem();

    @Select("select usercode, username, description, isenable, logincount, lastlogindate from sys_user where UserCode in\n" +
            "(select usercode from sys_userorganizemap where OrganizeCode = #{organizecode}) order by usercode")
    List<SysRegisterQuerry> getuserbyorganizecode(String organizecode);

}
