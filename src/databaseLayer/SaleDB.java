package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

import modelLayer.*;

public class SaleDB implements SaleDBIF {
	private ProductDBIF productDb;
// making Strings into queries 
	private static final String SELECT_FROM_SALE_WITH_ID = "SELECT * FROM Sale WHERE ID LIKE ?";
	private static final String SELECT_EMPLOYEE_WITH_CPR = "SELECT * FROM Employee WHERE CPR = ?";
	private static final String SELECT_TARGETED_CATEGORY = "SELECT ID, targetedCategoryID, productBarcode FROM Sale JOIN OrderLine ON Sale.ID = OrderLine.saleID WHERE targetedCategoryID = ?";
	private static final String SELECT_BOOKS_FAST = "SELECT * FROM Book JOIN Product ON Product.barcode = Book.barcode WHERE DATEDIFF(day, receivedInStore, dateSold) < 30;";
	private static final String SELECT_GAMES_FAST = "SELECT * FROM Game JOIN Product ON Product.barcode = Game.barcode WHERE DATEDIFF(day, receivedInStore, dateSold) < 30;";
	private static final String SELECT_BOOKS_SLOW = "SELECT * FROM Book JOIN Product ON Product.barcode = Book.barcode WHERE DATEDIFF(day, receivedInStore, dateSold) > 30;";
	private static final String SELECT_GAMES_SLOW = "SELECT * FROM Game JOIN Product ON Product.barcode = Game.barcode WHERE DATEDIFF(day, receivedInStore, dateSold) > 30;";
	private static final String SELECT_BOOKS_NOTSOLD = "SELECT * FROM Book JOIN Product ON Product.barcode = Book.barcode WHERE dateSold IS NULL;";
	private static final String SELECT_GAMES_NOTSOLD = "SELECT * FROM Game JOIN Product ON Product.barcode = Game.barcode WHERE dateSold IS NULL;";
	private static final String SELECT_BOOKS_YEAR = "SELECT * FROM Book JOIN Product ON Product.barcode = Book.barcode WHERE YEAR(dateSold) = ?;";
	private static final String SELECT_GAMES_YEAR = "SELECT * FROM Game JOIN Product ON Product.barcode = Game.barcode WHERE YEAR(dateSold) = ?;";
	private static final String SELECT_BOOKS_MONTH = "SELECT * FROM Book JOIN Product ON Product.barcode = Book.barcode WHERE MONTH(dateSold) = ?;";
	private static final String SELECT_GAMES_MONTH = "SELECT * FROM Game JOIN Product ON Product.barcode = Game.barcode WHERE MONTH(dateSold) = ?;";
	private static final String SELECT_BOOKS_DAY = "SELECT * FROM Book JOIN Product ON Product.barcode = Book.barcode WHERE DAY(dateSold) = ?;";
	private static final String SELECT_GAMES_DAY = "SELECT * FROM Game JOIN Product ON Product.barcode = Game.barcode WHERE DAY(dateSold) = ?;";
	private static final String SELECT_BARCODE_MOST_PROFIT = "SELECT * FROM (SELECT [barcode], [title], [costPrice], [RRP], [quantity] FROM [Product] "
			+ "INNER JOIN [OrderLine] ON [Product].[barcode] = [OrderLine].[productBarcode] "
			+ "GROUP BY [barcode], [quantity], [title], [RRP], [CostPrice]) AS [Derived table]";
	private static final String INSERT_INTO_SALE = "INSERT INTO Sale (ID, transactionDate, targetedCategoryID, paymentMethod, totalPrice, employeeCPR, version) VALUES(?,?,?,?,?,?,?)";
	private static final String UPDATE_COPY = "UPDATE Copy SET dateSold = ?, version = ? WHERE articleNumber LIKE ? AND version = ?";
	private static final String INSERT_INTO_ORDERLINE = "INSERT INTO OrderLine (saleID, productBarcode, quantity, version) VALUES (?,?,?,?)";
	private static final String DELETE_SALE = "DELETE FROM Sale WHERE ID = ?";

	private PreparedStatement psSelectSaleWithID;
	private PreparedStatement psSelectEmployeeWithCPR;
	private PreparedStatement psSelectTargetedCategory;
	private PreparedStatement psSelectBooksFast;
	private PreparedStatement psSelectGamesFast;
	private PreparedStatement psSelectBooksSlow;
	private PreparedStatement psSelectGamesSlow;
	private PreparedStatement psSelectBooksNotSold;
	private PreparedStatement psSelectGamesNotSold;
	private PreparedStatement psSelectBooksYear;
	private PreparedStatement psSelectGamesYear;
	private PreparedStatement psSelectBooksMonth;
	private PreparedStatement psSelectGamesMonth;
	private PreparedStatement psSelectBooksDay;
	private PreparedStatement psSelectGamesDay;
	private PreparedStatement psSelectBarcodeMostProfit;
	private PreparedStatement psInsertIntoSale;
	private PreparedStatement psUdateCopy;
	private PreparedStatement psInsertIntoOrderline;
	private PreparedStatement psDeleteSale;

	public SaleDB() {
		initPreparedStatement();
		productDb = new ProductDB();
	}

	private void initPreparedStatement() {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			psSelectSaleWithID = connection.prepareStatement(SELECT_FROM_SALE_WITH_ID);
			psSelectEmployeeWithCPR = connection.prepareStatement(SELECT_EMPLOYEE_WITH_CPR);
			psSelectTargetedCategory = connection.prepareStatement(SELECT_TARGETED_CATEGORY);
			psSelectBooksFast = connection.prepareStatement(SELECT_BOOKS_FAST);
			psSelectGamesFast = connection.prepareStatement(SELECT_GAMES_FAST);
			psSelectBooksSlow = connection.prepareStatement(SELECT_BOOKS_SLOW);
			psSelectGamesSlow = connection.prepareStatement(SELECT_GAMES_SLOW);
			psSelectBooksNotSold = connection.prepareStatement(SELECT_BOOKS_NOTSOLD);
			psSelectGamesNotSold = connection.prepareStatement(SELECT_GAMES_NOTSOLD);
			psSelectBooksYear = connection.prepareStatement(SELECT_BOOKS_YEAR);
			psSelectGamesYear = connection.prepareStatement(SELECT_GAMES_YEAR);
			psSelectBooksMonth = connection.prepareStatement(SELECT_BOOKS_MONTH);
			psSelectGamesMonth = connection.prepareStatement(SELECT_GAMES_MONTH);
			psSelectBooksDay = connection.prepareStatement(SELECT_BOOKS_DAY);
			psSelectGamesDay = connection.prepareStatement(SELECT_GAMES_DAY);
			psSelectBarcodeMostProfit = connection.prepareStatement(SELECT_BARCODE_MOST_PROFIT);
			psInsertIntoSale = connection.prepareStatement(INSERT_INTO_SALE);
			psUdateCopy = connection.prepareStatement(UPDATE_COPY);
			psInsertIntoOrderline = connection.prepareStatement(INSERT_INTO_ORDERLINE);
			psDeleteSale = connection.prepareStatement(DELETE_SALE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psSelectSaleWithID.setInt(1, ID);
			ResultSet resultSet = psSelectSaleWithID.executeQuery();
			if (resultSet.next()) {
				foundSale = buildSale(resultSet);
			}
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return foundSale;
	}

	public ArrayList<Sale> buildSales(ResultSet resultSet) throws SQLException {
		ArrayList<Sale> saleInformation = new ArrayList<>();
		while (resultSet.next()) {
			Sale saleInfo = buildSale(resultSet);
			saleInformation.add(saleInfo);
		}
		return saleInformation;
	}

	public Sale buildSale(ResultSet resultSet) throws SQLException {
		Sale builtSale = null;
		builtSale = new Sale(resultSet.getInt("ID"), resultSet.getDate("transactionDate"),
				new TargetedCategory(resultSet.getInt("targetedCategoryID")), resultSet.getString("paymentMethod"),
				resultSet.getDouble("totalPrice"), buildEmployee(resultSet.getInt("employeeCPR")));
		return builtSale;
	}

	public Employee buildEmployee(long employeeCPR) throws SQLException {
		Employee builtEmployee = null;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psSelectEmployeeWithCPR.setLong(1, employeeCPR);
			DBConnection.getInstance().getConnection().commit();
			ResultSet resultSet = psSelectEmployeeWithCPR.executeQuery();
			if (resultSet.next()) {
				builtEmployee = new Employee(resultSet.getInt("CPR"), // CPR
						(resultSet.getString("firstName") + " " + resultSet.getString("lastName")), // NAME
						(resultSet.getString("street") + ", " + resultSet.getInt("zipcode") + ", "
								+ resultSet.getString("city")), // ADDRESS
						resultSet.getInt("phoneNumber"), resultSet.getString("email"), resultSet.getString("position"));
			}
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
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

	public ArrayList<Product> getProductsAnalytics(String choice, String type, int year, int month, int day,
			int targetedCategoryID) throws SQLException {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		ArrayList<Product> finiteProducts = new ArrayList<Product>();
		ArrayList<Product> productsOfCategory = new ArrayList<Product>();
		if (year > 0) {
			try {
				DBConnection.getInstance().getConnection().setAutoCommit(false);
				psSelectBooksYear.setInt(1, year);
				psSelectGamesYear.setInt(1, year);
				DBConnection.getInstance().getConnection().commit();
				if (type.equals("Book")) {
					ResultSet resultSet = psSelectBooksYear.executeQuery();
					foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
				} else if (type.equals("Game")) {
					ResultSet resultSet = psSelectGamesYear.executeQuery();
					foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
				}
				DBConnection.getInstance().getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else if (choice.equals("Slow")) {
			try {
				if (type.equals("Book")) {
					ResultSet resultSet = psSelectBooksSlow.executeQuery();
					foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
				} else if (type.equals("Game")) {
					ResultSet resultSet = psSelectGamesSlow.executeQuery();
					foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else if (choice.equals("Fast")) {
			try {
				if (type.equals("Book")) {
					ResultSet resultSet = psSelectBooksFast.executeQuery();
					foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
				} else if (type.equals("Game")) {
					ResultSet resultSet = psSelectGamesFast.executeQuery();
					foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else if (month > 0) {
			try {
				DBConnection.getInstance().getConnection().setAutoCommit(false);
				psSelectBooksMonth.setInt(1, month);
				psSelectGamesMonth.setInt(1, month);
				DBConnection.getInstance().getConnection().commit();
				if (type.equals("Book")) {
					ResultSet resultSet = psSelectBooksMonth.executeQuery();
					foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
				} else if (type.equals("Game")) {
					ResultSet resultSet = psSelectGamesMonth.executeQuery();
					foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
				}

				DBConnection.getInstance().getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else if (choice.equals("Not sold")) {
			try {
				if (type.equals("Book")) {
					ResultSet resultSet = psSelectBooksNotSold.executeQuery();
					foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
				} else if (type.equals("Game")) {
					ResultSet resultSet = psSelectGamesNotSold.executeQuery();
					foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else if (day > 0) {
			try {
				DBConnection.getInstance().getConnection().setAutoCommit(false);
				psSelectBooksDay.setInt(1, day);
				psSelectGamesDay.setInt(1, day);
				DBConnection.getInstance().getConnection().commit();
				if (type.equals("Book")) {
					ResultSet resultSet = psSelectBooksDay.executeQuery();
					foundProducts.addAll(productDb.buildObjects(resultSet, "Book"));
				} else if (type.equals("Game")) {
					ResultSet resultSet = psSelectGamesDay.executeQuery();
					foundProducts.addAll(productDb.buildObjects(resultSet, "Game"));
				}
				DBConnection.getInstance().getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		else if (choice.equals("Most profit")) {
			try {
				DBConnection.getInstance().getConnection().setAutoCommit(false);
				DBConnection.getInstance().getConnection().commit();
				ResultSet resultSet = psSelectBarcodeMostProfit.executeQuery();
				while (resultSet.next()) {
					foundProducts.add(new Product(resultSet.getString("barcode"), resultSet.getString("title"),
							resultSet.getDouble("costPrice"), resultSet.getDouble("RRP"),
							resultSet.getInt("quantity")));
				}
				DBConnection.getInstance().getConnection().setAutoCommit(true);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (targetedCategoryID >= 0) {
				try {
					DBConnection.getInstance().getConnection().setAutoCommit(false);
					psSelectTargetedCategory.setInt(1, targetedCategoryID);
					DBConnection.getInstance().getConnection().commit();
					ResultSet resultSet = psSelectTargetedCategory.executeQuery();
					while (resultSet.next()) {
						productsOfCategory.add(new Product(resultSet.getString("productBarcode")));
					}
					DBConnection.getInstance().getConnection().setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				for (Product p : foundProducts) {
					for (Product r : productsOfCategory) {
						if (p.getBarcode().equals(r.getBarcode())) {
							finiteProducts.add(p);
						}
					}
				}
				foundProducts = finiteProducts;
			}
		}
		return foundProducts;
	}

	public Sale createSale(Sale sale, Copy copy, OrderLine orderLine) throws SQLException {
		int resultSale = 0;
		int resultCopy = 0;
		int resultOrderLine = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psInsertIntoSale.setInt(1, sale.getID());
			psInsertIntoSale.setDate(2, sale.getDate());
			psInsertIntoSale.setInt(3, sale.getTargetedCategory().getID());
			psInsertIntoSale.setString(4, sale.getPaymentMethod());
			psInsertIntoSale.setDouble(5, sale.getTotalPrice());
			psInsertIntoSale.setLong(6, sale.getEmployee().getCPR());
			resultSale = psInsertIntoSale.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}

		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psUdateCopy.setDate(1, copy.getDateSold());
			psUdateCopy.setString(2, copy.getArticleNumber());
			resultCopy = psUdateCopy.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}

		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psInsertIntoOrderline.setInt(1, orderLine.getSale().getID());
			psInsertIntoOrderline.setString(2, orderLine.getProduct().getBarcode());
			psInsertIntoOrderline.setInt(3, orderLine.getQuantity());
			resultOrderLine = psInsertIntoOrderline.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return resultSale == 1 && resultCopy == 1 && resultOrderLine == 1 ? sale : null;
	}

	// Delete Sale by going into the DB
	public boolean deleteSale(int ID) throws SQLException {
		int resultSale = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psDeleteSale.setInt(1, ID);
			resultSale = psDeleteSale.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return resultSale > 1;
	}
}