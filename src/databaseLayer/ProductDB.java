package databaseLayer;

import modelLayer.Product;
import modelLayer.Book;
import modelLayer.Game;
import java.sql.*;
import java.util.ArrayList;

import modelLayer.Supplier;

public class ProductDB {

	private Product buildObject(ResultSet resultSet, String selectedType) throws SQLException {
		Product builtProduct = null;

		try {

			if (selectedType.equals("Book")) {

				builtProduct = new Book(resultSet.getString("Title"), resultSet.getString("barcode"),
						resultSet.getDouble("costPrice"), resultSet.getDouble("recommendedRetailPrice"),
						resultSet.getInt("amountInStock"), resultSet.getString("publicationDate"),
						resultSet.getString("description"), resultSet.getDate("receivedInStore"),
						buildSupplier(resultSet.getInt("supplierCVR")), resultSet.getString("author"),
						resultSet.getString("genre"), resultSet.getString("ISBN"));

				if (selectedType.equals("Game")) {
					builtProduct = new Game(resultSet.getString("Title"), resultSet.getString("barcode"),
							resultSet.getDouble("costPrice"), resultSet.getDouble("recommendedRetailPrice"),
							resultSet.getInt("amountInStock"), resultSet.getString("publicationDate"),
							resultSet.getString("description"), resultSet.getDate("receivedInStore"),
							buildSupplier(resultSet.getInt("supplierCVR")), resultSet.getString("Type"));

				}
			}
		}

		catch (SQLException e) {

			e.printStackTrace();

		}

		return builtProduct;

	}

	private Supplier buildSupplier(int supplierCVR) throws SQLException {
		Supplier builtSupplier = null;
		String SelectSupplier = String.format("SELECT * FROM Supplier WHERE supplierCVR = '" + supplierCVR + "'");
		Statement statement = DBConnection.getInstance().getConnection().createStatement();
		ResultSet rs = statement.executeQuery(SelectSupplier);

		if (rs.next()) {
			try {

				builtSupplier = new Supplier(rs.getInt("CVR"), rs.getString("name"), rs.getString("contactPerson"),
						rs.getString("address"), rs.getString("phoneNumber"), rs.getString("email"),
						rs.getString("productCategory"));

			}

			catch (SQLException e) {

				e.printStackTrace();

			}

		}
		return builtSupplier;
	}

	// a method for finding stuff with barcode since we have no article number this
	// time? maybe?

	public ArrayList<Product> getProductInformation(String barcode) throws SQLException {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		String selectBooks = String.format(
				"SELECT * FROM Book JOIN Product ON Book.barcode = Product.barcode WHERE barcode = '" + barcode + "'");
		String selectGames = String.format(
				"SELECT * FROM Game JOIN Product ON Game.barcode = Product.barcode WHERE barcode = '" + barcode + "'");
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();

			ResultSet rsBook = statement.executeQuery(selectBooks);
			if (rsBook.next()) {
				foundProducts = buildObjects(rsBook, "Book");

			}

			ResultSet rsGame = statement.executeQuery(selectGames);
			if (rsGame.next()) {
				foundProducts = buildObjects(rsGame, "Game");

			}

		}

		catch (SQLException e) {

			e.printStackTrace();

		}

		return foundProducts;
	}
	
	private ArrayList<Product> buildObjects(ResultSet resultSet, String category) throws SQLException {
		ArrayList<Product> resultProducts = new ArrayList<Product>();
		while(resultSet.next()) {
			Product product = buildObject(resultSet, category);
			resultProducts.add(product);
				}
		return resultProducts;
	}

	// method to make a book and add it into the db // WIP STILL

	/*
	 * public Product createBook(Book book) throws SQLException {
	 * 
	 * 
	 * String sqlProduct =
	 * "INSERT INTO Product (barcode, title, costPrice, RRP, amountInStock, publicationDate, supplierCVR, description)"
	 * + " VALUES( " + book.getBarcode() + ", '" + book.getTitle() + "', '" +
	 * book.getCostPrice() + "', " + book.getRecommendedRetailPrice() + ", " +
	 * book.getAmountInStock() + ", " + book.getPublicationDate() + ", '" +
	 * book.getSupplier().getCVR() + "', " + book.getDescription() + "')";
	 * 
	 * String sqlBook = "INSERT INTO Book (barcode, ISBN, author, genre) VALUES( " +
	 * book.getBarcode() + ", " + book.getISBN() + ", '" + book.getAuthor() + ", " +
	 * book.getGenre() + "')";
	 * 
	 * }
	 */

}
