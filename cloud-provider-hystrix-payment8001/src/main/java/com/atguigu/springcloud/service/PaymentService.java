package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author zhanghao
 * @date 2021/3/21 22:15
 */
@Service
public class PaymentService {

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
