package com.cupid.algorithm.dp;

import java.util.Arrays;
import java.util.Stack;

public class LongestCommonSequenceTest {
	
	// Reset the standard output to a file.
	/*static{
		PrintStream out = null;
		try {
			OutputStream fos = new FileOutputStream("d:\\lcs.txt");
			out = new PrintStream(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(out);
	}*/
	
	private static int savedRecursions = 0;
	
	public static void print(int[][] c,char[] x,char[] y){
		for(int i=0;i<x.length;i++){
			for(int j=0;j<y.length;j++){
				System.out.format("%3s", c[i][j]);
			}
			System.out.println();
		}
	}
	
	public static int[] findMonoIncreasingNumberSequence(int[] original,int[][] maxLength){
		
		int result[] = null;
		int size = original.length;
		// Copy the integer array
		int[] sorted = original.clone();
		// Quick Sort, which takes O(N*lgN)
		Arrays.sort(sorted, 1, size);
		
		for(int i=0;i<size;i++){
			maxLength[0][i] =0;
		}
		for(int i=0;i<size;i++){
			maxLength[i][0] =0;
		}
		// DP, finding the LCS of the original and sorted number sequence.
		// It takes O(N*N)
		for(int i=1;i<size;i++){
			for(int j=1;j<size;j++){
				if(sorted[i] == original[j]){
					maxLength[i][j] = maxLength[i-1][j-1] + 1;
				}else if(maxLength[i-1][j] >= maxLength[i][j-1]){
					maxLength[i][j] = maxLength[i-1][j];
				}else{
					maxLength[i][j] = maxLength[i][j-1];
				}
			}
		}
		int resultSize = maxLength[size-1][size-1];
		result = new int[resultSize];
		
		int i=original.length-1;
		int j=original.length-1;
		// Find an optimal solution.
		while(i>0 && j>0){
			if(sorted[i] == original[j]){
				resultSize--;
				result[resultSize] = sorted[i];
				i = i-1;
				j = j-1;
			}else if(maxLength[i-1][j] >= maxLength[i][j-1]){
				i = i -1;
			}else{
				j = j-1;
			}
		}
		return result;
	}
	
	// Use dynamic programming to calculate LCS length of two strings , with a min(m,n)+1 size array 
	public static <T> int findLCSLengthBy1Array(T[] longArray,T[] shortArray){
		int size = Math.min(longArray.length, shortArray.length)-1;
		int length = 0;
		if(size>0){
			int[] lcs = new int[size+1];
			for(int i=1;i<longArray.length;i++){
				int lcs0 = lcs[0];
				for(int j=1;j<shortArray.length;j++){
					// Save lcs[j] acting as C[i-1,j-1] first
					// for the next calculation.
					int swap = lcs[j];
					if(longArray[i]==shortArray[j]){
						// If two chars match,
						// use the current C[i-1,j-1]
						lcs[j] = lcs[0]+1;
					}else if(lcs[j] >=lcs[j-1]){
						lcs[j] = lcs[j];
					}else{
						lcs[j] = lcs[j-1];
					}
					// Get C[i,j] after it is modified.
					lcs[0] = swap;
				} 
				lcs[0] = lcs0;
			}
			length = lcs[size];
		}
		return length ;
	}
	
	// Use dynamic programming to calculate LCS length of two strings with a 2*min(m,n) size table 
	public static <T> int findLCSLengthBy2Array(T[] longString,T[] shortString){
		int size = Math.min(longString.length, shortString.length)-1;
		int length = 0;
		if(size>0){
			int[][] lcs = new int[2][size+1];
			for(int i=1;i<longString.length;i++){
				int k=1;
				for(int j=1;j<shortString.length;j++){
					if(longString[i]==shortString[j]){
						lcs[k][j] = lcs[k-1][j-1] +1;
					}else if(lcs[k-1][j] >= lcs[k][j-1]){
						lcs[k][j] = lcs[k-1][j];
					}else{
						lcs[k][j] = lcs[k][j-1];
					}
				}
				// Assign current row to previous row.
				lcs[k-1] = lcs[k].clone();
				// And reset current row.
				for(int m=0;m<lcs[1].length;m++){
					lcs[1][m] = 0;
				}
			}
			// Final result is saved at lcs[previous][min(m,n)]
			length = lcs[0][size];
		}
		return length ;
	}
	
	public static <T> void dp_bottomUp_findLCSLength(T[] x,T[] y,int[][] lcsLength){
		for(int i=0;i<y.length;i++){
			lcsLength[0][i] =0;
		}
		for(int i=0;i<x.length;i++){
			lcsLength[i][0] =0;
		}
		for(int i=1;i<x.length;i++){
			for(int j=1;j<y.length;j++){
				if(x[i] == y[j]){
					lcsLength[i][j] = lcsLength[i-1][j-1] + 1;
				}else if(lcsLength[i-1][j] >= lcsLength[i][j-1]){
					lcsLength[i][j] = lcsLength[i-1][j];
				}else{
					lcsLength[i][j] = lcsLength[i][j-1];
				}
			}
		}
	}
	
	public static <T> void dp_topDown_findLCSLength(T[] x,T[] y,int[][] lcsLength){
		for(int i=0;i<y.length;i++){
			lcsLength[0][i] =0;
		}
		for(int i=0;i<x.length;i++){
			lcsLength[i][0] =0;
		}
		// Initialize the auxiliary table
		for(int i=1;i<x.length;i++){
			for(int j=1;j<y.length;j++){
				lcsLength[i][j] = -1;
			}
		}
		// Call the memoized-type recursive function that actually performs the task.
		memoized_findLCSLength(x, y, lcsLength, x.length-1, y.length-1);
	}
	
	private static <T> int memoized_findLCSLength(T[] x,T[] y,int[][] lcsLength,int i,int j){
		if(lcsLength[i][j]>=0){
			// Keep track of how many recursion calls are saved.
			savedRecursions++;
			return lcsLength[i][j];
		}else{
			int result = 0; 
			if(x[i] == y[j])
				result = memoized_findLCSLength(x, y, lcsLength, i-1, j-1) + 1;
			else{
				result = Math.max(memoized_findLCSLength(x, y, lcsLength, i-1, j),
							 memoized_findLCSLength(x, y, lcsLength, i, j-1));
			}
			// Do not forget to update auxiliary table.
			lcsLength[i][j] = result;
			return result;
		}
	}
	// Get a concrete optimal solution.
	public static <T> String findLCS(int[][] lcsLength,T[] x,T[] y){
		StringBuilder sb = new StringBuilder();
		int i=x.length-1;
		int j=y.length-1;
		while(i>0 && j>0){
			if(x[i] == y[j]){
				sb.insert(0, x[i]);
				i = i-1;
				j = j-1;
			}else if(lcsLength[i-1][j] >= lcsLength[i][j-1]){
				i = i -1;
			}else{
				j = j-1;
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		Character[] x1 = new Character[201];
		Character[] y1 = new Character[101];
		x1[0] = '?';
		y1[0] = '?';
		
		// Randomly generate character of English letters in lower case.
		for(int i=1;i<x1.length;i++){
			char randomChar = (char)(97+(int)(Math.random()*26));
			x1[i] = randomChar;
		}
		for(int i=1;i<y1.length;i++){
			char randomChar = (char)(97+(int)(Math.random()*26));
			y1[i] = randomChar;			
		}
		
		int[][] bottom_lcsLength1 = new int[x1.length][y1.length];
		int[][] top_lcsLength1 = new int[x1.length][y1.length];
		
		
		dp_topDown_findLCSLength(x1, y1, top_lcsLength1);
		dp_bottomUp_findLCSLength(x1,y1,bottom_lcsLength1);
		
		String bottom_lcs = findLCS(bottom_lcsLength1,x1, y1);
		String top_lcs = findLCS(top_lcsLength1, x1, y1);
		
		
		// Test that bottom-up and top-down approaches get the same result.
		System.out.println("The longest common sequence found by bottom-up manner is " + bottom_lcs);
		System.out.println("The longest common sequence found by top-down manner is " + top_lcs);
		System.out.println("Are they the same? " + bottom_lcs.equals(top_lcs));
		
		// Test that findLCSLengthBy2Array() and findLCSLengthBy1Array() are correct.
		System.out.println("The LCS length calculated by normal m*n table is " + bottom_lcs.length());
		System.out.println("The LCS length calculated by 2*min(m,n) entries is "+ findLCSLengthBy2Array(x1, y1));
		System.out.println("The LCS length calculated by min(m,n)+1 entries is "+findLCSLengthBy1Array(x1, y1));
		
		// Print out how many duplicate recursion calls for solving overlapping sub-problems were saved.
		System.out.println("Recursion calls saved by memoized top-down manner: " + savedRecursions + " times...");
		
		int[] numberSequence = new int[]{Integer.MIN_VALUE,2,3,6,1,4};
	//	int[] numberSequence = new int[]{Integer.MIN_VALUE,19,4,6,1,7,21,12,8,3,3,5,10,9,4};
		int size = numberSequence.length;
		int[][] maxLength = new int[size][size];
		
		int[] result = findMonoIncreasingNumberSequence(numberSequence,maxLength);
		for(int i=0;i<maxLength.length;i++){
			for(int j=0;j<maxLength.length;j++){
				System.out.format("%3s", maxLength[i][j]);
			}
			System.out.println();
		}
		for (int i : result) {
			System.out.print(i);
		}
	}

}
