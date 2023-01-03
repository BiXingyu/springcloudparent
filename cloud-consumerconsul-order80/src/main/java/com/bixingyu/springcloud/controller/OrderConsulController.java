package com.bixingyu.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author BiXingyu
 * @create 2022-12-24 19:49
 */
@RestController
public class OrderConsulController {

    @Resource
    private RestTemplate restTemplate;

    public static final String INVOKE_URL = "http://consul-provider-payment/";

    @RequestMapping(value = "/consumer/payment/consul" , method = RequestMethod.GET)
    public String paymentInfo(){
        String template = restTemplate.getForObject(INVOKE_URL + "payment/consul", String.class);
        return  template;

    }

}
