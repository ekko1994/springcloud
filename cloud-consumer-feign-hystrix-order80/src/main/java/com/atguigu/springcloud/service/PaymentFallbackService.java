package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author zhanghao
 * @date 2021/3/22 13:44
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{

    @Override
    public String paymentInfo_OK(Integer id) {
        return "---->PaymentFallbackService paymentInfo_OK: fallback()";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "---->PaymentFallbackService paymentInfo_TimeOut: fallback()";
    }
}
