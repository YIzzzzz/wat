package com.jan.wat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.EquServer.config.Command;
import com.jan.wat.EquServer.config.GlobalParameter;
import com.jan.wat.EquServer.helper.DateTime;
import com.jan.wat.EquServer.helper.Tools;
import com.jan.wat.mapper.*;
import com.jan.wat.pojo.*;
import com.jan.wat.pojo.vo.OrganizeTree;
import com.jan.wat.pojo.vo.EquParaQuery;
import com.jan.wat.pojo.vo.SigEuipementparaQuery;
import com.jan.wat.pojo.vo.*;
import com.jan.wat.service.*;
import org.dom4j.Attribute;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.Test;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
    SysUserMapper sysUserMapper;
    @Autowired
    EquEquipmentparaMapper equEquipmentparaMapper;

    @Autowired
    EquAlarmrecordMapper equAlarmrecordMapper;
    @Autowired
    EquCommandMapper equCommandMapper;
    @Autowired
    ISysOrganizeService iSysOrganizeService;
    @Autowired
    IEquEquipmentrealdataService iEquEquipmentrealdataService;
    @Autowired
    IEquEquipmentgroupService iEquEquipmentgroupService;
    @Autowired
    IEquAlarmrecordService iEquAlarmrecordService;
    @Autowired
    SysUserorganizemapMapper sysUserorganizemapMapper;
    @Autowired
    ISysUsermenumapService iSysUsermenumapService;
    @Autowired
    SysUserrolemapMapper sysUserrolemapMapper;
    @Autowired
    WatFlowMapper watFlowMapper;

    @Autowired
    IWatFlowService iWatFlowService;

    @Autowired
    IEquEquipmentService iEquEquipmentService;

    @Autowired
    IWatAlarmrecordService iWatAlarmrecordService;

    @Autowired
    ISysRoleService iSysRoleService;

    @Autowired
    IEquServerEquipmentMapService iEquServerEquipmentMapService;
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

        LocalDateTime time = LocalDateTime.now();
        boolean b = sysRoleMapper.updateRole("003", "004", "123", "46","546","64",time);
        if(b){
            System.out.println("-------------------------------------------");
        }else{
            System.out.println("************************************************");
        }

//        System.out.println(aSuper);

    }


    @Test
    public void sigEquipment(){
        List<SigEuipementparaQuery> sigEquipmentPara = equParaMapper.getSigEquipmentPara("10200");
        System.out.println(sigEquipmentPara);
    }

    @Test
    public void equipments() {
        equEquipmentMapper.getEuipments("2", "and egm.equipmentgroup_id = uem.equipmentgroup_id");
    }
//    @Test
//    public void readequipmentpara(){
//        List<ReadEquipmentparaQuery> aSuper = equEquipmentparaMapper.readEquipmentpara("huluadmin");
//        System.out.println(aSuper);

//        int index =0;
//        for(ReadEquipmentparaQuery e : aSuper){
//            System.out.println(e);
//            index ++;
//            if(index > 8) break;
//        }
//    }

    @Test
    public void para(){
        List<MulEquipparaQuery> mulEquipmentPara = equParaMapper.getMulEquipmentPara(",(SeLeCT para_ID FROM equ_equipmentpara a) t0 inner join (SeLeCT para_ID FROM equ_equipmentpara a) t1 on t0.para_ID=t1.para_ID ");
        System.out.println(mulEquipmentPara);
    }

    @Test
    public void para1() {
        List<LimitQuery> limit = equParaMapper.getLimit(10, "(1,2)");
        System.out.println(limit);
    }
    @Test
    public void testUncheckcommand(){
        List<EquUncheckcommandQuery> huluadmin = equCommandMapper.getEquUncheckcommand("huluadmin","1","0","3");

        int index =0;
        for(EquUncheckcommandQuery e : huluadmin){
            System.out.println(e);
            index ++;
            if(index > 20) break;
        }
    }
    @Test
    public void getHistoryCommand(){
        System.out.println(equCommandMapper.getHistoryCommand("huluadmin",""));
    }



    @Test
    public void getRealData(){
        String equipmentGroupId = "0";
        String usercode = "huluadmin";
        String equipmentId = "0";
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
            where.append(String.format(" and uem.EquipmentGroup_ID in (%s)",set.toString()));
        }

        if(!equipmentId.equals("0"))
            where.append(String.format(" and e.ID = 5s",equipmentId));

        List<RealDataQuery> realDataQuery = equEquipmentMapper.getRealDataQuery(usercode,where.toString());
        JSONArray array = new JSONArray();
        for(RealDataQuery realData : realDataQuery){
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
        System.out.println(array.toString());
    }

    @Test
    public void udpTest(){
        LambdaQueryWrapper<EquCommand> queryCommand = new LambdaQueryWrapper<>();
        queryCommand.eq(EquCommand::getEquipmentId, "13389");
        queryCommand.lt(EquCommand::getStatus,2);
        queryCommand.isNull(EquCommand::getResponsetime);
        queryCommand.le(EquCommand::getSendnum, GlobalParameter.commandSendMaxNumLimit);
        queryCommand.orderBy(true,false,EquCommand::getSettingtime);
        List<EquCommand> list = iEquCommandService.list(queryCommand);

    }

    @Test
    public void testFaiurecommand(){

        List<FailureAndHistoryCommandQuery> huluadmin = equCommandMapper.getEquFailurecommand("huluadmin", 3,"","0","0");

        int index =0;
        for(FailureAndHistoryCommandQuery e : huluadmin){
            System.out.println(e);
            index ++;
            if(index > 20) break;
        }
    }

//    @Test
//    public void testgetuserbyorganizecode(){
//        List<SysRegisterQuerry> getuserbyorganizecode = sysUserMapper.getuserbyorganizecode("0105");
//        for(SysRegisterQuerry e: getuserbyorganizecode){
//            System.out.println(e);
//        }
//    }

    @Test
    public void getEquUnrecoveryalarm(){
        List<EquAlarmQuery> huluadmin = iEquAlarmrecordService.getEquUnrecoveryalarm("huluadmin", "0", "0");
        for(EquAlarmQuery e:huluadmin){
            System.out.println(e);
        }
    }

    @Test
    public void getEquYesterdayalarm(){
        iEquAlarmrecordService.getEquYesterdayalarm("shbf","0","0");
    }

    @Test
    public void getEquYesterdayrecoveryalarm(){
        iEquAlarmrecordService.getEquYesterdayrecoveryalarm("shbf","0","0");
//        equAlarmrecordMapper.getEquYesterdayrecoveryalarm("shbf","2018-02-01 00:00:00","2021-02-01 23:59:59");
//        iEquAlarmrecordService.getEquYesterdayalarm("shbf","0","0");
    }

    @Test
    public void getEquYesterdayconfirmalarm(){
        iEquAlarmrecordService.getEquYesterdayconfirmalarm("shbf","8817","0");
//        equAlarmrecordMapper.getEquYesterdayconfirmalarm("shbf","2018-02-01 00:00:00","2021-02-01 23:59:59");
//        iEquAlarmrecordService.getEquYesterdayalarm("shbf","0","0");
    }

    @Test
    public void getEquUnconfirmalarm(){
        iEquAlarmrecordService.getEquUnconfirmalarm("shbf","0","0");
//        equAlarmrecordMapper.getEquUnconfirmalarm("shbf");
//        iEquAlarmrecordService.getEquYesterdayalarm("shbf","0","0");
    }

    @Test
    public void getEquhistoryalarm(){
//        iEquAlarmrecordService.getEquhistoryalarm("thy","0","0","0","1","2018-07-02 10:40:27","2018-07-06 10:40:27");
//        equAlarmrecordMapper.getEquhistoryalarm("thy");
//        iEquAlarmrecordService.getEquYesterdayalarm("shbf","0","0");
    }


    @Test
    public void getOrangizecodebyusercode(){
        String thy = sysUserorganizemapMapper.getOrganizecodebyusercode("thy");
        System.out.println("-------------------" + thy);
    }


    @Test
    public void getruleseqbyusercode(){
        String thy = sysUserrolemapMapper.getroleseqbyusercode("thy");
        System.out.println(thy);

    }

    @Test
    public void getroleseqbyusercode(){
        String thy = sysRoleMapper.getroleseqbyusercode("thy");
        System.out.println(thy);
    }

    @Test
    public void sys_usermenumapTest(){
        iSysUsermenumapService.list();
    }

    @Test
    public void updatepassword(){

        List<EquParaQuery> querybyparagroupid = equParaMapper.querybyparagroupid(1);
        System.out.println(querybyparagroupid);

    }

    @Test
    public void getMaxId(){

      // equCommandMapper.getHistoryupdateprogramrecord("thy","","1");
    }




    @Test
    public void getAllFlow(){

//        iEquCommandService.getHistoryupdateprogramrecord("thy","0","0","0","0","0","0");

//        iEquAlarmrecordService.getEquhistoryalarm("thy","0","0","2","","");
    }
//
//    @Autowired
//    IEquEquipmentService iEquEquipmentService;
    @Test
    public void testGetRealDataQuery(){

        System.out.println(iEquEquipmentService.getRealDataQuery( "3","0","thy"));
//        System.out.println(iEquEquipmentgroupService.getChildrenGroupId("thy","3"));
    }
    @Test
    public void getMonthBetween1(){
        System.out.println(iEquEquipmentService.getMonths("2010-01-01 10:10:10.313","2030-01-01 10:10:10.313"));
    }

    @Test
    public void getMonthBetween(){
        String startDate = "201002";
        String endDate = "202010";
        ArrayList<String> result = new ArrayList<String>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");//格式化为年月

            Calendar min = Calendar.getInstance();
            Calendar max = Calendar.getInstance();
            min.setTime(sdf.parse(startDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(sdf.parse(endDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

            Calendar curr = min;
            while (curr.before(max)) {
                result.add("rdasdata"+sdf.format(curr.getTime()));
                curr.add(Calendar.MONTH, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    @Test
    public void test_XML(){
        String xml = "<D><V i=\"0\">-0.787</V><V i=\"1\">-0.626</V><V i=\"2\">2.78</V><V i=\"3\">3.0</V><V i=\"4\">9019740.1</V><V i=\"5\">9000957.3</V><V i=\"218\">18782.8</V><V i=\"14\">0.0</V><V i=\"219\">0.0</V><V i=\"10\">0.0</V><V i=\"12\">0.0</V><V i=\"13\">0.0</V><V i=\"220\">0.0</V><V i=\"11\">0.0</V><V i=\"36\">0.0</V><V i=\"34\">2485.0</V><V i=\"20\">0.0</V><V i=\"21\">1.0</V><V i=\"22\">0.0</V><V i=\"23\">0.0</V><V i=\"43\">0.0</V><V i=\"224\">0.0</V><V i=\"213\">0.0</V><V i=\"17\">100.0</V><V i=\"228\">0.0</V><V i=\"225\">0.0</V><V i=\"18\">100.0</V><V i=\"227\">0.0</V><V i=\"19\">24.0</V></D>";
        System.out.println(xml);
        Iterator<Element> iter = Tools.XML2iter(xml);
        while(iter.hasNext()){
            Element element= iter.next();
            List<Attribute> attributes = element.attributes();
            System.out.println(attributes.get(0).getValue()+" "+element.getStringValue());
        }
    }
    @Test
    public void isExits(){
        System.out.println(equipmentdataMapper.isExit("rdasdata202105"));
    }

    @Test
    public void testGetData(){
        System.out.println(equipmentdataMapper.getData("rdasdata202106","2010-01-01 10:10:10.313","2030-01-01 10:10:10.313","77777777"));
    }
    @Test
    public void testGetMaps(){
        System.out.println(equEquipmentMapper.getMaps("thy"));
    }
    @Test
    public void getEquDataType(){
        System.out.println(equDatatypeMapper.getEquDataType("4000010"));
    }

    @Test
    public void getAccumulateData(){
        System.out.println(equEquipmentMapper.getAccumulateData("rdasdata202111","2021-01-05","2022-01-05","8944"));
    }
}
