package com.ace.training.usecase.callcenter;

public class Fresher extends Employee implements Agent {
	private static final String DESIGNATION = "FRESHER";
	private Agent excelarate;
	public Fresher(Agent excelarate) {
		super(DESIGNATION);
		this.excelarate = excelarate;
	}

	@Override
	public void handleCall(Calller caller) {
		System.out.println(caller.getCallerNumber());
		
	}

}
