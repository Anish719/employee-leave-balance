package com.cuelogic.employee.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuelogic.employee.dao.EmployeeJWTDataDao;
import com.cuelogic.employee.entity.EmployeeJWTData;

@Service
public class EmployeeJWTDataService implements IEmployeeJWTDataService {
	
	@Autowired
	private EmployeeJWTDataDao employeeJWTDataDao;

	@Override
	@Transactional
	public void addDatainJWTTable(EmployeeJWTData employeeJWTData) {
		employeeJWTDataDao.addDatainJWTTable(employeeJWTData);
	}

	@Override
	@Transactional
	public void deleteDataFromJWTTable(String username) {
		employeeJWTDataDao.deleteDataFromJWTTable(username);
	}

	@Override
	@Transactional
	public boolean isLoggedInUser(String username) {
		return employeeJWTDataDao.isLoggedInUser(username);
	}

}
