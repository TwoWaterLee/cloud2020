package com.naver.cloud2020.dao;

import com.naver.cloud2020.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {
	int create(Payment payment);

	Payment getPaymentById(Long id);
}
