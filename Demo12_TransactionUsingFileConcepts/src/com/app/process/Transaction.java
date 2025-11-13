package com.app.process;

public class Transaction {
String sname, scoun, saccno,sIFSC;
double bal,amnt;
String ttype,rname,rcoun,raccno,rIFSC;
Transaction(String[] data){
	sname=data[0];
	scoun=data[1];
	saccno=data[2];
	sIFSC=data[3];
	bal=Double.parseDouble(data[4]);
	amnt=Double.parseDouble(data[5]);
	ttype=data[6];
	rname=data[7];
	rcoun=data[8];
	raccno=data[9];
	rIFSC=data[10];
}

}