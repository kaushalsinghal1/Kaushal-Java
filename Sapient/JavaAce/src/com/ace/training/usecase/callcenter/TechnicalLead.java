package com.ace.training.usecase.callcenter;

public class TechnicalLead extends Employee implements Agent {
	private static final String DESIGNATION = "TL";
	private Agent excelarate;

	public TechnicalLead(Agent excelarate) {
		super(DESIGNATION);
		this.excelarate = excelarate;
	}

	@Override
	public void handleCall(Calller caller) {
		System.out.println("Process call by "+ DESIGNATION+" , name: "+ getName());
		System.out.println(caller.getCallerNumber());
		System.out.println("Delegate call to PM");
		excelarate.handleCall(caller);
	}

}
