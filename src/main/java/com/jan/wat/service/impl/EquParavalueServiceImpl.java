package com.jan.wat.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.mapper.EquParaMapper;
import com.jan.wat.mapper.EquParagroupMapper;
import com.jan.wat.pojo.EquPara;
import com.jan.wat.pojo.EquParagroup;
import com.jan.wat.pojo.EquParavalue;
import com.jan.wat.mapper.EquParavalueMapper;
import com.jan.wat.pojo.vo.EquParavalueQuery;
import com.jan.wat.service.IEquParagroupService;
import com.jan.wat.service.IEquParavalueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
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
@Service("EquParavalueServiceImpl")
public class EquParavalueServiceImpl extends ServiceImpl<EquParavalueMapper, EquParavalue> implements IEquParavalueService {

    @Autowired
    EquParavalueMapper equParavalueMapper;

    @Override
    public IPage<EquParavalueQuery> selectByPage(Page<EquParavalueQuery> page, Integer para_id) {
        return equParavalueMapper.selectByPage(page,  para_id);
    }

    @Override
    public boolean updateByIdAndParaID(EquParavalue equParavalue) {

        UpdateWrapper<EquParavalue> wrapper = new UpdateWrapper<>();

        wrapper.eq("ID",equParavalue.getId());
        wrapper.eq("Para_ID",equParavalue.getParaId());

        return update(equParavalue,wrapper);

    }


}
