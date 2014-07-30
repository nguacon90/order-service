package vn.com.vndirect.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import vn.com.vndirect.comparator.OrderValueComparator;
import vn.com.vndirect.comparator.PortfolioComparator;
import vn.com.vndirect.model.Order;
import vn.com.vndirect.model.Porfolio;

public class OrderMemoryCacheImpl implements OrderMemoryCache {

	private List<Order> orderList;
	private ConcurrentMap<String, Porfolio> porfolioMap;

	public OrderMemoryCacheImpl() {
		this.orderList = Collections.synchronizedList(new ArrayList<Order>());
		this.porfolioMap = new ConcurrentHashMap<String, Porfolio>();
	}

	@Override
	public void put(Order order) {
		orderList.add(order);
		putToPorfolioMap(order);
	}

	@Override
	public int size() {
		return this.orderList.size();
	}

	@Override
	public List<Order> getTopOrder(int number) {
		Comparator<Order> comprator = new OrderValueComparator();
		Collections.sort(this.orderList, comprator);

		if (number > size()) {
			return this.orderList;
		}

		return this.orderList.subList(0, number);
	}

	@Override
	public List<String> getTopAccount(int number) {
		List<String> result = new ArrayList<String>();
		List<Porfolio> top = getTopPorfolio(number);
		for (Porfolio porfolio : top) {
			result.add(porfolio.getAccount());
		}
		return result;
	}

	private void putToPorfolioMap(Order order) {
		String account = order.getAccount();
		double currOrderValue = order.getPrice() * order.getQuantity();
		Porfolio porfolio = new Porfolio(account, currOrderValue);
		if (porfolioMap.containsKey(account)) {
			double orderValue = porfolioMap.get(account).getTotalOrderValue();
			porfolio.setTotalOrderValue(currOrderValue + orderValue);
		}
		
		porfolioMap.put(account, porfolio);
	}

	private List<Porfolio> getTopPorfolio(int number) {
		Comparator<Porfolio> comparator = new PortfolioComparator();
		List<Porfolio> porfolios = new ArrayList<Porfolio>(porfolioMap.values());
		Collections.sort(porfolios, comparator);
		
		if (number > porfolios.size()) {
			return porfolios;
		}
		
		return porfolios.subList(0, number);
	}

}
