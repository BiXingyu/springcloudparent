package com.bixingyu.springcloud.service.impl;

import com.bixingyu.springcloud.dao.PaymentDao;
import com.bixingyu.springcloud.entities.Payment;
import com.bixingyu.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author BiXingyu
 * @create 2022-12-21 22:49
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
