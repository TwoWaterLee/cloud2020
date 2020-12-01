package com.naver.cloud2020.controller;

import com.naver.cloud2020.entity.CommonResult;
import com.naver.cloud2020.entity.Payment;
import com.naver.cloud2020.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

	@Resource
	private PaymentService paymentService;

	@PostMapping(value = "/payment/create")
	public CommonResult create(Payment payment) {
		int result = paymentService.create(payment);
		log.info("***插入结果: " + result);
		if (result > 0) {
			return new CommonResult(200, "create success", result);
		} else {
			return new CommonResult(444, "create fail", null);
		}
	}

	@GetMapping(value = "/payment/get/{id}")
	public CommonResult getPaymentById(@PathVariable("id") Long id) {
		Payment payment = paymentService.getPaymentById(id);
		log.info("***查询结果: " + payment);
		if (payment != null) {
			return new CommonResult(200, "query success", payment);
		} else {
			return new CommonResult(444, "query fail, ID: " + id, null);
		}
	}
}
