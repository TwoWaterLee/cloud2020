package com.naver.cloud2020.service;

import com.naver.cloud2020.dao.OrderDao;
import com.naver.cloud2020.domain.Order;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderDao orderDao;

	@Resource
	private StorageService storageService;

	@Resource
	private AccountService accountService;

	@Override
	@GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
	public void create(Order order) {
		log.info("--> 开始新建订单");
		orderDao.create(order);

		log.info("-->扣减库存 start");
		storageService.decrease(order.getProductId(), order.getCount());
		log.info("-->扣减库存 end");

		log.info("-->扣减账户 start");
		accountService.decrease(order.getUserId(), order.getMoney());
		log.info("-->扣减账户 end");

		log.info("-->修改订单 start");
		orderDao.update(order.getUserId(), 0);
		log.info("-->修改订单 end");

		log.info("--> 完成新建订单");
	}
}
