package com.cupid.algorithm.graph.algorithms4th;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CycleDectection {
	
	private boolean[] marked;
	private boolean cyclic = false;

	public CycleDectection(Graph G){
		dfsGraph_Recursion(G);
	}
	
	public boolean isCyclic(){
		return cyclic;
	}
	
	public void dfsGraph_Recursion(Graph G){
		marked = new boolean[G.V()];
		for (int v=0;v<G.V();v++) {
			if(!marked[v]){
				dfs(G,v,-1);
			}
		}
	}
	
	private void dfs(Graph G, int u,int predecessor) {
		marked[u] = true;
		int u_predecessor = predecessor;
		for (Integer v : G.adj(u)) {
			if(!marked[v]){
				predecessor = u;
				dfs(G,v,predecessor);
				predecessor = u_predecessor;
			}else{
				if( v!=predecessor){
					cyclic = true;
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
			CycleDectection cd = new CycleDectection(G);
			System.out.println("Cycle in the graph ? " + cd.isCyclic());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
