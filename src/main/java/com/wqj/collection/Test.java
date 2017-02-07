package com.wqj.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class Test {

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add(null);
		list.remove(0);
		
		LinkedList linkedList = new LinkedList();
//		HashSet
//		Object
//		String
		linkedList.add("A");
		linkedList.add("B");
		linkedList.add("C");
		
		linkedList.add(1, "D");
		for (Object obj : linkedList) {
			System.out.println(obj);
		}
		
		
		TreeSet<String> set = new TreeSet<String>();
		HashMap map = new HashMap();
		map.put(null, "");
		map.get(null);
		map.remove(null);
		
		Set keySet = map.keySet();
		Collection values = map.values();
		
		
		
	}
	
}
