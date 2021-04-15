package com.zdc.sharding.java;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//@ComponentScan("com.zdc.sharding.java")
@MapperScan(basePackages = "com.zdc.sharding.java.mapper",sqlSessionFactoryRef = "sqlSessionFactory")
public class App 
{
    public static void main( String[] args )
    {

        SpringApplication.run(App.class,args);
    }
}
