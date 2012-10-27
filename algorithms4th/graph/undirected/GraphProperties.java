package com.cupid.algorithm.algorithms4th.graph.undirected;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GraphProperties {
	
	// Rather than using boolean values to indicate whether a vertex was visited,
	// use a Integer array to mark each visited vertex.
	// If marked[v] = -1 , vertex v has not been visited.
	// If v is root of a BFS, marked[v] = 0;
	// Other positive values represent for the shortest path length from source(root) to a particular vertex.
	private int[] marked;
	// Integer array e is used to store eccentricity  of every vertex.
	private int[] e;
	// Integer array g is used to store minimum length of circle(s) involving the root vertex.
	private int[] g;
	// For calculating minimum length of circle(s), Integer array p is used to store predecessor of every vertex. 
	private int[] p;
	private Graph myGraph;
	private int center; 
	
	public GraphProperties(Graph G){
		e = new int[G.V()];
		g = new int[G.V()];
		p = new int[G.V()];
		marked = new int[G.V()];
		this.myGraph = G;
		
		// Calculate the eccentricity and girth for the tree rooted at each vertex
		// to decide diameter, radius, center, and girth of a given graph.
		// It takes O(V*(V+E))
		for(int i=0;i<myGraph.V();i++){
			breathFirstSearch(i,e,g);
		}
	}
	
	public int eccentricity(int v){
		return e[v];
	}
	
	public int girth(){
		int min = Integer.MAX_VALUE;
		for(int i=0;i<g.length;i++){
			if(g[i] < min){
				min = g[i];
			}
		}
		return min;
	}
	
	// The diameter of a graph is the maximum eccentricity
	// of any vertex.
	public int diameter(){
		int max = Integer.MIN_VALUE;
		for(int i=0;i<e.length;i++){
			if(e[i] > max){
				max = e[i];
			}
		}
		return max;
	}
	
	// The radius of a graph is the smallest eccentricity of any vertex. A center is
	// a vertex whose eccentricity is the radius.
	public int radius(){
		int min = Integer.MAX_VALUE;
		for(int i=0;i<e.length;i++){
			if(e[i] < min){
				min = e[i];
				center = i;
			}
		}
		return min;
	}
	
	// Calculate the eccentricity and girth for a given vertex.
	// Definition from Algorithms 4th edition:
	
	// The eccentricity of a vertex v is the the length of the shortest path from that vertex
	// to the farthest vertex from v 
	
	// The girth of a graph is the length of its shortest cycle. If a graph is acyclic, then its
	// girth is infinite.
	private void breathFirstSearch(int v,int[] e,int[] g){
		
		// Initialize int[] marked before a BFS is going to commence.
		for(int i=0;i<myGraph.V();i++){
			marked[i] = -1;
		}
		// A standard BFS begins.
		Queue<Integer> q = new LinkedList<Integer>();
		int eccen = 0;
		int girth = Integer.MAX_VALUE;
		marked[v] = 0;
		q.add(v);
		while(!q.isEmpty()){
			int u = q.poll();
			for(Integer w: myGraph.adj(u)){
				if(marked[w] <0){
					// Each level of BFS should increase shortest path length by 1
					marked[w] = marked[u] +1;
					// Assign a shortest path length to an eccentricity.
					eccen = marked[w];
					// w's parent u was found.
					p[w] = u;
					q.add(w);
				}else{
					// If the marked vertex w is not u's parent,
					// there is a cycle here.
					if(w != p[u]){
						// Decide that whether this cycle's length is greater than
						// the smallest one that has been discovered.
						// If no, this cycle is the smallest one.
						if(girth > marked[w] + marked[u] + 1){
							girth = marked[w] + marked[u] + 1;
						}
					}
				}
			}
		}
		// Store eccentricity and girth for root vertex in every BFS.
		e[v] = eccen;
		g[v] = girth;
	}
	
	
	public int getCenter() {
		return center;
	}

	public static void main(String[] args) {
		File file = new File("d:\\tinyG6.txt");
		try {
			Scanner in = new Scanner(file);
			Graph G = new Graph(in);
			System.out.println(G);
			GraphProperties gps = new GraphProperties(G);
			System.out.println("The diameter is: " + gps.diameter());
			System.out.println("The radius is: " +gps.radius());
			System.out.println("The center is vertex No." + gps.getCenter());
			if(gps.girth() == Integer.MAX_VALUE){
				System.out.println("The girth is infinite since the graph is acyclic.");
			}else{
				System.out.println("The girth is: " + gps.girth());
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

}
