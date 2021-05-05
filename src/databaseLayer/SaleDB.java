package databaseLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

import modelLayer.Sale;
import modelLayer.TargetedCategory;
import modelLayer.Employee;
import modelLayer.OrderLine;
import modelLayer.Product;

public class SaleDB implements SaleDBIF {
	private ProductDB productDb = new ProductDB();

	// TODO comment
	public ArrayList<Sale> getSaleInformation() throws SQLException {
		ResultSet resultSet = null;
		ArrayList<Sale> saleInformation = new ArrayList<Sale>();
		Statement statement = DBConnection.getInstance().getConnection().createStatement();
		try {
			resultSet = statement.executeQuery("SELECT * FROM Sale");
			saleInformation = buildSales(resultSet);
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
			foundSale = buildSale(resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return foundSale;
	}

	// TODO comment
	private ArrayList<Sale> buildSales(ResultSet resultSet) throws SQLException {
		ArrayList<Sale> saleInformation = new ArrayList<>();
		while (resultSet.next()) {
			Sale saleInfo = buildSale(resultSet);
			saleInformation.add(saleInfo);
		}
		return saleInformation;
	}

	// TODO comment
	private Sale buildSale(ResultSet resultSet) throws SQLException {
		Sale builtSale = null;

		builtSale = new Sale(resultSet.getInt("ID"), resultSet.getDate("transactionDate"),
				new TargetedCategory(resultSet.getInt("targetedCategoryID")), resultSet.getString("paymentMethod"),
				resultSet.getDouble("totalPrice"), buildEmployee(resultSet.getInt("EmployeeCPR")));
		return builtSale;
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

	public ArrayList<Product> getFastSellingProducts(String type) throws SQLException {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		String selectBooks = "SELECT *FROM Book WHERE DATEDIFF(day, dateSold, receivedInStore) < 30;";
		String selectGames = "SELECT *FROM Game WHERE DATEDIFF(day, dateSold, receivedInStore) < 30;";
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			if (type.equals("Book")) {
				ResultSet resultSet = statement.executeQuery(selectBooks);
				foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
			} else if (type.equals("Game"))
				;
			{
				ResultSet resultSet = statement.executeQuery(selectGames);
				foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundProducts;
	}

	public ArrayList<Product> getSlowSellingProducts(String type) throws SQLException {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		String selectBooks = "SELECT * FROM Book WHERE DATEDIFF(day, dateSold, receivedInStore) > 30;";
		String selectGames = "SELECT * FROM Game WHERE DATEDIFF(day, dateSold, receivedInStore) > 30;";
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			if (type.equals("Book")) {
				ResultSet resultSet = statement.executeQuery(selectBooks);
				foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
			} else if (type.equals("Game"))
				;
			{
				ResultSet resultSet = statement.executeQuery(selectGames);
				foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundProducts;
	}

	private ArrayList<OrderLine> buildOrderLines(ResultSet resultSet) throws SQLException {
		ArrayList<OrderLine> orderLines = new ArrayList<>();
		while (resultSet.next()) {
			OrderLine orderLine = new OrderLine(new Sale(resultSet.getInt("saleID")), resultSet.getInt("quantity"),
					new Product(resultSet.getString("productBarcode")));
			orderLines.add(orderLine);
		}
		return orderLines;
	}

	public ArrayList<OrderLine> getBestSellersProducts() throws SQLException {
		ArrayList<OrderLine> foundOrderLines = new ArrayList<OrderLine>();
		String selectBarcode = "SELECT y.saleID ,y.productBarcode ,y.quantity FROM OrderLine y INNER JOIN (SELECT productBarcode, COUNT(*) AS CountOf"
				+ " FROM OrderLine" + " GROUP BY productBarcode" + " HAVING COUNT(*) > 1"
				+ " ) dt ON y.productBarcode=dt.productBarcode";
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(selectBarcode);
			foundOrderLines.addAll(buildOrderLines(resultSet));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundOrderLines;
	}

	public ArrayList<Product> getNotSellingProducts(String type) throws SQLException {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		String selectBooks = "SELECT * FROM Book WHERE dateSold IS NULL;";
		String selectGames = "SELECT * FROM Game WHERE dateSold IS NULL;";
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			if (type.equals("Book")) {
				ResultSet resultSet = statement.executeQuery(selectBooks);
				foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
			} else if (type.equals("Game"))
				;
			{
				ResultSet resultSet = statement.executeQuery(selectGames);
				foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundProducts;
	}
	
	public ArrayList<Product> getProductsSoldInYear(int year, String type) throws SQLException {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		String selectBooks = "SELECT * FROM Book WHERE YEAR(dateSold) = " + year + ";";
		String selectGames = "SELECT * FROM Book WHERE YEAR(dateSold) = " + year + ";";
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			if (type.equals("Book")) {
				ResultSet resultSet = statement.executeQuery(selectBooks);
				foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
			} else if (type.equals("Game"))
				;
			{
				ResultSet resultSet = statement.executeQuery(selectGames);
				foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundProducts;
	}
	
	public ArrayList<Product> getProductsSoldInMonth(int month, String type) throws SQLException {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		String selectBooks = "SELECT * FROM Book WHERE MONTH(dateSold) = " + month + ";";
		String selectGames = "SELECT * FROM Book WHERE MONTH(dateSold) = " + month + ";";
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			if (type.equals("Book")) {
				ResultSet resultSet = statement.executeQuery(selectBooks);
				foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
			} else if (type.equals("Game"))
				;
			{
				ResultSet resultSet = statement.executeQuery(selectGames);
				foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundProducts;
	}
	
	public ArrayList<Product> getProductsSoldInDay(int day, String type) throws SQLException {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		String selectBooks = "SELECT * FROM Book WHERE DAY(dateSold) = " + day + ";";
		String selectGames = "SELECT * FROM Book WHERE DAY(dateSold) = " + day + ";";
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			if (type.equals("Book")) {
				ResultSet resultSet = statement.executeQuery(selectBooks);
				foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
			} else if (type.equals("Game"))
				;
			{
				ResultSet resultSet = statement.executeQuery(selectGames);
				foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundProducts;
	}
	
	public ArrayList<Product> mostProfitableProducts() throws SQLException {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		String selectBarcode = "SELECT * FROM ("
				+ "SELECT [barcode], [title]"
				+ ",(quantity + COUNT(barcode)) * -(RRP - CostPrice) AS [Total Profit]"
				+ "FROM [Product]"
				+ "INNER JOIN [OrderLine]"
				+ "ON [Product].[barcode] = [OrderLine].[productBarcode]"
				+ "GROUP BY [barcode], [quantity], [title], [RRP], [CostPrice]"
				+ ") AS [Derived table]";
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(selectBarcode);
			while (resultSet.next()) {
				foundProducts.add(new Product(resultSet.getString("barcode")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundProducts;
	}
}