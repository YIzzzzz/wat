package com.jan.wat.service;

import com.jan.wat.pojo.EquCommand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.EquUncheckcommandQuery;
import com.jan.wat.pojo.vo.FailureAndHistoryCommandQuery;

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
    public List<FailureAndHistoryCommandQuery> getHistoryCommand(String usercode, String equipmentGroupId, String equipmentId, String commandtype, String startTime, String endTime);

    public List<FailureAndHistoryCommandQuery> getEquFailurecommand(String usercode, String equipmentId, String equipmentgroupId);
}
