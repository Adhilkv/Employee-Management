package com.employeeManagment.EmployeeManagment;

public class Authencationbean {

	private String message;
	
	public Authencationbean(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

}