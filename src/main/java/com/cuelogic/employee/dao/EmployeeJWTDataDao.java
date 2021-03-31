package com.cuelogic.employee.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuelogic.employee.entity.EmployeeJWTData;

@Repository
public class EmployeeJWTDataDao implements IEmployeeJWTDataDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public void addDatainJWTTable(EmployeeJWTData employeeJWTData) {
		try {
			Session session = entityManager.unwrap(Session.class);
			session.saveOrUpdate(employeeJWTData);
		} catch (Exception e) {
			System.out.println("Exception occured adding Data in JWT table " + e.getMessage());
		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteDataFromJWTTable(String username) {
		try {
			Session session = entityManager.unwrap(Session.class);
			
			Query query = session.createQuery("delete from EmployeeJWTData where username= :theusername");
			query.setParameter("theusername", username);

			query.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception occured while delete Data from JWT table " + e.getMessage());
		}
	}

	@Override
	public boolean isLoggedInUser(String username) {
		
		boolean success = false;
		
		try {
			Session session = entityManager.unwrap(Session.class);
			
			EmployeeJWTData employeeJWTData = session.get(EmployeeJWTData.class, username);
			
			if (employeeJWTData != null) success = true;
			
		} catch (Exception e) {
			System.out.println("Exception in isLoggedInUser() " + e.getMessage());
		}
		
		return success;
	}


}
