package com.bixingyu.springcloud.controller;

import com.bixingyu.springcloud.service.PaymentFeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author BiXingyu
 * @create 2022-12-25 22:55
 */
@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "defaultFallback" , commandProperties = {
//        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" , value = "1000")
//})
public class OrderFeignHystrixController {
    @Resource
    private PaymentFeignHystrixService paymentFeignHystrixService;


    @GetMapping("/customer/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentFeignHystrixService.paymentInfo_OK(id);
        log.info("消费者调用paymentInfo_OK API 返回{}" , result);
        return  result;
    }
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler" , commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" , value = "1500")
    })
    @GetMapping("/customer/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
//        System.out.println(10 / 0);
        String result = paymentFeignHystrixService.paymentInfo_TimeOut(id);
        log.info("消费者调用paymentInfo_OK API 返回{}" , result);
        return  result;
    }


    public String paymentInfo_TimeOutHandler(@PathVariable("id") Integer id) {
        log.info("消费者调用paymentInfo_OK API 失败 /(ㄒoㄒ)/~~，id是：{}" , id);
        return  "消费者调用paymentInfo_OK API 失败 /(ㄒoㄒ)/~~，id是： " + id;
    }

    @GetMapping("/customer/hystrix/error/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_ErrorHandler" , commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" , value = "1000")
//    })
//    @HystrixCommand
    public String paymentInfo_Error(@PathVariable("id") Integer id){
        return paymentFeignHystrixService.paymentInfo_Error(id);
    }
    public String paymentInfo_ErrorHandler(Integer id){
        return "消费者端口80报错,线程：" + Thread.currentThread().getName() + "\t/(ㄒoㄒ)/~~";
    }

    public String defaultFallback(){
        return "我是一个平淡无奇de默认Fallback";
    }
}
