package com.myInstitute.ui;

import java.util.Date;

import javax.validation.constraints.Size;

public class CreateUserRequest {

	@Size(max = 8, min = 3, message = "Firstname should be of minimum 3 chracters and maximum 8 chracters")
	private String firstname;

	private String lastname;

	private Long userId;

	private String address;

	private int age;

	// @Past(message="Birthdate can not be in future date ")
	private Date dob;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long id) {
		this.userId = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
