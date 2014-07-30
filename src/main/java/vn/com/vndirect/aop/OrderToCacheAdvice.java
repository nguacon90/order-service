package vn.com.vndirect.aop;

import java.lang.reflect.Method;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.model.OrderResult;
import vn.com.vndirect.model.ResponseMessage;
import vn.com.vndirect.services.OrderMemoryCache;
import vn.com.vndirect.utils.StatisticUtils;


public class OrderToCacheAdvice implements AfterReturningAdvice{

	private static final Logger logger = Logger.getLogger("reportsLog");
	
	private OrderMemoryCache orderMemoryCache;
	
	public OrderToCacheAdvice(OrderMemoryCache orderMemoryCache) {
		this.orderMemoryCache = orderMemoryCache;
	}
	
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] arg2,
			Object target) throws Throwable {
		Response response = (Response) returnValue;
		OrderResult orderResult = (OrderResult) response.getEntity();
		if(ResponseMessage.SUCCESS.getCode().equals(orderResult.getResponseMessage().getCode())) {
			orderMemoryCache.put(orderResult.getOrder());
			String orderLog = generateOderInfoLog(orderResult.getOrder());
			logger.info(orderLog);
		}
	}

	private String generateOderInfoLog(Order order) {
		StringBuilder log = new StringBuilder(StatisticUtils.ORDER_TITLE.getValue());
		log.append(order.getOrderId()).append(StatisticUtils.SEPERATOR.getValue())
		.append(order.getAccount()).append(StatisticUtils.SEPERATOR.getValue())
		.append(order.getPrice()).append(StatisticUtils.SEPERATOR.getValue())
		.append(order.getQuantity()).append(StatisticUtils.SEPERATOR.getValue())
		.append(order.getSymbol()).append(StatisticUtils.SEPERATOR.getValue())
		.append(order.getOrderType()).append(StatisticUtils.SEPERATOR.getValue());
		return log.toString();
	}

}
