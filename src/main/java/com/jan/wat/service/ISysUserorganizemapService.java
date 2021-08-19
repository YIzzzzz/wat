package com.jan.wat.service;

import com.jan.wat.pojo.SysUserorganizemap;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.SysRegisterQuerry;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
public interface ISysUserorganizemapService extends IService<SysUserorganizemap> {

    public String getOrganizecodebyusercode(String usercode);


}
