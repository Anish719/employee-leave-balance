package com.cuelogic.employee.service;

public interface IEmployeeSecurityService {
	
	public String disableEmployeeFromSystem(int id);
	
	public int getIDForTheUser(String username);

}
