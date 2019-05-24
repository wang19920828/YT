package org.fh.general.ecom.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("org.fh.general.ecom.order.dao")  //配置mapper扫描
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@EnableAutoConfiguration(exclude={})
public class GeneralEcomOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneralEcomOrderApplication.class, args);
    }
}
