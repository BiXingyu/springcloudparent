package com.bixingyu.springcloud.controller;

import com.bixingyu.springcloud.entities.CommonResult;
import com.bixingyu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author BiXingyu
 * @create 2022-12-22 0:07
 */
@RestController
@Slf4j
public class OrderController {
    @Resource
    RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    private static AtomicInteger num = new AtomicInteger(0);
    private Integer server;
    public static final String URL1 = "http://localhost:8001/";
    public static final String URL2 = "http://localhost:8002/";
    private static List<ServiceInstance> urlList = null;
//    static{
//        urlList.add(URL1);
//        urlList.add(URL2);
//    }

    public static final String URL = "http://CLOUD-PAYMENT-SERVICE/";


    @GetMapping("/payment")
    public CommonResult create(Payment payment){
        CommonResult commonResult = restTemplate.postForObject(URL + "payment/" ,payment, CommonResult.class);
        return commonResult;
    }

    @GetMapping("/payment/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id)  {
        CommonResult commonResult = restTemplate.getForObject(URL + "payment/" + id, CommonResult.class);
        return commonResult;
    }
    @GetMapping("/payment/entity/{id}")
    public CommonResult getPaymentById2(@PathVariable("id") Long id)  {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(URL + "payment/" + id, CommonResult.class);
        log.info("entity的响应码值：{} , 响应码：{}, 头信息：{}" , entity.getStatusCodeValue() , entity.getStatusCode(),entity.getHeaders());
        CommonResult commonResult = entity.getBody();
        return commonResult;
    }
    @GetMapping("/payment/order/{id}")
    public CommonResult getPaymentById3(@PathVariable("id") Long id)  {
        for(;;) {
            Integer now = num.get();
            if(num.compareAndSet(now , now + 1)){
                urlList = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

                server = now % urlList.size();
                break;
            }
        }
        ServiceInstance serviceInstance = urlList.get(server);
        CommonResult commonResult = restTemplate.getForObject( serviceInstance.getUri() + "payment/" + id, CommonResult.class);
        return commonResult;
    }


}
