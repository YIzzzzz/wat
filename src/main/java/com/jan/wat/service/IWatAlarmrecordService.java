package com.jan.wat.service;

import com.jan.wat.pojo.WatAlarmrecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.WatAlarmQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
public interface IWatAlarmrecordService extends IService<WatAlarmrecord> {

    public List<WatAlarmQuery> getWatUnrecoveryalarm(String usrcode);

    public List<WatAlarmQuery> getWatYesterdayalarm(String usercode);

    public List<WatAlarmQuery> getWatYesterdayrecoveryalarm(String usercode);

    public List<WatAlarmQuery> getWatYesterdayconfirmalarm(String usercode);

    public List<WatAlarmQuery> getWatUnconfirmalarm(String usercode);

}
