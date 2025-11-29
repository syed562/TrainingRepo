package com.app.main;

import java.util.Scanner;

import com.app.account.Account;
import com.app.benef.Beneficiary;
import com.app.exceptions.BeneficiaryNotFoundException;
import com.app.exceptions.TransactionFailedException;
import com.app.payments.CreditCardPayment;
import com.app.payments.NetBankingPayment;
import com.app.payments.Payment;
import com.app.payments.UPIPayment;

public class Main {
public static void main(String[] args) throws  BeneficiaryNotFoundException {
	Scanner sc=new Scanner(System.in);
	Beneficiary ben[]= {new Beneficiary("Sabi"),new Beneficiary("Tom")};
	Account acc1=new Account(1000);
	Account acc2=new Account(500);
	Account acc3=new Account(500);
	Payment[] pay= {new CreditCardPayment(-1000, acc1), new NetBankingPayment(20, acc2),new UPIPayment(1000, acc3)};
	for(Payment p:pay) {
		try {
			p.processPayment(ben[0]);
		} catch (TransactionFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	System.out.println("Enter the name u want to search");
	boolean find=false;
	String search=sc.next();
	for(Beneficiary b:ben) {
		if(b.name.equals(search)) {
			find=true;
		}
	}
	
	if(!find)throw new BeneficiaryNotFoundException("Given name is not in list");
}
}
