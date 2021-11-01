package com.jan.wat.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.EquEquipment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author January
 * @since 2021-06-25
 */
public interface IEquEquipmentService extends IService<EquEquipment> {
    public List<EuipmentsQuery> getEuipments(String euipmentGroupID, String equipmentId, String userCode);

    public String getRealDataQuery(String equipmentGroupId, String equipmentId, String usercode);
    public String getEquipmentValue(String usercode, String id);
    public List<EquEquipment> getEquEquipment(String usercode,String equipment_id,String equipmentgroup_id);

    public List<EquipmentQuery> getEquipmentbyequipmentgroupid(Integer equipmentgroup_id);

    public List<String> getEquipmentid();

    public List<EquEquipment> getEquEquipmentByUsercode(String usercode);

    public List<RealValue> encodeXML(String xml);

    public String getValue(String usercode, String id, String startTime, String endTime);

    public List<String> getMonths(String time1, String time2);

    public List<MapQuery> getJW(String usercode);

}
