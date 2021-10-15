package com.jan.wat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.EquEquipment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jan.wat.pojo.vo.EquParaQuery;
import com.jan.wat.pojo.vo.EquipmentQuery;
import com.jan.wat.pojo.vo.EuipmentsQuery;
import com.jan.wat.pojo.vo.RealDataQuery;
import org.springframework.web.bind.annotation.PathVariable;

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

    String getRealDataQuery(long current, long size,String equipmentGroupId, String equipmentId, String usercode);

    public List<EquEquipment> getEquEquipment(String usercode,String equipment_id,String equipmentgroup_id);

    public List<EquipmentQuery> getEquipmentbyequipmentgroupid(Integer equipmentgroup_id);

    public List<String> getEquipmentid();

    public List<EquEquipment> getEquEquipmentByUsercode(String usercode);



}
