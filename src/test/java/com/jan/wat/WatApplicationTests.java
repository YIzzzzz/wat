package com.jan.wat;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.EquServer.config.GlobalParameter;
import com.jan.wat.EquServer.helper.DateTime;
import com.jan.wat.mapper.*;
import com.jan.wat.pojo.*;
import com.jan.wat.pojo.vo.OrganizeTree;
import com.jan.wat.pojo.vo.EquParaQuery;
import com.jan.wat.pojo.vo.SigEuipementparaQuery;
import com.jan.wat.pojo.vo.ReadEquipmentparaQuery;
import com.jan.wat.pojo.vo.SysRoleeditQuery;
import com.jan.wat.pojo.vo.*;
import com.jan.wat.service.IEquCommandService;
import com.jan.wat.service.IEquDatatypeService;
import com.jan.wat.service.ISysOrganizeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class WatApplicationTests {

    @Autowired
    EquipmentdataMapper equipmentdataMapper;

    @Autowired
    IEquDatatypeService iEquDatatypeService;
    @Autowired
    EquDatatypeMapper equDatatypeMapper;
    @Autowired
    EquServerMapper equServerMapper;
    @Autowired
    IEquCommandService iEquCommandService;
    @Autowired
    EquEquipmentMapper equEquipmentMapper;
    @Autowired
    EquParaMapper equParaMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    EquEquipmentparaMapper equEquipmentparaMapper;

//    @Test
//    void contextLoads() {
//    }


    @Test
    public void insertTest(){
        Equipmentdata equipmentdata = new Equipmentdata();
        equipmentdata.setCollecttime(DateTime.DateNow());
        equipmentdata.setData("<D><V i=\"0\">-0.787</V>");
        equipmentdata.setEquipmentId("77777777");
        equipmentdata.setStr("");
        equipmentdata.setUploadtime(DateTime.DateNow());
        equipmentdataMapper.insertData("rdasdata202106",equipmentdata);
    }

    @Test
    void testGetServerList(){
        System.out.println(equServerMapper.getServerList("10"));
    }

    @Test
    void testQuetry(){
        QueryWrapper<EquCommand> queryCommand = new QueryWrapper<>();
        queryCommand.eq("Equipment_ID", "1234");
        queryCommand.le("Status",2);
        queryCommand.isNull("ResponseTime");
        queryCommand.lt("SendNum", GlobalParameter.commandSendMaxNumLimit);
        queryCommand.orderBy(true,false,"SettingTime");
        System.out.println(queryCommand);
        List<EquCommand> list = iEquCommandService.list(queryCommand);
    }

    @Test
    public void testtimg(){
        byte[] data = {0x0D,0x0B,0x15,0x11,0x36,0x2B};
        System.out.println(DateTime.getDate(data,0,6));
    }


    @Test
    public void testPage(){



        Page<EquDatatype> page = new Page<>(1,5);
        iEquDatatypeService.page(page,null);
//        equDatatypeMapper.selectPage(page,null);

        page.getRecords().forEach(System.out::println);
    }

    @Test
    public void testesdquipxdment(){
        List<Object> realData1 = equEquipmentMapper.getRealData();

        System.out.println(realData1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",realData1);

//        Select.List realData = realData1;
        System.out.println(jsonObject.toJSONString());

//        return "2";
    }

//    @Test
//    public void test() throws ParseException {
////        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
////        System.out.println(df.parse(df.format(new Date())));// new Date()为获取当前系统时间
//        SysUser sysUser = new SysUser();
//        sysUser.setCreatedate(new Date());
//        System.out.println(sysUser.getCreatedate());
//    }

    @Autowired
    ISysOrganizeService iSysOrganizeService;

    @Test
    public void createJson(){
        List<SysOrganize> list = iSysOrganizeService.list();
        List<OrganizeTree> trees = new ArrayList<>();

        func("*",0,list,trees);
        System.out.println(trees);

    }


    public void func(String father, int index, List<SysOrganize> list, List<OrganizeTree> trees){

        if(index == list.size())
            return;
        SysOrganize s = list.get(index);
        OrganizeTree tree = new OrganizeTree(s);

        if(father.equals("*")  || s.getParentcode().equals(father)){
            trees.add(tree);
            func(s.getOrganizecode(),index+1, list, tree.getChildren());
        }else{
            func(s.getParentcode(),index, list, trees);
        }
        return;
    }

    @Test
    public void queryparalist(){

        List<EquParaQuery> equParaQueries = equParaMapper.queryParaList();

        for(EquParaQuery e:equParaQueries){

            System.out.println(e);
        }
    }

    @Test
    public void SysRole(){

        List<SysRoleeditQuery> aSuper = sysRoleMapper.selectByRolecode("super");

        System.out.println(aSuper);

    }


    @Test
    public void sigEquipment(){
        List<SigEuipementparaQuery> sigEquipmentPara = equParaMapper.getSigEquipmentPara("0");
        System.out.println(sigEquipmentPara);
    }

    @Test
    public void equipments(){
        equEquipmentMapper.getEuipments("2","and egm.equipmentgroup_id = uem.equipmentgroup_id");
    @Test
    public void readequipmentpara(){
//        List<ReadEquipmentparaQuery> aSuper = equEquipmentparaMapper.readEquipmentpara("huluadmin");
//        System.out.println(aSuper);

//        int index =0;
//        for(ReadEquipmentparaQuery e : aSuper){
//            System.out.println(e);
//            index ++;
//            if(index > 8) break;
//        }
    }

    @Test
    public void para(){
        List<MulEquipparaQuery> mulEquipmentPara = equParaMapper.getMulEquipmentPara(",(SeLeCT para_ID FROM equ_equipmentpara a) t0 inner join (SeLeCT para_ID FROM equ_equipmentpara a) t1 on t0.para_ID=t1.para_ID ");
        System.out.println(mulEquipmentPara);
    }

    @Test
    public void para1(){
        List<LimitQuery> limit = equParaMapper.getLimit(10, "(1,2)");
        System.out.println(limit);
    }

}
