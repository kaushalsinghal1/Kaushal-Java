package com.ace.training.usecase.callcenter;

public class ProductManager extends Employee implements Agent {
	private static final String DESIGNATION = "PM";
	private Agent escalate;

	public ProductManager(Agent excelarate, String name) {
		super(DESIGNATION, name);
		this.escalate = excelarate;
	}

	@Override
	public void handleCall(Calller caller) {
		System.out.println("Process call by " + DESIGNATION + " , name: "
				+ getName());
		System.out.println(caller.getCallerNumber());

	}

}
