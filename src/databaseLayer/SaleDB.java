package databaseLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

import modelLayer.Sale;
import modelLayer.TargetedCategory;
import modelLayer.Employee;

public class SaleDB implements SaleDBIF {

	// TODO comment
	public ArrayList<Sale> getSaleInformation() throws SQLException {
		ResultSet resultSet = null;
		ArrayList<Sale> saleInformation = new ArrayList<Sale>();
		Statement statement = DBConnection.getInstance().getConnection().createStatement();
		try {
			resultSet = statement.executeQuery("SELECT * FROM Sale");
			saleInformation = buildObjects(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saleInformation;
	}

	public Sale getOneSaleInformation(int ID) throws SQLException {
		Sale foundSale = null;
		String selectSale = "SELECT * FROM Sale WHERE ID = " + ID + "";
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(selectSale);
			foundSale = buildObject(resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return foundSale;
	}

	// TODO comment
	private ArrayList<Sale> buildObjects(ResultSet resultSet) throws SQLException {
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

		builtSale = new Sale(resultSet.getInt("ID"), resultSet.getDate("transactionDate"),
				new TargetedCategory(resultSet.getInt("targetedCategoryID")), resultSet.getString("paymentMethod"),
				resultSet.getDouble("totalPrice"), buildEmployee(resultSet.getInt("EmployeeCPR")));
		return builtSale;
	}

	private TargetedCategory buildCategory(ResultSet resultSet) throws SQLException {
		TargetedCategory builtCategory = null;
		builtCategory = new TargetedCategory(resultSet.getInt("ID"), resultSet.getString("title"),
				resultSet.getInt("minimumAge"), resultSet.getInt("maximumAge"), resultSet.getString("gender"),
				resultSet.getString("other"));
		return builtCategory;
	}
	
	private ArrayList<TargetedCategory> buildCategories(ResultSet resultSet) throws SQLException {
		ArrayList<TargetedCategory> foundCategories = new ArrayList<>();
		while (resultSet.next()) {
			TargetedCategory category = buildCategory(resultSet);
			foundCategories.add(category);
		}
		return foundCategories;
	}

	// TODO comment aaaaa
	private Employee buildEmployee(int EmployeeCPR) throws SQLException {
		Employee builtEmployee = null;
		String SelectEmployee = String.format("SELECT * FROM Employee WHERE EmployeeCPR = " + EmployeeCPR + "");
		Statement statement = DBConnection.getInstance().getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery(SelectEmployee);

		if (resultSet.next()) {

			try {
				builtEmployee = new Employee(resultSet.getInt("CPR"),
						(resultSet.getString("firstName") + resultSet.getString("lastName")),
						(resultSet.getString("street") + ", " + resultSet.getInt("zipcode") + ", "
								+ resultSet.getString("city")),
						resultSet.getInt("phoneNumber"), resultSet.getString("email"), resultSet.getString("position"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return builtEmployee;
	}

	public ArrayList<TargetedCategory> getAllTargetedCategories() throws SQLException {

		ArrayList<TargetedCategory> foundTargetedCategories = new ArrayList<TargetedCategory>();
		String selectCategories = "SELECT * FROM TargetedCategory";

		try {

			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			ResultSet rsCategory = statement.executeQuery(selectCategories);
			foundTargetedCategories.addAll(buildCategories(rsCategory));

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return foundTargetedCategories;

	}

}
