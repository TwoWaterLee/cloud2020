package com.naver.cloud2020.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.naver.cloud2020.entity.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class CustomerBlockHandler {
	public static CommonResult handleException(BlockException exception) {
		return new CommonResult(2020, "自定义的限流处理信息...CustomerBlockHandler");
	}

}
