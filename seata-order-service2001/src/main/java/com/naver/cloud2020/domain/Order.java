package com.naver.cloud2020.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	private Long id;
	private Long userId;
	private Long productId;
	private Integer count;
	private BigDecimal money;
	private Integer status; // 0:creating，1:finish
}
