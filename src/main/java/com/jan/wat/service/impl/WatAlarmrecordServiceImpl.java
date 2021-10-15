package com.jan.wat.service.impl;

import com.jan.wat.pojo.WatAlarmrecord;
import com.jan.wat.mapper.WatAlarmrecordMapper;
import com.jan.wat.pojo.vo.WatAlarmQuery;
import com.jan.wat.service.IWatAlarmrecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Service("WatAlarmrecordServiceImpl")
public class WatAlarmrecordServiceImpl extends ServiceImpl<WatAlarmrecordMapper, WatAlarmrecord> implements IWatAlarmrecordService {

    @Autowired
    WatAlarmrecordMapper watAlarmrecordMapper;

    @Override
    public List<WatAlarmQuery> getWatUnrecoveryalarm(String usrcode) {
        return watAlarmrecordMapper.getWatUnrecoveryalarm(usrcode);
    }

    @Override
    public List<WatAlarmQuery> getWatYesterdayalarm(String usercode) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime minus = localDateTime.minus(Period.ofDays(1));
        String start = minus.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));
        String end = minus.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59"));

        return watAlarmrecordMapper.getWatYesterdayalarm(usercode,start,end);
    }

    @Override
    public List<WatAlarmQuery> getWatYesterdayrecoveryalarm(String usercode) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime minus = localDateTime.minus(Period.ofDays(1));
        String start = minus.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));
        String end = minus.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59"));

        return watAlarmrecordMapper.getWatYesterdayrecoveryalarm(usercode,start,end);
    }

    @Override
    public List<WatAlarmQuery> getWatYesterdayconfirmalarm(String usercode) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime minus = localDateTime.minus(Period.ofDays(1));
        String start = minus.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));
        String end = minus.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59"));

        return watAlarmrecordMapper.getWatYesterdayconfirmalarm(usercode,start,end);
    }

    @Override
    public List<WatAlarmQuery> getWatUnconfirmalarm(String usercode) {
        return watAlarmrecordMapper.getWatUnconfirmalarm(usercode);
    }
}
