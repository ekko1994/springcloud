package com.atguigu.snowflake.controller;

import com.atguigu.snowflake.service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghao
 * @date 2021/3/28 19:56
 */
@RestController
public class SnowFlakeController {

    @Autowired
    private SnowFlakeService snowFlakeService;

    @GetMapping("/snowflake")
    public String snowFlake(){

        return snowFlakeService.getIDBySnowFlake();
    }
}
