package com.wqj.menuStudy;

public class TestRecursion {

	public static void main(String[] args) {
		/*
		 * for (int i = 1; i <= 9; i++) { for (int j = 1; j <= i; j++) {
		 * System.out.print(j + " * " + i + " = " + (i * j) + " "); }
		 * System.out.println(); }
		 */
		m(9);
	}

	//乘法表
	public static void m(int i) {
		if (i == 1) {
			System.out.println("1*1=1 ");
		} else {
			m(i - 1);
			for (int j = 1; j <= i; j++) {
				System.out.print(j + "*" + i + "=" + j * i + " ");
			}
			System.out.println();
		}
	}

	//阶乘
	static int multiply(int n) {
		if (n == 1 || n == 0){
			return n;
		}else{
			return n * multiply(n - 1);
		}
	}
	
	
	
	
}
