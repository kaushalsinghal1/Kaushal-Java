package com.ace.training.usecase.callcenter;

public class CallcenterMain {
	public static void main(String[] args) {
		Calller calller = new UserCaller();
		CallHandler callHandler = CallHandler.getInstance();
		callHandler.calling(calller);
	
	}
}
