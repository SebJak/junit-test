package org.junit.test.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.junit.test.model.BaseEntity;

@Entity
@Table(name="EMPLOYEE")
public class Employee extends BaseEntity {

	private static final long serialVersionUID = -3946617834063076500L;
	
	@Column(length=32,nullable=false)
	private String firstName;
	
	@Column(length=32, nullable=false)
	private String lastName;
	
	@Column(nullable=false, unique=true)
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
