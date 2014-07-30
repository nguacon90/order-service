package vn.com.vndirect.services;

import java.util.List;

import vn.com.vndirect.model.Order;

public class StatisticServiceImpl implements StatisticService {

	private OrderMemoryCache orderMemoryCache;
	
	public StatisticServiceImpl(OrderMemoryCache orderMemoryCache) {
		this.orderMemoryCache = orderMemoryCache;
	}
	
	@Override
	public List<Order> getTopOrder(int topOrderNumber) {
		return orderMemoryCache.getTopOrder(topOrderNumber);
	}

	@Override
	public List<String> getTopAccount(int topAccountNumber) {
		return orderMemoryCache.getTopAccount(topAccountNumber);
	}

}
