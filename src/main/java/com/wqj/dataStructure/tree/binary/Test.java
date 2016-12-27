package com.wqj.dataStructure.tree.binary;

import com.wqj.dataStructure.tree.binary.arithmetic.ArrayTree;

public class Test {

	 public static void main(String[] args) {
	       // Object[] array = new Object[] {'1', '2', '3', '4', null, '5', '6', '7', '8', null, null, '9', 'A'};
	        Object[] t1 = new Object[] {1, null,null};
	        Object[] t2 = new Object[] {1, 2,null};
	        Object[] t3 = new Object[] {1, null,2};
	        Object[] t4 = new Object[] {1, 2,3};
	        
	        ArrayTree tree = new ArrayTree(t4);

	        System.out.println("先序遍历");
	        tree.preShow(tree.getRoot());
	        System.out.println();

//	        System.out.println("中序遍历");
//	        tree.middleShow(tree.getRoot());
//	        System.out.println();
//
//	        System.out.println("后序遍历");
//	        tree.backShow(tree.getRoot());
//	        System.out.println();
	    }
	
	
}
