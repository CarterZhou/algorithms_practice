package com.cupid.algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
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

public class From2011To2012_SimpleBFS {
	
	private Queue<Node> src = new LinkedList<Node>();
	private Queue<Node> des = new LinkedList<Node>();
	private Set<Integer> values = new HashSet<Integer>();
	// Keep track of the number of visited nodes.
	private int visitedNodes = 0;
	
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
	
	private void addOrNot(Node n){
		if(!values.contains(n.hashCode())){
			values.add(n.hashCode());
			src.add(n);
			visitedNodes++;
		}
	}
	
	// A simple solution is as follows:
	// Use BFS to generate numbers on each level, and  test whether a number is 2012,
	// and the number is either calculated by either *3 or -5.
	// In this case, because 2012 cannot be divided by 3,
	// thus only subtracting 5 is a valid operation. That is, the final calculation should be 2017 - 5 = 2012
	// Also notice that -5 should be a from-left-to-right operation.
	// That is, after -5, the direction is going to the exit, not going back to the entrance.
	public void compute(int entrance,int exit){
		Node start = new Node(entrance,OperationAndDirection.START,null);
		Node n1 = new Node(entrance+7,OperationAndDirection.ADD7_RIGHT,start);
		Node n2 = new Node(entrance/2,OperationAndDirection.DIV2_RIGHT,start);
		addOrNot(start);
		addOrNot(n1);
		addOrNot(n2);
		
		// BFS
		while(!src.isEmpty()){
			Node n = src.poll();
			int p = n.value;
			if (n.value == exit && n.od == OperationAndDirection.SUB5_RIGHT){
				Stack<OperationAndDirection> operations = new Stack<OperationAndDirection>();
				while(n.parent!=null){
					operations.push(n.od);
					n = n.parent;
				}
				System.out.print(entrance);

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
				System.out.println(" = " + exit);
				System.out.println(visitedNodes + " nodes were visited.");
				return;
			}
			switch (n.od) {
			case SUB5_RIGHT:
				addOrNot(new Node(p*3,OperationAndDirection.MUL3_LEFT,n));
				break;
			case SUB5_LEFT:
				addOrNot(new Node(p+7,OperationAndDirection.ADD7_LEFT,n));
				addOrNot(new Node(p/2,OperationAndDirection.DIV2_LEFT,n));
				addOrNot(new Node(p*3,OperationAndDirection.MUL3_RIGHT,n));
				break;
			case ADD7_RIGHT:
				addOrNot(new Node(p-5,OperationAndDirection.SUB5_RIGHT,n));
				addOrNot(new Node(p/2,OperationAndDirection.DIV2_LEFT,n));
				addOrNot(new Node(p*3,OperationAndDirection.MUL3_RIGHT,n));
				break;
			case ADD7_LEFT:
				addOrNot(new Node(p/2,OperationAndDirection.DIV2_RIGHT,n));
				break;
			case MUL3_RIGHT:
				addOrNot( new Node(p-5,OperationAndDirection.SUB5_LEFT,n));
				break;
			case MUL3_LEFT:
				addOrNot(new Node(p+7,OperationAndDirection.ADD7_LEFT,n));
				addOrNot(new Node(p-5,OperationAndDirection.SUB5_RIGHT,n));
				addOrNot(new Node(p/2,OperationAndDirection.DIV2_LEFT,n));
				break;
			case DIV2_RIGHT:
				addOrNot(new Node(p+7,OperationAndDirection.ADD7_LEFT,n));
				addOrNot(new Node(p-5,OperationAndDirection.SUB5_RIGHT,n));
				addOrNot(new Node(p*3,OperationAndDirection.MUL3_RIGHT,n));
				break;
			case DIV2_LEFT:
				addOrNot(new Node(p+7,OperationAndDirection.ADD7_RIGHT,n));
				break;
			default:
				break;
			}
		}
	}

	public static void main(String[] args) {
		From2011To2012_SimpleBFS problem =  new From2011To2012_SimpleBFS();
		int entrance = 2011;
		int exit = 2012;
		long begin = System.currentTimeMillis();
		problem.compute(entrance,exit);
		long end = System.currentTimeMillis();
		System.out.println("Time elapsed : " + (end - begin) + " ms");
	}

}
