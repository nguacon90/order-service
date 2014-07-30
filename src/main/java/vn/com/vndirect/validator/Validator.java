package vn.com.vndirect.validator;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.validator.exception.ValidateException;

public interface Validator {
	void validate(Order order) throws ValidateException;
}
