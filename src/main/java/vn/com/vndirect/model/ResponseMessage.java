package vn.com.vndirect.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import vn.com.vndirect.validator.exception.ValidatorStatus;

@SuppressWarnings("serial")
@XmlRootElement(name="ResponseMessage")
public class ResponseMessage implements Serializable {
	private String code;
	private String message;
	public static final ResponseMessage SUCCESS = new ResponseMessage(
			ValidatorStatus.OK.getCode(), ValidatorStatus.OK.getMessage());

	public ResponseMessage() {
	}

	public ResponseMessage(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
