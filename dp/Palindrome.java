package com.cupid.algorithm.dp;

public class Palindrome {
	
	public static String longestPalindromeSubsequence(char[] input){
		char[] reversedInput = input.clone();
		// Reverse the input string first.
		for(int i=1,j=reversedInput.length-1;i<=j;i++,j--){
			char swap = reversedInput[j];
			reversedInput[j] = reversedInput[i];
			reversedInput[i] = swap;
		}

		int[][] lcsLength = new int[input.length][input.length];
		
		// Find LCS of input string and its reversion
		LongestCommonSequence.dp_bottomUp_findLCSLength(input, reversedInput, lcsLength);
		
		// Construct a solution based on the calculated table.
		String result = LongestCommonSequence.findLCS(lcsLength, input, reversedInput);
		
		return result;
	}
	
	public static void main(String[] args) {
		
		//String input = "?character";
		
		int size = 11;
		char[] input = new char[size];
		input[0] = '?';
		// Randomly generate 10 English letters and add them into the input string
		for(int i=1;i<size;i++){
			char randomChar = (char)(97+(int)(Math.random()*26));
			input[i] = randomChar;
		}
		String result = longestPalindromeSubsequence(input);
		System.out.println(new String(input).substring(1));
		System.out.println(new String(result));
	}

}
