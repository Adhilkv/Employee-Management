package com.employeeManagment.EmployeeManagment.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_details")
public class User {

	@Id
	@Column(name = "username",nullable = false)
	private String userName;

	@Column(name = "first_name",nullable = false)
	private String firstName;

	@Column(name = "last_name",nullable = false)
	private String lastName;

	@Column(name = "password",nullable = false)
	private String password;

	@Column(name = "department",nullable = false)
	private String department;

	@Column(name = "created_date",nullable = false)
	private String createdDate;
	
	
	@Column(name = "role",nullable = false)
	private String role;
	 
	public User() {

	}

	public User(String userName, String firstName, String lastName, String password, String department,
			String createdDate,String role) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.department = department;
		this.createdDate = createdDate;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
