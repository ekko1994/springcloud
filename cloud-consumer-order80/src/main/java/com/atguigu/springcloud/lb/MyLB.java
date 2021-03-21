package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhanghao
 * @date 2021/3/21 19:12
 */
@Component
public class MyLB implements LoadBalancer {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("*****第几次请求next: " + next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> instances) {
        int index = getAndIncrement() % instances.size();
        return instances.get(index);
    }
}
