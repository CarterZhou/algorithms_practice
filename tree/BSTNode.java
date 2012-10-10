package com.cupid.algorithm.tree;

public class BSTNode {
	
	private BSTNode parent = null;
	private BSTNode left = null;
	private BSTNode right = null;
	private int key = Integer.MIN_VALUE;
	private  boolean visited = false;
	
	public BSTNode(BSTNode p,BSTNode l,BSTNode r,int k){
		this.parent = p;
		this.left = l;
		this.right = r;
		this.key = k;
	}
	
	public BSTNode(int k){
		this.key = k;
	}

	public void setParent(BSTNode parent) {
		this.parent = parent;
	}

	public void setLeft(BSTNode left) {
		this.left = left;
	}

	public void setRight(BSTNode right) {
		this.right = right;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public BSTNode getParent() {
		return parent;
	}

	public BSTNode getLeft() {
		return left;
	}

	public BSTNode getRight() {
		return right;
	}

	public int getKey() {
		return key;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
}
