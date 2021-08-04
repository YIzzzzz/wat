package com.jan.wat.service;

import com.jan.wat.pojo.EquEquipment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.EuipmentsQuery;

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
}
