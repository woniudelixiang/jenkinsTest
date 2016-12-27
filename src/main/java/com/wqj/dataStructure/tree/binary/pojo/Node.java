package com.wqj.dataStructure.tree.binary.pojo;

/**
 * 节点信息
 * @author Qijun wang
 * @email wqjjob@126.com
 * @date 2016年7月21日 下午12:45:44
 */
public class Node {
	Node left;
    Node right;
    Object data;
    public Node() {
		
	}
    
	public Node(Object data) {
        left = null;
        right = null;
        this.data = data;
    }
	
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
