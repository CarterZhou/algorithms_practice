package com.cupid.algorithm.graph.algorithms4th;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class DepthFirstPath {
	private boolean[] marked;
	private int[] edgeTo;
	private final int source;
	private Graph G;
	
	public DepthFirstPath(int source,Graph G){
		this.source = source;
		this.G = G;
	}
	
	public void dfsFormSource(){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		dfs(G,source);
	}

	private void dfs(Graph G, int source) {
		marked[source] = true;
		for (Integer v : G.adj(source)) {
			if(!marked[v]){
				edgeTo[v] = source;
				dfs(G,v);
			}
		}
	}
	
	public boolean hasPathTo(int destination){
		return marked[destination];
	}
	
	public Iterable<Integer> findPath(int destination){
		Stack<Integer> s = null;
		if(hasPathTo(destination)){
			s = new Stack<Integer>();
			int src = edgeTo[destination]; 
			while(src!=source){
				s.push(src);
				src = edgeTo[src];
			}
			s.push(source);
		}
		return s;
	}
	
	public int getSource() {
		return source;
	}

	public static void main(String[] args){
		File file = new File("d:\\tinyG.txt");
		try {
			Scanner in = new Scanner(file);
			Graph G = new Graph(in);
			System.out.println(G);
			DepthFirstPath dfsp = new DepthFirstPath(2, G);
			dfsp.dfsFormSource();
			int des = 6;
			Stack<Integer> path = (Stack<Integer>) dfsp.findPath(des);
			if(path!=null){
				System.out.println("Path from " + dfsp.getSource() + " to " + des + " is:" );
				while(!path.empty()){
					int v = path.pop();
					if(v == dfsp.getSource()){
						System.out.print(v);
					}else{
						System.out.print("-" + v);
					}
				}
				System.out.println("-" + des);
			}else{
				System.out.println("No Path from " + dfsp.getSource() + " to " + des );
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	} 
}
