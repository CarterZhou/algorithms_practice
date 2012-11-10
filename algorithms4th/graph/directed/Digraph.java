package com.cupid.algorithm.algorithms4th.graph.directed;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.cupid.algorithm.algorithms4th.graph.undirected.Bag;

public class Digraph {
	
	private final int V;
    private int E;
    private Bag<Integer>[] adj;
	
	@SuppressWarnings("unchecked")
	public Digraph(int v) {
		if(v<0){
			throw new IllegalArgumentException();
		}
		this.V = v;
		this.E = 0;
		adj = (Bag<Integer>[])new Bag[V];
		for(int i=0;i<V;i++){
			adj[i] = new Bag<Integer>();
		}
	}
	
	public Digraph(Scanner in){
		this(in.nextInt());
	    int E = in.nextInt();
	    for (int i = 0; i < E; i++) {
	       int u = in.nextInt();
	       int v = in.nextInt();
	       addEdge(u, v);
	    }
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public Iterable<Integer> adj(int v){
		if(v<0 || v>=V){
			throw new IndexOutOfBoundsException();
		}
		return adj[v];
	}
	
	public void addEdge(int u,int v){
		if (u < 0 || u >= V) throw new IndexOutOfBoundsException();
        if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
        E++;
        adj[u].add(v);
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		String NEWLINE = System.getProperty("line.separator");
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
	
	// Test case
	public static void main(String[] args) {
		File file = new File("d:\\tinyDG.txt");
		try {
			Scanner in = new Scanner(file);
			Digraph G = new Digraph(in);
			System.out.println(G);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	} 
}