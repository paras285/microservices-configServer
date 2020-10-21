package com.demo.propertyreader.models;

public class Author {

	private String firstName;
	private String lastName;
	private String designation;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Override
	public String toString() {
		return "Author Details : [firstName=" + firstName + ", lastName=" + lastName + ", designation=" + designation + "]";
	}
	
	
}
