package databaseLayer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelLayer.*;

public class EmployeeDB {

	//Create an employee in DB
	public boolean createEmployee(Employee employee) throws SQLException {
		String sqlEmployee = "INSERT INTO Employee (CPR, name, address, phoneNumber, email, position) VALUES(?,?,?,?,?,?)";
		int resultEmployee = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection().prepareStatement(sqlEmployee);
			statementEmployee.setLong(1, employee.getCPR());
			statementEmployee.setString(2, employee.getName());
			statementEmployee.setString(3, employee.getAddress());
			statementEmployee.setInt(4,  employee.getPhoneNumber());
			statementEmployee.setString(5, employee.getEmail());
			statementEmployee.setString(6, employee.getPosition());
			resultEmployee = statementEmployee.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statementEmployee.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		}
		catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return resultEmployee > 1;
	}
		
	//Update firstName 
	public Employee updateName(Employee employee) throws SQLException {
		String sql = "UPDATE Employee SET name = ? WHERE CPR = ?";
		int result = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection().prepareStatement(sql);
			statementEmployee.setString(1, employee.getName());
			statementEmployee.setLong(2, employee.getCPR());
			result = statementEmployee.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statementEmployee.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		}
		catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return result == 1 ? employee : null;
		}
	
	//Update Address
	public Employee updateAddress(Employee employee, String street, String city, int zip, String country) throws SQLException {
		String sql = "UPDATE Employee SET street = ?, city = ?, zip = ?, country = ? WHERE CPR = ?";
		int result = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection().prepareStatement(sql);
			statementEmployee.setString(1, street);
			statementEmployee.setString(2,  city);
			statementEmployee.setInt(3,  zip);
			statementEmployee.setString(4,  country);
			statementEmployee.setLong(5, employee.getCPR());
			result = statementEmployee.executeUpdate();
			statementEmployee.close();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		}
		catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return result == 1 ? employee : null;
		}

	
	//Update phoneNumber
	public Employee updatePhoneNumber(Employee employee) throws SQLException {
		String sql = "UPDATE Employee SET phoneNumber = '" + employee.getPhoneNumber() + "'" + "WHERE CPR = " + employee.getCPR();
		int result = DBConnection.getInstance().executeUpdate(sql);
		return result == 1 ? employee : null;
	}
	
	//Update email
	public Employee updateEmail(Employee employee) throws SQLException {
		String sql = "UPDATE Employee SET email = '" + employee.getEmail() + "'" + "WHERE CPR = " + employee.getCPR();
		int result = DBConnection.getInstance().executeUpdate(sql);
		return result == 1 ? employee : null;
		}
	
	//Update Position
		public Employee updatePosition(Employee employee) throws SQLException {
			String sql = "UPDATE Employee SET position = '" + employee.getPosition() + "'" + "WHERE CPR = " + employee.getCPR();
			int result = DBConnection.getInstance().executeUpdate(sql);
			return result == 1 ? employee : null;
		}
		

	public boolean deleteEmployee(int CPR) throws SQLException {
		String sqlEmployee = "DELETE FROM Employee WHERE CPR like '" + CPR + "'";
		int resultEmployee = DBConnection.getInstance().executeUpdate(sqlEmployee);
		return resultEmployee > 1;
		}
	
}
