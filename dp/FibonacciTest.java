package com.cupid.algorithm.dp;

public class FibonacciTest {
	
	
	public static long recursiveFibonacci(int n){
		if(n==0)
			return 0l;
		else if(n==1)
			return 1l;
		else
			return recursiveFibonacci(n-2) + recursiveFibonacci(n-1);
	}
	
	public static long dp_bottomUp_Fibonacci2(int n){
		// If n == 0 or 1, simply return 0 or 1.
		long x = 1l;
		if(n==0){
			x = 0l;
		}else if(n==1){
			x = 1l;
		}else{
			// One variable y is used to store the value of (n-2)th element.
			// The other variable x is used to store the value of (n-1)th the element.
			// The result will be finally stored in x.
			long y = 0l;
			for(int i=2;i<=n;i++){
				long z = x;
				x = x + y;
				y = z;
			}
		}
		return x;
	}
	
	public static long dp_bottomUp_Fibonacci1(int n,long[] values){
		for (int i = 0; i <= n; i++) {
			if (i == 0) {
				values[i] = 0l;
			} else if (i == 1) {
				values[i] = 1l;
			} else {
				values[i] = values[i - 2] + values[i - 1];
			}
		}
		return values[n];
	}
	
 	public static void main(String[] args) {
 		int nth = 10;
 		long[] values = new long[nth+1];
 		
 		//long begin1 = System.currentTimeMillis();
 		System.out.println(dp_bottomUp_Fibonacci1(nth, values));
 		//long end1 = System.currentTimeMillis();
 		//System.out.println("Time elapsed..." + (end1-begin1)/1000 + " seconds");
 		
 		System.out.println(dp_bottomUp_Fibonacci2(nth));
 		
 		/*long begin = System.currentTimeMillis();
 		System.out.println(recursiveFibonacci(nth));
 		long end = System.currentTimeMillis();
 		System.out.println("Time elapsed..." + (end-begin)/1000 + " seconds");*/
 		
 		
 		//System.out.println(Long.MAX_VALUE);
	}

}
