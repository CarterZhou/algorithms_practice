package com.cupid.algorithm.backtrack;

public class NQueens {

	private static char[][] chessBoard = null;
	
	private static int rowsAndCols = 8;
	
	private static int solutionCount = 0;
	
	public static void initChessBoard(int queens){
		chessBoard = new char[queens][queens];
		for(int i=0;i<queens;i++)
			for(int j=0;j<queens;j++)
				chessBoard[i][j] = '_';
	}
	
	public static void printSolution(char[][] chessBoard){
		for(int i=0;i<rowsAndCols;i++){
			for(int j=0;j<rowsAndCols;j++)
				System.out.print(chessBoard[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	public static boolean backTrack_nQueen(char[][] chessBoard,int column){
		boolean success = false;
		for(int row=0;row<rowsAndCols;row++){
			if(isValid(row,column)){
				chessBoard[row][column] = 'Q';
				if(column == rowsAndCols-1){
					// When a solution is found,set success as true.
					// This will cause the method to return immediately.
					//success = true;
					
					// If the method is supposed to find out all solutions,
					// do not set success flag.
					// Just print a particular solution
					// and reset current position
					solutionCount ++;
					printSolution(chessBoard);
					chessBoard[row][column] = '_';
				}else{
					success = backTrack_nQueen(chessBoard,column+1);
					if(!success){
						chessBoard[row][column] = '_';
					}
				}
			}
		}
		return success;
	}
	
	private static boolean isValid(int row,int column){
		int bound = rowsAndCols -1;
		int r = row;
		int c = column;
		
		while(r-1>=0 && c-1>=0){
			if(chessBoard[r-1][c-1] == 'Q'){
				return false;
			}
			r = r-1;
			c = c-1;
		}
		r = row;
		c = column;
		
		while(c-1>=0){
			if(chessBoard[r][c-1] == 'Q'){
				return false;
			}
			c = c-1;
		}
		c = column;
		
		while(r+1 <= bound && c-1 >=0){
			if(chessBoard[r+1][c-1] == 'Q'){
				return false;
			}
			r = r+1;
			c = c-1;
		}
		return true;
	}
	
	public static void main(String[] args) {
		initChessBoard(rowsAndCols);
		boolean s = backTrack_nQueen(chessBoard,0);
		System.out.println(solutionCount);
		for(int i=0;i<rowsAndCols;i++){
			for(int j=0;j<rowsAndCols;j++)
				System.out.print(chessBoard[i][j] + " ");
			System.out.println();
		}
	}

}
