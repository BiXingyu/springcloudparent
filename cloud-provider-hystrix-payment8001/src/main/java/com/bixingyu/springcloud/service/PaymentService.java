package com.bixingyu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author BiXingyu
 * @create 2022-12-25 21:05
 */
@Service
public class PaymentService {

    /**
     * 正常访问，肯定OK
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池：" + Thread.currentThread().getName() +  " paymentInfo_OK,id: " + id + "\t啊哈哈";
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler" , commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" , value = "2000")
    })
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber = 3;
        //int age = 10 / 0
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() +  "\tpaymentInfo_TimeOut,id: " + String.valueOf(id) + "\t啊哈哈 耗时" + timeNumber + "秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池：" + Thread.currentThread().getName() +  "\tpaymentInfo_TimeOutHandler,id: " + String.valueOf(id) + "\t/(ㄒoㄒ)/~~";
    }

//    @HystrixCommand(fallbackMethod = "paymentInfo_Error_do", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")
//    })
    public String paymentInfo_Error(Integer id){
        if(id < 0){
            throw new RuntimeException("id不能小于0");
        }else{
            return "线程池：" + Thread.currentThread().getName() +  "\tpaymentInfo_TimeOutHandler,id: " + String.valueOf(id) + "O(∩_∩)O";
        }
    }

    public String paymentInfo_Error_do(Integer id){
        return "这个id " + id + "\t不能小于0";
    }



    //******服务熔断实操
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback" , commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled" , value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold" , value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" , value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" , value = "60")
    })
    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("*****id不能为负数");
        }else{
            String serialNumber = IdUtil.simpleUUID();
            return Thread.currentThread().getName() + "\t调用成功，流水号：\t" + serialNumber;
        }
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "调用失败！";
    }

}
