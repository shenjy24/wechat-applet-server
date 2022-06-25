package com.jonas.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2018/08/06
 */
@Configuration
@MapperScan("com.jonas.data.mysql.mapper")
public class MyBatisPlusConfig {

}
