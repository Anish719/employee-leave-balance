package com.cuelogic.employee.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cuelogic.employee.entity.EmployeeSecurity;

@Repository
public interface EmployeeSecurityRepository extends JpaRepository<EmployeeSecurity, Integer> {
	
	Optional<EmployeeSecurity> findByUserName(String username);

}
