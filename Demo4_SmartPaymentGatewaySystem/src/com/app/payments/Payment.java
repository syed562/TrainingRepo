package com.app.payments;

import com.app.account.Account;
import com.app.benef.Beneficiary;
import com.app.exceptions.TransactionFailedException;

public abstract class Payment {
double amount;
Account acc;
public Payment(double amount,Account acc) {
	this.acc=acc;
	this.amount=amount;
	
}

public abstract  void processPayment(Beneficiary ben) throws TransactionFailedException;
}
