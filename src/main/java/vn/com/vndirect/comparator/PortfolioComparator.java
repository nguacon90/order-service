package vn.com.vndirect.comparator;

import java.util.Comparator;

import vn.com.vndirect.model.Porfolio;

public class PortfolioComparator implements Comparator<Porfolio>{

	@Override
	public int compare(Porfolio o1, Porfolio o2) {
		if(o2.getTotalOrderValue() > o1.getTotalOrderValue()) return 1;
		if(o2.getTotalOrderValue() < o1.getTotalOrderValue()) return -1;
		return 0;
	}

}
