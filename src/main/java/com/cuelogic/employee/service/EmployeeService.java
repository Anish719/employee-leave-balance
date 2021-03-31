package com.cuelogic.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuelogic.employee.dao.EmployeeDAO;
import com.cuelogic.employee.entity.Employee;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	@Transactional
	public void addEmployee(Employee employee) {
		employeeDAO.addEmployee(employee);
	}

	@Override
	@Transactional
	public String viewLeaveBalanceByID(int id) {
		return employeeDAO.viewLeaveBalanceByID(id);
	}

	@Override
	@Transactional
	public String getEmployeeDetails(int id) {
		return employeeDAO.getEmployeeDetails(id);
	}

	@Override
	@Transactional
	public String applyForLeave(int id, String leaveType) {
		return employeeDAO.applyForLeave(id, leaveType);
	}

}
