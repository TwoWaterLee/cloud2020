package com.naver.cloud2020.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

	public String paymentInfo_OK(Integer id) {
		return "线程池: " + Thread.currentThread().getName() + " paymentInfo_OK,id: " + id + "\t" + "哈哈哈";
	}

//	@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")
//	})
	public String paymentInfo_TimeOut(Integer id) {

//		int age = 10 / 0;
		int timeNumber = 2;
		try {
			TimeUnit.SECONDS.sleep(timeNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "线程池: " + Thread.currentThread().getName() + " paymentInfo_TimeOut,id: " + id + "\t" + "呜呜呜" + " 耗时: " + timeNumber;
	}

	public String paymentInfo_TimeOutHandler(Integer id) {
		return "线程池: " + Thread.currentThread().getName() + " 系统繁忙，请稍后再试, id: " + id + "\t" + "哭了";
	}

	@HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
	})
	public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
		if (id < 0) {
			throw new RuntimeException("*** id 不能为负数");
		}
		String serialNumber = IdUtil.simpleUUID();

		return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
	}

	public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
		return "id 不能为负数，请稍后再试，id: " + id;
	}
}
