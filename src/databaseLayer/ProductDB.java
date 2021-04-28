package databaseLayer;

import modelLayer.Product;
import modelLayer.Book;
import modelLayer.Game;
import java.sql.*;
import modelLayer.Supplier;

public class ProductDB {

	private Product buildObject(ResultSet rs, String selectedType) throws SQLException 
	{
		Product builtProduct = null;

		try 
		{

			if (selectedType.equals("Book")) 
			{

				builtProduct = new Book(rs.getString("Title"), rs.getString("barcode"), rs.getDouble("costPrice"),
						rs.getDouble("recommendedRetailPrice"), rs.getInt("amountInStock"), 
						rs.getString("publicationDate"), rs.getString("description"), rs.getDate("receivedInStore"),
						buildSupplier(rs.getInt("supplierCVR")), rs.getString("author"), rs.getString("genre"), rs.getString("ISBN"));	
				
			 if (selectedType.equals("Game")) 
			 {
					builtProduct = new Game(rs.getString("Title"), rs.getString("barcode"), rs.getDouble("costPrice"),
							rs.getDouble("recommendedRetailPrice"), rs.getInt("amountInStock"), 
							rs.getString("publicationDate"), rs.getString("description"), rs.getDate("receivedInStore"),
							buildSupplier(rs.getInt("supplierCVR")), rs.getString("Type"));	 
					
				
			}
			}
		}

		catch (SQLException e) 
		{

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

	public Product findProductInDB(String barcode) throws SQLException {
		Product foundProduct = null;

		try {

			// String SelectProducts = String.format("SELECT * FROM Product WHERE barcode =
			// '"+barcode+"'");
			// not sure about searching just for products so imma leave it greyed out for
			// now someone with brain help
			String SelectBooks = String
					.format("SELECT * FROM Book JOIN Product ON Book.barcode = Product.barcode WHERE barcode = '"
							+ barcode + "'");
			String SelectGames = String
					.format("SELECT * FROM Game JOIN Product ON Game.barcode = Product.barcode WHERE barcode = '"
							+ barcode + "'");

			Statement statement = DBConnection.getInstance().getConnection().createStatement();

			ResultSet rs1 = statement.executeQuery(SelectBooks);
			if (rs1.next()) {
				foundProduct = buildObject(rs1, "Book");

			}

			ResultSet rs2 = statement.executeQuery(SelectGames);
			if (rs2.next()) {
				foundProduct = buildObject(rs2, "Game");

			}

		}

		catch (SQLException e) {

			e.printStackTrace();

		}

		return foundProduct;
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
