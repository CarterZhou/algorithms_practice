package com.cupid.algorithm.sort;

import java.util.Random;

public class InsertionSortTest {
	
	public static void insertionSort(int[] a){
		for(int i=1;i<a.length;i++){
			int current = a[i];
			int j = i-1;
			while(j>=0 && a[j] > current){
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = current;
		}
	}
	
	public static void recursiveInsertionSort(int a[],int last){
		if(last == 0)
			return;
		else{
			recursiveInsertionSort(a, last-1);
			int current = a[last];
			int j = last-1;
			while(j>=0 && a[j] > current){
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = current;
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
		recursiveInsertionSort(a,a.length-1);
		for (int num : a) {
			System.out.print(num + "-");
		}
		System.out.println();
	}

}
