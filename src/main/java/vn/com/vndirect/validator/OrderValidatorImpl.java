package vn.com.vndirect.validator;

import java.util.List;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.validator.exception.ValidateException;

public class OrderValidatorImpl implements Validator {

	private List<Validator> validators;
	
	@Override
	public void validate(Order order) throws ValidateException {
		for (Validator validator : validators) {
			validator.validate(order);
		}
	}

	public void setValidators(List<Validator> validators) {
		this.validators = validators;
	}

}
