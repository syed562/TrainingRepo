package develop;

public class ProcessPayment {
	public int pc;
public static void processFund() {
	System.out.println("ProcessPayment.processFund()");
}
public void processNEFTFund() {
	pc++;
	System.out.println("ProcessPayment.processNEFTFund()");
}
}
