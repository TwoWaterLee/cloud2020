package com.naver.cloud2020.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.naver.cloud2020.entity.CommonResult;
import com.naver.cloud2020.entity.Payment;
import com.naver.cloud2020.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {

	public static final String SERVICE_URL = "http://nacos-payment-provider";

	@Resource
	private RestTemplate restTemplate;

	@Resource
	private PaymentService paymentService;

	@RequestMapping("/consumer/fallback/{id}")
//	@SentinelResource(value = "fallback") // 没有配置
//	@SentinelResource(value = "fallback", fallback = "handlerFallback") // 只负责业务异常
//	@SentinelResource(value = "fallback", blockHandler = "blockHandler") // 只负责sentinel控制台配置违规
	@SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler",
	exceptionsToIgnore = {IllegalArgumentException.class})
	public CommonResult<Payment> fallback(@PathVariable Long id) {
		CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
		if (id == 4) {
			throw new IllegalArgumentException("非法参数异常...");
		} else if (result.getData() == null) {
			throw new NullPointerException("空指针异常...");
		}
		return result;
	}

	public CommonResult handlerFallback(@PathVariable Long id, Throwable e) {
		Payment payment = new Payment(id, "null");
		return new CommonResult(444, "兜底异常处理器,exception内容: " + e.getMessage(), payment);
	}

	public CommonResult blockHandler(@PathVariable Long id, BlockException blockException) {
		Payment payment = new Payment(id, "null");
		return new CommonResult(445, "blockHandler-sentinel限流，exception内容: " + blockException.getMessage(), payment);
	}

	@GetMapping(value = "/consumer/paymentSQL/{id}")
	public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
		return paymentService.paymentSQL(id);
	}
}
