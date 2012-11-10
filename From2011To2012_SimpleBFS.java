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
	
	Queue<Node> q = new LinkedList<Node>();
	Set<Integer> values = new HashSet<Integer>();
	
	private  enum OperationAndDirection {
		 START(0),
		 LEFT_7(1),RIGHT_7(2),
		 LEFT_5(3),RIGHT_5(4),
		 LEFT_3(5),RIGHT_3(6),
		 LEFT_2(7),RIGHT_2(8);
		 
		public final int value;

		OperationAndDirection(int value) {
			this.value = value;
		}
	}
	
	private class Node{
		public int value;
		public OperationAndDirection od;
		public Node parent;
		public int level;
		public Node(int v,OperationAndDirection od,Node p,int l){
			this.value = v;
			this.parent = p;
			this.od = od;
			this.level = l;
		}


		@Override
		public String toString() {
			return value+":"+level+":"+od;
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
	
	public void addOrNot(Node n){
		if(!values.contains(n.hashCode())){
			values.add(n.hashCode());
			q.add(n);
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
		Node start = new Node(entrance,OperationAndDirection.START,null,1);
		Node n1 = new Node(entrance+7,OperationAndDirection.RIGHT_7,start,2);
		Node n2 = new Node(entrance/2,OperationAndDirection.RIGHT_2,start,2);
		addOrNot(start);
		addOrNot(n1);
		//addOrNot(n2);
		
		// BFS
		while(!q.isEmpty()){
			Node n = q.poll();
			int p = n.value;
			int l = n.level;
			if (n.value == exit && n.od == OperationAndDirection.RIGHT_5 ){
				Stack<OperationAndDirection> operations = new Stack<OperationAndDirection>();
				while(n.parent!=null){
					operations.push(n.od);
					n = n.parent;
				}
				System.out.print(entrance);

				while(!operations.empty()) {
					OperationAndDirection op = operations.pop();
					if(op == OperationAndDirection.LEFT_7 || op == OperationAndDirection.RIGHT_7){
						System.out.print(" +7");
					}else if(op == OperationAndDirection.LEFT_5 || op == OperationAndDirection.RIGHT_5){
						System.out.print(" -5");
					}else if(op == OperationAndDirection.LEFT_3 || op == OperationAndDirection.RIGHT_3){
						System.out.print(" *3");
					}else if(op == OperationAndDirection.LEFT_2 || op == OperationAndDirection.RIGHT_2){
						System.out.print(" /2");
					}
				}
				System.out.println(" = " + exit);
				return;
			}
			switch (n.od) {
			case RIGHT_5:
				addOrNot(new Node(p*3,OperationAndDirection.LEFT_3,n,l+1));
				break;
			case  LEFT_5:
				addOrNot(new Node(p+7,OperationAndDirection.LEFT_7,n,l+1));
				addOrNot(new Node(p/2,OperationAndDirection.LEFT_2,n,l+1));
				addOrNot(new Node(p*3,OperationAndDirection.RIGHT_3,n,l+1));
				break;
			case RIGHT_7:
				addOrNot(new Node(p-5,OperationAndDirection.RIGHT_5,n,l+1));
				addOrNot(new Node(p/2,OperationAndDirection.LEFT_2,n,l+1));
				addOrNot(new Node(p*3,OperationAndDirection.RIGHT_3,n,l+1));
				break;
			case LEFT_7:
				addOrNot(new Node(p/2,OperationAndDirection.RIGHT_2,n,l+1));
				break;
			case RIGHT_3:
				addOrNot( new Node(p-5,OperationAndDirection.LEFT_5,n,l+1));
				break;
			case LEFT_3:
				addOrNot(new Node(p+7,OperationAndDirection.LEFT_7,n,l+1));
				addOrNot(new Node(p-5,OperationAndDirection.RIGHT_5,n,l+1));
				addOrNot(new Node(p/2,OperationAndDirection.LEFT_2,n,l+1));
				break;
			case RIGHT_2:
				addOrNot(new Node(p+7,OperationAndDirection.LEFT_7,n,l+1));
				addOrNot(new Node(p-5,OperationAndDirection.RIGHT_5,n,l+1));
				addOrNot(new Node(p*3,OperationAndDirection.RIGHT_3,n,l+1));
				break;
			case LEFT_2:
				addOrNot(new Node(p+7,OperationAndDirection.RIGHT_7,n,l+1));
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
