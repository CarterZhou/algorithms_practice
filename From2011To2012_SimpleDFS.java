package com.cupid.algorithm;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * @author Zhou Hao
 *	
 * The "from 2011 to 2012" maze is as follows:
 * 		  ---------  -------------
 *      	+7	   |  |     *3
 *           	   ----
 * 2011->                            -> 2012
 *  			   ----
 *          /2	   |  |     -5
 *        ---------   -------------
 *
 *  Rule: Start at 2011. By moving through the maze and doing any arithmetic operations you encounter, 
 *        exit the maze with a result of 2012. 
 *        You may pass through an operation several times, but not twice in a row.
 *  
 *  Problem source: http://www2.stetson.edu/~efriedma/holiday/2011/index.html
 *  Problem was discovered in : http://weibo.com/1915548291/z4eTPtAnv
 *  Problem first introduced in Chinese was in : http://www.matrix67.com/blog/archives/4790
 * 
 */

public class From2011To2012_SimpleDFS {
	
	private Set<Integer> values = new HashSet<Integer>();
	// Keep track of the number of visited nodes.
	private long visitedNodes = 0;
	// Keep track of the number of solutions within 30 levels of DFS tree.
	private int solutions= 0;
	private int entrance;
	private int exit;
	
	
	private  enum OperationAndDirection {
		 START(0),
		 ADD7_LEFT(1),ADD7_RIGHT(2),
		 SUB5_LEFT(3),SUB5_RIGHT(4), // SUB5_RIGHT is the ONLY OandD that leads to the exit 2012.
		 MUL3_LEFT(5),MUL3_RIGHT(6),
		 DIV2_LEFT(7),DIV2_RIGHT(8);
		 
		public final int value;

		OperationAndDirection(int value) {
			this.value = value;
		}
	}
	
	private class Node{
		public int value;
		public OperationAndDirection od;
		public Node parent;
		public Node(int v,OperationAndDirection od,Node p){
			this.value = v;
			this.parent = p;
			this.od = od;
		}


		@Override
		public String toString() {
			return value+":"+od;
		}

		@Override
		public boolean equals(Object obj) {
			return this.value == ((Node)obj).value && this.od == ((Node)obj).od;
		}

		@Override
		public int hashCode() {
			return this.value*10 + this.value + this.od.value;
		}
	}
	
	public From2011To2012_SimpleDFS(int en, int ex) {
		this.entrance = en;
		this.exit = ex;
	}

	private void dfsOrNot(Node n,int level){
		if(!values.contains(n.hashCode())){
			values.add(n.hashCode());
			dfs(n,level);
			values.remove(n.hashCode());
		}
	}
	
	private void dfs(Node n, int level){
		visitedNodes++;
		if (n.value == exit && n.od == OperationAndDirection.SUB5_RIGHT){
			solutions++;
			Stack<OperationAndDirection> operations = new Stack<OperationAndDirection>();
			while(n.parent!=null){
				operations.push(n.od);
				n = n.parent;
			}
			System.out.print(this.entrance);

			while(!operations.empty()) {
				OperationAndDirection op = operations.pop();
				if(op == OperationAndDirection.ADD7_LEFT || op == OperationAndDirection.ADD7_RIGHT){
					System.out.print(" +7");
				}else if(op == OperationAndDirection.SUB5_LEFT || op == OperationAndDirection.SUB5_RIGHT){
					System.out.print(" -5");
				}else if(op == OperationAndDirection.MUL3_LEFT || op == OperationAndDirection.MUL3_RIGHT){
					System.out.print(" *3");
				}else if(op == OperationAndDirection.DIV2_LEFT || op == OperationAndDirection.DIV2_RIGHT){
					System.out.print(" /2");
				}
			}
			System.out.println(" = " + this.exit);
		}
		
		if(level == 30){
			return;
		}else{
			int p = n.value;
			switch (n.od) {
			case START:
				dfsOrNot(new Node(entrance+7,OperationAndDirection.ADD7_RIGHT,n),level+1);
				dfsOrNot(new Node(entrance/2,OperationAndDirection.DIV2_RIGHT,n),level+1);
				break;
			case SUB5_RIGHT:
				dfsOrNot(new Node(p*3,OperationAndDirection.MUL3_LEFT,n),level+1);
				break;
			case SUB5_LEFT:
				dfsOrNot(new Node(p+7,OperationAndDirection.ADD7_LEFT,n),level+1);
				dfsOrNot(new Node(p/2,OperationAndDirection.DIV2_LEFT,n),level+1);
				dfsOrNot(new Node(p*3,OperationAndDirection.MUL3_RIGHT,n),level+1);
				break;
			case ADD7_RIGHT:
				dfsOrNot(new Node(p-5,OperationAndDirection.SUB5_RIGHT,n),level+1);
				dfsOrNot(new Node(p/2,OperationAndDirection.DIV2_LEFT,n),level+1);
				dfsOrNot(new Node(p*3,OperationAndDirection.MUL3_RIGHT,n),level+1);
				break;
			case ADD7_LEFT:
				dfsOrNot(new Node(p/2,OperationAndDirection.DIV2_RIGHT,n),level+1);
				break;
			case MUL3_RIGHT:
				dfsOrNot( new Node(p-5,OperationAndDirection.SUB5_LEFT,n),level+1);
				break;
			case MUL3_LEFT:
				dfsOrNot(new Node(p+7,OperationAndDirection.ADD7_LEFT,n),level+1);
				dfsOrNot(new Node(p-5,OperationAndDirection.SUB5_RIGHT,n),level+1);
				dfsOrNot(new Node(p/2,OperationAndDirection.DIV2_LEFT,n),level+1);
				break;
			case DIV2_RIGHT:
				dfsOrNot(new Node(p+7,OperationAndDirection.ADD7_LEFT,n),level+1);
				dfsOrNot(new Node(p-5,OperationAndDirection.SUB5_RIGHT,n),level+1);
				dfsOrNot(new Node(p*3,OperationAndDirection.MUL3_RIGHT,n),level+1);
				break;
			case DIV2_LEFT:
				dfsOrNot(new Node(p+7,OperationAndDirection.ADD7_RIGHT,n),level+1);
				break;
			default:
				break;
			}
		}
	}
	
	
	public void compute(){
		Node start = new Node(this.entrance,OperationAndDirection.START,null);
		dfs(start,1);
		System.out.println(visitedNodes + " nodes were visited.");
		System.out.println(solutions + " solutions.");
	}

	public static void main(String[] args) {
		From2011To2012_SimpleDFS problem =  new From2011To2012_SimpleDFS(2011,2012);
		long begin = System.currentTimeMillis();
		problem.compute();
		long end = System.currentTimeMillis();
		System.out.println("Time elapsed : " + (end - begin) + " ms");
	}

}
