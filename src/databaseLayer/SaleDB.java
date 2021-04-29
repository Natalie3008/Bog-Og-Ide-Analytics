package databaseLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

import modelLayer.Sale;
import modelLayer.Employee;

public class SaleDB implements SaleDBIF {

	// TODO comment
	public List<Sale> getSaleInformation() throws SQLException {
		ResultSet resultSet = null;
		List<Sale> saleInformation = null;
		Statement statement = DBConnection.getInstance().getConnection().createStatement();
		try {
			resultSet = statement.executeQuery("SELECT * FROM Sale");
			saleInformation = buildObjects(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saleInformation;
	}

	// TODO comment
	private List<Sale> buildObjects(ResultSet resultSet) throws SQLException {
		ArrayList<Sale> saleInformation = new ArrayList<>();
		while (resultSet.next()) {
			Sale saleInfo = buildObject(resultSet);
			saleInformation.add(saleInfo);
		}
		return saleInformation;
	}

	// TODO comment
	private Sale buildObject(ResultSet resultSet) throws SQLException {
		Sale builtSale = null;
		// Employee is null?
		builtSale = new Sale(resultSet.getInt("ID"), resultSet.getDouble("totalPrice"),
				resultSet.getDate("transactionDate"), resultSet.getString("ageCategory"),
				resultSet.getString("paymentMethod"), buildEmployee(resultSet.getInt("EmployeeCPR")));
		return builtSale;
	}

	// TODO comment
	private Employee buildEmployee(int EmployeeCPR) throws SQLException {
		Employee builtEmployee = null;
		String SelectEmployee = String.format("SELECT * FROM Employee WHERE EmployeeCPR = ' " + EmployeeCPR + "'");
		Statement statement = DBConnection.getInstance().getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery(SelectEmployee);
		if (resultSet.next()) {
			try {
				builtEmployee = new Employee(resultSet.getInt("CPR"), resultSet.getString("name"),
						resultSet.getString("address"), resultSet.getInt("phoneNumber"),
						resultSet.getString("position"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return builtEmployee;
	}
}
