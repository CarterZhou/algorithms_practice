package com.cupid.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree {

	private BSTNode root = null;

	public BSTNode getRoot() {
		return root;
	}

	private void setRoot(BSTNode root) {
		this.root = root;
	}

	public BinarySearchTree(BSTNode root) {
		this.root = root;
	}

	public BSTNode search(int key, boolean returnNearest) {
		BSTNode node = null;
		BSTNode root = this.getRoot();
		while (root != null) {
			if (key < root.getKey()) {
				root = root.getLeft();
			} else if (key > root.getKey()) {
				root = root.getRight();
			} else {
				node = root;
				root = null;
			}
		}
		return node;
	}
	/**
	* Search for the node for the given key.
	* If exists, return it.
	* If not, return the node which contains the nearest key to the given key.
	* For example, there are two nodes with key 40 and 45 in a BST, respectively.
	* Searching for 42 will return 40 and 44 will return 45.
	*/
	public BSTNode searchForNearest(int key) {
		BSTNode node = null;
		BSTNode root = this.getRoot();
		BSTNode nearest = null;
		while (root != null) {
			nearest = root;
			if (key < root.getKey()) {
				root = root.getLeft();
			} else if (key > root.getKey()) {
				root = root.getRight();
			} else {
				node = root;
				root = null;
			}
		}
		if(node!=null){
			return node;
		}else{
			BSTNode n = null;
			if(key < nearest.getKey()){
				n = this.predecessor(nearest);
				if(key-n.getKey() < nearest.getKey() - key)
					nearest = n;
			}
			if(key > nearest.getKey()){
				n = this.successor(nearest);
				if(key- nearest.getKey() > n.getKey() - key)
					nearest = n;
			}
			return nearest;
		}
		
	}
	
	public BSTNode[] getClosestNodesWithQueue(int myKey,int m){
		// Declare a queue of size m to save the result set
		Queue<BSTNode> q = new LinkedList<BSTNode>();
		// Rather than walking tree recursively,
		// use a stack as auxiliary data structure for in-order traversal within while loop.
		Stack<BSTNode> s = new Stack<BSTNode>();
		// Get the node with closest key to the given key first.
		// Its key may or may not be the same as myKey. 
		BSTNode me = this.searchForNearest(myKey);
		BSTNode root = this.getRoot();
		// Do in-order traversal, during which enqueue or dequeue nodes appropriately.
		while(!s.empty() || root!=null){
			while(root!=null){
				s.push(root);
				root = root.getLeft();
			}
			if(!s.empty()){
				// Get the first node in the stack.
				root = s.pop();
				// Save the biggest mth nodes which are less than the closest key.
				if(root.getKey() < me.getKey()){
					if(q.size()>=m){
						q.poll();
					}	
					q.add(root);
				// Once we encounter the ME node with closest key,
				// we judge that whether its key equals the given myKey.
				// If yes, skip and do not save it in the queue.
				// If no, since it's the nearest node, we have no pointing not saving it!
				}else if(root.getKey() == me.getKey() && me.getKey()!= myKey){
					if(q.size()>=m){
						q.poll();
					}	
					q.add(root);
				// We will encounter those nodes with greater key (than myKey) at some point during traversal.
				}else if(root.getKey() > me.getKey()){
					// If the size of queue is still less than m,
					// simply save the node.
					if(q.size()<m){
						q.add(root);
					// If the size of queue is >= m,
					// we judge that whether the difference between its key and myKey is less than
					// the difference between head's key and myKey.
					// If yes, remove the head of queue and save current node.
					// If no, we have got all m nodes with keys less than myKey.
					// Break the traversal.
					}else if(myKey - q.peek().getKey() > root.getKey() - myKey){
						q.poll();
						q.add(root);
					}else{
						break;
					}
				}
				root = root.getRight();
			}
		}
		return q.toArray(new BSTNode[0]);
	}
	
	/**
	 * Get a number of m nodes which contains the closest keys to the given key.
	 * Here, by closest it means the differences between these keys and the given key
	 * is the 1st,2nd,3rd...mth smallest.
	 * Two examples:
	 * The in-order traversal of a BST is 10,20,25,30,40
	 * When calls this method with parameters (25,2), it returns 20,30;
	 * When calls this method with parameters (28,2), it returns 25,30
	 * 
	 * @param myKey
	 * @param m
	 * @return BSTNode[]
	 */
	public BSTNode[] getClosestNodes(int myKey, int m) {
		BSTNode[] closestNodes = new BSTNode[m];
		BSTNode me = this.searchForNearest(myKey);
		int index = 0;
		BSTNode p = null;
		BSTNode s = null;
		// Test whether the returned node contains the given KEY.
		// If not, save the node
		// and move predecessor pointer and successor pointer accordingly.
		if (me.getKey() < myKey) {
			closestNodes[index] = me;
			index++;
			s = this.successor(me);
			p = this.predecessor(me);
		}else if(me.getKey() > myKey){
			closestNodes[index] = me;
			index++;
			p = this.predecessor(me);
			s = this.successor(me);
		}else{
			p = this.predecessor(me);
			s = this.successor(me);
		}
		for (;index < m; index++) {
			// When s is null, s actually points to the right-most element of
			// the BST.
			// In such case no more elements' key greater than KEY can be added into
			// result set.
			// Thus continually add elements's key less than KEY into result set until
			// its size reaches m.
			if (p != null && (s == null || myKey - p.getKey() < s.getKey() - myKey)) {
				closestNodes[index] = p;
				p = this.predecessor(p);
				// When p is null, p actually points to the left-most element of
				// the BST.
				// In such case no more elements' key less than KEY can be added into
				// result set.
				// Thus continually add elements' key greater than KEY into result set
				// until its size reaches m.
			} else if (s != null && (p == null || myKey - p.getKey() > s.getKey() - myKey)) {
				closestNodes[index] = s;
				s = this.successor(s);
			} else {
				if (p != null && s != null) {
					closestNodes[index] = p;
					p = this.predecessor(p);
					index = index + 1;
					if (index < m) {
						closestNodes[index] = s;
						s = this.successor(s);
					}
				}
			}
		}
		return closestNodes;
	}

	/**
	* Given the root of a binary tree,
	* verify that whether this tree is binary search tree.
	* A succinct version.
	*/
	public boolean verifyElegantly(BSTNode root, int smallest, int largest) {
		if (root == null)
			return true;
		if (root.getKey() > smallest && root.getKey() <= largest) {
			return verifyElegantly(root.getLeft(), smallest, root.getKey())
					&& verifyElegantly(root.getRight(), root.getKey(), largest);
		} else {
			return false;
		}
	}

	// Given the root of a binary tree,
	// verify that whether this tree is binary search tree.
	// Somewhat over-complicated.
	public boolean verify(BSTNode root) {
		boolean isBST = true;
		if (root.getLeft() != null) {
			if (root.getKey() <= predecessor(root).getKey())
				return false;
			isBST = verify(root.getLeft());
		}
		if (root.getRight() != null) {
			if (root.getKey() > successor(root).getKey())
				return false;
			isBST = verify(root.getRight());
		}
		return isBST;
	}

	public void delete(BSTNode node) {
		if (node.getLeft() == null) {
			transplant(node, node.getRight());
		} else if (node.getRight() == null) {
			transplant(node, node.getLeft());
		} else {
			BSTNode successor = successor(node);
			if (successor != node.getRight()) {
				transplant(successor, successor.getRight());
				successor.setRight(node.getRight());
				node.getRight().setParent(successor);
			}
			transplant(node, successor);
			successor.setLeft(node.getLeft());
			node.getLeft().setParent(successor);
		}
	}

	private void transplant(BSTNode original, BSTNode replacement) {
		if (original.getParent() == null) {
			setRoot(replacement);
		} else if (original == original.getParent().getLeft()) {
			original.getParent().setLeft(replacement);
		} else {
			original.getParent().setRight(replacement);
		}
		if (replacement != null) {
			replacement.setParent(original.getParent());
		}
	}

	public void insert(BSTNode node) {
		BSTNode child = this.getRoot();
		BSTNode parent = null;
		while (child != null) {
			parent = child;
			if (node.getKey() < child.getKey()) {
				child = child.getLeft();
			} else {
				child = child.getRight();
			}
		}
		node.setParent(parent);
		if (parent == null) {
			setRoot(node);
		} else if (node.getKey() < parent.getKey()) {
			parent.setLeft(node);
		} else {
			parent.setRight(node);
		}
	}

	public void insertRecursively(BSTNode node, BSTNode root, BSTNode parent) {
		if (root == null) {
			node.setParent(parent);
			if (parent == null) {
				setRoot(node);
			} else if (node.getKey() < parent.getKey()) {
				parent.setLeft(node);
			} else {
				parent.setRight(node);
			}
		} else if (node.getKey() < root.getKey()) {
			insertRecursively(node, root.getLeft(), root);
		} else {
			insertRecursively(node, root.getRight(), root);
		}
	}

	// A more elegant version of in-order tree walk without recursion,using
	// stack.
	public void inOrder(BSTNode root) {
		Stack<BSTNode> s = new Stack<BSTNode>();
		while (!s.empty() || root != null) {
			while (root != null) {
				s.push(root);
				root = root.getLeft();
			}
			if (!s.empty()) {
				root = s.pop();
				System.out.print(root.getKey() + "-");
				root = root.getRight();
			}
		}
	}

	public void postOrder(BSTNode root) {
		Stack<BSTNode> s = new Stack<BSTNode>();
		while (!s.empty() || root != null) {
			if (root.getLeft() != null && !root.getLeft().isVisited()) {
				s.push(root);
				root = root.getLeft();
			} else if (root.getRight() != null && !root.getRight().isVisited()) {
				s.push(root);
				root = root.getRight();
			} else {
				System.out.print(root.getKey() + "-");
				root.setVisited(true);
				if (!s.empty())
					root = s.pop();
				else
					root = null;
			}
		}
	}

	// Non-recursive pre-order tree walk using stack.
	public void preOrder(BSTNode root) {
		Stack<BSTNode> s = new Stack<BSTNode>();
		boolean traversed = false;
		while (!s.empty() || root != null) {
			if (root != null) {
				System.out.print(root.getKey() + "-");
				traversed = true;
			} else if (!s.empty()) {
				root = s.pop();
			}
			if (root.getLeft() != null && traversed) {
				s.push(root);
				root = root.getLeft();
			} else {
				root = root.getRight();
			}
			traversed = false;
		}
	}

	public BSTNode minNode(BSTNode node) {
		if (node.getLeft() != null) {
			return minNode(node.getLeft());
		} else {
			return node;
		}
	}

	public BSTNode maxNode(BSTNode node) {
		if (node.getRight() != null) {
			return maxNode(node.getRight());
		} else {
			return node;
		}
	}

	// If node's key is the largest,
	// this method will return NULL (the parent of root node).
	public BSTNode successor(BSTNode node) {
		if (node.getRight() != null) {
			node = node.getRight();
			while (node.getLeft() != null) {
				node = node.getLeft();
			}
			return node;
		}
		BSTNode parent = node.getParent();
		while (parent != null && parent.getRight() == node) {
			node = parent;
			parent = parent.getParent();
		}
		return parent;
	}

	// If node's key is the smallest,
	// this method will return NULL (the parent of root node).
	public BSTNode predecessor(BSTNode node) {
		if (node.getLeft() != null) {
			node = node.getLeft();
			while (node.getRight() != null) {
				node = node.getRight();
			}
			return node;
		}
		BSTNode parent = node.getParent();
		while (parent != null && parent.getLeft() == node) {
			node = parent;
			parent = parent.getParent();
		}
		return parent;
	}

	// Recursive in-order traversal
	public void inOrderWalk(BSTNode node) {
		if (node != null) {
			inOrderWalk(node.getLeft());
			System.out.print(node.getKey() + "-");
			inOrderWalk(node.getRight());
		}
	}

	// Recursive post-order traversal
	public void postOrderWalk(BSTNode node) {
		if (node != null) {
			postOrderWalk(node.getLeft());
			postOrderWalk(node.getRight());
			System.out.print(node.getKey() + "-");
		}
	}

	// Recursive pre-order traversal
	public void preOrderWalk(BSTNode node) {
		if (node != null) {
			System.out.print(node.getKey() + "-");
			preOrderWalk(node.getLeft());
			preOrderWalk(node.getRight());
		}
	}

}
