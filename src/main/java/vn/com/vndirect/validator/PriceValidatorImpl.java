package vn.com.vndirect.validator;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.stock.StockInfoService;
import vn.com.vndirect.stock.model.StockInfo;
import vn.com.vndirect.validator.exception.ValidateException;
import vn.com.vndirect.validator.exception.ValidatorStatus;

public class PriceValidatorImpl implements Validator {

	private StockInfoService stockInfoService;

	public PriceValidatorImpl(StockInfoService stockInfoService) {
		this.stockInfoService = stockInfoService;
	}

	@Override
	public void validate(Order order) throws ValidateException {
		String symbol = order.getSymbol();
		StockInfo stockInfo = stockInfoService.getPrice(symbol);
		if (stockInfo != null) {
			double floorPrice = stockInfo.getFloorPrice();
			double ceilingPrice = stockInfo.getCeilingPrice();
			double price = order.getPrice();
			if (price < floorPrice || price > ceilingPrice) {
				throw new ValidateException(
						ValidatorStatus.PRICE_ISNOT_INRANGE.getCode(),
						ValidatorStatus.PRICE_ISNOT_INRANGE.getMessage());
			}
		}
	}

}
