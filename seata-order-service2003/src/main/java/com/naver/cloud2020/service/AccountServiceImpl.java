package com.naver.cloud2020.service;

import com.naver.cloud2020.dao.AccountDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

	@Resource
	private AccountDao accountDao;

	@Override
	public void decrease(Long userId, BigDecimal money) {
		log.info("--> 扣减余额 start");
		accountDao.decrease(userId, money);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("--> 扣减余额 end");
	}
}
