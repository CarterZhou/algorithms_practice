package com.cupid.algorithm.tree;

import java.util.Random;



public class BinarySearchTreeTest {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * Build a binary search tree
		 */
		BSTNode n1 = new BSTNode(100);
		BSTNode n2 = new BSTNode(53);
		BSTNode n3 = new BSTNode(55);
		BSTNode n4 = new BSTNode(120);
		BSTNode n5 = new BSTNode(150);
		
		BSTNode n6 = new BSTNode(40);
		BSTNode n7 = new BSTNode(45);
		BSTNode n8 = new BSTNode(34);
		n2.setLeft(n6);
		n6.setParent(n2);
		n7.setParent(n6);
		n8.setParent(n6);
		n6.setLeft(n8);
		n6.setRight(n7);
		
		n2.setParent(n1);
		n2.setRight(n3);
		n3.setParent(n2);
		n4.setParent(n5);
		n5.setParent(n1);
		n5.setLeft(n4);
		
		n1.setParent(null);
		n1.setLeft(n2);
		n1.setRight(n5);
		BinarySearchTree bst = new BinarySearchTree(n1);
		/**
		 * Test tree search
		 */
		/*BSTNode n = bst.search(42,true);
		if(n!=null){
			System.out.println(n.getKey());
			System.out.println(bst.successor(n).getKey());
			System.out.println(bst.predecessor(n).getKey());
		}*/
		/**
		 * Test searching for closest nodes for the given key
		 */
		//System.out.println(bst.searchForNearest(45).getKey());
		BinarySearchTree bst1 = new BinarySearchTree(null);
		for(int i=0;i<20;i++){
			bst1.insert(new BSTNode(new Random().nextInt(200)));
			//bst1.insertRecursively(new BSTNode(new Random().nextInt(100)),bst1.getRoot(),null);
		}
		bst1.inOrder(bst1.getRoot());
		System.out.println();
		System.out.println(bst1.searchForNearest(100).getKey());
		BSTNode[] nodes = bst1.getClosestNodes(100, 10);
		BSTNode[] nodes1 = bst1.getClosestNodesWithQueue(100, 10);
		for (BSTNode bstNode : nodes) {
			System.out.print(bstNode.getKey() + "-");
		}
		System.out.println();
		for (BSTNode bstNode : nodes1) {
			System.out.print(bstNode.getKey() + "-");
		}
		/**
		 * Test tree traversals
		 */
		
		/*bst.postOrderWalk(bst.getRoot());
		System.out.println();
		bst.preOrderWalk(bst.getRoot());
		bst.postOrder(bst.getRoot());
		bst.inOrderWalk(bst.getRoot());
//		System.out.println();
//		bst.nonRecursiveInOrderWalkWithStack(bst.getRoot());
		System.out.println();
		bst.inOrder(bst.getRoot());*/
		
		//bst.nonRecursivePostOrderWalk(bst.getRoot());
	/*	System.out.println();
		bst.inOrderWalk(bst.getRoot());
		System.out.println();
		System.out.println();
		bst.postOrderWalk(bst.getRoot());
		*/
		
		/**
		 * Test predecessor() and successor()
		 * and minNode() and maxNode()
		 */
		/*BinaryNode p = bst.predecessor(n1);
		BinaryNode s = bst.successor(n1);
		BinaryNode sN5 = bst.successor(n5);
		if(s!= null){
			System.out.println(s.getKey());
		}
		if(sN5!=null){
			System.out.println(sN5.getKey());
		}else{
			System.out.println("The node's key is the largest, no successor.");
		}
		System.out.println(bst.minNode(bst.getRoot()).getKey());
		System.out.println(bst.maxNode(bst.getRoot()).getKey());
		*/
		
		/**
		 * Test insert and delete
		 */
		/*BinarySearchTree bst1 = new BinarySearchTree(null);
		for(int i=0;i<10;i++){
			bst1.insert(new BSTNode(new Random().nextInt(100)));
			//bst1.insertRecursively(new BSTNode(new Random().nextInt(100)),bst1.getRoot(),null);
		}
		
		//bst1.inOrder(bst1.getRoot());
		System.out.println();
		bst1.preOrderWalk(bst1.getRoot());
		System.out.println();
		bst1.preOrder(bst1.getRoot());
		System.out.println("\n");
		bst1.postOrderWalk(bst1.getRoot());
		System.out.println();
		bst1.postOrder(bst1.getRoot());*/
		/**/
		//System.out.print("\n" + bst1.getRoot().getKey());
		/*BinaryNode s = bst1.successor(bst1.getRoot());
		if(s!=null)
			System.out.println("\n" + s.getKey());
		bst1.delete(bst1.getRoot());
		bst1.inOrder(bst1.getRoot());
		System.out.print("\n" + bst1.getRoot().getKey());
		*/
		/**
		 * Test verify()
		 */
		/*BSTNode bn1 = new BSTNode(20);
		BSTNode bn2 = new BSTNode(10);
		BSTNode bn3 = new BSTNode(60);
		BSTNode bn4 = new BSTNode(40);
		BSTNode bn5 = new BSTNode(50);
		BSTNode bn6 = new BSTNode(30);
		
		bn1.setParent(null);
		bn2.setParent(bn1);
		bn3.setParent(bn1);
		bn1.setLeft(bn2);
		bn1.setRight(bn3);
		
		bn4.setParent(bn3);
		bn3.setLeft(bn4);
		
		bn5.setParent(bn4);
		bn4.setRight(bn5);
		
		bn6.setParent(bn2);
		bn2.setRight(bn6);
		
		BinarySearchTree bst2 = new BinarySearchTree(bn1);
		System.out.println(bst2.verifyElegantly(bst2.getRoot(),Integer.MIN_VALUE,Integer.MAX_VALUE));
		System.out.println(bst2.verify(bst2.getRoot()));*/
	}

}
