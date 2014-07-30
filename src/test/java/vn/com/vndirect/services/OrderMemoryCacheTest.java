package vn.com.vndirect.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vn.com.vndirect.model.Order;

public class OrderMemoryCacheTest {

	private OrderMemoryCache orderMemoryCache;
	
	@Before
	public void setUp(){
		orderMemoryCache = new OrderMemoryCacheImpl();
	}
	
	@Test
	public void testSaveOrderToCache() {
		Order order = generateVNDOrderWithPrice("0001", 12.3, 200);
		orderMemoryCache.put(order);
		assertTrue(orderMemoryCache.size() == 1);
	}
	
	@Test
	public void testGetTopBiggestOrder(){
		Order order1 = generateVNDOrderWithPrice("0001", 12.5, 2000);
		Order order2 = generateVNDOrderWithPrice("0001", 10, 1000);
		Order order3 = generateVNDOrderWithPrice("0001", 13, 1200);
		Order order4 = generateVNDOrderWithPrice("0001", 12, 100);
		orderMemoryCache.put(order1);
		orderMemoryCache.put(order2);
		orderMemoryCache.put(order3);
		orderMemoryCache.put(order4);
		
		List<Order> top = orderMemoryCache.getTopOrder(5);
		
		assertEquals(order1.getQuantity(), top.get(0).getQuantity(), 0);
		assertEquals(order3.getQuantity(), top.get(1).getQuantity(), 0);
		assertEquals(order2.getQuantity(), top.get(2).getQuantity(), 0);
		assertEquals(order4.getQuantity(), top.get(3).getQuantity(), 0);
		
	}
	
	
	@Test
	public void testGetTopAccountWhenHasnotTransaction(){
		List<String> top = orderMemoryCache.getTopAccount(5);
		assertEquals(0, top.size());
	}
	
	@Test
	public void testGetTopAccountWithNumberOfOrderIsSmallerThan5(){
		Order order1 = generateVNDOrderWithPrice("0001", 5,  2000);
		Order order2 = generateVNDOrderWithPrice("0001", 10, 1000);
		Order order3 = generateVNDOrderWithPrice("0002", 13, 1200);
		Order order4 = generateVNDOrderWithPrice("0003", 12, 100);
		orderMemoryCache.put(order1);
		orderMemoryCache.put(order2);
		orderMemoryCache.put(order3);
		orderMemoryCache.put(order4);
		
		List<String> top = orderMemoryCache.getTopAccount(5);
		assertEquals("0001", top.get(0));
		assertEquals("0002", top.get(1));
		assertEquals("0003", top.get(2));
	}
	
	@Test
	public void testGetTopAccountWithNumberOfOrderIsLargerThan5(){
		Order order1 = generateVNDOrderWithPrice("0001", 5,  2000);
		Order order2 = generateVNDOrderWithPrice("0001", 10, 1000); //20.000
		Order order3 = generateVNDOrderWithPrice("0002", 13, 1200); //15.600
		Order order4 = generateVNDOrderWithPrice("0003", 15, 10000);//150.000
		Order order5 = generateVNDOrderWithPrice("0013", 12.5, 2500);//31.250
		Order order6 = generateVNDOrderWithPrice("0022", 16.7, 3600);//60.120
		Order order7 = generateVNDOrderWithPrice("105", 18, 95600);  //1.720.800
		orderMemoryCache.put(order1);
		orderMemoryCache.put(order2);
		orderMemoryCache.put(order3);
		orderMemoryCache.put(order4);
		orderMemoryCache.put(order5);
		orderMemoryCache.put(order6);
		orderMemoryCache.put(order7);
		
		List<String> top = orderMemoryCache.getTopAccount(5);
		assertEquals(5, top.size());
		assertEquals("105", top.get(0));
		assertEquals("0003", top.get(1));
		assertEquals("0022", top.get(2));
		assertEquals("0013", top.get(3));
		assertEquals("0001", top.get(4));
	}
	
	private Order generateVNDOrderWithPrice(String account, double price, double quantity) {
		Order order = new Order();
		order.setAccount(account);
		order.setSymbol("VND");
		order.setOrderType("LO");
		order.setPrice(price);
		order.setQuantity(quantity);
		return order;
	}
}
