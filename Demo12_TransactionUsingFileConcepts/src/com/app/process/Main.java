package com.app.process;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {
public static void main(String[] args) {
	String fname="Transactions.txt";
	double totalHDFCPaid=0;
	FileInputStream fis=null;
	StringBuilder txt=new StringBuilder();
	int ch;
	try {
		fis=new FileInputStream(fname);
		while((ch=fis.read())!=-1) {
			txt.append((char)ch);
		}
		String[] lines=txt.toString().split("\\r?\\n");
		for(String line:lines) {
			line=line.trim();
			if(line.isEmpty() ||!line.contains(","))continue;
			String[] data=line.split(",");
			if(data.length<11)continue;
			Transaction t=new Transaction(data);
			if(t.amnt<=0) {
				System.out.println("Invalid amnt");
				continue;
			}
			if(t.amnt>t.bal) {
				System.out.println("Insufficient balance");
				continue;
			}
			   System.out.println("✅ " + t.sname + " transferred " + t.amnt +
                       " to " + t.rname + " via " + t.ttype);
			   if(t.sIFSC.startsWith("HDFC")){
			   		totalHDFCPaid+=t.amnt;

			   }
		}
		
		
        System.out.println("\n💰 Total amount paid by HDFC Bank: " + totalHDFCPaid);

		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
        try {
            if (fis != null) fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}}}

