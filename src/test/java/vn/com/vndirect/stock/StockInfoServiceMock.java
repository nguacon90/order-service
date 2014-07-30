package vn.com.vndirect.stock;

import java.util.HashMap;
import java.util.Map;

import vn.com.vndirect.stock.model.StockInfo;

public class StockInfoServiceMock implements StockInfoService {

	private Map<String, StockInfo> stockInfos;
	
	public StockInfoServiceMock(){
		stockInfos = new HashMap<String, StockInfo>();
		StockInfo VND = generateStockInfo(13.2, 18, "VND");
		StockInfo ABI = generateStockInfo(15.3, 19, "ABI");
		stockInfos.put("VND", VND);
		stockInfos.put("ABI", ABI);
	}
	
	@Override
	public StockInfo getPrice(String symbol) {
		return stockInfos.get(symbol.toUpperCase());
	}

	private StockInfo generateStockInfo(double floorPrice, double ceilingPrice, String code){
		StockInfo stockInfo = new StockInfo();
		stockInfo.setCeilingPrice(ceilingPrice);
		stockInfo.setCode(code);
		stockInfo.setFloorPrice(floorPrice);
		return stockInfo;
	}
}
