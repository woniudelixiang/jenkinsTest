package com.wqj.dataStructure.tree.binary.arithmetic;

import com.wqj.dataStructure.tree.binary.pojo.Node;

/**
 * 基于数组存储创建二叉树
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016年7月21日 下午2:11:42
 */
public class ArrayTree {
	Node root; // 根节点
	int size; // 树长度
	Object[] data; // 数的数据

	public ArrayTree() {
		
	}

	public ArrayTree(Object[] data) {
		this.data = data;
		size = data.length;
		root = createTree(0);
	}

	// 采用递归生成二叉树
	public Node createTree(int index) { 
		if (index >= size)
			return null;
		if (data[index] == null)
			return null;
		Node node = new Node(data[index]);
		node.setLeft(createTree(2 * index + 1));
		node.setRight(createTree(2 * index + 2));
		return node;
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
