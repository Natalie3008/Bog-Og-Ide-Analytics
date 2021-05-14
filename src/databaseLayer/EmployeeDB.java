package databaseLayer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelLayer.*;

public class EmployeeDB {

	// Create an employee in DB
	public boolean createEmployee(Employee employee) throws SQLException {
		String sqlEmployee = "INSERT INTO Employee (CPR, name, address, phoneNumber, email, position) VALUES(?,?,?,?,?,?)";
		int resultEmployee = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection()
					.prepareStatement(sqlEmployee);
			statementEmployee.setLong(1, employee.getCPR());
			statementEmployee.setString(2, employee.getName());
			statementEmployee.setString(3, employee.getAddress());
			statementEmployee.setInt(4, employee.getPhoneNumber());
			statementEmployee.setString(5, employee.getEmail());
			statementEmployee.setString(6, employee.getPosition());
			resultEmployee = statementEmployee.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statementEmployee.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return resultEmployee > 1;
	}

	// Update firstName
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
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return result == 1 ? employee : null;
	}

	// Update Address
	public Employee updateAddress(Employee employee, String street, String city, int zip, String country)
			throws SQLException {
		String sql = "UPDATE Employee SET street = ?, city = ?, zip = ?, country = ? WHERE CPR = ?";
		int result = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection().prepareStatement(sql);
			statementEmployee.setString(1, street);
			statementEmployee.setString(2, city);
			statementEmployee.setInt(3, zip);
			statementEmployee.setString(4, country);
			statementEmployee.setLong(5, employee.getCPR());
			result = statementEmployee.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statementEmployee.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return result == 1 ? employee : null;
	}

	// Update phoneNumber
	public Employee updatePhoneNumber(Employee employee) throws SQLException {
		String sql = "UPDATE Employee SET phoneNumber = ? WHERE CPR = ?";
		int result = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection().prepareStatement(sql);
			statementEmployee.setInt(1, employee.getPhoneNumber());
			statementEmployee.setLong(2, employee.getCPR());
			result = statementEmployee.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statementEmployee.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return result == 1 ? employee : null;
	}

	// Update email
	public Employee updateEmail(Employee employee) throws SQLException {
		String sql = "UPDATE Employee SET email = ? WHERE CPR = ?";
		int result = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection().prepareStatement(sql);
			statementEmployee.setString(1, employee.getEmail());
			statementEmployee.setLong(2, employee.getCPR());
			result = statementEmployee.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statementEmployee.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return result == 1 ? employee : null;
	}

	// Update Position
	public Employee updatePosition(Employee employee) throws SQLException {
		String sql = "UPDATE Employee SET position = ? WHERE CPR = ?";
		int result = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection().prepareStatement(sql);
			statementEmployee.setString(1, employee.getPosition());
			statementEmployee.setLong(2, employee.getCPR());
			result = statementEmployee.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statementEmployee.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return result == 1 ? employee : null;
	}

	public boolean deleteEmployee(long CPR) throws SQLException {
		String sqlEmployee = "DELETE FROM Employee WHERE CPR like ?";
		int result = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection().prepareStatement(sql);
			statementEmployee.setLong(1, CPR);
			result = statementEmployee.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statementEmployee.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return result > 1;
	}

}
