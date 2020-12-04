package com.naver.cloud2020.service;

import com.naver.cloud2020.entity.CommonResult;
import com.naver.cloud2020.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class)
public interface PaymentService {
	@GetMapping(value = "/paymentSQL/{id}")
	CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
