package com.jan.wat.controller;

import com.jan.wat.mapper.SysUserMapper;
import com.jan.wat.pojo.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysUserControllerTest {
    @Autowired
    private SysUserMapper userMapper;

    @Test
    public void testselect(){
        List<SysUser> userlist = userMapper.selectList(null);
        userlist.forEach(System.out::println);

    }



}