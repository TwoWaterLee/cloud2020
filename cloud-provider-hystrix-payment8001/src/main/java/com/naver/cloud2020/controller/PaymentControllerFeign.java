package com.naver.cloud2020.controller;

import com.naver.cloud2020.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class PaymentControllerFeign {

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/consumer/payment/{id}")
	public String paymentInfo(@PathVariable("id") Integer id) {
		return paymentService.paymentInfo_OK(id);
	}

	@GetMapping("/consumer/paymentTimeOut/{id}")
	@HystrixCommand
	public String paymentTimeOut(@PathVariable("id") Integer id) {
		return paymentService.paymentInfo_TimeOut(id);
	}

	public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
		return "对方繁忙或者已经down机,请稍后再试!";
	}

}
