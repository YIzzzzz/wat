package com.jan.wat.service;

import com.jan.wat.pojo.WatFlow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.FlowViewQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
public interface IWatFlowService extends IService<WatFlow> {

//    List<FlowViewQuery> getAllFlowtypeList(String equipmentGroupId, String equipmentId);

//    public List<FlowViewQuery> getAllFlow();



    public List<FlowViewQuery> getAllFlow(String usercode,String equipment_id,String equipmentgroup_id);

    public List<Integer> getAlarmtypeid(String equipment_id);

}
