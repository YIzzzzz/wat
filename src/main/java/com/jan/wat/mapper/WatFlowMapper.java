package com.jan.wat.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jan.wat.pojo.WatFlow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jan.wat.pojo.vo.FlowViewQuery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author January
 * @since 2021-07-08
 */
@Repository
public interface WatFlowMapper extends BaseMapper<WatFlow> {

//    public List<FlowViewQuery> getByCondition(@Param("EquipmentGroup_ID")String equipmentGroupId,
//                                              @Param("Equipment_ID")String equipmentId);


    @Select("<script>" +
            "select distinct a.*\n" +
            "from flow_view as a,equ_equipmentgroup_equipment_map egm, equ_user_equipmentgroup_map uem\n" +
            "where a.equipment_id = egm.equipment_id and egm.equipmentgroup_id = uem.equipmentgroup_id and uem.userCode = #{usercode}" +
            "<if test='equipmentgroup_id != \"\"'>" +
            "and a.id in (select equipment_id from equ_equipmentgroup_equipment_map where equipmentgroup_id in (${set}))" +
            "</if>" +
            "<if test='equipment_id != \"\"'>" +
            "and a.id=#{equipment_id}" +
            "</if>" +
            "</script>")
    @Results(id="getCommand", value={
            @Result(column="caliber_id", property="caliber_id"),
            @Result(column="equipment_name", property="equipment_name"),
            @Result(column="classify_id", property="classify_id"),
            @Result(column="model", property="model"),
            @Result(column="clientname", property="clientname"),
            @Result(column="bookno", property="bookno"),
            @Result(column="price", property="price"),
            @Result(column="areas", property="areas"),
            @Result(column="persons", property="persons"),
            @Result(column="classify_name", property="classify_name"),
            @Result(column="caliber_name", property="caliber_name"),
            @Result(column="category", property="category"),
            @Result(column="status", property="status"),
            @Result(column="flowtype_id", property="flowtype_id"),
            @Result(column="flowtype_name", property="flowtype_name"),
            @Result(column="des", property="des"),
            @Result(column="siteno", property="siteno"),
            @Result(column="storemode", property="storemode"),
            @Result(column="enable", property="enable"),
            @Result(column="category_name", property="category_name"),
            @Result(column="equipment_id", property="equipment_id")
    })
    public List<FlowViewQuery> getAllFlow(String usercode,String set,String equipment_id,String equipmentgroup_id);

    @Select("select a.id from wat_alarmtype a, wat_alarmtype_flow_map b\n" +
            "where b.Equipment_ID = #{equipment_id} and a.ID = b.AlarmType_ID")
    public List<Integer> getAlarmtypeid(String equipment_id);

}
