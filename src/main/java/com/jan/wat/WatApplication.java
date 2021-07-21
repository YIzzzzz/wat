package com.jan.wat;

import com.jan.wat.EquServer.udpNetty.BootNettyUdpServer;
import com.jan.wat.mapper.EquEquipmentMapper;
import com.jan.wat.pojo.EquEquipment;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
//@Configurable("com.jan.wat.config.RedisConfig")

@SpringBootApplication
@MapperScan("com.jan.wat.mapper")
@EnableScheduling
@EnableAsync
public class WatApplication implements CommandLineRunner {

    @Autowired
    RedisTemplate  redisTemplate;

    @Autowired
    BootNettyUdpServer bootNettyUdpServer;

    @Autowired
    EquEquipmentMapper equEquipmentMapper;
//    @PostConstruct
    @Async("doSomethingExecutor")
    public void insertDB(){
        while (true) {
            EquEquipment equList = (EquEquipment) redisTemplate.opsForList().rightPop("equList");
            if(null != equList){
                equEquipmentMapper.insert(equList);
            }
        }
    }

//    @Async
//    @PostConstruct
//    public void UdpServer(){
//        /**
//         * 使用异步注解方式启动netty udp服务端服务
//         */
//        bootNettyUdpServer.bind(9999);
//
//    }

    @Async
    @Override
    public void run(String... args) throws Exception {
        bootNettyUdpServer.bind(9999,7003,8003);
    }

    public static void main(String[] args) {
        SpringApplication.run(WatApplication.class, args);
//        SpringApplication app = new SpringApplication(WatApplication.class);
//        app.run(args);
    }
}
