package com.jan.wat.service;

import com.jan.wat.pojo.EquAlarmrecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.EquAlarmQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author January
 * @since 2021-07-06
 */
public interface IEquAlarmrecordService extends IService<EquAlarmrecord> {

    public List<EquAlarmQuery> getEquUnrecoveryalarm(String usercode, String equipment_id, String equipmentgroup_id);

    public List<EquAlarmQuery> getEquYesterdayalarm(String usercode, String equipment_id, String equipmentgroup_id);

    public List<EquAlarmQuery> getEquYesterdayrecoveryalarm(String usercode, String equipment_id, String equipmentgroup_id);

    public List<EquAlarmQuery> getEquYesterdayconfirmalarm(String usercode, String equipment_id, String equipmentgroup_id);

    public List<EquAlarmQuery> getEquUnconfirmalarm(String usercode, String equipment_id, String equipmentgroup_id);

    public List<EquAlarmQuery> getEquhistoryalarm(String usercode, String equipment_id, String equipmentgroup_id, String alarmtype, String start, String end);

    public String statisticsAlarm(String usercode, List<String> groupId, List<String> groupName, String startTime, String endTime);

}
