package vn.com.vndirect.stock;

import vn.com.vndirect.stock.model.StockInfo;

public interface StockInfoService {

	StockInfo getPrice(String symbol);

}
