package org.fh.general.ecom.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("org.fh.general.ecom.product.dao")  //配置mapper扫描
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling //定时任务
@EnableAutoConfiguration(exclude={})
public class GeneralEcomProductApplication {

    public static void main(String[] args) {

        SpringApplication.run(GeneralEcomProductApplication.class, args);
    }
}
