package com.cupid.algorithm.sort;


public class Heap {
	
	private int heapSize = 0;
	
	private void maxHeapify(int[] array,int index){
		int left = leftChild(index);
		int right = rightChild(index);
		int largest = index;
		if(left <= heapSize && array[index] < array[left] ){
			largest = left;
		}
		if(right<= heapSize && array[largest] < array[right]){
			largest = right;
		}
		if(largest != index){
			int tmp = array[index];
			array[index] = array[largest];
			array[largest] = tmp;
			maxHeapify(array,largest);
		}
	}
	// Because the nodes with indices floor(n/2) + 1 ... n
	// ARE　leaves, there is no need to call maxHeapify on such nodes.
	private void buildHeap(int[] array){
		for(int i = heapSize/2;i>0;i--){
			maxHeapify(array, i);
		}
	}
		
	public void heapSort(int[] array){
		heapSize = array.length - 1;
		buildHeap(array);
		while(heapSize > 1){
			int max = array[1];
			array[1] = array[heapSize];
			array[heapSize] = max;
			heapSize--;
			maxHeapify(array,1);
		}
	}
	
	private int leftChild(int index){
		return index*2;
	}
	
	private int rightChild(int index){
		return index*2 + 1;
	}
	
	private int parent(int index){
		return index/2;
	}
}
