package com.cuelogic.employee.dao;

import com.cuelogic.employee.entity.Employee;

public interface IEmployeeDAO {
	
	public void addEmployee(Employee employee);
	
	public String viewLeaveBalanceByID(int id);
	
	public String getEmployeeDetails(int id);
	
	public String applyForLeave(int id, String leaveType);

}
