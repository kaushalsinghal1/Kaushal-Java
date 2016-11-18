package com.ace.training.usecase.callcenter;

public class TechnicalLead extends Employee implements Agent {
	private static final String DESIGNATION = "TL";
	private Agent escalate;

	public TechnicalLead(Agent excelarate, String name) {
		super(DESIGNATION, name);
		this.escalate = excelarate;
	}

	@Override
	public void handleCall(Calller caller) {
		System.out.println("Process call by " + DESIGNATION + " , name: "
				+ getName());
		System.out.println(caller.getCallerNumber());
		System.out.println("Delegate call to PM");
		escalate.handleCall(caller);
	}

}
