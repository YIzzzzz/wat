package com.jan.wat.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.EquParavalueQuery;
import com.jan.wat.pojo.vo.SysRoleeditQuery;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("select m.menucode, m.parentcode, m.menuname,\n" +
            "(case when exists(select 1 from sys_rolemenumap b where b.menucode = m.menucode and b.rolecode = #{roleCode})\n" +
            "then '1'\n" +
            "else '0' end) as chk\n" +
            "from sys_menu m\n" +
            "where m.isenable = 1;")
    List<SysRoleeditQuery> selectByRolecode(String rolecode);
}
