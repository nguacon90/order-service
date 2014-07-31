package vn.com.vndirect.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.validator.exception.ValidateException;
import vn.com.vndirect.validator.exception.ValidatorStatus;

public class InputValidatorTest {

	private Validator inputValidator;
	private Order order;

	private Order generateOrder(String account, String symbol, double price,
			double quantity, String orderType) {
		Order order = new Order();
		order.setAccount(account);
		order.setSymbol(symbol);
		order.setOrderType(orderType);
		order.setPrice(price);
		order.setQuantity(quantity);
		return order;
	}

	@Before
	public void setUp() {
		order = new Order();
		inputValidator = new InputValidatorImpl();
	}

	@Test
	public void testAccountIsNull() {
		try {
			inputValidator.validate(order);
			fail("Must throw INVALID_ACCOUNT exception");
		} catch (ValidateException ve) {
			assertEquals(ValidatorStatus.INVALID_ACCOUNT.getCode(),
					ve.getCode());
		}
	}

	@Test
	public void testAccountIsEmptyString() {
		try {
			order = generateOrder(StringUtils.EMPTY, "VND", 12, 10, "ATO");
			inputValidator.validate(order);
			fail("Must throw INVALID_ACCOUNT exception");
		} catch (ValidateException ve) {
			assertEquals(ve.getCode(),
					ValidatorStatus.INVALID_ACCOUNT.getCode());
		}
	}

	@Test
	public void testAccountIsValid() throws ValidateException {
		order = generateOrder("abcd", "VND", 12, 10, "ATO");
		inputValidator.validate(order);
		assertTrue(true);
	}

	@Test
	public void testSymbolIsNull() {
		try {
			order = generateOrder("abcd", null, 12, 10, "ATO");
			inputValidator.validate(order);
			fail("Must throw exception");
		} catch (ValidateException e) {
			assertEquals(e.getCode(), ValidatorStatus.INVALID_SYMBOL.getCode());
		}
	}

	@Test
	public void testSymbolIsEmpty() {
		try {
			order = generateOrder("abcd", "", 12, 10, "ATO");
			inputValidator.validate(order);
			fail("Must throw exception");
		} catch (ValidateException e) {
			assertEquals(e.getCode(), ValidatorStatus.INVALID_SYMBOL.getCode());
		}
	}

	@Test
	public void testValidSymbol() throws ValidateException {
		order = generateOrder("abcd", "VND", 12, 10, "ATO");
		inputValidator.validate(order);
		assertTrue(true);
	}

	@Test
	public void testPriceIsNegativeNumber() {
		try {
			order = generateOrder("abcd", "VND", -12, 10, "ATO");
			inputValidator.validate(order);
			fail("Must throw exception");
		} catch (ValidateException e) {
			assertEquals(e.getCode(), ValidatorStatus.INVALID_PRICE.getCode());
		}
	}

	@Test
	public void testPriceIsZero() {
		try {
			order = generateOrder("abcd", "VND", 0, 10, "ATO");
			inputValidator.validate(order);
			fail("Must throw exception");
		} catch (ValidateException e) {
			assertEquals(e.getCode(), ValidatorStatus.INVALID_PRICE.getCode());
		}
	}

	@Test
	public void testPriceIsPositiveNumber() throws ValidateException {
		order = generateOrder("abcd", "VND", 10, 10, "ATO");
		inputValidator.validate(order);
		assertTrue(true);
	}

	@Test
	public void testQuantityIsNegativeNumber() {
		try {
			order = generateOrder("abcd", "VND", 12, -10, "ATO");
			inputValidator.validate(order);
			fail("Must throw exception");
		} catch (ValidateException e) {
			assertEquals(e.getCode(),
					ValidatorStatus.INVALID_QUANTITY.getCode());
		}
	}

	@Test
	public void testQuantityIsZero() {
		try {
			order = generateOrder("abcd", "VND", 12, 0, "ATO");
			inputValidator.validate(order);
			fail("Must throw exception");
		} catch (ValidateException e) {
			assertEquals(e.getCode(),
					ValidatorStatus.INVALID_QUANTITY.getCode());
		}
	}

	@Test
	public void testQuantityIsPositiveNumber() throws ValidateException {
		order = generateOrder("abcd", "VND", 13, 10, "ATO");
		inputValidator.validate(order);
		assertTrue(true);
	}

	@Test
	public void testOrderTypeIsEmpty() {
		try {
			order = generateOrder("abcd", "VND", 12, 10, "");
			inputValidator.validate(order);
			fail("Must throw exception");
		} catch (ValidateException e) {
			assertEquals(e.getCode(),
					ValidatorStatus.INVALID_ORDERTYPE.getCode());
		}
	}
	
	@Test
	public void testOrderTypeIsNull() {
		try {
			order = generateOrder("abcd", "VND", 12, 10, null);
			inputValidator.validate(order);
			fail("Must throw exception");
		} catch (ValidateException e) {
			assertEquals(e.getCode(),
					ValidatorStatus.INVALID_ORDERTYPE.getCode());
		}
	}
	
	@Test
	public void testOrderTypeIsNotEmptyOrNull() throws ValidateException {
			order = generateOrder("abcd", "VND", 12, 10, "aaa");
			inputValidator.validate(order);
			assertTrue(true);
	}
}
