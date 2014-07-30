package vn.com.vndirect.validator.exception;

@SuppressWarnings("serial")
public class ValidateException extends Exception {

	private String code;
	private String message;
	
	public ValidateException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public ValidateException(String code, Throwable e){
		super(e);
		this.code = code;
	}
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	

}
