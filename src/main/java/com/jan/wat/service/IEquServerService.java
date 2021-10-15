package com.jan.wat.service;

import com.jan.wat.pojo.EquServer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.EquServerQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author January
 * @since 2021-07-06
 */
public interface IEquServerService extends IService<EquServer> {

    public List<EquServerQuery> getList(String equipment_id);

}
