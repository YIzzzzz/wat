package com.jan.wat;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jan.wat.EquServer.config.GlobalParameter;
import com.jan.wat.EquServer.helper.DateTime;
import com.jan.wat.mapper.EquDatatypeMapper;
import com.jan.wat.mapper.EquServerMapper;
import com.jan.wat.mapper.EquipmentdataMapper;
import com.jan.wat.pojo.EquCommand;
import com.jan.wat.pojo.EquDatatype;
import com.jan.wat.pojo.Equipmentdata;
import com.jan.wat.service.IEquCommandService;
import com.jan.wat.service.IEquDatatypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    void contextLoads() {
    }

    @Test
    void insertTest(){
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

    @Autowired
    GlobalParameter globalParameter;

    @Test
    public void testYml(){
    }

}
