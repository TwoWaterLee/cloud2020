package com.naver.cloud2020.controller;

import com.naver.cloud2020.domain.CommonResult;
import com.naver.cloud2020.domain.Order;
import com.naver.cloud2020.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

	@Resource
	private OrderService orderService;

	@GetMapping("/order/create")
	public CommonResult create(Order order) {
		orderService.create(order);
		return new CommonResult(200, "订单创建成功");
	}
}
