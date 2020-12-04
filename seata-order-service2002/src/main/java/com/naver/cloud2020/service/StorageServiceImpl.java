package com.naver.cloud2020.service;

import com.naver.cloud2020.dao.StorageDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

	@Resource
	private StorageDao storageDao;

	@Override
	public void decrease(Long productId, Integer count) {
		log.info("--> 扣减库存 start");
		storageDao.decrease(productId, count);
		log.info("--> 扣减库存 end");
	}
}
