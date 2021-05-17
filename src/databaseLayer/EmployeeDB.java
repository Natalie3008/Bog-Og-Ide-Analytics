package databaseLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelLayer.*;

public class EmployeeDB {

	// Create an employee in DB
	public boolean createEmployee(Employee employee, String street, int zip, String city, String country) throws SQLException {
		String sqlEmployee = "INSERT INTO Employee (CPR, name, street, zip, city, country, phoneNumber, email, position, version) VALUES(?,?,?,?,?,?,?,?,?,?)";
		int resultEmployee = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection()
					.prepareStatement(sqlEmployee);
			statementEmployee.setLong(1, employee.getCPR());
			statementEmployee.setString(2, employee.getName());
			statementEmployee.setString(3, street);
			statementEmployee.setInt(4, zip);
			statementEmployee.setString(5, city);
			statementEmployee.setString(6, country);
			statementEmployee.setInt(7, employee.getPhoneNumber());
			statementEmployee.setString(8, employee.getEmail());
			statementEmployee.setString(9, employee.getPosition());
			statementEmployee.setInt(10, 0);
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
		String sql = "UPDATE Employee SET name = ?, version = ? WHERE CPR = ? AND version = ?";
		int result = 0;
		int version = getVersion(employee.getCPR());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection().prepareStatement(sql);
			statementEmployee.setString(1, employee.getName());
			statementEmployee.setInt(2,  version + 1);
			statementEmployee.setLong(3, employee.getCPR());
			statementEmployee.setInt(4, version);
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
	public Employee updateAddress(Employee employee, String street, int zip, String city, String country)
			throws SQLException {
		String sql = "UPDATE Employee SET street = ?, zip = ?, city = ?, country = ?, version = ? WHERE CPR = ? AND version = ?";
		int result = 0;
		int version = getVersion(employee.getCPR());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection().prepareStatement(sql);
			statementEmployee.setString(1, street);
			statementEmployee.setInt(2, zip);
			statementEmployee.setString(3, city);
			statementEmployee.setString(4, country);
			statementEmployee.setInt(5, version + 1);
			statementEmployee.setLong(6, employee.getCPR());
			statementEmployee.setInt(7, version);
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
		String sql = "UPDATE Employee SET phoneNumber = ?, version = ? WHERE CPR = ? AND version = ?";
		int result = 0;
		int version = getVersion(employee.getCPR());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection().prepareStatement(sql);
			statementEmployee.setInt(1, employee.getPhoneNumber());
			statementEmployee.setInt(2,  version + 1);
			statementEmployee.setLong(3, employee.getCPR());
			statementEmployee.setInt(4,  version);
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
		String sql = "UPDATE Employee SET email = ?, version = ? WHERE CPR = ? AND version = ?";
		int result = 0;
		int version = getVersion(employee.getCPR());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection().prepareStatement(sql);
			statementEmployee.setString(1, employee.getEmail());
			statementEmployee.setInt(2,  version + 1);
			statementEmployee.setLong(3, employee.getCPR());
			statementEmployee.setInt(4,  version);
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
		String sql = "UPDATE Employee SET position = ?, version = ? WHERE CPR = ? AND version = ?";
		int result = 0;
		int version = getVersion(employee.getCPR());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection().prepareStatement(sql);
			statementEmployee.setString(1, employee.getPosition());
			statementEmployee.setInt(2,  version + 1);
			statementEmployee.setLong(3, employee.getCPR());
			statementEmployee.setInt(4,  version);
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
			PreparedStatement statementEmployee = DBConnection.getInstance().getConnection().prepareStatement(sqlEmployee);
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
	
	private int getVersion(long CPR) throws SQLException {
		String sqlFindVersion = "SELECT version FROM Employee WHERE CPR = ?";
		 int version = -1;
	        try {
	        	DBConnection.getInstance().getConnection().setAutoCommit(false);
	        	PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sqlFindVersion);
	        	statement.setLong(1, CPR);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                version = resultSet.getInt(1);
	            }
	            DBConnection.getInstance().getConnection().setAutoCommit(true);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return version;
	}
}