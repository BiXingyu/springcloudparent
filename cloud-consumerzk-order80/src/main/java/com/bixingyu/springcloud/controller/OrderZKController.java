package com.bixingyu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author BiXingyu
 * @create 2022-12-24 15:33
 */
@RestController
@Slf4j
public class OrderZKController {

    public static final String INVOKE_URL = "http://cloud-provider-payment/";
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/payment/zk" , method = RequestMethod.GET)
    public String paymentInfo(){
        String template = restTemplate.getForObject(INVOKE_URL + "payment/zk", String.class);
        return  template;

    }
}
