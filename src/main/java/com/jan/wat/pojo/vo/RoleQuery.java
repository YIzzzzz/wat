package com.jan.wat.pojo.vo;

import com.jan.wat.pojo.SysUser;
import com.jan.wat.pojo.SysUserrolemap;
import lombok.Data;

import java.util.List;

@Data
public class RoleQuery {
    private List<RoleUserItem> role;
    private List<RoleUserItem> all;
}

