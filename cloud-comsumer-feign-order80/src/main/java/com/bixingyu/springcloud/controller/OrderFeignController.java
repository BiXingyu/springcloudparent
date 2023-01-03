package com.bixingyu.springcloud.controller;

import com.bixingyu.springcloud.entities.CommonResult;
import com.bixingyu.springcloud.entities.Payment;
import com.bixingyu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author BiXingyu
 * @create 2022-12-25 19:15
 */
@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")  Long id){
        return paymentFeignService.getPaymentById(id);
    }
    @GetMapping(value = "/comsumer/feign/timeout")
    public String paymentFeignTimeout(){
        //openfeign-ribbon,客户端一般默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
