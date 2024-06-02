package com.wizian.web.config;


import org.springframework.context.annotation.Configuration;
import org.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackages = "com.wizian.web.mapper")
public class MyBatisConfig {
    // Additional configuration if needed
}