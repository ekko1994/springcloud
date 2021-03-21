package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhanghao
 * @date 2021/3/20 17:31
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info(">>>>>service: {}", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(">>>>instance: serviceId = {}, host = {}, port = {}, uri = {}", instance.getServiceId(),
                    instance.getHost(), instance.getPort(), instance.getUri());
        }
        return this.discoveryClient;
    }

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment) {
        int result = paymentService.create(payment);
        log.info("*******插入结果*******" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,O(∩_∩)O哈哈~,端口号是:" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*******查询结果*******" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,端口号是:" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录,查询ID:" + id, null);
        }
    }
}
