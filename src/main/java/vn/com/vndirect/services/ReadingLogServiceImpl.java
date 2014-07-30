package vn.com.vndirect.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.utils.StatisticUtils;

public class ReadingLogServiceImpl implements ReadingLogService {

	private static final Logger logger = Logger
			.getLogger(ReadingLogServiceImpl.class);

	private String logFileURL;
	private BufferedReader buffer;
	private OrderMemoryCache orderMemoryCache;
	
	public ReadingLogServiceImpl(String logFileURL, OrderMemoryCache orderMemoryCache) {
		this.logFileURL = logFileURL;
		this.orderMemoryCache = orderMemoryCache;
	}
	
	@Override
	public void readLogFile() {
		String uri = logFileURL;
		try {
			buffer = new BufferedReader(new FileReader(uri));
			String line = StringUtils.EMPTY;
			while ((line = buffer.readLine()) != null) {
				String[] splitLine = line.split(StatisticUtils.ORDER_TITLE.getValue());
				String[] orderStrs = splitLine[1].split("\\" + StatisticUtils.SEPERATOR.getValue());
				Order order = convertToOrder(orderStrs);
				if(order.getOrderId() != null){
					orderMemoryCache.put(order);
				}
			}
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public Order convertToOrder(String[] orderStrs) {
		Order order = new Order();
		if(orderStrs.length == 6) {
			order.setOrderId(orderStrs[0]);
			order.setAccount(orderStrs[1]);
			order.setPrice(Double.valueOf(orderStrs[2]));
			order.setQuantity(Double.valueOf(orderStrs[3]));
			order.setSymbol(orderStrs[4]);
			order.setSymbol(orderStrs[5]);
		}
		return order;
	}

	public void setLogFileURL(String logFileURL) {
		this.logFileURL = logFileURL;
	}
}
