package com.cupid.algorithm.dp;

public class ActivitySelection {
	
	public static void ConstructSolution(int[] opt,int[] compatible,int[] v,int number){
		if(number == 0){
			System.out.println("done");
		}else if(v[number] + opt[compatible[number]] > opt[number-1]){
			System.out.println(v[number] + " by "+ " Activity " + number);
			ConstructSolution(opt, compatible, v, compatible[number]);
		}else{
			ConstructSolution(opt, compatible, v, number-1);
		}
	}
	
	// CLRS 16.1-5
	public static int ActivitySelectionMaximizingValue(int[] start,int[] finish,int[] v,int n,int[] opt){
		int[] compatible = new int[n+1];
		// Find out all compatible activities for each activity,
		// runs in O(N*lgN)
		for(int i=1;i<compatible.length;i++){
			compatible[i] = binarySearchCompatible(finish,start[i]);
		}

		for(int i=1;i<opt.length;i++){
			opt[i] = 0;
			if(opt[compatible[i]]+v[i] > opt[i-1]){
				opt[i] = opt[compatible[i]]+v[i];
			}else{
				opt[i] = opt[i-1];
			}
		}
		
		ConstructSolution(opt,compatible,v,n);
		
		return opt[n];
	}
	
	// Given activity j's start time and finish time table, 
	// do a binary search to find a compatible activity i with activity j, 
	// where i is the largest number <= j  
	private static int binarySearchCompatible(int[] finish,int startTime){
		int highestCompatible = 0;
		int low = 0;
		int high = finish.length-1;
		int mid = 0;
		// Do binary search
		while(low<=high){
			mid = (low+high)/2;
			// When low = high,
			// an exact finish time may or may not be found.
			// Assign value to highestCompatible accordingly.
			if(low==high){
				// e.g. find 15 between 14,16 and finish[mid] = 16.
				// In this case return index of 14
				if(startTime<finish[mid]){
					highestCompatible = mid-1;
				// e.g. find 15 between 14,16 and finish[mid] = 14.
				// In this case return index of 14
			    }else if(startTime>finish[mid]){
			    	highestCompatible = mid;
				}
			 }
			if(startTime<finish[mid]){
				 high = mid-1;
			}else if(startTime>finish[mid]){
				low = mid+1;
			}else{
				highestCompatible = mid;
				break;
			}
		}
		return highestCompatible;
	}
	
	public static void main(String[] args) {
		int[] start = new int[]{-1,1,3,0,5,3,5,6,8,8,2,12};
		 // If finish-time array is not in order, sort it in O(N*lgN)
		int[] finish = new int[]{0,4,5,6,7,9,9,10,11,12,14,16};
		// Different weights are given to different activities.
		int[] value = new int[]{0,3,2,4,8,2,5,6,5,7,4,5};
		int[] optimal = new int[start.length];
		optimal[0] = 0;
		int size = start.length-1;
		System.out.println("The maximum value is " + ActivitySelectionMaximizingValue(start, finish, value, size,optimal));
	}

}
