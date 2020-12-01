package com.naver.cloud2020.service.impl;

import com.naver.cloud2020.dao.PaymentDao;
import com.naver.cloud2020.entity.Payment;
import com.naver.cloud2020.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Resource
	private PaymentDao paymentDao;

	public int create(Payment payment) {
		return paymentDao.create(payment);
	}

	public Payment getPaymentById(Long id) {
		return paymentDao.getPaymentById(id);
	}

}
