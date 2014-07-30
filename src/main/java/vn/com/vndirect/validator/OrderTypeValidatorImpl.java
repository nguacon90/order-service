package vn.com.vndirect.validator;

import java.util.List;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.validator.exception.ValidateException;
import vn.com.vndirect.validator.exception.ValidatorStatus;

public class OrderTypeValidatorImpl implements Validator {

	private List<String> orderTypes;

	public OrderTypeValidatorImpl(List<String> orderTypes) {
		this.orderTypes = orderTypes;
	}

	@Override
	public void validate(Order order) throws ValidateException {
		String orderType = order.getOrderType();
		if (!orderTypes.contains(orderType)) {
			throw new ValidateException(
					ValidatorStatus.INVALID_ORDERTYPE.getCode(),
					ValidatorStatus.INVALID_ORDERTYPE.getMessage());
		}

	}

}
