package com.jan.wat.service.impl;

import com.jan.wat.pojo.SysUsermenucolumnsetting;
import com.jan.wat.mapper.SysUsermenucolumnsettingMapper;
import com.jan.wat.pojo.vo.EquParaQuery;
import com.jan.wat.service.ISysUsermenucolumnsettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Service("SysUsermenucolumnsettingServiceImpl")
public class SysUsermenucolumnsettingServiceImpl extends ServiceImpl<SysUsermenucolumnsettingMapper, SysUsermenucolumnsetting> implements ISysUsermenucolumnsettingService {
}
