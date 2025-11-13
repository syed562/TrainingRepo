package emi;

import java.util.Scanner;

public class Inputs {
 public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
//	cost of baleno delta - 800000,alfa - 1200000,beta -1000000,pass the color,loan amount - ,loan tenure - 3 yr or 5 yrs
//
	
	System.out.println("The costs are :");
	System.out.println("delta : "+800000);
	System.out.println("alpha : "+1200000);
	System.out.println("beta : "+ 1000000);
	//Scanner sc=new Scanner(System.in);
	System.out.println("Enter your choice model");
	String choice=sc.next();
	System.out.println("Enter the rate of interest");
	float rate=sc.nextFloat();
	System.out.println("Enter number of years : ");
	int t=sc.nextInt();
	double amnt=0;
	if(choice.equalsIgnoreCase("delta"))amnt=800000;
	else if(choice.equalsIgnoreCase("alpha"))amnt=1200000;
	else if(choice.equalsIgnoreCase("beta"))amnt=1000000;
	double interest=InterestCalc.simpleinter(amnt, rate, t);
	double total=amnt+interest;
	System.out.println("Emi is "+InterestCalc.emicalc(total,t));
	System.out.println("Emi is "+InterestCalc.emicalcCompInt(amnt,rate,t));
}
}