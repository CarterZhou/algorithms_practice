package com.cupid.algorithm.graph.algorithms4th;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TwoColorability {
	
	private boolean[] marked;
	// 1 and 0 are used, representing for two colors
	private int[] color;
	private boolean twoColorable = true;

	public TwoColorability(Graph G){
		dfsGraph_Recursion(G);
	}
	
	public boolean isTwoColorable(){
		return twoColorable;
	}
	
	public void dfsGraph_Recursion(Graph G){
		marked = new boolean[G.V()];
		color = new int[G.V()];
		for (int v=0;v<G.V();v++) {
			if(!marked[v]){
				color[v] = 1;
				dfs(G,v);
			}
		}
	}
	
	private void dfs(Graph G, int u) {
		marked[u] = true;
		for (Integer v : G.adj(u)) {
			if(!marked[v]){
				// Assign the other color before searching. 
				color[v] = color[u] ^ 1;
				dfs(G,v);
			}else{
				// If an adjacent vertex is marked,
				// check that whether current vertex is colored by the same color of adjacent vertex's.
				// If true, this graph is not two-colorable.
				if(color[u] == color[v]){
					twoColorable = false;
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		File file = new File("d:\\tinyG3.txt");
		try {
			Scanner in = new Scanner(file);
			Graph G = new Graph(in);
			System.out.println(G);
			TwoColorability tc = new TwoColorability(G);
			System.out.println("Is the graph two-colorable? " + tc.isTwoColorable());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
