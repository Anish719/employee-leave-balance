package com.cuelogic.employee.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cuelogic.employee.entity.EmployeeSecurity;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {
	
	@Autowired
	EmployeeSecurityRepository employeeSecurityRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<EmployeeSecurity> employeeSecurity = employeeSecurityRepository.findByUserName(username);
		
		employeeSecurity.orElseThrow(() -> new UsernameNotFoundException("not found" + username));
		
		return employeeSecurity.map(EmployeeUserDetails::new).get();
	}

}
