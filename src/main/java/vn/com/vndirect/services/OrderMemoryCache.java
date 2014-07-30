package vn.com.vndirect.services;

import java.util.List;

import vn.com.vndirect.model.Order;

public interface OrderMemoryCache {

	void put(Order order);

	int size();

	List<Order> getTopOrder(int number);

	List<String> getTopAccount(int number);

}
