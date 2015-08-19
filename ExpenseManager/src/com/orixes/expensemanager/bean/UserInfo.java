package com.orixes.expensemanager.bean;

public class UserInfo {

	private String name;
	private String mobileNumber;
	private String email;
	private String salary;
	
	public UserInfo(String name, String mobileNumber, String email, String salary) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public String getSalary() {
		return salary;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", mobileNumber=" + mobileNumber
				+ ", email=" + email + ", salary=" + salary + "]";
	}	
	
}
