package com.jan.wat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.EquParavalue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.EquParavalueQuery;
import com.jan.wat.pojo.vo.ParaTree;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
public interface IEquParavalueService extends IService<EquParavalue> {


    public List<EquParavalueQuery> selectByPage(Integer para_id);

    public boolean updateByIdAndParaID(EquParavalue equParavalue);

}
