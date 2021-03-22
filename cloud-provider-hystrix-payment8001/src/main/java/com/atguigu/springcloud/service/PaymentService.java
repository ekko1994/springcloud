package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author zhanghao
 * @date 2021/3/21 22:15
 */
@Service
public class PaymentService {

    /**
     * 测试服务熔断
     * @see HystrixCommandProperties
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") //失败率
    })
    public String paymentCircuitBreaker(@PathVariable Integer id){
        if(id < 0){
            throw new RuntimeException("******id不能是负数******");
        }
        String serailNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "调用成功,流水号:" + serailNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id不能是负数,请稍候再试(┬＿┬),id: " + id;
    }

    /**
     * 正常访问
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_OK,id:" + id + "\t" + "o(∩_∩)o 哈哈";
    }

    /**
     * 超时访问
     */
    @HystrixCommand(fallbackMethod = "payment_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        int num = 5;
        // 暂停3秒钟
        try {
            TimeUnit.SECONDS.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "线程池:    " + Thread.currentThread().getName() + " ===> paymentInfo_TimeOut, id: " + id + "\t" + "耗时: " + num +
                "秒";
    }

    public String payment_TimeOutHandler(Integer id){
        return "线程池:    " + Thread.currentThread().getName() + " ===> paymentInfo_TimeOut, id: " + id + "\t" + "o" +
                "(╥﹏╥)o ";
    }
}
