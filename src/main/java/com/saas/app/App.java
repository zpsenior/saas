package com.saas.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages={"com.saas.*", "com.saas.wpay", "com.saas.aliyun", "com.saas.training"})
@MapperScan(basePackages = {"com.saas.auth.dao", "com.saas.payment.dao", "com.saas.goods.dao", "com.saas.training.dao"})
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
