package com.cuelogic.employee.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuelogic.employee.authentication.AuthenticationRequest;
import com.cuelogic.employee.entity.EmployeeJWTData;
import com.cuelogic.employee.service.EmployeeJWTDataService;
import com.cuelogic.employee.service.EmployeeSecurityService;
import com.cuelogic.employee.util.EmployeeUtil;
import com.cuelogic.employee.util.JWTUtil;

@RestController
@RequestMapping("/cue")
public class EmployeeLoginRestController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private EmployeeSecurityService employeeSecurityService;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private EmployeeUtil employeeUtil;

	@Autowired
	private EmployeeJWTDataService employeeJWTDataService;

	@PostMapping(value = "/login", produces = "application/json")
	public String doLogin(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException bce) {
			return employeeUtil.invalidUserMessage("Incorrect UserName or Password");
		} catch (DisabledException de) {
			return employeeUtil
					.invalidUserMessage("User is not allowed to use the service, please contact Adminstrator");
		}

		final String username = authenticationRequest.getUsername();

		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		final int id = employeeSecurityService.getIDForTheUser(username);

		final String jwt = jwtUtil.generateToken(userDetails, id);

		employeeJWTDataService.addDatainJWTTable(new EmployeeJWTData(username, jwt));

		return employeeUtil.buildLoginJWTData(jwt);
	}

	@PostMapping(value = "/logout", produces = "application/json")
	public String doLogout(@RequestBody AuthenticationRequest authenticationRequest) {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException bce) {
			return employeeUtil.invalidUserMessage("Incorrect UserName or Password");
		} catch (DisabledException de) {
			return employeeUtil
					.invalidUserMessage("User is not allowed to use the service, please contact Adminstrator");
		}

		final String username = authenticationRequest.getUsername();

		employeeJWTDataService.deleteDataFromJWTTable(username);

		return "Logged out successfully!!";
	}

}
