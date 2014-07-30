package vn.com.vndirect.comparator;

import java.util.Comparator;

import vn.com.vndirect.model.Order;

public class OrderValueComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		double order1Value = o1.getPrice() * o1.getQuantity();
		double order2Value = o2.getPrice() * o2.getQuantity();
		if(order2Value > order1Value) return 1;
		if(order2Value < order1Value) return -1;
		return 0;
	}

}
