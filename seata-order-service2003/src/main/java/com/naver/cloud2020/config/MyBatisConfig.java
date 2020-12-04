package com.naver.cloud2020.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.naver.cloud2020.dao"})
public class MyBatisConfig {
}
