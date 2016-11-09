package com.ace.training.design;

public class PurchaseItem {

	private Item item;
	private int quntity;

	public int getTotalPrice() {
		return item.getPrice() * quntity;
	}

}
