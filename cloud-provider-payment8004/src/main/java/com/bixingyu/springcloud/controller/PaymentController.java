package com.bixingyu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author BiXingyu
 * @create 2022-12-24 14:48
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;


    @RequestMapping(value = "/payment/zk")
    public String paymentzk(){
        return "springcloud with zookeeper: " + serverPort + "\t" + UUID.randomUUID().toString();
    }

}
