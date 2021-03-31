package com.cuelogic.employee.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuelogic.employee.entity.Employee;
import com.cuelogic.employee.service.EmployeeSecurityService;
import com.cuelogic.employee.service.EmployeeService;

@RestController
@RequestMapping("/cue/employee/admin")
public class EmployeeAdminRestController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeSecurityService employeeSecurityService;

	@PostMapping(value = "/add", produces = "application/json", consumes = "application/json")
	public Employee addEmployee(@RequestBody Employee employee) {

		employeeService.addEmployee(employee);

		return employee;
	}

	@GetMapping(value = "/disable/{id}")
	public String disableEmployee(@PathVariable("id") int id) {
		return employeeSecurityService.disableEmployeeFromSystem(id);
	}

	@GetMapping(value = "/leavebalance/{id}", produces = "application/json")
	public String viewLeaveBalanceByID(@PathVariable("id") int id, @RequestHeader(value = "Authorization") String authorizationHeader) {
		return employeeService.viewLeaveBalanceByID(id);
	}

}
