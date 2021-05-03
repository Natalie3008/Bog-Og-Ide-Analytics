package databaseLayer;

import modelLayer.Product;
import modelLayer.Book;
import modelLayer.Copy;
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
						resultSet.getString("description"),	buildSupplier(resultSet.getInt("supplierCVR")), 
						resultSet.getString("author"), resultSet.getString("genre"), resultSet.getString("ISBN"));
						builtProduct.setCopies(buildCopies(builtProduct, resultSet.getString("barcode"), "Book"));
			}

			else if (selectedType.equals("Game")) {
				builtProduct = new Game(resultSet.getString("Title"), resultSet.getString("barcode"),
						resultSet.getDouble("costPrice"), resultSet.getDouble("recommendedRetailPrice"),
						resultSet.getInt("amountInStock"), resultSet.getString("publicationDate"),
						resultSet.getString("description"), buildSupplier(resultSet.getInt("supplierCVR")), resultSet.getString("Type"));
						builtProduct.setCopies(buildCopies(builtProduct, resultSet.getString("barcode"), "Game"));
			}
			
		}catch (SQLException e) {

			e.printStackTrace();

		}

		return builtProduct;

	}

	private ArrayList<Copy> buildCopies(Product product, String barcode, String type) throws SQLException {
		ArrayList<Copy> builtCopies = new ArrayList<Copy>();
		String selectBookCopies = "SELECT * FROM Book WHERE barcode = '" + barcode + "'";
		String selectGameCopies = "SELECT * FROM Game WHERE barcode = '" + barcode + "'";
		Statement statement = DBConnection.getInstance().getConnection().createStatement();
		ResultSet resultSet = null;
		if (type.equals("Book")) {
			resultSet = statement.executeQuery(selectBookCopies);	
		}
		else if (type.equals("Game")) {
			resultSet = statement.executeQuery(selectGameCopies);	
		}
		while (resultSet.next()) {
			try {
				builtCopies.add(new Copy (resultSet.getString("articleNumber"), product, resultSet.getDate("dateSold"), resultSet.getDate("receivedInStore")));
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		return builtCopies;
	}
	
	private Supplier buildSupplier(int supplierCVR) throws SQLException {
		Supplier builtSupplier = null;
		String selectSupplier = "SELECT * FROM Supplier WHERE CVR = '" + supplierCVR + "'";
		Statement statement = DBConnection.getInstance().getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery(selectSupplier);

		if (resultSet.next()) {
			try {

				builtSupplier = new Supplier(resultSet.getInt("CVR"), resultSet.getString("name"), resultSet.getString("contactPerson"),
						resultSet.getString("address"), resultSet.getString("phoneNumber"), resultSet.getString("email"),
						resultSet.getString("productCategory"));

			}

			catch (SQLException e) {

				e.printStackTrace();

			}

		}
		return builtSupplier;
	}

	// a method for finding stuff with barcode since we have no article number this
	// time? maybe?

	public ArrayList<Product> getOneProductInformation(String barcode) throws SQLException {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		String selectBooks = "SELECT * FROM Book JOIN Product ON Book.barcode = Product.barcode WHERE Book.barcode = '" + barcode + "'";
		String selectGames = "SELECT * FROM Game JOIN Product ON Game.barcode = Product.barcode WHERE Book.barcode = '" + barcode + "'";
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
	
	public ArrayList<Product> getProductInformation() throws SQLException {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		String selectBooks = "SELECT * FROM Book JOIN Product ON Book.barcode = Product.barcode";
		String selectGames = "SELECT * FROM Game JOIN Product ON Game.barcode = Product.barcode";
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();

			ResultSet rsBook = statement.executeQuery(selectBooks);
			if (rsBook.next()) {
				foundProducts.addAll(buildObjects(rsBook, "Book"));

			}

			ResultSet rsGame = statement.executeQuery(selectGames);
			if (rsGame.next()) {
				foundProducts.addAll(buildObjects(rsGame, "Game"));

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
