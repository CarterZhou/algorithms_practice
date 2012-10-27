package com.cupid.algorithm.algorithms4th.graph.undirected;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


// Algorithms 4th ed. 4.1.36
// Definition:
// A bridge in a graph is an edge that, if removed, would separate
// a connected graph into two disjoint subgraphs. A graph that has no bridges is said
// to be edge connected.2

public class EdgeConnectivity {
	// The number of visit to each vertex during DFS.
	private int[] dfsOrder;
	// For any Vertex v, whichAncestor[v] tells that the farthest ancestor.
	// It can reach via its own or its children's back edges.
	// If no back edge, it means it can only connect to parent through a DFS Tree edge.
	private int[] whichAncestor;
			
	private int count;
	private Graph myGraph;
	
	private boolean noBridges = true;
	
	public EdgeConnectivity(Graph G){
		this.myGraph = G;
		count = 0;
		
		dfsOrder = new int[G.V()];
		whichAncestor = new int[G.V()];
		
		for(int i=0;i<dfsOrder.length;i++){
			dfsOrder[i] = 0;
		}
	}
	
	private void DepthFirstSearch(){
		for(int i=0;i<myGraph.V();i++)
			if(dfsOrder[i] == 0){
				dfs(i,i);
			}
	}
	
	private void dfs(int u,int predecessor){
		count = count + 1;
		dfsOrder[u] = count;
		// By default, no back edge connecting vertex u and any ancestor
		whichAncestor[u] = count;
		
		for(Integer v: myGraph.adj(u)){
			// If adjacent vertex has not been visited
			if(dfsOrder[v] == 0){
				dfs(v,u);
				// When the next level of DFS returns,
				// Update back edge information.
				if(dfsOrder[u] >= whichAncestor[v]){
					whichAncestor[u]  = whichAncestor[v];
				// If, for vertex u, there is no back edge connecting its ancestors,
				// A bridge exists, connecting u and its child, v.
				}else{
					noBridges = false;
					System.out.println("A bridge is found:" + u + "-" + v);
				}
			// When dfsOrder[v] > 0, a back edge is found.
			// Because there may be many back edges,
			// we should update back edge as the one to the farthest ancestor.
			}else if(dfsOrder[v] > 0 && v!=predecessor){
				if(dfsOrder[v] < whichAncestor[u])
					whichAncestor[u] = dfsOrder[v];
			}
		}
	}
	
	public boolean isEdgeConnected(){
		DepthFirstSearch();
		return this.noBridges;
	}
	
	
	public static void main(String[] args) {
		File file = new File("d:\\tinyG.txt");
		try {
			Scanner in = new Scanner(file);
			Graph G = new Graph(in);
			System.out.println(G);
			EdgeConnectivity ec = new EdgeConnectivity(G);
			System.out.println("\nIs the graph edge connected ? " + ec.isEdgeConnected());
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
}
