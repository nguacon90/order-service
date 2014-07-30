package vn.com.vndirect.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.validator.exception.ValidateException;
import vn.com.vndirect.validator.exception.ValidatorStatus;

public class OrderTypeValidatorTest {

	private Validator orderTypeValidator;
	private Order order;
	private List<String> orderTypes;
	
	private Order generateVNDOrderWithOrderType(String orderType) {
		Order order = new Order();
		order.setAccount("aaaa");
		order.setSymbol("VND");
		order.setOrderType(orderType);
		order.setPrice(19);
		order.setQuantity(100);
		return order;
	}

	@Before
	public void setUp() {
		orderTypes = new ArrayList<String>(Arrays.asList("LO", "MP", "ATC", "ATO", "MOK", "MAK", "MTL"));
		orderTypeValidator = new OrderTypeValidatorImpl(orderTypes);
	}

	@Test
	public void testOrderWithOrderTypeIsNotValid() {
		order = generateVNDOrderWithOrderType("ASX");
		try {
			orderTypeValidator.validate(order);
			fail("Must throw exception");
		} catch (ValidateException e) {
			assertEquals(ValidatorStatus.INVALID_ORDERTYPE.getCode(), e.getCode());
		}
	}
	
	@Test
	public void testOrderWithOrderTypeIsLO() throws ValidateException{
		order = generateVNDOrderWithOrderType("LO");
		orderTypeValidator.validate(order);
		assertTrue(true);
	}
}
