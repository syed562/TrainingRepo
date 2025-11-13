package emi;

public class InterestCalc {
public static double simpleinter(double p,float rate,int t) {
	double si=0;
	si=p*rate*t/100;
	
	return si;
}

public static double emicalc(double amnt,int years) {
	double emi=amnt/(12*years);
	return emi;
}

public static double emicalcCompInt(double p,double r,int n) {
double rate=r/(12*100.0);
	double emi=(p*rate*Math.pow(1+rate, n*12))/(Math.pow(1+rate, n*12)-1);
	return emi;
}
}
