package test;

import payment.CreditCardPayments;
import payment.PayPalPayment;
import payment.PaymentProcess;

public class Main {
//	 public static void main(String[] args) {
//	        PaymentProcess processor = new PaymentProcess();
//	        
//	        processor.pay(new CreditCardPayments());
//	        processor.pay(new PayPalPayment());
//	        
//	    }
	
	
	public static void main(String[] args) {
		PaymentProcess pr=new PaymentProcess(new CreditCardPayments());
		pr.pay();
	}
}
