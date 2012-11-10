package com.cupid.algorithm.algorithms4th.graph.directed;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Degrees {
	 
	private int[] in;
	private int[] out;
	private boolean[] marked;
	
	public Degrees(Digraph dg){
		in = new int[dg.V()];
		out = new int[dg.V()];
		marked = new boolean[dg.V()];
		
		for(int u=0;u<dg.V();u++){
			if(!marked[u]){
				dfs(dg,u,-1);
			}
		}
	}

	
	

	private void dfs(Digraph dg, int u,int p) {
		marked[u] = true;
		for(Integer v : dg.adj(u)){
			out[u]++;
			in[v]++;
			if(!marked[v] && v!=p)
			  dfs(dg,v,u);
		}
	
	}

	public int inDegree(int v){
		return in[v];
	}
	
	public int outDegree(int v){
		return out[v];
	}
	

	public static void main(String[] args) {
		File file = new File("d:\\tinyDG.txt");
		try {
			Scanner in = new Scanner(file);
			Digraph dg = new Digraph(in);
			System.out.println(dg);
			Degrees d = new Degrees(dg);
			
			System.out.println(d.inDegree(6));
			System.out.println(d.outDegree(6));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
