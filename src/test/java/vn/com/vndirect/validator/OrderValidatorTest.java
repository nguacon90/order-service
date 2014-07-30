package vn.com.vndirect.validator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.stock.StockInfoService;
import vn.com.vndirect.stock.StockInfoServiceMock;
import vn.com.vndirect.validator.exception.ValidateException;
import vn.com.vndirect.validator.exception.ValidatorStatus;

public class OrderValidatorTest {

	private Validator inputValidator;
	private Validator orderTypeValidator;
	private Validator priceValidator;
	List<Validator> validators;
	
	@Before
	public void setUp() {
		List<String> orderTypes = new ArrayList<String>(Arrays.asList("LO", "MP", "ATC", "ATO", "MOK", "MAK", "MTL"));
		inputValidator = new InputValidatorImpl();
		orderTypeValidator = new OrderTypeValidatorImpl(orderTypes);
		StockInfoService stockInfoService = new StockInfoServiceMock();
		priceValidator = new PriceValidatorImpl(stockInfoService);
		validators = new ArrayList<Validator>();
		validators.add(inputValidator);
		validators.add(priceValidator);
		validators.add(orderTypeValidator);
	}
	
	private Order generateVNDOrderWithPrice(double price) {
		Order order = new Order();
		order.setAccount("aaaa");
		order.setSymbol("VND");
		order.setOrderType("LO");
		order.setPrice(price);
		order.setQuantity(100);
		return order;
	}
	
	@Test
	public void testValidOrder() throws ValidateException {
		OrderValidatorImpl orderValidator = new OrderValidatorImpl();
		orderValidator.setValidators(validators);
		Order order = generateVNDOrderWithPrice(18);
		orderValidator.validate(order);
		assertTrue(true);
	}
	
	@Test
	public void testInvalidPriceOrder(){
		OrderValidatorImpl orderValidator = new OrderValidatorImpl();
		orderValidator.setValidators(validators);
		Order order = generateVNDOrderWithPrice(-18);
		try {
			orderValidator.validate(order);
			fail("Must throw exception");
		} catch (ValidateException e) {
			assertEquals(ValidatorStatus.INVALID_PRICE.getCode(), e.getCode());
		}
	}
}
