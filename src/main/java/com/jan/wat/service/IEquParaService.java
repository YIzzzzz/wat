package com.jan.wat.service;

import com.jan.wat.pojo.EquPara;
import com.baomidou.mybatisplus.extension.service.IService;
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

    List<ParaTree> getTree();
}
