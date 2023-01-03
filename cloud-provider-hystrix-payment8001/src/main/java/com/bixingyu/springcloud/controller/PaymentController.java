package com.bixingyu.springcloud.controller;

import com.bixingyu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author BiXingyu
 * @create 2022-12-25 21:09
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_OK(id);
        log.info("******result: " + result);
        return result;
    }
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("******result: " + result);
        return result;
    }


    @GetMapping("/payment/hystrix/error/{id}")
    public String paymentInfo_Error(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_Error(id);
    }

    @GetMapping("/payment/hystrix/circuitbreaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id")  Integer id){
        return paymentService.paymentCircuitBreaker(id);
    }


}
