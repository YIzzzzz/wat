package com.jan.wat.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.EquPara;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.EquParaQuery;
import com.jan.wat.pojo.vo.MulEquipparaQuery;
import com.jan.wat.pojo.vo.SigEuipementparaQuery;
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

    List<EquParaQuery> querybyparagroupid(int id);

    IPage<EquParaQuery> selectByPage(Page<EquParaQuery> page);

    List<ParaTree> getTree();

    List<SigEuipementparaQuery> getSigEquipmentPara(String equipment_ID);

    boolean sendCommand(List<SigEuipementparaQuery> queries, String userCode);

    List<MulEquipparaQuery> getMulEquipmentPara(List<String> equipmentIds);

    public Boolean addParaCommands(JSONObject json);
}
