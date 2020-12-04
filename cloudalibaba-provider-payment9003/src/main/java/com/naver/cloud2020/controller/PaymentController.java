package com.naver.cloud2020.controller;

import com.naver.cloud2020.entity.CommonResult;
import com.naver.cloud2020.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

	@Value("${server.port}")
	private String port;

	public static HashMap<Long, Payment> hashMap = new HashMap<>();
	static {
		hashMap.put(1L, new Payment(1L, "1234567891"));
		hashMap.put(2L, new Payment(2L, "1234567892"));
		hashMap.put(3L, new Payment(3L, "1234567893"));
	}

	@GetMapping(value = "/paymentSQL/{id}")
	public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
		Payment payment = hashMap.get(id);
		CommonResult<Payment> result = new CommonResult<>(200, "from mysql, port: " + port, payment);
		return result;
	}
}
