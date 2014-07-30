package vn.com.vndirect.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name="OrderResult")
public class OrderResult implements Serializable{
	private ResponseMessage responseMessage;
	private Order order;

	public OrderResult(){}
	
	public OrderResult(ResponseMessage responseMessage, Order order) {
		this.responseMessage = responseMessage;
		this.order = order;
	}

	public ResponseMessage getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
