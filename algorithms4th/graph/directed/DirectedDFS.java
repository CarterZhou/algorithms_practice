package com.cupid.algorithm.algorithms4th.graph.directed;

public class DirectedDFS {
	
	private boolean[] marked;

	
	public DirectedDFS(Digraph dg){
		marked = new boolean[dg.V()];
		
		
		for(int u=0;u<dg.V();u++){
			if(!marked[u]){
				dfs(dg,u);
			}
		}
	}
	
	
	private void dfs(Digraph dg, int u) {
		marked[u] = true;
		for(Integer v: dg.adj(u)){
			if(!marked[v]){
				dfs(dg,v);
			}
		}
	}


	public static void main(String[] args) {
		
	}

}
