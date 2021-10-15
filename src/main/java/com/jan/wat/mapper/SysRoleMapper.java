package com.jan.wat.mapper;


import com.jan.wat.pojo.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.SysRoleeditQuery;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("select distinct m.menucode, m.parentcode, m.menuname, m.iconclass, m.url, \n" +
            "(case when exists(select 1 from sys_rolemenumap b where b.menucode = m.menucode and b.rolecode = #{roleCode})\n" +
            "then '1'\n" +
            "else '0' end) as chk\n" +
            "from sys_menu m\n" +
            "where m.isenable = 1")
    @Results(id="selectByRolecode", value={
            @Result(column="menucode", property="index"),
            @Result(column="parentcode", property="parentcode"),
            @Result(column="menuname", property="title"),
            @Result(column="iconclass", property="iconclass"),
            @Result(column="chk", property="chk"),
            @Result(column="url", property="url")
    })
    List<SysRoleeditQuery> selectByRolecode(String rolecode);

    @Update("UPDATE sys_role SET RoleCode = #{rolecode}, RoleSeq = #{roleseq}, RoleName = #{rolename}, Description = #{description}, UpdatePerson = #{updateperson}, UpdateDate = #{updatedate} " +
            "WHERE (`RoleCode` = #{oldcode})")
    public boolean updateRole(String oldcode, String rolecode, String roleseq, String rolename, String description,  String updateperson, LocalDateTime updatedate);

    @Select("select * from sys_role order by roleseq asc")
    public List<SysRole> getallrole();

    @Select("select roleseq from sys_role r, sys_userrolemap urm where urm.usercode = #{usercode} and r.rolecode = urm.rolecode")
    public String getroleseqbyusercode(String usercode);

    @Select("select distinct m.menucode, m.parentcode, m.menuname, m.iconclass, m.url,\n" +
            " b.s as chk\n" +
            "from sys_menu m, sys_usermenumap b\n" +
            "where m.isenable = 1 and b.menucode = m.menucode and b.usercode = #{usercode}")
    @ResultMap(value = "selectByRolecode")
    List<SysRoleeditQuery> selectByUsercode(String usercode);

}
