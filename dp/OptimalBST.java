package com.cupid.algorithm.dp;

import java.util.Random;

public class OptimalBST {
	
	// CLRS 15.5-2
	public static void ConstructOptimalBST(int[][] root, int lowerKey,int higherKey,int numberOfKeys){
		int parent = root[lowerKey][higherKey];
		
		// Construct the root of optimal BST
		if(higherKey == numberOfKeys && lowerKey == 1){
			System.out.println("K"+parent+" is the root.");
		}
		// Construct left sub-tree
		if(lowerKey<=parent-1){
			System.out.println("K"+root[lowerKey][parent-1]+" is the left child of K"+parent );
			ConstructOptimalBST(root, lowerKey, parent-1, numberOfKeys);
		}else{
			System.out.println("D"+ (parent-1) +" is the left child of K"+parent );
		}
		// Construct right sub-tree
		if(higherKey >=parent+1){
			System.out.println("K"+root[parent+1][higherKey]+" is the right child of K"+parent );
			ConstructOptimalBST(root, parent+1, higherKey, numberOfKeys);
		}else{
			System.out.println("D"+ parent +" is the right child of K"+parent );
		}
	}
	
	// Normal dp function for optimal BST problem
	public static int[][] dp_bottomUp_OptimalBST(int[] p,int q[],int numberOfKeys,int[][] cost){
		int n = numberOfKeys;
		int[][] w = new int[n+1+1][n+1];
		int[][] root = new int[n+1][n+1];
		
		for(int i=0;i<=n;i++){
			cost[i+1][i] = q[i];
		}
		for(int i=0;i<=n;i++){
			w[i+1][i] = q[i];
		}
		
		for(int k=1;k<=n;k++){
			for(int i=1;i<=n-k+1;i++){
				int j = i+k-1;
				cost[i][j] = Integer.MAX_VALUE;
				w[i][j] = w[i][j-1] + p[j] + q[j];
				for(int r=i;r<=j;r++){
					int t = cost[i][r-1] + cost[r+1][j] + w[i][j];
					if(t < cost[i][j]){
						cost[i][j] = t;
						root[i][j] = r;
					}
				}
			}
		}
		return root;
	}
	// CLRS 15.5-4
	// It is claimed here 
	// http://ripcrixalis.blog.com/2011/02/08/clrs-15-5-optimal-binary-search-trees/
	// that this slightly modified version runs in O(n*n)
	// not quite understand though...
	public static int[][] dp_bottomUp_OptimalBST_optimized(int[] p,int q[],int numberOfKeys,int[][] cost){
		int n = numberOfKeys;
		int[][] w = new int[n+1+1][n+1];
		int[][] root = new int[n+1+1][n+1];
		
		for(int i=0;i<=n;i++){
			cost[i+1][i] = q[i];
		}
		for(int i=0;i<=n;i++){
			w[i+1][i] = q[i];
		}
		for(int i=1;i<=n+1;i++){
			root[i][i-1] = i-1;
		}
		for(int k=1;k<=n;k++){
			for(int i=1;i<=n-k+1;i++){
				int j = i+k-1;
				cost[i][j] = Integer.MAX_VALUE;
				w[i][j] = w[i][j-1] + p[j] + q[j];
				// get the number of root between root[i][j-1] and root[i+1][j]
				for(int r=root[i][j-1];r<=root[i+1][j];r++){
					if(k == 1){
						r = r+1;
					}
					int t = cost[i][r-1] + cost[r+1][j] + w[i][j];
					if(t < cost[i][j]){
						cost[i][j] = t;
						root[i][j] = r;
					}
				}
			}
		}
		return root;
			
	}
	
	public static void main(String[] args) {
		// These probabilities are presented as integer + %.
		// The probabilities associated with successful search.
		int[] p = new int[]{-1,15,10,5,10,20};
		// The probabilities associated with unsuccessful search.
		int[] q = new int[]{5,10,5,5,5,10};
		
		
		// CLRS 15.5-2		
		/*int[] p = new int[]{-1,4,6,8,2,10,12,14};
		int[] q = new int[]{6,6,6,6,5,5,5,5};*/
		
		int numberOfKeys = 5;
		
		// Test optimized OptimalBST function by comparison.
		int[][] cost = new int[numberOfKeys+1+1][numberOfKeys+1];
		int[][] cost_op = new int[numberOfKeys+1+1][numberOfKeys+1];
		
		int[][] root = dp_bottomUp_OptimalBST(p, q, numberOfKeys,cost);
		int[][] root_op = dp_bottomUp_OptimalBST_optimized(p, q, numberOfKeys,cost_op);
		
		System.out.println("A search cost of this optimal BST is " + (double)cost[1][numberOfKeys]/100 + "\n");
		System.out.println("A search cost of this optimal BST is " + (double)cost_op[1][numberOfKeys]/100 + "\n");		
		
		ConstructOptimalBST(root, 1, numberOfKeys, numberOfKeys);
		System.out.println();
		ConstructOptimalBST(root_op, 1, numberOfKeys, numberOfKeys);
		
	}
}
