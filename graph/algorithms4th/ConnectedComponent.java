package com.cupid.algorithm.graph.algorithms4th;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConnectedComponent {

	private boolean[] marked;
	private int[] ids;
	private int count;
	
	public int id(int vertex){
		return ids[vertex];
	}
	
	public ConnectedComponent(Graph G){
		this.count = 0;
		dfsGraph_Recursion(G);
	}
	

	public boolean connected(int u,int v){
		return id(u) == id(v);
	}
	
	public int getCount() {
		return count;
	}
	
	public void dfsGraph_Recursion(Graph G){
		marked = new boolean[G.V()];
		ids = new int[G.V()];
		for (int v=0;v<G.V();v++) {
			if(!marked[v]){
				dfs(G,v);
				count++;
			}
		}
	}
	
	private void dfs(Graph G, int vertex) {
		marked[vertex] = true;
		ids[vertex] = count;
		for (Integer v : G.adj(vertex)) {
			if(!marked[v]){
				dfs(G,v);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		File file = new File("d:\\tinyG.txt");
		try {
			Scanner in = new Scanner(file);
			Graph G = new Graph(in);
			System.out.println(G);
			ConnectedComponent cc = new ConnectedComponent(G);
			System.out.println(cc.getCount() + " connected component(s) in the graph.");
			
			Bag<Integer>[] components = (Bag<Integer>[]) new Bag[cc.getCount()];
			for(int i=0;i<cc.getCount();i++){
				components[i] = new Bag<Integer>();
			}
			for(int i=0;i<G.V();i++){
				components[cc.id(i)].add(i);
			}
			for (int i=0;i<cc.getCount();i++) {
				System.out.println("Component " + i + " contains:");
				for (Integer v : components[i]) {
					System.out.print(v + " ");	
				}
				System.out.println();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
