package databaseLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

import modelLayer.Sale;
import modelLayer.TargetedCategory;
import modelLayer.Copy;
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
		String selectSale = "SELECT * FROM Sale WHERE ID = " + ID;
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
	public ArrayList<Sale> buildSales(ResultSet resultSet) throws SQLException {
		ArrayList<Sale> saleInformation = new ArrayList<>();
		while (resultSet.next()) {
			Sale saleInfo = buildSale(resultSet);
			saleInformation.add(saleInfo);
		}
		return saleInformation;
	}

	// TODO comment
	public Sale buildSale(ResultSet resultSet) throws SQLException {
		Sale builtSale = null;

		builtSale = new Sale(resultSet.getInt("ID"), resultSet.getDate("transactionDate"),
				new TargetedCategory(resultSet.getInt("targetedCategoryID")), resultSet.getString("paymentMethod"),
				resultSet.getDouble("totalPrice"), buildEmployee(resultSet.getInt("EmployeeCPR")));
		return builtSale;
	}

	// TODO comment aaaaa
	public Employee buildEmployee(int EmployeeCPR) throws SQLException {
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

	public ArrayList<OrderLine> buildOrderLines(ResultSet resultSet) throws SQLException {
		ArrayList<OrderLine> orderLines = new ArrayList<>();
		while (resultSet.next()) {
			OrderLine orderLine = new OrderLine(new Sale(resultSet.getInt("saleID")), resultSet.getInt("quantity"),
					new Product(resultSet.getString("productBarcode")));
			orderLines.add(orderLine);
		}
		return orderLines;
	}

	public ArrayList<Product> getProductsAnalytics(String choice, String type, int year, int month, int day)
			throws SQLException {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		String selectBooksFast = "SELECT * FROM Book JOIN Product ON Product.barcode = Book.barcode WHERE DATEDIFF(day, receivedInStore, dateSold) < 30;";
		String selectGamesFast = "SELECT * FROM Game JOIN Product ON Product.barcode = Game.barcode WHERE DATEDIFF(day, receivedInStore, dateSold) < 30;";
		String selectBooksSlow = "SELECT * FROM Book JOIN Product ON Product.barcode = Book.barcode WHERE DATEDIFF(day, receivedInStore, dateSold) > 30;";
		String selectGamesSlow = "SELECT * FROM Game JOIN Product ON Product.barcode = Game.barcode WHERE DATEDIFF(day, receivedInStore, dateSold) > 30;";
		String selectBooksNotSold = "SELECT * FROM Book JOIN Product ON Product.barcode = Book.barcode WHERE dateSold IS NULL;";
		String selectGamesNotSold = "SELECT * FROM Game JOIN Product ON Product.barcode = Game.barcode WHERE dateSold IS NULL;";
		String selectBooksYear = "SELECT * FROM Book JOIN Product ON Product.barcode = Book.barcode WHERE YEAR(dateSold) = "
				+ year + ";";
		String selectGamesYear = "SELECT * FROM Game JOIN Product ON Product.barcode = Game.barcode WHERE YEAR(dateSold) = "
				+ year + ";";
		String selectBooksMonth = "SELECT * FROM Book JOIN Product ON Product.barcode = Book.barcode WHERE MONTH(dateSold) = "
				+ month + ";";
		String selectGamesMonth = "SELECT * FROM Game JOIN Product ON Product.barcode = Game.barcode WHERE MONTH(dateSold) = "
				+ month + ";";
		String selectBooksDay = "SELECT * FROM Book JOIN Product ON Product.barcode = Book.barcode WHERE DAY(dateSold) = "
				+ day + ";";
		String selectGamesDay = "SELECT * FROM Game JOIN Product ON Product.barcode = Game.barcode WHERE DAY(dateSold) = "
				+ day + ";";
		String selectBarcodeMostProfit = "SELECT * FROM (SELECT [barcode], [title]"
				+ ",(quantity + COUNT(barcode)) * (RRP - CostPrice) AS [Total Profit] FROM [Product]"
				+ "INNER JOIN [OrderLine] ON [Product].[barcode] = [OrderLine].[productBarcode]"
				+ "GROUP BY [barcode], [quantity], [title], [RRP], [CostPrice]) AS [Derived table]";
		if (year > 0) {
			try {
				Statement statement = DBConnection.getInstance().getConnection().createStatement();
				if (type.equals("Book")) {
					ResultSet resultSet = statement.executeQuery(selectBooksYear);
					foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
				} else if (type.equals("Game")) {
					ResultSet resultSet = statement.executeQuery(selectGamesYear);
					foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		else if (choice.equals("Slow")) {
			try {
				Statement statement = DBConnection.getInstance().getConnection().createStatement();
				if (type.equals("Book")) {
					ResultSet resultSet = statement.executeQuery(selectBooksSlow);
					foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
				} else if (type.equals("Game")) {
					ResultSet resultSet = statement.executeQuery(selectGamesSlow);
					foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else if (choice.equals("Fast")) {
			try {
				Statement statement = DBConnection.getInstance().getConnection().createStatement();
				if (type.equals("Book")) {
					ResultSet resultSet = statement.executeQuery(selectBooksFast);
					foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
				} else if (type.equals("Game")) {
					ResultSet resultSet = statement.executeQuery(selectGamesFast);
					foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else if (month > 0) {
			try {
				Statement statement = DBConnection.getInstance().getConnection().createStatement();
				if (type.equals("Book")) {
					ResultSet resultSet = statement.executeQuery(selectBooksMonth);
					foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
				} else if (type.equals("Game")) {
					ResultSet resultSet = statement.executeQuery(selectGamesMonth);
					foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else if (choice.equals("Not sold")) {
			try {
				Statement statement = DBConnection.getInstance().getConnection().createStatement();
				if (type.equals("Book")) {
					ResultSet resultSet = statement.executeQuery(selectBooksNotSold);
					foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
				} else if (type.equals("Game")) {
					ResultSet resultSet = statement.executeQuery(selectGamesNotSold);
					foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else if (day > 0) {
			try {
				Statement statement = DBConnection.getInstance().getConnection().createStatement();
				if (type.equals("Book")) {
					ResultSet resultSet = statement.executeQuery(selectBooksDay);
					foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
				} else if (type.equals("Game")) {
					ResultSet resultSet = statement.executeQuery(selectGamesDay);
					foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else if (choice.equals("Most profit")) {
			try {
				Statement statement = DBConnection.getInstance().getConnection().createStatement();
				ResultSet resultSet = statement.executeQuery(selectBarcodeMostProfit);
				while (resultSet.next()) {
					foundProducts.add(new Product(resultSet.getString("barcode")));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return foundProducts;
	}

	public ArrayList<OrderLine> getSalesAnalytics() {
		ArrayList<OrderLine> foundOrderLines = new ArrayList<OrderLine>();
		String selectBarcodeBest = "SELECT y.saleID ,y.productBarcode ,y.quantity FROM OrderLine y INNER JOIN (SELECT productBarcode, COUNT(*) AS CountOf"
				+ " FROM OrderLine GROUP BY productBarcode HAVING COUNT(*) > 1"
				+ " ) dt ON y.productBarcode=dt.productBarcode";
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(selectBarcodeBest);
			foundOrderLines.addAll(buildOrderLines(resultSet));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundOrderLines;
	}

	// CRUD Sale

	// Create Sale by implementing into DB and updating dateSold via articleNumber
	// in Copy
	public Sale createSale(Sale sale, Copy copy) throws SQLException {
		String sqlSale = "INSERT INTO Sale (ID, transactionDate, targetedCategoryID, paymentMethod, totalPrice, employeeCPR)"
				+ " VALUES(?,?,?,?,?,?)";
		String sqlCopy = "UPDATE Copy SET dateSold = ? WHERE articleNumber LIKE ?";
		int resultSale = 0;
		int resultCopy = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementSale = DBConnection.getInstance().getConnection().prepareStatement(sqlSale);
			statementSale.setInt(1, sale.getID());
			statementSale.setDate(2, sale.getDate());
			statementSale.setInt(3, sale.getAgeCategory().getID());
			statementSale.setString(4,  sale.getPaymentMethod());
			statementSale.setDouble(5, sale.getTotalPrice());
			statementSale.setLong(6, sale.getEmployee().getCPR());
			resultSale = statementSale.executeUpdate();
			statementSale.close();
			DBConnection.getInstance().getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			
		}
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementCopy = DBConnection.getInstance().getConnection().prepareStatement(sqlCopy);
			statementCopy.setDate(1, copy.getDateSold());
			statementCopy.setString(2, copy.getArticleNumber());
			resultCopy = statementCopy.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return resultSale == 1 && resultCopy == 1 ? sale : null;
	}

	// Delete Sale by going into the DB
	public boolean deleteSale(int ID) throws SQLException {
		String sqlSale = "DELETE FROM Sale WHERE ID = ?";
		int resultSale = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
		PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sqlSale);
		statement.setInt(1, ID);
		resultSale = statement.executeUpdate();
		DBConnection.getInstance().getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return resultSale > 1;
	}
}