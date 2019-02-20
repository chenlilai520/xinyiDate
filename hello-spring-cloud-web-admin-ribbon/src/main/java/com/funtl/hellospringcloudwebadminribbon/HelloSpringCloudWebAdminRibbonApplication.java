package com.funtl.hellospringcloudwebadminribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
public class HelloSpringCloudWebAdminRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringCloudWebAdminRibbonApplication.class, args);
    }

}
