package com.cuelogic.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employeejwtdata")
public class EmployeeJWTData {

	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "token")
	private String token;

	public EmployeeJWTData() {
		super();
	}

	public EmployeeJWTData(String username, String token) {
		this.username = username;
		this.token = token;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "EmployeeJWTData [userName=" + username + ", token=" + token + "]";
	}

}
