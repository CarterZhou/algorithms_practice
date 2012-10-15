package com.cupid.algorithm;

public class SearchLargestSmallerNumber {

	private static int binarySearchNearest(int[] array,int x){
		int nearest = 0;
		int low = 0;
		int high = array.length-1;
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
				if(x<array[mid]){
					nearest = mid-1;
				// e.g. find 15 between 14,16 and finish[mid] = 14.
				// In this case return index of 14
			    }else if(x>array[mid]){
			    	nearest = mid;
				}
			 }
			if(x < array[mid]){
				 high = mid-1;
			}else if(x > array[mid]){
				low = mid+1;
			}else{
				// If array[mid] = x iterate backwards until the largest number < x is found.
				mid = mid -1;
				while(array[mid] == x && mid >=0){
					mid = mid-1;
				}
				// If no such value is found, return the first number of array
				if(mid <0){
					mid = 0;
				}
				nearest = mid;
				break;
			}
		}
		return nearest;
	}
	
	public static void main(String[] args) {
		int[] a = new int[]{0,4,5,6,7,9,9,9,9,10,11,12,14,16};
		int index = binarySearchNearest(a, 1);
		System.out.println("Index is " + index + " and value is " +  a[index]);
	}

}
