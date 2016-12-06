package com.test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class RefrernceType {
	public static void main(String[] args) throws InterruptedException {
		WeakReference<Integer> weakRef = new WeakReference<Integer>(
				new Integer(10));
		SoftReference<Integer> softRef = new SoftReference<Integer>(
				new Integer(20));
		Thread.sleep(10000);
		System.out.println("WeekRef"+ weakRef);
		System.out.println("softRef"+ softRef);
		
		
	}

}
