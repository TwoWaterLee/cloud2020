package com.naver.cloud2020.controller;

import com.naver.cloud2020.entity.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GateWayController {

	@GetMapping("/gateway/hello")
	private CommonResult getHello() {
		return new CommonResult(200, "hello");
	}
}
