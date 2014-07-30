package vn.com.vndirect.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name="TopAccountResult")
public class TopAccountResult implements Serializable {
	private ResponseMessage responseMessage;
	private List<String> topAccounts;
	
	public TopAccountResult(){}
	
	public TopAccountResult(ResponseMessage responseMessage,
			List<String> topAccounts) {
		this.responseMessage = responseMessage;
		this.topAccounts = topAccounts;
	}
	public ResponseMessage getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}

	public List<String> getTopAccounts() {
		return topAccounts;
	}

	public void setTopAccounts(List<String> topAccounts) {
		this.topAccounts = topAccounts;
	}

}
