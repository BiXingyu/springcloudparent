package com.bixingyu.springcloud.controller;

import com.bixingyu.springcloud.entities.CommonResult;
import com.bixingyu.springcloud.entities.Payment;
import com.bixingyu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author BiXingyu
 * @create 2022-12-21 22:53
 */
@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;


    @Value("${server.port}")
    private String serverPort;


    @PostMapping(value = "/payment")
    public CommonResult create(@RequestBody Payment payment) {
        log.info("获取到的payment：{}" ,payment);
        int result = paymentService.create(payment);
        log.info("*****插入结果：{}" , result);
        CommonResult commonResult = new CommonResult();
        if(result > 0){
            commonResult.setCode(200);
            commonResult.setMessage("插入成功,port:" + serverPort);
            commonResult.setData(result);
        }else{
            commonResult.setCode(400);
            commonResult.setMessage("插入失败");
        }
        return commonResult;

    }
    @GetMapping(value = "/payment/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {


        Payment payment = paymentService.getPaymentById(id);
        CommonResult<Payment> commonResult = new CommonResult<>();
        commonResult.setCode(200);
        if(payment != null){

            commonResult.setMessage("查询成功,port:" + serverPort);
            commonResult.setData(payment);
        }else{
            commonResult.setMessage("未查询到数据,查询id为" + id);

        }
        return commonResult;
    }


    @GetMapping(value = "/discovery")
    public DiscoveryClient getDiscoveryClient(){
        log.info("************Discovery:{}" , discoveryClient);

        List<String> services = discoveryClient.getServices();
        for(String service : services){
            List<ServiceInstance> instances = discoveryClient.getInstances(service.toString());
            for(ServiceInstance serviceInstance : instances){
                log.info("serviceInstance:{},{},{},{},{},{},{}" ,  serviceInstance.getInstanceId(),serviceInstance.getHost(),serviceInstance.getPort(),serviceInstance.getServiceId(),serviceInstance.getMetadata(),serviceInstance.getScheme(),serviceInstance.getUri());
            }
            log.info("service:{}" ,  service);

        };
        return discoveryClient;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
