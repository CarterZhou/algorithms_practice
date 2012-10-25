package com.cupid.algorithm.graph.algorithms4th;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BreathFirstPath {
	
	private boolean[] marked;
	private int[] edgeTo;
	private final int source;
	private Graph myGraph;
	
	public BreathFirstPath(int src,Graph G){
		this.source = src;
		this.myGraph = G;
	}
	
	public int getSource() {
		return source;
	}

	public void breadFirstSearch(){
		marked = new boolean[myGraph.V()];
		edgeTo = new int[myGraph.V()];
		Queue<Integer> q = new LinkedList<Integer>();
		marked[source] = true;
		q.add(source);
		while(!q.isEmpty()){
			int u = q.poll();
			for(Integer v : myGraph.adj(u)){
				if(!marked[v]){
					edgeTo[v] = u;
					marked[v] = true;
					q.add(v);
				}
			}
		}
	}
	
	public boolean hasPathTo(int destination){
		return marked[destination];
	}
	
	public Iterable<Integer> findPath(int destination){
		Stack<Integer> s = new Stack<Integer>();
		int degrees = 0;
		boolean same = true;
		if(hasPathTo(destination)){
			while(destination!= source){
				if(same){
					degrees++;
					same = false;
				}else{
					same = true;
				}
				s.push(destination);
				destination = edgeTo[destination];
			}
			s.push(source);
		}
		s.push(degrees);
		return s;
	}
	
	public static void main(String[] args) {
		File file = new File("d:\\tinyG.txt");
		try {
			Scanner in = new Scanner(file);
			Graph G = new Graph(in);
			System.out.println(G);
			BreathFirstPath bfsp = new BreathFirstPath(0,G);
			bfsp.breadFirstSearch();
			int des = 9;
			Stack<Integer> path = (Stack<Integer>) bfsp.findPath(des);
			if(path.size()>0){
				System.out.println("Path from " + bfsp.getSource() + " to " + des + " is:" );
				while(!path.empty()){
					int v = path.pop();
					if(v == bfsp.getSource()){
						System.out.print(v);
					}else{
						System.out.print("-" + v);
					}
				}
			}else{
				System.out.println("No Path from " + bfsp.getSource() + " to " + des );
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
