package com.naver.cloud2020.service;

import com.naver.cloud2020.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
	@GetMapping(value = "/payment/get/{id}")
	CommonResult getPaymentById(@PathVariable("id") Long id);

	@GetMapping(value = "/payment/feign/timeout")
	String paymentFeignTimeout();
}
