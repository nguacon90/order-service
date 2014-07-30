package vn.com.vndirect.stock.model;

public class StockInfo {

	private double floorPrice;
	private double ceilingPrice;
	private double basicPrice;
	private String floorCode;
	private String code;
	
	public double getFloorPrice() {
		return this.floorPrice;
	}

	public void setFloorPrice(double floorPrice) {
		this.floorPrice = floorPrice;
	}

	public double getCeilingPrice() {
		return ceilingPrice;
	}

	public void setCeilingPrice(double ceilingPrice) {
		this.ceilingPrice = ceilingPrice;
	}

	public double getBasicPrice() {
		return basicPrice;
	}

	public void setBasicPrice(double basicPrice) {
		this.basicPrice = basicPrice;
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
