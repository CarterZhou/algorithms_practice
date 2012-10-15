package com.cupid.algorithm.backtrack;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class KnightsTour {
	
	public KnightsTour(){
	/*	try {
			OutputStream fos = new FileOutputStream("d:\\knightstour_6.txt");
			PrintStream out = new PrintStream(fos);
			System.setOut(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
	}
	
	
	private  int[][] chessBoard = null;
	
	private static long backTrackCounter = 0l;
	
	private static int solutionCounter = 0;
	
	private  int rows = 8;
	private  int cols = 8;
	
	private  void printSolution(int[][] cb){
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++)
				System.out.print(cb[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	private  void initChessBoard() {
		chessBoard = new int[rows][cols];
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				chessBoard[i][j] = 0;
	}
	
	public  boolean backTrack_knightsTour(/*int[][] cb,*/int row, int column, int step) {
		boolean success = false;
		PossibleMoves pms = new PossibleMoves(row,column);
		int moveIndex = 0;
		while(!success && moveIndex < pms.moves.length ){
			int nextRow = pms.moves[moveIndex].row;
			int nextCol = pms.moves[moveIndex].col;
			if(isValid(chessBoard,row,column)){
				chessBoard[row][column] = step;
				if(step == rows * cols){
					// When a solution is found,set success as true.
					// This will cause the method to return immediately.
					success = true;
					
					// If the method is supposed to find out all solutions,
					// do not set success flag.
					// Just print a particular solution
					// and reset current position
					/*solutionCounter++;
					printSolution(cb);
					chessBoard[row][column] = 0;
					break;*/
				}else{
					success = backTrack_knightsTour(/*cb,*/nextRow,nextCol, step+1);
					if(!success){
						chessBoard[row][column] = 0;
					}
				}
			}
			moveIndex ++;
		}
		if(moveIndex == 8)
			backTrackCounter++;
		return success;
	}
	private  boolean isValid(int[][] chessBoard, int row, int col) {
		if((0 <= row && row <= rows-1) && (0 <= col && col <= cols-1)){
			if(chessBoard[row][col] == 0)
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		KnightsTour kt = new KnightsTour();
		kt.initChessBoard();
		long begin = System.currentTimeMillis();
	//	for(int i=0;i<kt.rowsAndCols;i++){
		//	for(int j=0;j<kt.rowsAndCols;j++){
				kt.backTrack_knightsTour(/*kt.chessBoard,*/1,0,1);
			//}
		//}
		long end = System.currentTimeMillis();
		for(int i=0;i<kt.rows;i++){
			for(int j=0;j<kt.cols;j++)
				System.out.print(kt.chessBoard[i][j] + " ");
			System.out.println();
		}		
		
		//System.out.println("\nthere are " + solutionCounter + " solutions...");
		System.out.println("running time: "+ ((double)(end-begin))/1000 + " seconds...");
		System.out.println("\ndid backtrack " +backTrackCounter+" times...");
				
	}
}

class Move{
	public int row = 0;
	public int col = 0;
}

class PossibleMoves{
	
	public Move[] moves = new Move[8];
	
	public PossibleMoves(int r,int c){
		moves[0] = new Move();
		moves[0].row = r-2;
		moves[0].col = c+1;
		
		moves[1] = new Move();
		moves[1].row = r-1;
		moves[1].col = c+2;
		
		moves[2] = new Move();
		moves[2].row = r+1;
		moves[2].col = c+2;
		
		moves[3] = new Move();
		moves[3].row = r+2;
		moves[3].col = c+1;
		
		moves[4] = new Move();
		moves[4].row = r+2;
		moves[4].col = c-1;
		
		moves[5] = new Move();
		moves[5].row = r+1;
		moves[5].col = c-2;
		
		moves[6] = new Move();
		moves[6].row = r-1;
		moves[6].col = c-2;
		
		moves[7] = new Move();
		moves[7].row = r-2;
		moves[7].col = c-1;
	}
}
