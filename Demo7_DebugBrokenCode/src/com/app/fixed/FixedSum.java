package com.app.fixed;

public class FixedSum {
public static void main(String[] args) {
	String[] nums=args.length>0?args:new String[] {"1","2","3"};
	int sum=0;
	for(int i=0;i<nums.length;i++) {
		try {
			sum+=Integer.parseInt(nums[i]);
		}catch(NumberFormatException nfe) {
			System.err.println("Invalid number at index "+i+" : "+nums[i]);
		}
	}
	System.out.println("Sum is "+sum);
}
}
