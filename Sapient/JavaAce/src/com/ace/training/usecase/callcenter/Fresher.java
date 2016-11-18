package com.ace.training.usecase.callcenter;

public class Fresher extends Employee implements Agent {
	private static final String DESIGNATION = "FRESHER";
	private Agent escalate;

	public Fresher(Agent excelarate, String name) {
		super(DESIGNATION, name);
		this.escalate = excelarate;
	}


	@Override
	public void handleCall(Calller caller) {
		System.out.println("Process call by " + DESIGNATION + " , name: "
				+ getName());
		System.out.println(caller.getCallerNumber());
		System.out.println("Delegate call to TL");
		escalate.handleCall(caller);

	}

}
