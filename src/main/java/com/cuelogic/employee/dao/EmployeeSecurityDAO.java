package com.cuelogic.employee.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuelogic.employee.entity.EmployeeSecurity;

@Repository
public class EmployeeSecurityDAO implements IEmployeeSecurityDAO {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("rawtypes")
	@Override
	public String disableEmployeeFromSystem(int id) {

		try {

			Session session = entityManager.unwrap(Session.class);

			Query query = session.createQuery("UPDATE EmployeeSecurity SET active_status = :status WHERE id = :theID");
			query.setParameter("status", "N");
			query.setParameter("theID", id);

			query.executeUpdate();

		} catch (Exception e) {
			System.out.println("Exception occured while disabling ID " + id + e.getMessage());
		}

		return "Employee with ID " + id + " disabled successfully!!!!";
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getIDForTheUser(String username) {

		try {

			Session session = entityManager.unwrap(Session.class);

			Query<EmployeeSecurity> query = session.createQuery("from EmployeeSecurity where userName= :theUserName");
			query.setParameter("theUserName", username);

			List<EmployeeSecurity> employeeSecurityList = query.getResultList();
			
			if (employeeSecurityList != null && employeeSecurityList.size() == 1)
				return employeeSecurityList.get(0).getId();

		} catch (Exception e) {
			System.out.println("Exception occured while getting ID " + e.getMessage());
		}
		
		return 0;
	}

}
