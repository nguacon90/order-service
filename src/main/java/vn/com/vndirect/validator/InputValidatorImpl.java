package vn.com.vndirect.validator;

import org.apache.commons.lang3.StringUtils;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.validator.exception.ValidateException;
import vn.com.vndirect.validator.exception.ValidatorStatus;

public class InputValidatorImpl implements Validator {

	@Override
	public void validate(Order order) throws ValidateException {
		if (StringUtils.isEmpty(order.getAccount())) {
			throw new ValidateException(ValidatorStatus.INVALID_ACCOUNT.getCode(),
					ValidatorStatus.INVALID_ACCOUNT.getMessage());
		}
		
		if(StringUtils.isEmpty(order.getSymbol())){
			throw new ValidateException(ValidatorStatus.INVALID_SYMBOL.getCode(),
					ValidatorStatus.INVALID_SYMBOL.getMessage());
		}
		
		if(order.getPrice() <= 0) {
			throw new ValidateException(ValidatorStatus.INVALID_PRICE.getCode(),
					ValidatorStatus.INVALID_PRICE.getMessage());
		}
		
		if(order.getQuantity() <= 0) {
			throw new ValidateException(ValidatorStatus.INVALID_QUANTITY.getCode(),
					ValidatorStatus.INVALID_QUANTITY.getMessage());
		}
		
		if(StringUtils.isEmpty(order.getOrderType())) {
			throw new ValidateException(ValidatorStatus.INVALID_ORDERTYPE.getCode(),
					ValidatorStatus.INVALID_ORDERTYPE.getMessage());
		}
	}

}
