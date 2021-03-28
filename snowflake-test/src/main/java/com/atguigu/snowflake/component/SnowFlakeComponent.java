package com.atguigu.snowflake.component;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhanghao
 * @date 2021/3/28 19:57
 */
@Component
@Slf4j
public class SnowFlakeComponent {

    private long workId = 0;
    private long datacenterId = 1;
    private Snowflake snowflake = IdUtil.getSnowflake(workId, datacenterId);

    @PostConstruct
    public void init() {
        try {
            workId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workId: {}", workId);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("当前机器的workId获取失败", e);
            workId = NetUtil.getLocalhostStr().hashCode();
        }
    }

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workId, long datacenterId) {
        Snowflake snowflake = IdUtil.getSnowflake(workId, datacenterId);
        return snowflake.nextId();
    }
}
