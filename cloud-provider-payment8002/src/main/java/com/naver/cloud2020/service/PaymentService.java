package com.naver.cloud2020.service;

import com.naver.cloud2020.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

	public int create(Payment payment);

	public Payment getPaymentById(@Param("id") Long id);
}
