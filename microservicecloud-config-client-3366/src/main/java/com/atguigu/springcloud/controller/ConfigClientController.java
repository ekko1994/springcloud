package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghao
 * @date 2021/3/23 10:30
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping("/config")
    public String getConfig() {
        String str = "applicationName: " + applicationName;
        System.out.println("******str: " + str);
        return "applicationName: " + applicationName;
    }
}
