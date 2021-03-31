package com.cuelogic.employee.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuelogic.employee.service.EmployeeJWTDataService;
import com.cuelogic.employee.service.EmployeeService;
import com.cuelogic.employee.util.EmployeeUtil;

@RestController
@RequestMapping("/cue/employee/user")
public class EmployeeUserRestController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeUtil employeeUtil;
	
	@Autowired
	private EmployeeJWTDataService employeeJWTDataService;

	@GetMapping(value = "/getMyDetails", produces = "application/json")
	public String getUserDetails(@RequestHeader(value = "Authorization") String authorizationHeader) {
		if (employeeJWTDataService.isLoggedInUser(employeeUtil.getUserName(authorizationHeader))) {
			return employeeService.getEmployeeDetails(employeeUtil.getSeceretID(authorizationHeader));
		} else {
			return "Please login to use this service";
		}
	}

	@GetMapping(value = "/leaveBalance", produces = "application/json")
	public String getUserLeaveBalance(@RequestHeader(value = "Authorization") String authorizationHeader) {
		return employeeService.viewLeaveBalanceByID(employeeUtil.getSeceretID(authorizationHeader));
	}

	@PutMapping(value = "/applyForLeave", produces = "application/json")
	public String applyForLeave(@RequestHeader(value = "Authorization") String authorizationHeader,
			@RequestParam(value = "type") String leaveType) {
		return employeeService.applyForLeave(employeeUtil.getSeceretID(authorizationHeader), leaveType);
	}

}
