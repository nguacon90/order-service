package vn.com.vndirect.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name="TopOrderResult")
public class TopOrderResult implements Serializable{
	private ResponseMessage responseMessage;
	private List<Order> topOrders;

	public TopOrderResult(){}
	
	public TopOrderResult(ResponseMessage responseMessage, List<Order> orders){
		this.responseMessage = responseMessage;
		this.topOrders = orders;
	}
	
	public ResponseMessage getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}

	public List<Order> getTopOrders() {
		return topOrders;
	}

	public void setTopOrders(List<Order> topOrders) {
		this.topOrders = topOrders;
	}

}
