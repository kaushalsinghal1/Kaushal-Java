package com.ace.training.usecase.callcenter;

public class CallHandler {

	private Agent agent;
	private static CallHandler instance;

	public static CallHandler getInstance() {
		if (instance == null) {
			synchronized (CallHandler.class) {
				if (instance == null) {
					instance = new CallHandler();
				}
			}
		}
		return instance;
	}

	private CallHandler() {
		
		Agent pm = new ProductManager(null,"Product Manager");
		Agent tl = new TechnicalLead(pm, "Technical Lead");
		agent = new Fresher(tl, "Fresher");
	}

	public void calling(Calller calller) {
		agent.handleCall(calller);
	}
	
}
