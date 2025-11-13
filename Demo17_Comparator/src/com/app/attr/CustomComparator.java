package com.app.attr;

import java.util.Comparator;

public class CustomComparator implements Comparator<Customer> {

	@Override
	public int compare(Customer o1, Customer o2) {
		// TODO Auto-generated method stub
		if(o1.getBal()>o2.getBal())return 1;
		else if(o1.getBal()<o2.getBal())return -1;
		else return 0;
		
	}

}
