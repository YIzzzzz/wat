package com.jan.wat.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.pojo.EquEquipment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.EquParaQuery;
import com.jan.wat.pojo.vo.EquipmentQuery;
import com.jan.wat.pojo.vo.EuipmentsQuery;
import com.jan.wat.pojo.vo.RealDataQuery;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author January
 * @since 2021-06-25
 */
@Repository
public interface EquEquipmentMapper extends BaseMapper<EquEquipment> {

    List<Object> getRealData();

    @Select("select distinct A.id as id,A.Name as aName, ee.Name as equipmentType_id,A.PowerType as powerType\n" +
            "from equ_equipment A,equ_equipmentgroup_equipment_map egm, equ_user_equipmentgroup_map uem, equ_equipmenttype ee\n" +
            "where A.id = egm.equipment_id and egm.equipmentgroup_id = uem.equipmentgroup_id and A.EquipmentType_ID = ee.id and uem.userCode = #{userCode} ${where}")
    @Results(id = "Para",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "aName",property = "aName"),
            @Result(column = "equipmentType_id",property = "equipmenttypename"),
            @Result(column = "powerType",property = "powerType")
    })
    List<EuipmentsQuery> getEuipments(String userCode, String where);


    @Select("select distinct e.Id as id,e.Name as n,CONVerT(e.LastCollectTime,datetime) as lastCollectTime,\n" +
            "(case when exists(select Id from equ_alarmrecord ar where ar.equipment_Id=e.Id and ar.alarmType=1 and ar.recovery='false') then 'true' else 'false' end) as equipmentalarm\n" +
            ",(case when exists(select Id from equ_alarmrecord ar where ar.equipment_Id=e.Id and ar.alarmType=2 and ar.recovery='false') then 'true' else 'false' end) as outLinealarm\n" +
            "from equ_equipment e,equ_equipmentgroup_equipment_map as gem,equ_user_equipmentgroup_map uem\n" +
            "where gem.equipment_Id=e.Id and uem.equipmentgroup_Id=gem.equipmentgroup_Id and uem.UserCode = #{usercode} ${where}")
    IPage<RealDataQuery> getRealDataQuery(Page<RealDataQuery> page, String usercode, String where);

    @Select("<script>" +
            "select distinct a.*, em.name as manufacturername, et.name as equipmenttypename\n" +
            "from equ_equipment a,equ_equipmentgroup_equipment_map as gem,equ_user_equipmentgroup_map uem,equ_manufacturer em,equ_equipmenttype et\n" +
            "where gem.equipment_id=a.id and uem.equipmentgroup_id=gem.equipmentgroup_id and uem.usercode=#{usercode} and a.manufacturer_id = em.id and a.equipmenttype_id = et.id" +
            "<if test='equipmentgroup_id != \"0\"'>" +
            "and a.id in (select equipment_id from equ_equipmentgroup_equipment_map where equipmentgroup_id in (${set}))" +
            "</if>" +
            "<if test='equipment_id != \"\"'>" +
            "and a.id=#{equipment_id}" +
            "</if>" +
            "order by a.id asc" +
            "</script>")
    @Results(id="getCommand", value={
            @Result(column="name", property="aname"),
            @Result(column="port", property="p"),
            @Result(column="status", property="s")
    })
    public List<EquEquipment> getEquEquipment(String usercode,String set,String equipment_id,String equipmentgroup_id);


    @Select("select eq.id as id, eq.name as name \n" +
            "from equ_equipment eq, equ_equipmentgroup_equipment_map eem\n" +
            "where eq.ID = eem.Equipment_ID and eem.EquipmentGroup_ID = #{equipmentgroup_id}")
    public List<EquipmentQuery> getEquipmentbyequipmentgroupid(Integer equipmentgroup_id);

    @Select("select id from equ_equipment")
    public List<String> getEquipmentid();

    @Select("<script>" +
            "select distinct a.*, em.name as manufacturername, et.name as equipmenttypename\n" +
            "from equ_equipment a,equ_equipmentgroup_equipment_map as gem,equ_user_equipmentgroup_map uem,equ_manufacturer em,equ_equipmenttype et\n" +
            "where gem.equipment_id=a.id and uem.equipmentgroup_id=gem.equipmentgroup_id and uem.usercode=#{usercode} and a.manufacturer_id = em.id and a.equipmenttype_id = et.id" +
            "</script>")
    @Results(id="getCommands", value={
            @Result(column="name", property="aname"),
            @Result(column="port", property="p"),
            @Result(column="address", property="address"),
            @Result(column="setuptime", property="setuptime"),
            @Result(column="status", property="s")
    })
    public List<EquEquipment> getEquEquipmentByUsercode(String usercode);

}
