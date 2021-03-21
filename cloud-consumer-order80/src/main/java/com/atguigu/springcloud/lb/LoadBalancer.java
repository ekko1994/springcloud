package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author zhanghao
 * @date 2021/3/21 19:09
 */
public interface LoadBalancer {

    /**
     * 根据算法得到一个ServiceInstance实例
     * @param instances
     * @return org.springframework.cloud.client.ServiceInstance
     */
    public ServiceInstance instance(List<ServiceInstance> instances);
}
