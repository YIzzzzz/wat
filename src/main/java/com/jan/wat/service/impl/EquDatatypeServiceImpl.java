package com.jan.wat.service.impl;

import com.jan.wat.pojo.EquDatatype;
import com.jan.wat.mapper.EquDatatypeMapper;
import com.jan.wat.service.IEquDatatypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Service("EquDatatypeServiceImpl")
public class EquDatatypeServiceImpl extends ServiceImpl<EquDatatypeMapper, EquDatatype> implements IEquDatatypeService {
    @Autowired
    IEquDatatypeService iEquDatatypeService;

    @Override
    public Map<Integer, String> getMap(){
        Map<Integer, String> map = new HashMap<>();
        List<EquDatatype> list = iEquDatatypeService.list();
        for(EquDatatype item : list){
            map.put(item.getId(), item.getName());
        }
        return map;
    }

}
