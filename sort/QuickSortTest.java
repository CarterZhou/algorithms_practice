package com.cupid.algorithm.sort;

public class QuickSortTest {

	
	public static void quickSort(int[] a,int begin,int end){
		if(begin < end){
			int pivotIndex = partition(a,begin,end);
			quickSort(a,begin,pivotIndex-1);
			quickSort(a,pivotIndex+1,end);
		}
	}
	
	private static int partition(int[] a,int begin,int end){
		int pivot = a[end];
		int i=begin-1;
		for(int j = begin;j<end;j++){
			if(a[j] <= pivot){
				i++;
				int tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
			}
		}
		int tmp = a[i+1];
		a[i+1] = a[end];
		a[end] = tmp;
		return i+1;
	}
	
	public static void main(String[] args) {
		int[] unsorted = new int[]{5,3,17,10,85,19,6,22,9};
		quickSort(unsorted,0,unsorted.length-1);
		for(int i=1;i<unsorted.length;i++){
			System.out.print(unsorted[i] + "-");
		}
	}

}
