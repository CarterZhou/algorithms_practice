package com.cupid.algorithm.sort;


public class HeapSort {

	public static void main(String[] args) {
		int[] unsorted = new int[]{Integer.MIN_VALUE,5,3,17,10,85,19,6,22,9};
		new Heap().heapSort(unsorted);
		for(int i=1;i<unsorted.length;i++){
			System.out.print(unsorted[i] + "-");
		}
	}

}
