package com.cuelogic.employee.dao;

import com.cuelogic.employee.entity.EmployeeJWTData;

public interface IEmployeeJWTDataDao {
	
	public void addDatainJWTTable(EmployeeJWTData employeeJWTData);
	
	public void deleteDataFromJWTTable(String username);
	
	public boolean isLoggedInUser(String username);

}
