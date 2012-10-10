package com.cupid.algorithm;

public class MaxSubArrayTest {

	public static int maxSub(int a[],Result r){
		int sum = 0;
		int tmp_sum = 0;
		int count = 0;
		for(int i=0;i<a.length;i++){
			tmp_sum += a[i];
			count ++;
			if(sum <= tmp_sum){
				sum = tmp_sum;
				r.setBegin(i-count+1);
				r.setEnd(i);
			}else if(tmp_sum < 0){
				tmp_sum = 0;
				count = 0;
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		Result r = new Result();
		int[] a = new int[]{13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-2,15,-7,8};
		int result = maxSub(a,r);
		System.out.println("Maximum sub-array: "+result);
		System.out.println("begins at..." + r.getBegin());
		System.out.println("ends at..." + r.getEnd());
		
	}

}
