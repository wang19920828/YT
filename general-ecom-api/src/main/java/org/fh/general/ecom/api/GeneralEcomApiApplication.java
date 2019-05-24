package org.fh.general.ecom.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableZuulProxy
@EnableAutoConfiguration(exclude={})
@EnableDiscoveryClient
@EnableFeignClients
public class GeneralEcomApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneralEcomApiApplication.class, args);
    }
}
