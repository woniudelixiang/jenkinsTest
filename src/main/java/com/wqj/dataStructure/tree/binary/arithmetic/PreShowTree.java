package com.wqj.dataStructure.tree.binary.arithmetic;

import com.wqj.dataStructure.tree.binary.pojo.Node;

/**
 * 基于先序遍历创建二叉树
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016年7月21日 下午2:12:02
 */
public class PreShowTree {
	Node root; // 根节点
	int size;  // 树长度
	String data; // 数的数据
	int index; 

	public PreShowTree(String data) {
		this.data = data;
		size = data.length();
		index = 0;
		root = createTree();
	}

	public Node createTree() { // 采用递归生成二叉树
		char ch = data.charAt(index ++);
		if(ch == '0')
			return null;
		else {
			Node node = new Node(ch);
			node.setLeft(createTree());
			node.setRight(createTree());
			return node;
		}
	}

	// 先序遍历
	public void preShow(Node node) { 
		if (node != null) {
			System.out.print(node.getData() + " ");
			preShow(node.getLeft());
			preShow(node.getRight());
		}
	}

	// 中序遍历
	public void middleShow(Node node) { 
		if (node != null) {
			middleShow(node.getLeft());
			System.out.print(node.getData() + " ");
			middleShow(node.getRight());
		}
	}

	// 后序遍历
	public void backShow(Node node) {
		if (node != null) {
			backShow(node.getLeft());
			backShow(node.getRight());
			System.out.print(node.getData() + " ");
		}
	}

	// 得到根节点
	public Node getRoot() {
		return root;
	}
}
