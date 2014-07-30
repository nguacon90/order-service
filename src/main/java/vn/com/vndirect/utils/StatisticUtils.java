package vn.com.vndirect.utils;

public enum StatisticUtils {
	ORDER_TITLE("PLACE_ORDER"), SEPERATOR("|");
	
	private String value;
	StatisticUtils (String value) {
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
}
