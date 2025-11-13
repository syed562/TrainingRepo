
package com.app.payments;
import com.app.account.Account;
import com.app.benef.Beneficiary;
import com.app.exceptions.TransactionFailedException;

public class NetBankingPayment extends Payment implements Refundable,Retryable{

	public NetBankingPayment(double amount, Account acc) {
		super(amount, acc);
		
	}

	@Override
	public void retry() {
		System.out.println("CreditCardPayment.retry()");
		
	}

	public void refund() {
		System.out.println("CreditCardPayment.refund()");
		
	}

	@Override
	public void processPayment(Beneficiary ben) throws TransactionFailedException {
		try{
			acc.debit(amount);
			System.out.println("NetBanking.processPayment()");
		
		System.out.println("Amount is debited to :"+ben.name);
		}catch(Exception e) {
			throw new TransactionFailedException("Transaction failed");
		}
	}

}
