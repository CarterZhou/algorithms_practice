package com.cupid.algorithm.graph.algorithms4th;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GraphProperties {

	private int[] marked;
	private int[] e;
	private Graph myGraph;
	private int center;
	
	public GraphProperties(Graph G){
		e = new int[G.V()];
		marked = new int[G.V()];
		this.myGraph = G;
		
		// Calculate the eccentricity for each vertex
		// to decide diameter, radius and center of a given graph.
		// It takes O(V*(V+E))
		for(int i=0;i<myGraph.V();i++){
			e[i] = calculateEccentricity(i);
		}
	}
	
	public int eccentricity(int v){
		return e[v];
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
	
	
	// Calculate the eccentricity for a given vertex.
	// The eccentricity of a vertex v is the the length of the shortest path from that vertex
	// to the farthest vertex from v 
	private int calculateEccentricity(int v){
		
		for(int i=0;i<myGraph.V();i++){
			marked[i] = -1;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		int eccen = 0;
		marked[v] = 0;
		q.add(v);
		while(!q.isEmpty()){
			int u = q.poll();
			for(Integer w: myGraph.adj(u)){
				if(marked[w] <0){
					marked[w] = marked[u] +1;
					eccen = marked[w];
					q.add(w);
				}
			}
		}
		e[v] = eccen;
		return e[v];
	}
	
	
	public int getCenter() {
		return center;
	}

	public static void main(String[] args) {
		File file = new File("d:\\mediumG.txt");
		try {
			Scanner in = new Scanner(file);
			Graph G = new Graph(in);
			
			GraphProperties gps = new GraphProperties(G);
			System.out.println("The diameter is: " + gps.diameter());
			System.out.println("The radius is: " +gps.radius());
			System.out.println("The center is vertex No." + gps.getCenter());
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

}
