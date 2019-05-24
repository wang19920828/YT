package org.fh.general.ecom.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GeneralEcomEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneralEcomEurekaApplication.class, args);
    }
}
