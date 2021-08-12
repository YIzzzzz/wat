package com.jan.wat.mapper;

import com.jan.wat.pojo.SysUserrolemap;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.RoleUserItem;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
public interface SysUserrolemapMapper extends BaseMapper<SysUserrolemap> {
    @Select("SELECT u.UserCode as usercode, u.UserName as username , m.*FROM sys_userrolemap m, sys_user u where m.UserCode=u.UserCode and m.RoleCode = #{usercode}")
    public List<RoleUserItem> getRoleUserItem(String usercode);

    @Update("UPDATE sys_userrolemap SET rolecode = #{rolecode} WHERE usercode=#{usercode};")
    public boolean updateRolecode(String usercode, String rolecode);
}
