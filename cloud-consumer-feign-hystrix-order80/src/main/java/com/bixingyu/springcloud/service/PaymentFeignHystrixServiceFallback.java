package com.bixingyu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author BiXingyu
 * @create 2022-12-29 22:23
 */
@Component
public class PaymentFeignHystrixServiceFallback implements PaymentFeignHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFeignHystrixServiceFallback  paymentInfo_TimeOut ... /(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFeignHystrixServiceFallback  paymentInfo_TimeOut ... /(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_Error(Integer id) {
        return "PaymentFeignHystrixServiceFallback  paymentInfo_Error ... /(ㄒoㄒ)/~~";
    }
}
