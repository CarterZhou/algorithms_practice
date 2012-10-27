package com.cupid.algorithm.algorithms4th.graph.undirected;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class DepthFirstPath {
	private boolean[] marked;
	private int[] edgeTo;
	private final int source;
	private Graph myGraph;
	
	public DepthFirstPath(int source,Graph G){
		this.source = source;
		this.myGraph = G;
	}
	
	public void dfsFromSource(){
		marked = new boolean[myGraph.V()];
		edgeTo = new int[myGraph.V()];
		dfs(myGraph,source);
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
		Stack<Integer> s = new Stack<Integer>();
		if(hasPathTo(destination)){
			while(destination!=source){
				s.push(destination);
				destination = edgeTo[destination];
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
			DepthFirstPath dfsp = new DepthFirstPath(0, G);
			dfsp.dfsFromSource();
			int des = 5;
			Stack<Integer> path = (Stack<Integer>) dfsp.findPath(des);
			if(path.size()>0){
				System.out.println("Path from " + dfsp.getSource() + " to " + des + " is:" );
				while(!path.empty()){
					int v = path.pop();
					if(v == dfsp.getSource()){
						System.out.print(v);
					}else{
						System.out.print("-" + v);
					}
				}
			}else{
				System.out.println("No Path from " + dfsp.getSource() + " to " + des );
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	} 
}
