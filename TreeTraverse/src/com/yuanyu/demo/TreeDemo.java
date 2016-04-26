package com.yuanyu.demo;

public class TreeDemo {
	public static String reverse(String input){
		StringBuilder sb = new StringBuilder();
		if(input == null)
			return null;
		int length = input.length();
		for(int i = length -1 ; i >= 0; i-- ){
			sb.append(input.charAt(i));
		}
		return sb.toString();
	}
	
	
	public static void main(String[] arg){
		String helloStr = "Hello world!";
		System.out.println(String.format("%s reverse: %s", helloStr, reverse(helloStr)));
		Node root = new Node(7);
		Node secondLeft = new Node(3), secondRight = new Node(6);
		Node thirdLL = new Node(1), thirdLR = new Node(2), thirdRL = new Node(4), thirdRR = new Node(5);
		root.left = secondLeft;
		root.right = secondRight;
		secondLeft.left = thirdLL;
		secondLeft.right = thirdLR;
		secondRight.left = thirdRL;
		secondRight.right = thirdRR;
		
		Node.traverse(root);
	}
	
	
}
