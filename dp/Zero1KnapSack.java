package com.cupid.algorithm.dp;

public class Zero1KnapSack {
	
	// c[i,w] =c[i-1,w]  if wi > w
	// c[i,w] = max{c[i-1,w-wi] + vi,c[i-1,w]}  if wi <= w
	public static int ZeroOneKnapSack(int[] wt,int[] v,int weight,int n){
		int[][] c = new int[n+1][weight+1];
		
		for(int i=1;i<=n;i++){
			c[i][0] = 0; 
		}
		for(int w=1;w<=weight;w++){
			c[0][w] = 0; 
		}
		
		for(int i=1;i<=n;i++){
			for(int w = 1; w<=weight;w++){
				c[i][w] = 0; 
				if(wt[i] > w){
					c[i][w] = c[i-1][w];
				}else {
					if(c[i-1][w-wt[i]] + v[i] > c[i-1][w]){
						c[i][w] = c[i-1][w-wt[i]] + v[i];
					}else{
						c[i][w] = c[i-1][w];
					}
				}
			}
		}
		return c[n][weight];
	}
	
	public static void main(String[] args) {
		 
		int weight = 50;
		int[] wt = new int[]{-1,10,20,30};
		int[] v = new int[]{-1,60,100,120};
		int size = wt.length-1;
		System.out.println(ZeroOneKnapSack(wt, v, weight, size));
	}

}
