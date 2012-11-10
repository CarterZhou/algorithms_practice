package com.cupid.algorithm.algorithms4th.graph.directed;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class TopologicalOrder {
	
	// The color of a vertex indicates the type of edge.
	// If an edge points to white vertex, it is the vertex that has not been visited.
	// This is a tree edge.
	// If an edge points to black vertex,it is the vertex that has been visited 
	// and whose all its descendants and have been visited,if it has descendants.
	// This is a forward/cross edge.
	// If an edge points to gray vertex, it is the vertex that has been visited 
	// and whose only some descendants have been visited.
	// This is a back edge which forms a cycle in digraph.
	// The valid values are 'w','g','b'.
	private char[] color;
	private Digraph myDG;
	private Stack<Integer> reversePost;
	private boolean hasCycle;
	
	public TopologicalOrder(Digraph dg){
		this.myDG = dg;
		hasCycle = false;
		color = new char[dg.V()];
		reversePost = new Stack<Integer>();
		
		for(int i=0;i<dg.V();i++){
			color[i] = 'w';
		}
	}
	
	public void printAllOrders(){
	}
	
	public void printAnOrder(){
		
		for(int u=0;u<myDG.V();u++){
			if(color[u] == 'w'){
				dfs(myDG,u);
			}
		}
		if(!hasCycle){
			System.out.println("A topological order is:");
			while(!reversePost.isEmpty()){
				System.out.print(reversePost.pop()+ " ");
			}
		}else{
			System.out.println("The graph has a cycle, hence a topological order could not be found.");
		}
		
	}
	
	// O(V*V*E)
	public boolean isTopologicalOrder(int[] permutation){
		for(int i=1;i<permutation.length;i++){
			for(int j=0;j<i;j++){
				for(Integer v:myDG.adj(permutation[i])){
					if( v == permutation[j])
						return false;

				}
			}
		}
		return true;
	}
	
	private void dfs(Digraph dg, int u) {
		color[u] = 'g';
		for(Integer v:dg.adj(u)){
			if(color[v] == 'w'){
				dfs(dg,v);
			}else{
				if(color[v] == 'g'){
					hasCycle = true;
				}
			}
		}
		color[u] = 'b';
		reversePost.push(u);
	}

	public static void main(String[] args) {
		File file = new File("d:\\tinyDAG.txt");
		try {
			Scanner in = new Scanner(file);
			Digraph G = new Digraph(in);
			System.out.println(G);
			
			TopologicalOrder to = new TopologicalOrder(G);
			to.printAnOrder();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
