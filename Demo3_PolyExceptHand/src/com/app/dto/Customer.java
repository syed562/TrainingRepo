package com.app.dto;

public class Customer {
	
	public Customer(){
	System.out.println("inside default constructor");	
	}
	
	public Customer(String custname,String custemail,String accountdetails,double balance){
		this.accountno = accountdetails;
		this.amountbalance = balance;
		this.email = custemail;
		this.name = custname;
	}
	private String name;
	public String getName() {
		return name;
	}
	
	public double getAmountbalance() {
		return amountbalance;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getAccountno() {
		return accountno;
	}
	
	private double amountbalance;
	private String email;
    private String accountno;
    
    public void setAmount(double amount) {
    	this.amountbalance = amount;
    }
    
}