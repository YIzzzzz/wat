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


}
