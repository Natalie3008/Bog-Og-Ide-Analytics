package databaseLayer;

import modelLayer.Product;
import modelLayer.Book;
import modelLayer.Copy;
import modelLayer.Game;

import java.sql.*;
import java.util.ArrayList;

import modelLayer.Supplier;
//This class holds methods that are for Product management and interact with the database.
public class ProductDB {
// This it a methods that builds an object from result set and then returns it
	private Product buildObject(ResultSet resultSet, String selectedType) throws SQLException {
		Product builtProduct = null;
// Book and game are two different types because they have different fields
		try {
			if (selectedType.equals("Book")) {

				builtProduct = new Book(resultSet.getString("barcode"), resultSet.getString("title"),
						resultSet.getDouble("costPrice"), resultSet.getDouble("RRP"), resultSet.getInt("amountInStock"),
						resultSet.getString("publicationDate"), resultSet.getString("description"),
						buildSupplier(resultSet.getInt("supplierCVR")), resultSet.getString("ISBN"),
						resultSet.getString("author"), resultSet.getString("genre"));
				builtProduct.setCopies(buildCopies(builtProduct, resultSet.getString("barcode"), "Book"));
			}

			else if (selectedType.equals("Game")) {
				builtProduct = new Game(resultSet.getString("barcode"), resultSet.getString("title"),
						resultSet.getDouble("costPrice"), resultSet.getDouble("RRP"), resultSet.getInt("amountInStock"),
						resultSet.getString("publicationDate"), resultSet.getString("description"),
						buildSupplier(resultSet.getInt("supplierCVR")), resultSet.getString("type"));
				builtProduct.setCopies(buildCopies(builtProduct, resultSet.getString("barcode"), "Game"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return builtProduct;
	}
// a method to make an arraylist of Copy Objects based on info from the DB
	private ArrayList<Copy> buildCopies(Product product, String barcode, String type) throws SQLException {
		ArrayList<Copy> builtCopies = new ArrayList<Copy>();
		String selectBookCopies = "SELECT * FROM Book WHERE Book.barcode = '" + barcode + "'";
		String selectGameCopies = "SELECT * FROM Game WHERE Game.barcode = '" + barcode + "'";
		Statement statement = DBConnection.getInstance().getConnection().createStatement();
		ResultSet resultSet = null;
		if (type.equals("Book")) {
			resultSet = statement.executeQuery(selectBookCopies);
		} else if (type.equals("Game")) {
			resultSet = statement.executeQuery(selectGameCopies);
		}
		while (resultSet.next()) {
			try {
				builtCopies.add(new Copy(resultSet.getString("articleNumber"), resultSet.getDate("dateSold"),
						resultSet.getDate("receivedInStore"), product));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return builtCopies;
	}
// creating a supplier object 
	private Supplier buildSupplier(int supplierCVR) throws SQLException {
		Supplier builtSupplier = null;
		String selectSupplier = "SELECT * FROM Supplier WHERE CVR = '" + supplierCVR + "'";
		Statement statement = DBConnection.getInstance().getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery(selectSupplier);

		if (resultSet.next()) {
			try {
				builtSupplier = new Supplier(resultSet.getInt("CVR"), resultSet.getString("name"),
						resultSet.getString("contactPerson"),
						(resultSet.getString("street") + "," + resultSet.getInt("zipcode") + ","
								+ resultSet.getString("city") + ", " + resultSet.getString("country")),
						resultSet.getString("phoneNumber"), resultSet.getString("email"),
						resultSet.getString("category"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return builtSupplier;
	}
	
	// a method to get information about one object. It accesses the DB, executes statement and based on result it creates an returns an object

	public Product getOneProductInformation(String barcode) throws SQLException {
		Product foundProduct = null;
		String selectBooks = "SELECT * FROM Book JOIN Product ON Book.barcode = Product.barcode WHERE Book.barcode = '"
				+ barcode + "'"; // statements to be executed
		String selectGames = "SELECT * FROM Game JOIN Product ON Game.barcode = Product.barcode WHERE Game.barcode = '"
				+ barcode + "'"; // statements to be executed
		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();

			ResultSet rsBook = statement.executeQuery(selectBooks);
			if (rsBook.next()) {
				foundProduct = buildObject(rsBook, "Book");
			}

			ResultSet rsGame = statement.executeQuery(selectGames);
			if (rsGame.next()) {
				foundProduct = buildObject(rsGame, "Game");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundProduct;
	}
// a method to get information about all the products from the database
	public ArrayList<Product> getProductInformation() throws SQLException {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		String selectBooks = "SELECT * FROM Book JOIN Product ON Book.barcode = Product.barcode";
		String selectGames = "SELECT * FROM Game JOIN Product ON Game.barcode = Product.barcode";

		try {
			Statement statement = DBConnection.getInstance().getConnection().createStatement();

			ResultSet rsBook = statement.executeQuery(selectBooks);
			foundProducts.addAll(buildObjects(rsBook, "Book"));
			ResultSet rsGame = statement.executeQuery(selectGames);
			foundProducts.addAll(buildObjects(rsGame, "Game"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundProducts;
	}
	
	// a method to build many object of Product type from the database
	private ArrayList<Product> buildObjects(ResultSet resultSet, String category) throws SQLException {
		ArrayList<Product> resultProducts = new ArrayList<Product>();

		while (resultSet.next()) {
			Product product = buildObject(resultSet, category);
			resultProducts.add(product);
		}
		return resultProducts;
	}

	// a method to make a book and add it into the database
	public Product createBook(Book book, Copy copy) throws SQLException {
		String sqlProduct = "INSERT INTO Product (barcode, title, costPrice, RRP, amountInStock, publicationDate, description, supplierCVR)"
				+ " VALUES( " + book.getBarcode() + ", '" + book.getTitle() + "', " + book.getCostPrice() + ", "
				+ book.getRecommendedRetailPrice() + ", " + book.getAmountInStock() + ", " + book.getPublicationDate()
				+ ", '" + book.getDescription() + "', " + book.getSupplier().getCVR() + ")";
		String sqlBook = "INSERT INTO Book (articleNumber, barcode, ISBN, author, genre, receivedInStore, dateSold) VALUES ( '"
				+ copy.getArticleNumber() + "', " + book.getBarcode() + ", '" + book.getISBN() + "', '" + book.getAuthor()
				+ "', '" + book.getGenre() + "', " + copy.getReceivedInStore() + ", " + copy.getDateSold() + ")";
		int resultProduct = DBConnection.getInstance().executeUpdate(sqlProduct);
		int resultBook = DBConnection.getInstance().executeUpdate(sqlBook);
		return resultProduct == 1 && resultBook == 1 ? book : null;
	}
	// a method to make a game and add it into the database
	public Product createGame(Game game, Copy copy) throws SQLException {
		String sqlProduct = "INSERT INTO Product (barcode, title, costPrice, RRP, amountInStock, publicationDate, description, supplierCVR)"
				+ " VALUES( " + game.getBarcode() + ", '" + game.getTitle() + "', " + game.getCostPrice() + ", "
				+ game.getRecommendedRetailPrice() + ", " + game.getAmountInStock() + ", " + game.getPublicationDate()
				+ ", '" + game.getDescription() + "', " + game.getSupplier().getCVR() + ")";
		String sqlGame = "INSERT INTO Game (articleNumber, barcode, type, receivedInStore, dateSold) VALUES ( '"
				+ copy.getArticleNumber() + "', " + game.getBarcode() + ", '" + game.getType() + "', "
				+ copy.getReceivedInStore() + ", " + copy.getDateSold() + ")";
		int resultProduct = DBConnection.getInstance().executeUpdate(sqlProduct);
		int resultGame = DBConnection.getInstance().executeUpdate(sqlGame);
		return resultProduct == 1 && resultGame == 1 ? game : null;
	}
	//CRUD for Product - delete

	public boolean deleteProduct(String articleNumber, String barcode) throws SQLException {
		String sqlProduct = "DELETE FROM Product WHERE barcode LIKE '" + barcode + "'";
		String sqlBook = "DELETE FROM Book WHERE articleNumber LIKE '" + articleNumber + "'";
		String sqlGame = "DELETE FROM Game WHERE articleNumber LIKE '" + articleNumber + "'";
		int resultBook = DBConnection.getInstance().executeUpdate(sqlBook);
		int resultGame = DBConnection.getInstance().executeUpdate(sqlGame);
		int resultProduct = DBConnection.getInstance().executeUpdate(sqlProduct);
		return resultProduct > 1 || resultBook > 1 || resultGame > 1;
	}
	
	//CRUD for Product - updating amount in stock

	public Product updateAmountInStock(Product product) throws SQLException {
		String sql = "UPDATE Product SET amountInStock = '" + product.getAmountInStock() + "' WHERE barcode = "
				+ product.getBarcode();
		int result = DBConnection.getInstance().executeUpdate(sql);
		return result == 1 ? product : null;
	}
	
	//CRUD for Copy - updating date sold

	public Copy updateDateSold(Copy copy) throws SQLException {
		String sqlGame = "UPDATE Game SET dateSold = '" + copy.getDateSold() + "' WHERE articleNumber = "
				+ copy.getArticleNumber();
		String sqlBook = "UPDATE Book SET dateSold = '" + copy.getDateSold() + "' WHERE articleNumber = "
				+ copy.getArticleNumber();
		int resultGame = DBConnection.getInstance().executeUpdate(sqlGame);
		int resultBook = DBConnection.getInstance().executeUpdate(sqlBook);
		return resultGame == 1 || resultBook == 1 ? copy : null;
	}
	
	//CRUD Product - updating recommeded retail price

	public Product updateRRP(Product product) throws SQLException {
		String sql = "UPDATE Product SET RRP = '" + product.getRecommendedRetailPrice() + "' WHERE barcode = "
				+ product.getBarcode();
		int result = DBConnection.getInstance().executeUpdate(sql);
		return result == 1 ? product : null;
	}
}