package vn.com.vndirect.services;

import java.util.List;

import vn.com.vndirect.model.Order;

public interface StatisticService {

	List<Order> getTopOrder(int topOrderNumber);
	List<String> getTopAccount(int topAccountNumber);
}
