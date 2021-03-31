package com.cuelogic.employee.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuelogic.employee.entity.Employee;
import com.cuelogic.employee.util.EmployeeUtil;

@Repository
public class EmployeeDAO implements IEmployeeDAO {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	EmployeeUtil employeeUtil;

	@Override
	public void addEmployee(Employee employee) {

		try {

			Session session = entityManager.unwrap(Session.class);

			session.saveOrUpdate(employee);

		} catch (Exception e) {
			System.out.println("Exception occured adding employee " + e.getMessage());
		}

	}

	@Override
	public String viewLeaveBalanceByID(int id) {

		String leaveBalanceData = null;
		try {

			Session session = entityManager.unwrap(Session.class);

			Employee employee = session.get(Employee.class, id);

			leaveBalanceData = employeeUtil.buildLeaveBalanceData(employee);

		} catch (Exception e) {

		}
		return leaveBalanceData;
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getEmployeeDetails(int id) {

		String employeeDetails = null;

		try {

			Session session = entityManager.unwrap(Session.class);

			Query<Employee> query = session.createQuery("from Employee where security_id= :theSecurityID");
			query.setParameter("theSecurityID", id);

			List<Employee> employeeList = query.getResultList();

			if (employeeList != null && employeeList.size() == 1) {
				employeeDetails = employeeUtil.buildEmployeeData(employeeList.get(0));
			}

		} catch (Exception e) {
			System.out.println("Exception occured while getting employee details for id " + id + e.getMessage());
		}

		return employeeDetails;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String applyForLeave(int id, String leaveType) {

		boolean success = false;

		try {

			Session session = entityManager.unwrap(Session.class);

			Query<Employee> query = session.createQuery("from Employee where security_id= :theSecurityID");
			query.setParameter("theSecurityID", id);

			List<Employee> employeeList = query.getResultList();

			if (employeeList != null && employeeList.size() == 1) {
				Employee employee = employeeList.get(0);
				int remainingLeaves = "PL".equalsIgnoreCase(leaveType) ? employee.getPaidLeave()
						: employee.getSickLeave();
				String leave = "PL".equalsIgnoreCase(leaveType) ? "paidLeave" : "sickLeave";
				if (remainingLeaves > 0) {
					success = true;
					Query updateQuery = session.createQuery(
							"UPDATE Employee SET " + leave + " = :" + "rl" + " where security_id= :theSecurityID");
					updateQuery.setParameter("rl", remainingLeaves - 1);
					updateQuery.setParameter("theSecurityID", id);
					updateQuery.executeUpdate();
				}
			}

		} catch (Exception e) {
			System.out.println("Exception occured in applyForLeave() for id " + id + e.getMessage());
		}

		return employeeUtil.responseForApplyLeave(success);
	}

}
