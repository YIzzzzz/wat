package com.jan.wat.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.EquPara;
import com.jan.wat.mapper.EquParaMapper;
import com.jan.wat.pojo.vo.EquParaQuery;
import com.jan.wat.service.IEquParaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jan.wat.pojo.EquPara;
import com.jan.wat.mapper.EquParaMapper;
import com.jan.wat.pojo.EquParagroup;
import com.jan.wat.pojo.EquParavalue;
import com.jan.wat.pojo.vo.ParaTree;
import com.jan.wat.service.IEquParaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jan.wat.service.IEquParagroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
@Service("EquParaServiceImpl")
public class EquParaServiceImpl extends ServiceImpl<EquParaMapper, EquPara> implements IEquParaService {
    @Autowired
    IEquParagroupService iEquParagroupService;

    @Override
    public List<ParaTree> getTree() {
        LambdaQueryWrapper<EquPara> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EquPara::getType,2);
        List<EquParagroup> group = iEquParagroupService.list();
        List<EquPara> para = list(wrapper);

        Map<Integer,Integer> map = new HashMap<>();

        List<ParaTree> tree = new ArrayList<>();

        int index = 0;
        for(EquParagroup event : group){

            ParaTree branch = new ParaTree(event);
            map.put(event.getId(),index++);
            tree.add(branch);
        }

        for(EquPara event : para){
            index = map.get(event.getParagroupId());
            tree.get(index).getChildern().add(event);
        }

        return tree;
    }
    @Autowired
    EquParaMapper mapper;

    @Override
    public List<EquParaQuery> queryParaList() {
        return mapper.queryParaList();
    }

    @Override
    public IPage<EquParaQuery> selectByPage(Page<EquParaQuery> page) {
        return mapper.selectByPage(page);
    }


}
