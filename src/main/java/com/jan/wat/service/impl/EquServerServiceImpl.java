package com.jan.wat.service.impl;

import com.jan.wat.pojo.EquServer;
import com.jan.wat.mapper.EquServerMapper;
import com.jan.wat.pojo.vo.EquServerQuery;
import com.jan.wat.service.IEquServerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-06
 */
@Service("EquServerServiceImpl")
public class EquServerServiceImpl extends ServiceImpl<EquServerMapper, EquServer> implements IEquServerService {

    @Autowired
    EquServerMapper equServerMapper;

    @Override
    public List<EquServerQuery> getList(String equipment_id) {
        return equServerMapper.getList(equipment_id);
    }
}
