package com.jan.wat.service.impl;

import com.jan.wat.mapper.EquParaMapper;
import com.jan.wat.mapper.EquParagroupMapper;
import com.jan.wat.pojo.EquPara;
import com.jan.wat.pojo.EquParagroup;
import com.jan.wat.pojo.EquParavalue;
import com.jan.wat.mapper.EquParavalueMapper;
import com.jan.wat.pojo.vo.ParaTree;
import com.jan.wat.service.IEquParagroupService;
import com.jan.wat.service.IEquParavalueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Service("EquParavalueServiceImpl")
public class EquParavalueServiceImpl extends ServiceImpl<EquParavalueMapper, EquParavalue> implements IEquParavalueService {
}
