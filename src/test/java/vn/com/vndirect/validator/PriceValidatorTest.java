package vn.com.vndirect.validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.stock.StockInfoService;
import vn.com.vndirect.stock.StockInfoServiceMock;
import vn.com.vndirect.validator.exception.ValidateException;
import vn.com.vndirect.validator.exception.ValidatorStatus;

public class PriceValidatorTest {
	private Validator priceValidator;
	private Order order;
	private StockInfoService stockInfoService;

	private Order generateVNDOrderWithPrice(double price) {
		Order order = new Order();
		order.setAccount("aaaa");
		order.setSymbol("VND");
		order.setOrderType("LO");
		order.setPrice(price);
		order.setQuantity(100);
		return order;
	}

	@Before
	public void setUp() {
		order = new Order();
		stockInfoService = new StockInfoServiceMock();
		priceValidator = new PriceValidatorImpl(stockInfoService);
	}

	// VND: floorPrice: 13.2, ceiling price: 18
	@Test
	public void testPriceIsLowerThanFloorPrice() {
		order = generateVNDOrderWithPrice(10);
		try {
			priceValidator.validate(order);
			fail("Must throw exception");
		} catch (ValidateException e) {
			assertEquals(ValidatorStatus.PRICE_ISNOT_INRANGE.getCode(),
					e.getCode());
		}
	}

	@Test
	public void testPriceIsLargerThanCeilingPrice() {
		order = generateVNDOrderWithPrice(20);
		try {
			priceValidator.validate(order);
			fail("Must throw exception");
		} catch (ValidateException e) {
			assertEquals(ValidatorStatus.PRICE_ISNOT_INRANGE.getCode(),
					e.getCode());
		}
	}

	@Test
	public void testPriceIsBetweenFloorAndCeilingPrice() throws ValidateException {
		order = generateVNDOrderWithPrice(15);
		priceValidator.validate(order);
		assertTrue(true);
	}

}
