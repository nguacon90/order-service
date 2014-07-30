package vn.com.vndirect.model;

public class Porfolio {
	private String account;
	private double totalOrderValue;

	public Porfolio(String account, double totalOrderValue) {
		this.account = account;
		this.totalOrderValue = totalOrderValue;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public double getTotalOrderValue() {
		return totalOrderValue;
	}

	public void setTotalOrderValue(double totalOrderValue) {
		this.totalOrderValue = totalOrderValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalOrderValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Porfolio other = (Porfolio) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (Double.doubleToLongBits(totalOrderValue) != Double.doubleToLongBits(other.totalOrderValue))
			return false;
		return true;
	}

}
