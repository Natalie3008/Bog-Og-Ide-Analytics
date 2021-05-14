package controlLayer;


import java.sql.SQLException;

import databaseLayer.*;
import modelLayer.*;

public class EmployeeCtrl {
	private EmployeeDB employeeDb;
	private SaleCtrl saleCtrl;
	
	public EmployeeCtrl() {
		employeeDb = new EmployeeDB();
	}
	
	
	public boolean createEmployee(Employee employee, String street, int zip, String city, String country) {
		boolean result = true;

		try {
			employeeDb.createEmployee(employee, street, zip, city, country);
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	

		public boolean updateName(Employee employee) {
			boolean result = true;
			try {
				employeeDb.updateName(employee);
			} catch (SQLException e) {
				e.printStackTrace();
				result = false;
			}
			return result;
	}
		
		public boolean updateAddress(Employee employee, String street, int zip, String city, String country) {
			boolean result = true;
			try {
				employeeDb.updateAddress(employee, street, zip, city, country);
			} catch (SQLException e) {
				e.printStackTrace();
				result = false;
			}
			return result;
	}
		
		public boolean updatePhoneNumber(Employee employee) {
			boolean result = true;
			try {
				employeeDb.updatePhoneNumber(employee);
			} catch (SQLException e) {
				e.printStackTrace();
				result = false;
			}
			return result;
	}
		
		public boolean updateEmail(Employee employee) {
			boolean result = true;
			try {
				employeeDb.updateEmail(employee);
			} catch (SQLException e) {
				e.printStackTrace();
				result = false;
			}
			return result;
	}
		
		public boolean updatePosition(Employee employee) {
			boolean result = true;
			try {
				employeeDb.updatePosition(employee);
			} catch (SQLException e) {
				e.printStackTrace();
				result = false;
			}
			return result;
	}

		public boolean deleteEmployee(int CPR) {
			boolean result = true;
			try {
				employeeDb.deleteEmployee(CPR);
			} catch (SQLException e) {
				e.printStackTrace();
				result = false;
			}
			return result;
	}
		
}


