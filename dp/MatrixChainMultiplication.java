package com.cupid.algorithm.dp;

import java.util.Random;

public class MatrixChainMultiplication {
	
	public static int counter = 0;
	
	// Print out the optimal parenthesization of matrices
	public static void printSolution(int[][] sol,int i,int j){
		if(i==j){
			System.out.print("A"+i);
		}else{
			System.out.print("(");
			printSolution(sol, i, sol[i][j]);
			printSolution(sol, sol[i][j]+1, j);
			System.out.print(")");
		}
	}
	// Multiply two matrices according to mathematical definition
	private static Matrix multiply(Matrix m1,Matrix m2) throws MatrixOperationException{
		if(m1.columns != m2.rows){
			throw new MatrixOperationException();
		}
		Matrix product = new Matrix(m1.rows, m2.columns);
		for(int i=0;i<product.rows;i++){
			for(int j=0;j<product.columns;j++)
				product.mtrx[i][j] = 0;
		}
		for(int i=0;i<m1.rows;i++){
			for(int j=0;j<m2.rows;j++){
				for(int k=0;k<m2.columns;k++){
					product.mtrx[i][k] = product.mtrx[i][k] + m1.mtrx[i][j] * m2.mtrx[j][k];
					counter ++;
				}
			}
		}
		return product;
	}
	
	// Do the actual multiplication recursively with the provided solution table.
	public static Matrix matrixChainMultiply(Matrix[] matrices, int[][] solutions, int i, int j) throws MatrixOperationException {
		if(i==j){
			return matrices[i];
		}else{
			Matrix m1 = matrixChainMultiply(matrices, solutions, i, solutions[i][j]);
			Matrix m2 = matrixChainMultiply(matrices, solutions, solutions[i][j]+1,j);
			return multiply(m1, m2);
		}
	}
	
	// Use dynamic programming to work out the least times of multiplication will be performed
	// when a chain of matrices are multiplied.
	public static void dp_bottomUp_MatrixChainOrder(int[] p,int[][] m,int[][] solutions){
		int n = p.length-1;
		for(int i=1;i<=n;i++){
			m[i][i] = 0;
		}
		for(int l=2;l<=n;l++){
			for(int i=1;i<=n-l+1;i++){
				int j =	i+l-1;
				m[i][j] = Integer.MAX_VALUE;
				for(int k=i;k<=j-1;k++){
					int q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
					if(q<m[i][j]){
						m[i][j] = q;
						solutions[i][j] = k;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] p1 = new int[]{30,35,15,5,10,20,25};
		int[][] solutions1 = new int[p1.length][p1.length];
		int[][] m1 = new int[p1.length][p1.length];
		
		Matrix[] matrices = new Matrix[p1.length];
		
		Matrix mx0 = new Matrix(0, 0);
		Matrix mx1 = new Matrix(30, 35);
		Matrix mx2 = new Matrix(35, 15);
		Matrix mx3 = new Matrix(15, 5);
		Matrix mx4 = new Matrix(5, 10);
		Matrix mx5 = new Matrix(10, 20);
		Matrix mx6 = new Matrix(20, 25);
		
		
		matrices[0] = mx0;
		matrices[1] = mx1;
		matrices[2] = mx2;
		matrices[3] = mx3;
		matrices[4] = mx4;
		matrices[5] = mx5;
		matrices[6] = mx6;
		
		// Initialize all the matrices, filling random numbers.
		for(int s=1;s<matrices.length;s++){
			for(int i=0;i<matrices[s].rows;i++){
				for(int j=0;j<matrices[s].columns;j++){
					matrices[s].mtrx[i][j] = new Random().nextInt(5);
				}
			}
		}

		dp_bottomUp_MatrixChainOrder(p1, m1,solutions1);
		
		// Do the matrix chain multiplication, with optimal solutions table being provided.

		Matrix result = null;
		try {
			result = matrixChainMultiply(matrices, solutions1, 1, p1.length-1);
		} catch (MatrixOperationException e) {
			e.printStackTrace();
		}
		
		// Print out the result, a matrix of p0 * p6 size
		if(result != null){
			for(int i=0;i<result.rows;i++){
				String d = ",";
				for(int j=0;j<result.columns;j++){
					if(j == result.columns-1)
						d = "";
					System.out.print(result.mtrx[i][j]+d);
				}
				System.out.println();
			}
		}
		// Print out the number of multiplication of optimal solution.
		System.out.println("\nMultiplication was done " + m1[1][p1.length-1] +" times");
		// Use a global counter to confirm that the optimal solution was in fact adopted
		// when a chain of matrices was multiplied.
		System.out.println("Multiplication was done " + counter +" times, recorded by a counter");
		
		
	}


}

class MatrixOperationException extends Exception{
	
}

class Matrix{
	public int rows = 0;
	public int columns = 0;
	public int[][] mtrx = null;
	
	public Matrix(int rows,int columns){
		this.rows = rows;
		this.columns = columns;
		mtrx = new int[rows][columns];
	}
}
