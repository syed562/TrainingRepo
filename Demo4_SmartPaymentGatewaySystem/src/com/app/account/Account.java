package com.app.account;

import com.app.exceptions.InsufficientBalanceException;
import com.app.exceptions.InvalidAmountException;

public class Account {
public double balance;


public Account(double balance) {
	
	this.balance = balance;
}

public double getBalance() {
	return balance;
}
   
public void debit(double amnt) throws InvalidAmountException, InsufficientBalanceException {
	if(amnt<0) throw new InvalidAmountException("No balance");
	else if(balance<amnt || balance<0)throw new InsufficientBalanceException("Insufficient Balance");
}

public void credit(double amnt) {
	balance+=amnt;
	System.out.println("Amount is credited");
}

}



