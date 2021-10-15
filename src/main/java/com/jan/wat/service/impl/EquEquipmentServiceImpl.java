package com.jan.wat.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.EquEquipment;
import com.jan.wat.mapper.EquEquipmentMapper;
import com.jan.wat.pojo.EquEquipmentrealdata;
import com.jan.wat.pojo.vo.EquParaQuery;
import com.jan.wat.pojo.vo.EquipmentQuery;
import com.jan.wat.pojo.vo.EuipmentsQuery;
import com.jan.wat.pojo.vo.RealDataQuery;
import com.jan.wat.service.IEquEquipmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jan.wat.service.IEquEquipmentgroupService;
import com.jan.wat.service.IEquEquipmentrealdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author January
 * @since 2021-06-25
 */
@Service("EquEquipmentServiceImpl")
public class EquEquipmentServiceImpl extends ServiceImpl<EquEquipmentMapper, EquEquipment> implements IEquEquipmentService {

    @Autowired
    EquEquipmentMapper equEquipmentMapper;

    @Autowired
    IEquEquipmentrealdataService iEquEquipmentrealdataService;

    @Autowired
    IEquEquipmentgroupService iEquEquipmentgroupService;
    @Override
    public List<EuipmentsQuery> getEuipments(String euipmentGroupID, String equipmentId, String userCode) {
        StringBuilder where = new StringBuilder("");
        System.out.println("=============="+euipmentGroupID);
        if(!euipmentGroupID.equals("")){
            List<String> childrenGroupId = iEquEquipmentgroupService.getChildrenId(userCode, euipmentGroupID);
            System.out.println(childrenGroupId);
            if(childrenGroupId.size()>0){
                where.append(" and A.ID in (");
                int count = 0;
                int length = childrenGroupId.size();
                for(String i : childrenGroupId){
                    where.append(i);
                    count++;
                    if(count == length)
                        continue;
                    where.append(",");
                }
                where.append(")");
            }else{
                return new ArrayList<EuipmentsQuery>();
            }
        }
        if(!equipmentId.equals("0") && !equipmentId.equals("")){
            where.append(String.format(" and A.ID=%s",equipmentId));
        }

        return equEquipmentMapper.getEuipments(userCode, where.toString());
    }

    @Override
    public String getRealDataQuery(long current, long size,String equipmentGroupId, String equipmentId, String usercode) {
        Page<RealDataQuery> page = new Page<>(current, size);
        StringBuilder where = new StringBuilder();

        if(!equipmentGroupId.equals("0")){
            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentGroupId);
            StringBuilder set = new StringBuilder();
            int count = 0;
            for(int i : childrengroupId){
                count++;
                set.append(String.valueOf(i));
                if(count != childrengroupId.size())
                    set.append(",");
            }
            where.append(String.format(" uem.EquipmentGroup_ID in (%s)",set.toString()));
        }

        if(!equipmentId.equals("0"))
            where.append(String.format(" and e.ID = 5s",equipmentId));

        IPage<RealDataQuery> realDataQuery = equEquipmentMapper.getRealDataQuery(page,usercode,where.toString());
        JSONArray array = new JSONArray();
        for(RealDataQuery realData : realDataQuery.getRecords()){
            JSONObject json = new JSONObject();
            json.put("equipmentalarm",realData.getEquipmentalarm());
            json.put("id",realData.getId());
            json.put("lastcollecttime",realData.getLastcollecttime());
            json.put("n",realData.getN());
            json.put("outLinealarm",realData.getOutLinealarm());
            LambdaQueryWrapper<EquEquipmentrealdata> wrapper = new LambdaQueryWrapper<>();
            wrapper.orderByAsc(EquEquipmentrealdata::getPosition);
            wrapper.eq(EquEquipmentrealdata::getEquipmentId,realData.getId());
            List<EquEquipmentrealdata> list = iEquEquipmentrealdataService.list(wrapper);
            for(EquEquipmentrealdata equEquipmentrealdata : list){
                json.put(String.format("VT%s",equEquipmentrealdata.getDatatypeId().toString()),equEquipmentrealdata.getValue());
            }
            array.add(json);
        }
        return array.toString();
    }

    @Override
    public List<EquEquipment> getEquEquipment(String usercode, String equipment_id, String equipmentgroup_id) {
        StringBuilder set = new StringBuilder();
        set.append(equipmentgroup_id);
        if(equipmentgroup_id != "0"){
            List<Integer> childrengroupId = iEquEquipmentgroupService.getChildrenGroupId(usercode,equipmentgroup_id);
            for(int i : childrengroupId){

                set.append(",");
                set.append(String.valueOf(i));

            }
        }
        return equEquipmentMapper.getEquEquipment(usercode,set.toString(),equipment_id,equipmentgroup_id);
    }

    @Override
    public List<EquipmentQuery> getEquipmentbyequipmentgroupid(Integer equipmentgroup_id) {
        return equEquipmentMapper.getEquipmentbyequipmentgroupid(equipmentgroup_id);
    }

    @Override
    public List<String> getEquipmentid() {
        return equEquipmentMapper.getEquipmentid();
    }

    @Override
    public List<EquEquipment> getEquEquipmentByUsercode(String usercode) {
        return equEquipmentMapper.getEquEquipmentByUsercode(usercode);
    }

}
