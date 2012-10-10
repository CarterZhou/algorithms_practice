package com.cupid.algorithm.sort;

import java.util.Random;

public class SelectionSortTest {

	
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
	
	public  static void selectionSort(int[] a){
		for(int i=0;i<a.length;i++){
			int n_smallest = a[i];
			int j = i + 1;
			int position = i;
			while(j<a.length){
				if(n_smallest > a[j]){
					n_smallest = a[j];
					position = j;
				}
				j++;
			}
			int tmp = a[i];
			a[i] = n_smallest;
			a[position] = tmp;
		}
		
	}
	
	public static void main(String[] args) {
		int[] a = new int[100];
		for(int i=0;i<a.length;i++){
			Random r = new Random();
			a[i] = r.nextInt(10000);
		}
		
		for (int num : a) {
			System.out.print(num + "-");
		}
		System.out.println();
		System.out.println("----------");
		selectionSort(a);
		for (int num : a) {
			System.out.print(num + "-");
		}
		System.out.println();
	}

}
