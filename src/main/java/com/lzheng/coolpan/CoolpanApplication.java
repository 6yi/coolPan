package com.lzheng.coolpan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.lzheng.coolpan.dao")
public class CoolpanApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoolpanApplication.class, args);
    }

}
