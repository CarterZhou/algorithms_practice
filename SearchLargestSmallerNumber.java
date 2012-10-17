package com.cupid.algorithm;

public class SearchLargestSmallerNumber {

	private static int binarySearchNearest(int[] array, int x) {
		int nearest = 0;
		int low = 0;
		int high = array.length - 1;
		int mid = 0;
		// Do binary search
		while (low <= high) {
			mid = (low + high) / 2;
			// When low = high,
			// an exact number may or may not be found.
			// Assign value to nearest accordingly.
			if (low == high) {
				// e.g. find 15 between 14,16 and array[mid] = 16.
				// In this case return index of 14
				if (x < array[mid]) {
					nearest = mid - 1;
					// e.g. find 15 between 14,16 and array[mid] = 14.
					// In this case return index of 14
				} else if (x > array[mid]) {
					nearest = mid;
				}
			}
			if (x < array[mid]) {
				if(mid == 0){
					nearest = -1;
				}
				high = mid - 1;
			} else if (x > array[mid]) {
				low = mid + 1;
			} else {
				// If array[mid] = x iterate backwards until the largest number < x is found.
				mid = mid - 1;
				while (mid >= 0 && array[mid] == x) {
					mid = mid - 1;
				}
				// If no such value is found, return the -1 indicating no value is less than x
				if (mid < 0) {
					mid = -1;
				}
				nearest = mid;
				break;
			}
		}
		return nearest;
	}

	public static void main(String[] args) {
		int[] a = new int[] { 3, 4, 5, 6, 7, 9, 9, 9, 9, 10, 11, 12, 14, 16 };
		int x = 9;
		int index = binarySearchNearest(a, x);
		if(index==-1){
			System.out.println("No value is less than " + x + " is found");
		}else{
			System.out.println("Index is " + index + " and value is " + a[index]);
		}
	}

}
