package develop;

import java.util.Arrays;

public class FundTransfer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      //System.out.println("This is test");
//      print();
//      add(3,4);
      //fibonacci(5);
     // primeNumWithinRange(500);
//     System.out.println(presentlinear(8));
//      System.out.println(presentbinary(8));
		
		insertionSort();
		FundTransfer obj=new FundTransfer();
		System.out.println(obj.validateName("Nidhu"));
		int k=(6>7)?1:2;
      
	}

public static void print() {
	
	System.out.println("inside print");
	
	int i = 0;
	
	  i =i+2;   //=+2;
	  System.out.println("inside print - "+i);
	  i = i-1;
	  
	  i = i*2;
	  
	  i = i/3;
	  
	  i = i%2;
}


public static void add(int i,int j) {
	 
	int result = i+j;
	
	int arr[] = {1,2,5,7,9,10};
	
	for (int k=0;k<arr.length;k++) {
		System.out.println("value of k in for loop with array  "+arr[k]);
	}
	
	System.out.println("result - "+result);
	
	for (int k=0;k<10;k++) {
		System.out.println("value of k in for loop  "+k);
	}
	
	while(result<10 ) {
		
		System.out.println("value of result in while - "+ result);
		result = result+1;
	}
	
	do {
		result++;
		System.out.println("value of result in do while = "+result);
	}
	while(result<20);
}
 public static void fibonacci(int n) {
	 int f0=0;
	 int f1=1;
	 System.out.print(f0+" "+f1+" ");
	 while(n>2) {
		 int f2=f0+f1;
		 f0=f1;
		 f1=f2;
		 System.out.print(f2+" ");
		 n--;
		 
	 }
 }
public static void primeNumWithinRange(int n) {
	boolean marked[]=new boolean[n+1];
	Arrays.fill(marked,true);
	for(int i=2;i*i<=n;i++) {
		if(marked[i]) {
			for(int j=i*i;j<=n;j+=i) {
				marked[j]=false;
			}
		}
	}
	
	for(int k=2;k<=n;k++) {
		if(marked[k])System.out.print(k+" ");
	}
}

public static boolean presentlinear(int t) {
	int a[]= {2,32,14,56,26,7,8};
	for(int i:a) {
		if(i==t)return true;
	}
	return false;
}

public static boolean presentbinary(int t) {
	int a[]= {2,32,14,56,26,7,8};
	Arrays.sort(a);
	int s=0;
	int e=a.length;
	while(s<=e) {
		int m=s+(e-s)/2;
		if(a[m]==t)return true;
		else if(a[m]>t)e=m-1;
		else s=m+1;
	}
	return false;
	

}
public static void insertionSort() {
	int a[]= {2,32,14,56,26,7,8};
	for(int i=1;i<a.length;i++) {
		int key=a[i];
		int j=i-1;
		while(j>=0 && a[j]>key) {
			a[j+1]=a[j];
			j--;
		}
		
		a[j+1]=key;
	}
	System.out.println(Arrays.toString(a));
}


public boolean processINtrTransactions() {
	System.out.println("FundTransfer.processINtrTransactions()");
	return false;
}


public boolean validateName(String name) {
	switch(name) {
	case"Sabi":
		return true;
	
	case "Nidhu":
		return false;
	
	
	default:
		return false;
	}
}
}