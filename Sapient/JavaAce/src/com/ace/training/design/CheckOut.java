package com.ace.training.design;

import com.ace.training.Employee;

public class CheckOut {
	private ShoppingCart cart;

	public CheckOut(ShoppingCart cart) {
		this.cart = cart;
	}

	private void checkOutProcess() {
		boolean result = verifyCartDetails(cart);
		if (!result) {
			showError();
		}
		Order order = generateOrder(cart);
		sendForPayment(order);
		sendNotification(order);
	}

	private void sendNotification(Order order) {

	}

	private void sendForPayment(Order order) {

	}

	private Order generateOrder(ShoppingCart cart2) {
		return null;
	}

	private void showError() {

	}

	private boolean verifyCartDetails(ShoppingCart cart2) {
		return true;
	}
	public static void main(String[] args) {
		Employee e =new  Employee();
		try {
			e.clone();
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
