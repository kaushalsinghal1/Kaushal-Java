package com.ace.training.usecase;

import java.util.Map.Entry;

public class LRUTest {
public static void main(String[] args) {
	//LRULInkedHashMap<Integer, Integer> lru = new LRULInkedHashMap<>(3);
	LRUCustomImpl<Integer, Integer> lru = new LRUCustomImpl<>(3);
	lru.put(1, 1);
	lru.put(2, 2);
	lru.put(3, 3);
	lru.get(1);
	lru.put(4, 4);
//	System.out.println("2 should be removed");
	int key =2;
	System.out.println("Key: "+ key+" , value: "+ lru.get(key));
	lru.print();
	//printMap(lru);
	
}
private static void printMap(LRULInkedHashMap<Integer, Integer> lru){
	for(Entry<Integer, Integer> entry: lru.entrySet()){
		System.out.println("Key: "+ entry.getKey()+" , value: "+ entry.getValue());
	}
}
}
