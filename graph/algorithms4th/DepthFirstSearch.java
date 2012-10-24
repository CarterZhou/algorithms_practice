package com.cupid.algorithm.graph.algorithms4th;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class DepthFirstSearch {
	private boolean[] marked;
	
	
	public void dfsFormVertex(Graph G,int source){
		marked = new boolean[G.V()];
		dfs(G,source);
	}

	public void dfsGraph_Recursion(Graph G){
		marked = new boolean[G.V()];
		for (int v=0;v<G.V();v++) {
			if(!marked[v]){
				dfs(G,v);
			}
		}
	}
	
	private void dfs(Graph G, int source) {
		marked[source] = true;
		for (Integer v : G.adj(source)) {
			if(!marked[v]){
				dfs(G,v);
			}
		}
	}
	
	public void dfsGraph(Graph G){
		marked = new boolean[G.V()];
		Stack<Integer> s = new Stack<Integer>();
		boolean processedAll = false;
		for (int v=0;v<G.V();v++) {
			if(!marked[v]){
				s.push(v);
				marked[v] = true; 
				while(!s.empty()){
					int u = s.peek();
					Iterator<Integer> itr = G.adj(u).iterator();
					while(itr.hasNext()){
						Integer w = itr.next();
						if(!marked[w]){
							marked[w] = true; 
							s.push(w);
							break;
						}
						if(!itr.hasNext()){
							processedAll = true;
						}
					}
					if(processedAll){
						s.pop();
						processedAll = false;
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args){
		File file = new File("d:\\tinyG.txt");
		try {
			Scanner in = new Scanner(file);
			Graph G = new Graph(in);
			DepthFirstSearch dfs1 = new DepthFirstSearch();
			DepthFirstSearch dfs2 = new DepthFirstSearch();
			System.out.println(G);
			dfs1.dfsGraph_Recursion(G);
			System.out.println();
			dfs2.dfsGraph(G);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	} 
}
