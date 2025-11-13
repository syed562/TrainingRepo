package payment;

public class PaymentProcess {
//
//	public void pay(Payment payment) {
//		payment.process();
//}
//	
	
	public Payment  payment;

public PaymentProcess(Payment payment) {
	this.payment = payment;
}

public void pay() {
	payment.process();
}
	
	
}