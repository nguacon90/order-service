package vn.com.vndirect.stock;

import java.util.UUID;

import vn.com.vndirect.model.Order;

public class OrderServiceSenderImpl implements OrderServiceSender{

	@Override
	public String placeOrder(Order order) {
		return UUID.randomUUID().toString();
	}

}
