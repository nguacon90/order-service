package vn.com.vndirect.validator.exception;

public enum ValidatorStatus {
	INVALID_ACCOUNT("-1", "Account is invalid"), INVALID_SYMBOL("-2", "Symbol is invalid"), 
	INVALID_PRICE("-3", "Price is invalid"), INVALID_QUANTITY("-4", "Quantity is invalid"), 
	INVALID_ORDERTYPE("-5", "Order type is invalid"), 
	PRICE_ISNOT_INRANGE("-6", "Price is not between floor and ceiling price"), 
	OK("0", "");
	
	private String code;
	private String message;
	
	ValidatorStatus(String code, String messge) {
		this.code = code;
		this.message = messge;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	
}
