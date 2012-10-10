package com.cupid.algorithm.tree;

public class ConstructBinaryTree {
	/**
	 * Construct a binary tree based on the pre-order traversal result and in-order traversal result
	 * 
	 * @param preOrder The array which stores the pre-order traversal result
	 * @param inOrder  The array which stores the in-order traversal result
	 * @param preBegin This index always refers to the location of roots of sub-trees
	 * @param preEnd   This index always refers to where the end of a sub-tree is
	 * @param preLength  How many elements of a sub-tree
	 * @param inBegin  This index refers to where a left sub-tree begins
	 * @return
	 */
	public static BinaryTree construct(int[] preOrder,int[] inOrder,int preBegin,int preEnd,int preLength,int inBegin){
		// If left or right sub-tree is empty
		// simply return that empty tree.
		BinaryTree myTree = new BinaryTree(null);
		if(preBegin>preEnd){
			return myTree;
		}
		// Plant a tree, specifying its root.
		myTree.root = new BinaryTreeNode(preOrder[preBegin]);
		// When preBegin equals preEnd,
		// there is only one element(the root) in a tree,
		// simply return that tree.
		if(preBegin == preEnd){
			return myTree;
		}
		int i = inBegin;
		int leftCount = 0;
		while(inOrder[i] != preOrder[preBegin]){
			leftCount++;
			i++;
		}
		int rightCount = preLength - leftCount -1;
		myTree.root.left = construct(preOrder, inOrder, preBegin+1, preBegin + leftCount,leftCount,inBegin).root;
		myTree.root.right = construct(preOrder,inOrder, preBegin + leftCount +1, preBegin + leftCount+ rightCount,rightCount,i+1).root;
		return myTree;
	}
	
	public static void main(String[] args) {
		int[] preOrder = new int[]{1,2,3,4,5};
		int[] inOrder = new int[]{2,1,4,3,5};
		//int[] preOrder = new int[]{12,13,9,4,2,8,1,6,7,3,15,5,10};
	//	int[] inOrder = new int[]{4,9,2,13,8,1,12,3,7,15,6,5,10};
		BinaryTree myTree = construct(preOrder, inOrder,0,preOrder.length-1,preOrder.length,0);
		myTree.preOrder(myTree.root);
		System.out.println();
		myTree.inOrder(myTree.root);
	}

}

class BinaryTree{
	
	public BinaryTreeNode root = null;
	public BinaryTree(BinaryTreeNode root){
		this.root = root;
	}
	public void inOrder(BinaryTreeNode root){
		if(root!=null){
			inOrder(root.left);
			System.out.print(root.key + "-");
			inOrder(root.right);
		}
	}
	
	public void preOrder(BinaryTreeNode root){
		if(root!=null){
			System.out.print(root.key + "-");
			preOrder(root.left);
			preOrder(root.right);
		}
	}
}

class BinaryTreeNode{
	public int key;
	public BinaryTreeNode parent = null;
	public BinaryTreeNode left = null;
	public BinaryTreeNode right = null;
	
	public BinaryTreeNode(int key){
		this.key = key;
	}
}
