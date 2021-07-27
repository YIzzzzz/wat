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
