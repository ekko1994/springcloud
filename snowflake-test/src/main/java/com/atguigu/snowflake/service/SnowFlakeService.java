package com.atguigu.snowflake.service;

import com.atguigu.snowflake.component.SnowFlakeComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhanghao
 * @date 2021/3/28 19:57
 */
@Service
public class SnowFlakeService {

    @Autowired
    private SnowFlakeComponent snowFlakeComponent;

    public String getIDBySnowFlake() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 20; i++) {
            threadPool.submit(() -> {
                System.out.println(snowFlakeComponent.snowflakeId());
            });
        }
        return "hello snowflake";
    }
}
