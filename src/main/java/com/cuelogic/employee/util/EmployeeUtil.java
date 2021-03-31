package com.cuelogic.employee.util;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cuelogic.employee.entity.Employee;

@Component
public class EmployeeUtil {

	@Autowired
	private JWTUtil jwtUtil;

	@SuppressWarnings("unchecked")
	public String buildLeaveBalanceData(Employee employee) {

		JSONObject leaveBalanceData = new JSONObject();

		try {
			leaveBalanceData.put("Name", employee.getFirstName() + " " + employee.getLastName());
			leaveBalanceData.put("Paid Leave", employee.getPaidLeave());
			leaveBalanceData.put("Sick Leave", employee.getSickLeave());

		} catch (Exception e) {
			System.out.println("Exception ouccred in buildLeaveBalanceData");
		}

		return leaveBalanceData.toJSONString();

	}

	@SuppressWarnings("unchecked")
	public String buildLoginJWTData(String jwt) {

		JSONObject jwtData = new JSONObject();

		try {
			jwtData.put("jwt", jwt);
		} catch (Exception e) {
			System.out.println("Exception ouccred in buildLoginJWTData");
		}

		return jwtData.toJSONString();
	}

	@SuppressWarnings("unchecked")
	public String invalidUserMessage(String message) {
		JSONObject data = new JSONObject();
		data.put("error", message);

		return data.toJSONString();

	}

	@SuppressWarnings("unchecked")
	public String buildEmployeeData(Employee employee) {

		JSONObject employeeData = new JSONObject();

		try {
			employeeData.put("First Name", employee.getFirstName());
			employeeData.put("Last Name", employee.getLastName());
			employeeData.put("Email", employee.getEmail());
			employeeData.put("Designation", employee.getDesignation());
			employeeData.put("Paid Leave", employee.getPaidLeave());
			employeeData.put("Sick Leave", employee.getSickLeave());

		} catch (Exception e) {

		}

		return employeeData.toJSONString();
	}

	public int getSeceretID(String authorizationHeader) {

		int secretID = 0;
		String jwt = null;
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			secretID = jwtUtil.extractSecretIDFromToken(jwt);
		}

		return secretID;

	}

	public String getUserName(String authorizationHeader) {
		return jwtUtil.extractUsername(authorizationHeader.substring(7));
	}

	@SuppressWarnings("unchecked")
	public String responseForApplyLeave(boolean success) {

		JSONObject leaveData = new JSONObject();

		try {
			if (success) {
				leaveData.put("status", "successs");
				leaveData.put("Message", "Applied For Leave Successfully");
			} else {
				leaveData.put("status", "failure");
				leaveData.put("Message", "No Remaining Leaves");
			}

		} catch (Exception e) {

		}

		return leaveData.toJSONString();
	}

}
