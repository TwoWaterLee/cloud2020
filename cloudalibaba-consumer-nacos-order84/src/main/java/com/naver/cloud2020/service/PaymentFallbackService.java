package com.naver.cloud2020.service;

import com.naver.cloud2020.entity.CommonResult;
import com.naver.cloud2020.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentFallbackService implements PaymentService {

	@Override
	public CommonResult<Payment> paymentSQL(Long id) {
		return new CommonResult<>(444, "服务降级返回,--- PaymentFallbackService ", new Payment(id, "errorSerial"));
	}
}
