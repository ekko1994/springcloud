package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhanghao
 * @date 2021/3/23 10:28
 */
@SpringBootApplication
@EnableEurekaClient
public class ClientConfigMain3366 {

    public static void main(String[] args) {
        SpringApplication.run(ClientConfigMain3366.class, args);
    }
}
