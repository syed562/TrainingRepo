package com.app.oc;

public class ProcessingPayment {
public void processPay(Payment paymeth) {
	paymeth.pay();
}

public static void main(String[] args) {
	ProcessingPayment psp=new ProcessingPayment();
	psp.processPay(new CreditCard());
}


}
