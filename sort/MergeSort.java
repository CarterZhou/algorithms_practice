package com.cupid.algorithm.sort;

import java.util.Random;

public class MergeSort {
	
	public static void mergeSort(int[] a,int begin,int end){
		if(begin < end){
			int middle = (begin + end)/2;
			mergeSort(a,begin,middle);
			mergeSort(a,middle+1,end);
			merge(a,begin,middle,end);
		}
	}
	
	public static void merge(int[] a,int p,int q,int r){
		/*int[] left = new int[q-p+1];
		int[] right = new int[r-q];
		for(int i=0;i<left.length;i++){
			left[i] = a[p+i];
		}
		for(int i=0;i<right.length;i++){
			right[i] = a[q+1+i];
		}
		int leftIndex = 0;
		int rightIndex = 0;
		for(int i=p;i<=r;i++){
			if(leftIndex < left.length && rightIndex < right.length){
				if(left[leftIndex] <= right[rightIndex]){
					a[i] = left[leftIndex];
					leftIndex ++;
				}else{
					a[i] = right[rightIndex];
					rightIndex ++;
				}
			}else if(leftIndex < left.length){
				a[i] = left[leftIndex];
				leftIndex ++;
			}else{
				a[i] = right[rightIndex];
				rightIndex ++;
			}
		}*/
		
		// allocate one additional space 
		// in both left and right sorted sub-arrays
		// for storing the Integer.MAX_VALUE sentinels
		// for the convenience of comparing numbers in these arrays,
		// without caring about whether the numbers of one of two arrays
		// have been all copied into main array.
		int[] left = new int[q-p+1+1];
		int[] right = new int[r-q+1];
		for(int i=0;i<left.length-1;i++){
			left[i] = a[p+i];
		}
		left[left.length-1] = Integer.MAX_VALUE;
		for(int i=0;i<right.length-1;i++){
			right[i] = a[q+1+i];
		}
		right[right.length-1] = Integer.MAX_VALUE;
		int leftIndex = 0;
		int rightIndex = 0;
		for(int i=p;i<=r;i++){
			// No need to decide whether leftIndex or rightIndex
			// has already reached sub-array.length
			if(left[leftIndex] <= right[rightIndex]){
				a[i] = left[leftIndex];
				leftIndex ++;
			}else{
				a[i] = right[rightIndex];
				rightIndex ++;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] a = new int[10];
		for(int i=0;i<a.length;i++){
			Random r = new Random();
			a[i] = r.nextInt(100);
		}
		
		for (int num : a) {
			System.out.print(num + "-");
		}
		System.out.println();
		System.out.println("----------");
		mergeSort(a,0,a.length-1);
		for (int num : a) {
			System.out.print(num + "-");
		}
		System.out.println();
	}

}
