package com.bixingyu.springcloud.controller;

import com.bixingyu.springcloud.entities.CommonResult;
import com.bixingyu.springcloud.entities.Payment;
import com.bixingyu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author BiXingyu
 * @create 2022-12-21 22:53
 */
@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;
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
}
