package com.yuanyu.demo;

public class Node{
	Node left;
	Node right;
	int value;
	
	public Node(int value){
		this.value = value;
	}

	public Node(Node left, Node right, int value){
		this.left = left;
		this.right = right;
		this.value = value;
	}
	 
	public static void traverse(Node n){
		if(n.left != null){
			traverse(n.left);
		}
		
		if(n.right != null){
			traverse(n.right);
		}
		
		System.out.print(n.value + ",");
	}
}