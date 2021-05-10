package databaseLayer;

import java.sql.SQLException;

import modelLayer.Employee;

public interface EmployeeDBIF {

	Employee createEmployee(Employee employee) throws SQLException;
	Employee updateName(Employee employee) throws SQLException;
	Employee updatePhoneNumber(Employee employee) throws SQLException;
	Employee updateEmail(Employee employee) throws SQLException;
	Employee updatePosition(Employee employee) throws SQLException;
	boolean deleteEmployee(int CPR) throws SQLException;
	
}
