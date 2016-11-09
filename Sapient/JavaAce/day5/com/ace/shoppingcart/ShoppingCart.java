package com.ace.shoppingcart;

import java.util.List;

import com.ace.shoppingcart.notify.NotificationService;
import com.ace.training.usecase.sms.payment.IPayment;
import com.ace.training.usecase.sms.payment.PaymentFactory;
import com.ace.training.usecase.sms.payment.PaymentStatus;

public class ShoppingCart {
	private List<Product> products;
	private int totalAmount;
	private String paymentType;
	private PaymentFactory paymentFactory;
	private NotificationService notificationService;
	private InventoryService inventoryService;
	private PersistentService persistentService;

	public ShoppingCart(PaymentFactory paymentFactory,
			NotificationService notificationService,
			InventoryService inventoryService,
			PersistentService persistentService) {
		this.paymentFactory = paymentFactory;
		this.notificationService = notificationService;
		this.inventoryService = inventoryService;
		this.persistentService = persistentService;

	}

	public void checkout() {
		Order order = generateOrder();
		// Validate Order
		IPayment payment = PaymentFactory.getInstance().getPayment(paymentType);
		PaymentStatus paymentStatus = payment.doPayment(20);
		order.setPaymentStatus(paymentStatus);
		// Notify Inventory and Persist Order details if Payment Success
		notificationService.notifyCustomer(order);
		if (paymentStatus.isSuccess()) {
			inventoryService.updateInventory(order);
			persistentService.persistOrder(order);
		}
	}

	private Order generateOrder() {
		return new Order();
	}
}
