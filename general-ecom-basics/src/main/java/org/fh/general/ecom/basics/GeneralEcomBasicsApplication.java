package org.fh.general.ecom.basics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@MapperScan("org.fh.general.ecom.basics.dao")//配置mapper扫描
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Configuration
@PropertySource("classpath:config.properties")
@EnableAutoConfiguration(exclude={})
public class GeneralEcomBasicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneralEcomBasicsApplication.class, args);
    }
}
