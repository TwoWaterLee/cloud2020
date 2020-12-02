package com.naver.cloud2020.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
	ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
