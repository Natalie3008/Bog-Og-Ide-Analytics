package databaseLayer;

import java.sql.SQLException;

import modelLayer.Employee;

public interface EmployeeDBIF {
	
	boolean createEmployee(Employee employee, String street, int zip, String city, String country) throws SQLException;
	Employee updateName(Employee employee) throws SQLException;
	Employee updateAddress(Employee employee, String street, int zip, String city, String country) throws SQLException;
	Employee updatePhoneNumber(Employee employee) throws SQLException;
	Employee updateEmail(Employee employee) throws SQLException;
	Employee updatePosition(Employee employee) throws SQLException;
	boolean deleteEmployee(long CPR) throws SQLException;	
}
