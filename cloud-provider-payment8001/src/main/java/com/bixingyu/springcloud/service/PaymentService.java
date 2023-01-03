package com.bixingyu.springcloud.service;

import com.bixingyu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author BiXingyu
 * @create 2022-12-21 22:48
 */

public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(Long id);


}
