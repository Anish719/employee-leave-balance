package com.cuelogic.employee.service;

import com.cuelogic.employee.entity.Employee;

public interface IEmployeeService {
	
	public void addEmployee(Employee employee);
	
	public String viewLeaveBalanceByID(int id);
	
	public String getEmployeeDetails(int id);
	
	public String applyForLeave(int id, String leaveType);

}
