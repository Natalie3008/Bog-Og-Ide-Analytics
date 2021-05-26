package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelLayer.*;

public class EmployeeDB implements EmployeeDBIF {

	private static final String INSERT_INTO_EMPLOYEE = "INSERT INTO Employee (CPR, name, street, zip, city, country, phoneNumber, email, position, version) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_EMPLOYEE_NAME = "UPDATE Employee SET name = ?, version = ? WHERE CPR = ? AND version = ?";
	private static final String UPDATE_EMPLOYEE_ADDRESS = "UPDATE Employee SET street = ?, zip = ?, city = ?, country = ?, version = ? WHERE CPR = ? AND version = ?";
	private static final String UPDATE_EMPLOYEE_PHONE_NUMBER = "UPDATE Employee SET phoneNumber = ?, version = ? WHERE CPR = ? AND version = ?";
	private static final String UPDATE_EMPLOYEE_EMAIL = "UPDATE Employee SET email = ?, version = ? WHERE CPR = ? AND version = ?";
	private static final String UPDATE_EMPLOYEE_POSITION = "UPDATE Employee SET position = ?, version = ? WHERE CPR = ? AND version = ?";
	
	private static final String DELETE_EMPLOYEE_WITH_CPR = "DELETE FROM Employee WHERE CPR like ?";

	private static final String SELECT_VERSION_FROM_EMPLOYEE = "SELECT version FROM Employee WHERE CPR = ?";

	PreparedStatement psInsertIntoEmployee;
	PreparedStatement psUpdateEmployeeName;
	PreparedStatement psUpdateEmployeeAddress;
	PreparedStatement psUpdateEmployeePhoneNumber;
	PreparedStatement psUpdateEmployeeEmail;
	PreparedStatement psUpdateEmployeePosition;

	PreparedStatement psDeleteEmployeeWithCPR;

	PreparedStatement psSelectVersionFromEmployee;

	public EmployeeDB(){
		initPreparedStatement();
	}	

	private void initPreparedStatement() {
		Connection connection = DBConnection.getInstance().getConnection();
		try{
			psInsertIntoEmployee = connection.prepareStatement(INSERT_INTO_EMPLOYEE);
			psUpdateEmployeeName = connection.prepareStatement(UPDATE_EMPLOYEE_NAME);
			psUpdateEmployeeAddress = connection.prepareStatement(UPDATE_EMPLOYEE_ADDRESS);
			psUpdateEmployeePhoneNumber = connection.prepareStatement(UPDATE_EMPLOYEE_PHONE_NUMBER);
			psUpdateEmployeeEmail = connection.prepareStatement(UPDATE_EMPLOYEE_EMAIL);
			psUpdateEmployeePosition = connection.prepareStatement(UPDATE_EMPLOYEE_POSITION);

			psDeleteEmployeeWithCPR = connection.prepareStatement(DELETE_EMPLOYEE_WITH_CPR);

			psSelectVersionFromEmployee = connection.prepareStatement(SELECT_VERSION_FROM_EMPLOYEE);

		}catch(SQLException e ){
			e.printStackTrace();
		}
	}

	// Create an employee in DB
	public boolean createEmployee(Employee employee, String street, int zip, String city, String country) throws SQLException {
		int resultEmployee = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psInsertIntoEmployee.setLong(1, employee.getCPR());
			psInsertIntoEmployee.setString(2, employee.getName());
			psInsertIntoEmployee.setString(3, street);
			psInsertIntoEmployee.setInt(4, zip);
			psInsertIntoEmployee.setString(5, city);
			psInsertIntoEmployee.setString(6, country);
			psInsertIntoEmployee.setInt(7, employee.getPhoneNumber());
			psInsertIntoEmployee.setString(8, employee.getEmail());
			psInsertIntoEmployee.setString(9, employee.getPosition());
			psInsertIntoEmployee.setInt(10, 0);
			resultEmployee = psInsertIntoEmployee.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return resultEmployee > 1;
	}

	// Update firstName
	public Employee updateName(Employee employee) throws SQLException {
		int result = 0;
		int version = getVersion(employee.getCPR());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psUpdateEmployeeName.setString(1, employee.getName());
			psUpdateEmployeeName.setInt(2,  version + 1);
			psUpdateEmployeeName.setLong(3, employee.getCPR());
			psUpdateEmployeeName.setInt(4, version);
			result = psUpdateEmployeeName.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return result == 1 ? employee : null;
	}

	// Update Address
	public Employee updateAddress(Employee employee, String street, int zip, String city, String country)
			throws SQLException {
		int result = 0;
		int version = getVersion(employee.getCPR());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psUpdateEmployeeAddress.setString(1, street);
			psUpdateEmployeeAddress.setInt(2, zip);
			psUpdateEmployeeAddress.setString(3, city);
			psUpdateEmployeeAddress.setString(4, country);
			psUpdateEmployeeAddress.setInt(5, version + 1);
			psUpdateEmployeeAddress.setLong(6, employee.getCPR());
			psUpdateEmployeeAddress.setInt(7, version);
			result = psUpdateEmployeeAddress.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return result == 1 ? employee : null;
	}

	// Update phoneNumber
	public Employee updatePhoneNumber(Employee employee) throws SQLException {
		int result = 0;
		int version = getVersion(employee.getCPR());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psUpdateEmployeePhoneNumber.setInt(1, employee.getPhoneNumber());
			psUpdateEmployeePhoneNumber.setInt(2,  version + 1);
			psUpdateEmployeePhoneNumber.setLong(3, employee.getCPR());
			psUpdateEmployeePhoneNumber.setInt(4,  version);
			result = psUpdateEmployeePhoneNumber.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return result == 1 ? employee : null;
	}

	// Update email
	public Employee updateEmail(Employee employee) throws SQLException {
		int result = 0;
		int version = getVersion(employee.getCPR());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psUpdateEmployeeEmail.setString(1, employee.getEmail());
			psUpdateEmployeeEmail.setInt(2,  version + 1);
			psUpdateEmployeeEmail.setLong(3, employee.getCPR());
			psUpdateEmployeeEmail.setInt(4,  version);
			result = psUpdateEmployeeEmail.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return result == 1 ? employee : null;
	}

	// Update Position
	public Employee updatePosition(Employee employee) throws SQLException {
		int result = 0;
		int version = getVersion(employee.getCPR());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psUpdateEmployeePosition.setString(1, employee.getPosition());
			psUpdateEmployeePosition.setInt(2,  version + 1);
			psUpdateEmployeePosition.setLong(3, employee.getCPR());
			psUpdateEmployeePosition.setInt(4,  version);
			result = psUpdateEmployeePosition.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return result == 1 ? employee : null;
	}

	public boolean deleteEmployee(long CPR) throws SQLException {
		int result = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psDeleteEmployeeWithCPR.setLong(1, CPR);
			result = psDeleteEmployeeWithCPR.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return result > 1;
	}
	
	private int getVersion(long CPR) throws SQLException {
		 int version = -1;
	        try {
	        	DBConnection.getInstance().getConnection().setAutoCommit(false);
	        	psSelectVersionFromEmployee.setLong(1, CPR);
	            ResultSet resultSet = psSelectVersionFromEmployee.executeQuery();
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