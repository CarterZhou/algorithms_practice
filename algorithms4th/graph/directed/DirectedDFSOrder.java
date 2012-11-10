package com.cupid.algorithm.algorithms4th.graph.directed;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DirectedDFSOrder {

	private boolean[] marked;
	
	private Queue<Integer> preQ;
	private Queue<Integer> postQ;
	private Stack<Integer> reversePost;
	
	public DirectedDFSOrder(Digraph dg){
		marked = new boolean[dg.V()];
		
		preQ = new LinkedList<Integer>();
		postQ =new LinkedList<Integer>();
		reversePost = new Stack<Integer>();
		
		for(int u=0;u<dg.V();u++){
			if(!marked[u]){
				dfs(dg,u,-1);
			}
		}
	}
	
	private void dfs(Digraph dg, int u,int p) {
		marked[u] = true;
		preQ.add(u);
		for(Integer v:dg.adj(u)){
			if(!marked[v]){
				dfs(dg,v,u);
			}
		}
		postQ.add(u);
		reversePost.push(u);
	}

	public Queue<Integer> getPreQ() {
		return preQ;
	}



	public Queue<Integer> getPostQ() {
		return postQ;
	}



	public Stack<Integer> getReversePost() {
		return reversePost;
	}



	public static void main(String[] args) {
		
	}

}
