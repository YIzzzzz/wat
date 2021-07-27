package com.jan.wat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.EquPara;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.EquParaQuery;
import org.springframework.web.bind.annotation.PathVariable;
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
public interface IEquParaService extends IService<EquPara> {

    List<EquParaQuery> queryParaList();

    IPage<EquParaQuery> selectByPage(Page<EquParaQuery> page);

    List<ParaTree> getTree();
}
