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
    public List<RoleUserItem> getRoleUserItem();

//    @Select("select usercode, username, description, isenable, logincount, lastlogindate, createperson, createdate, updateperson, updatedate from sys_user where UserCode in\n" +
//            "(select usercode from sys_userorganizemap where OrganizeCode = #{organizecode}) order by usercode")
//    List<SysRegisterQuerry> getuserbyorganizecode(String organizecode);

//    @Select("select u.usercode, u.username as username, u.description as description, u.isenable as isenable, u.logincount as logincount, u.lastlogindate as lastlogindate, u.createperson as createperson, u.createdate as createdate, u.updateperson as updateperson, u.updatedate as updatedate, r.rolecode as rolecode, r.rolename as rolename, r.roleseq as roleseq from sys_user u, sys_userrolemap urm, sys_role r where u.UserCode in \n" +
//            "(select u.usercode from sys_userorganizemap where OrganizeCode = #{organizecode} order by u.usercode) and u.usercode = urm.usercode and urm.rolecode = r.rolecode")
//    public List<SysRegisterQuerry> getuserbyorganizecode(String organizecode);


    @Select("select u.usercode, u.username as username, u.description as description, u.isenable as isenable, u.logincount as logincount, u.lastlogindate as lastlogindate, u.createperson as createperson, u.createdate as createdate, u.updateperson as updateperson, u.updatedate as updatedate, r.rolecode as rolecode, r.rolename as rolename, r.roleseq as roleseq from sys_user u, sys_userrolemap urm, sys_role r \n" +
            "where u.usercode = urm.usercode and urm.rolecode = r.rolecode  and roleseq >= #{roleseq} order by u.usercode desc")
    public List<SysRegisterQuerry> getalluser(String roleseq);

    @Select("select u.usercode, u.username as username, u.description as description, u.isenable as isenable, u.logincount as logincount, u.lastlogindate as lastlogindate, u.createperson as createperson, u.createdate as createdate, u.updateperson as updateperson, u.updatedate as updatedate, r.rolecode as rolecode, r.rolename as rolename, r.roleseq as roleseq from sys_user u, sys_userrolemap urm, sys_role r, sys_userorganizemap uom\n" +
            "where u.usercode = uom.usercode and uom.organizecode = #{organizecode} and u.usercode = urm.usercode and urm.rolecode = r.rolecode  and r.roleseq >= #{roleseq}")
    public List<SysRegisterQuerry> getuserbyorganizecode(String organizecode, String roleseq);

}
