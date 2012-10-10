package com.cupid.algorithm.dp;

public class RodCuttingTest {

	public static int dp_topDown_rodCutting(int[] payoffs,int[] solutions,int length,int cost){
		int[] results = new int[length+1];
		results[0] = 0;
		return dp_topDown_rodCutting_auxiliary(payoffs,solutions,length, results,cost);
	}
	
	private static int dp_topDown_rodCutting_auxiliary(int[] payoffs,int[] solutions,int length,int[] results,int cost){
		if(results[length] > 0){
			return results[length];
		}
		if(length==0){
			return 0;
		}
		int k = Integer.MIN_VALUE;
		int c = cost;
		for(int i=1;i<=length;i++){
			if(i==length){
				cost = 0;
			}
			int payoff = payoffs[i] + dp_topDown_rodCutting_auxiliary(payoffs,solutions,length-i, results,cost) - cost;
			if(k<payoff){
				k = payoff;
				solutions[length] = i;
			}
		}
		cost = c;
		results[length] = k;
		return results[length];
	}
	
	
	/**
	 * The bottom-up approach solve related sub-problems first for a given problem.
	 *	And then based on the recorded optimal solutions to sub-problems,it solves the problem altogether. 
	 *
	 * @param payoffs
	 * @param solutions
	 * @param length
	 * @param cost
	 * @return
	 */
	public static int dp_bottomUp_rodCutting(int[] payoffs,int[] solutions,int length,int cost){
		int[] results = new int[length+1];
		int c = cost;
		results[0] = 0;
		if(length ==0){
			return results[length];
		}
		for(int i=1;i<=length;i++){
			int k = Integer.MIN_VALUE;
			for(int j=1;j<=i;j++){
				if(j==i){
					cost = 0;
				}
				if(k < payoffs[j] + results[i-j] - cost){
					k = payoffs[j] + results[i-j] - cost;
					solutions[i] = j;
				}
			}
			cost = c;
			results[i] = k;
		}
		return results[length];
	}
	
	public static void main(String[] args) {
		int[] payoffs = new int[]{0,1,5,8,9,10,12,17,20,24,30};
		int length = 4;
		// The solutions array record the length of first cut-off piece of rod for given total length of rod
		int[] solutions = new int[length+1];
//		int optimalResult1 = dp_bottomUp_rodCutting(payoffs,solutions,length,1);
		int optimalResult2 = dp_topDown_rodCutting(payoffs,solutions,length,1);
//		System.out.println(optimalResult1);
		System.out.println(optimalResult2);
		System.out.println(solutions[length]);
	}

}
