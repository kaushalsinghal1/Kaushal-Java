package com.ace.training.design;

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

}
