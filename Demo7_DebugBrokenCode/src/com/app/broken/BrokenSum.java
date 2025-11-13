package com.app.broken;

public class BrokenSum {
public static void main(String[] args) {
	
	//NullPointer+ArrayIndexOutOfBounds
	
	String[] nums=null;
	int sum=0;
	for(int i=0;i<=nums.length;i++) {
		sum+=Integer.parseInt(nums[i]);
	}
	System.out.println("Sum is : "+sum);
	
	
	
	
}
}
