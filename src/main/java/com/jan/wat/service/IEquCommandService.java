package com.jan.wat.service;

import com.jan.wat.pojo.EquCommand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.EquFailurecommandQuery;
import com.jan.wat.pojo.vo.EquUncheckcommandQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author January
 * @since 2021-07-06
 */
public interface IEquCommandService extends IService<EquCommand> {
    public List<EquUncheckcommandQuery> getEquUncheckcommand(String usercode, String equipmentId, String equipmentgroupId);

    public List<EquFailurecommandQuery> getEquFailurecommand(String usercode, String equipmentId, String equipmentgroupId);
}
