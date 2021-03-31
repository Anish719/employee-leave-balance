package com.cuelogic.employee.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "designation")
	private String designation;

	@Column(name = "paid_leave")
	private int paidLeave = 10;

	@Column(name = "sick_leave")
	private int sickLeave = 5;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "security_id")
	private EmployeeSecurity employeeSecurity;

	public Employee() {
		super();
	}

	public Employee(String firstName, String lastName, String email, String designation, int paidLeave, int sickLeave) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.designation = designation;
		this.paidLeave = paidLeave;
		this.sickLeave = sickLeave;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getPaidLeave() {
		return paidLeave;
	}

	public void setPaidLeave(int paidLeave) {
		this.paidLeave = paidLeave;
	}

	public int getSickLeave() {
		return sickLeave;
	}

	public void setSickLeave(int sickLeave) {
		this.sickLeave = sickLeave;
	}

	public EmployeeSecurity getEmployeeSecurity() {
		return employeeSecurity;
	}

	public void setEmployeeSecurity(EmployeeSecurity employeeSecurity) {
		this.employeeSecurity = employeeSecurity;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", designation=" + designation + ", paidLeave=" + paidLeave + ", sickLeave=" + sickLeave
				+ ", employeeSecurity=" + employeeSecurity + "]";
	}

}
