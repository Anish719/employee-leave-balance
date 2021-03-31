package com.cuelogic.employee.service;

import com.cuelogic.employee.entity.EmployeeJWTData;

public interface IEmployeeJWTDataService {

	public void addDatainJWTTable(EmployeeJWTData employeeJWTData);
	
	public void deleteDataFromJWTTable(String username);
	
	public boolean isLoggedInUser(String username);

}
