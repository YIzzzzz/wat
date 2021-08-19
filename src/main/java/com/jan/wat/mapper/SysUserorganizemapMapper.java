package com.jan.wat.mapper;

import com.jan.wat.pojo.SysUserorganizemap;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Repository
public interface SysUserorganizemapMapper extends BaseMapper<SysUserorganizemap> {

    @Select("SELECT organizecode FROM sys_userorganizemap where usercode = #{usercode}")
    public String getOrganizecodebyusercode(String usercode);

}
