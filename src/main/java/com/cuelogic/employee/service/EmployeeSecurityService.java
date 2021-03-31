package com.cuelogic.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuelogic.employee.dao.EmployeeSecurityDAO;

@Service
public class EmployeeSecurityService implements IEmployeeSecurityService {

	@Autowired
	EmployeeSecurityDAO employeeSecurityDAO;

	@Override
	@Transactional
	public String disableEmployeeFromSystem(int id) {
		return employeeSecurityDAO.disableEmployeeFromSystem(id);
	}

	@Override
	public int getIDForTheUser(String username) {
		return employeeSecurityDAO.getIDForTheUser(username);
	}

}
