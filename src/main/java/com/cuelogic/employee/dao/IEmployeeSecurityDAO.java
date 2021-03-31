package com.cuelogic.employee.dao;

public interface IEmployeeSecurityDAO {
	
	public String disableEmployeeFromSystem(int id);
	
	public int getIDForTheUser(String username);

}
