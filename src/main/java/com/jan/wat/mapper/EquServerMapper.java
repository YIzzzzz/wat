package com.jan.wat.mapper;

import com.jan.wat.pojo.EquServer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.EquServerQuery;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author January
 * @since 2021-07-06
 */
@Repository
public interface EquServerMapper extends BaseMapper<EquServer> {

    @Select("select A.ID,A.Name,A.IPAddress,A.Port,A.Des from equ_Server A, equ_Server_Equipment_Map B " +
            "where B.Server_ID = A.ID and B.Equipment_ID = #{equipment_ID}")
    public List<EquServer> getServerList(String equipment_ID);


    @Select("select d.name as datatypename, u.name as unitname\n" +
            "from equ_equipmentrealdata r, equ_datatype d, equ_unit u\n" +
            "where r.ifrecord = 1 and d.id = r.datatype_id and u.id = r.unit_id and r.equipment_id = #{equipment_id}")
    public List<EquServerQuery> getList(String equipment_id);

}
