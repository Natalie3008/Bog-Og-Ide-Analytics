package databaseLayer;

import modelLayer.Product;
import modelLayer.Book;
import modelLayer.Copy;
import modelLayer.Game;

import java.sql.*;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import modelLayer.Supplier;

public class ProductDB {

	public Product buildObject(ResultSet resultSet, String selectedType) throws SQLException {
		Product builtProduct = null;

		try {
			if (selectedType.equals("Product")) {

				builtProduct = new Product(resultSet.getString("barcode"), resultSet.getString("title"),
						resultSet.getDouble("costPrice"), resultSet.getDouble("RRP"), resultSet.getInt("amountInStock"),
						resultSet.getString("publicationDate"), resultSet.getString("description"),
						resultSet.getString("language"), buildSupplier(resultSet.getInt("supplierCVR")));		
			}
			
			if (selectedType.equals("Book")) {

				builtProduct = new Book(resultSet.getString("barcode"), resultSet.getString("title"),
						resultSet.getDouble("costPrice"), resultSet.getDouble("RRP"), resultSet.getInt("amountInStock"),
						resultSet.getString("publicationDate"), resultSet.getString("description"),
						resultSet.getString("language"), buildSupplier(resultSet.getInt("supplierCVR")),
						resultSet.getString("ISBN"), resultSet.getString("author"), resultSet.getString("genre"));
				builtProduct.setCopies(buildCopies(builtProduct, resultSet.getString("barcode"), "Book"));
			}

			else if (selectedType.equals("Game")) {
				builtProduct = new Game(resultSet.getString("barcode"), resultSet.getString("title"),
						resultSet.getDouble("costPrice"), resultSet.getDouble("RRP"), resultSet.getInt("amountInStock"),
						resultSet.getString("publicationDate"), resultSet.getString("description"),
						resultSet.getString("language"), buildSupplier(resultSet.getInt("supplierCVR")),
						resultSet.getString("type"));
				builtProduct.setCopies(buildCopies(builtProduct, resultSet.getString("barcode"), "Game"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return builtProduct;
	}

	public ArrayList<Copy> buildCopies(Product product, String barcode, String type) throws SQLException {
		ArrayList<Copy> builtCopies = new ArrayList<Copy>();
		String selectBookCopies = "SELECT * FROM Book WHERE Book.barcode LIKE ?";
		String selectGameCopies = "SELECT * FROM Game WHERE Game.barcode LIKE ?";
		ResultSet resultSet = null;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementBooks = DBConnection.getInstance().getConnection().prepareStatement(selectBookCopies);
			PreparedStatement statementGames = DBConnection.getInstance().getConnection().prepareStatement(selectGameCopies);
			statementBooks.setString(1,  barcode);
			statementGames.setString(1, barcode);
			DBConnection.getInstance().getConnection().commit();
			if (type.equals("Book")) {
				resultSet = statementBooks.executeQuery();
			} else if (type.equals("Game")) {
				resultSet = statementGames.executeQuery();
			}
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();

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

	public Supplier buildSupplier(int supplierCVR) throws SQLException {
		Supplier builtSupplier = null;
		String selectSupplier = "SELECT * FROM Supplier WHERE CVR = ?";
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(selectSupplier);
			statement.setLong(1, supplierCVR);
			DBConnection.getInstance().getConnection().commit();
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				builtSupplier = new Supplier(resultSet.getInt("CVR"), resultSet.getString("name"),
						resultSet.getString("contactPerson"),
						(resultSet.getString("street") + "," + resultSet.getInt("zipcode") + ","
								+ resultSet.getString("city") + ", " + resultSet.getString("country")),
						resultSet.getString("phoneNumber"), resultSet.getString("email"),
						resultSet.getString("category"));
			}
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();

		}
		return builtSupplier;
	}

	public Product getOneProductInformation(String barcode) throws SQLException {
		Product foundProduct = null;
		String selectBooks = "SELECT * FROM Book JOIN Product ON Book.barcode = Product.barcode WHERE Book.barcode LIKE ?";
		String selectGames = "SELECT * FROM Game JOIN Product ON Game.barcode = Product.barcode WHERE Game.barcode LIKE ?";
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementBooks = DBConnection.getInstance().getConnection().prepareStatement(selectBooks);
			PreparedStatement statementGames = DBConnection.getInstance().getConnection().prepareStatement(selectGames);
			statementBooks.setString(1, barcode);
			statementGames.setString(1, barcode);
			DBConnection.getInstance().getConnection().commit();
			ResultSet resultSetBook = statementBooks.executeQuery();
			if (resultSetBook.next()) {
				foundProduct = buildObject(resultSetBook, "Book");
			}
			ResultSet resultSetGame = statementGames.executeQuery(selectGames);
			if (resultSetGame.next()) {
				foundProduct = buildObject(resultSetGame, "Game");
			}
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return foundProduct;
	}

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

	public ArrayList<Product> buildObjects(ResultSet resultSet, String category) throws SQLException {
		ArrayList<Product> resultProducts = new ArrayList<Product>();

		while (resultSet.next()) {
			Product product = buildObject(resultSet, category);
			resultProducts.add(product);
		}
		return resultProducts;
	}

	// method to make a book and add it into the db // WIP STILL
	public Product createBook(Book book, Copy copy) throws SQLException {
		String sqlProduct = "INSERT INTO Product "
				+ "(barcode, title, costPrice, RRP, amountInStock, publicationDate, description, supplierCVR, version) VALUES(?,?,?,?,?,?,?,?,?)";
		String sqlBook = "INSERT INTO Book (articleNumber, barcode, ISBN, author, genre, receivedInStore, dateSold, version) VALUES (?,?,?,?,?,?,?, ?)";
		int resultProduct = 0;
		int resultBook = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementProduct = DBConnection.getInstance().getConnection()
					.prepareStatement(sqlProduct);
			statementProduct.setString(1, book.getBarcode());
			statementProduct.setString(2, book.getTitle());
			statementProduct.setDouble(3, book.getCostPrice());
			statementProduct.setDouble(4, book.getRecommendedRetailPrice());
			statementProduct.setInt(5, book.getAmountInStock());
			statementProduct.setString(6, book.getPublicationDate());
			statementProduct.setString(7, book.getDescription());
			statementProduct.setLong(8, book.getSupplier().getCVR());
			resultProduct = statementProduct.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statementProduct.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();

		}
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementBook = DBConnection.getInstance().getConnection().prepareStatement(sqlBook);
			statementBook.setString(1, copy.getArticleNumber());
			statementBook.setString(2, book.getBarcode());
			statementBook.setString(3, book.getISBN());
			statementBook.setString(4, book.getAuthor());
			statementBook.setString(5, book.getGenre());
			statementBook.setDate(6, copy.getReceivedInStore());
			statementBook.setDate(7, copy.getDateSold());
			resultBook = statementBook.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statementBook.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return resultProduct == 1 && resultBook == 1 ? book : null;
	}

	public Product createGame(Game game, Copy copy) throws SQLException {
		String sqlProduct = "INSERT INTO Product "
				+ "(barcode, title, costPrice, RRP, amountInStock, publicationDate, description, supplierCVR, version) VALUES(?,?,?,?,?,?,?,?,?)";
		String sqlGame = "INSERT INTO Game (articleNumber, barcode, type, receivedInStore, dateSold, version) VALUES (?,?,?,?,?, ?)";
		int resultProduct = 0;
		int resultGame = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementProduct = DBConnection.getInstance().getConnection()
					.prepareStatement(sqlProduct);
			PreparedStatement statementGame = DBConnection.getInstance().getConnection().prepareStatement(sqlGame);
			statementProduct.setString(1, game.getBarcode());
			statementProduct.setString(2, game.getTitle());
			statementProduct.setDouble(3, game.getCostPrice());
			statementProduct.setDouble(4, game.getRecommendedRetailPrice());
			statementProduct.setInt(5, game.getAmountInStock());
			statementProduct.setString(6, game.getPublicationDate());
			statementProduct.setString(7, game.getDescription());
			statementProduct.setLong(8, game.getSupplier().getCVR());
			statementGame.setString(1, copy.getArticleNumber());
			statementGame.setString(2, game.getBarcode());
			statementGame.setString(3, game.getType());
			statementGame.setDate(4, copy.getReceivedInStore());
			statementGame.setDate(5, copy.getDateSold());
			resultGame = statementGame.executeUpdate();
			resultProduct = statementProduct.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statementProduct.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return resultProduct == 1 && resultGame == 1 ? game : null;
	}

	public boolean deleteProduct(String articleNumber, String barcode) throws SQLException {
		String sqlProduct = "DELETE FROM Product WHERE barcode LIKE ?";
		String sqlBook = "DELETE FROM Book WHERE articleNumber LIKE ?";
		String sqlGame = "DELETE FROM Game WHERE articleNumber LIKE ?";
		int resultBook = 0;
		int resultGame = 0;
		int resultProduct = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementProduct = DBConnection.getInstance().getConnection()
					.prepareStatement(sqlProduct);
			PreparedStatement statementBook = DBConnection.getInstance().getConnection().prepareStatement(sqlBook);
			PreparedStatement statementGame = DBConnection.getInstance().getConnection().prepareStatement(sqlGame);
			statementProduct.setString(1, barcode);
			statementBook.setString(1, articleNumber);
			statementGame.setString(1, articleNumber);
			resultProduct = statementProduct.executeUpdate();
			resultBook = statementBook.executeUpdate();
			resultGame = statementGame.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statementBook.close();
			statementGame.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return resultProduct > 1 || resultBook > 1 || resultGame > 1;
	}

	public Product updateAmountInStock(Product product) throws SQLException {
		String sqlUpdate = "UPDATE Product SET amountInStock = ?, version = ? WHERE barcode LIKE ? AND version = ?";
		int result = 0;
		int version = getVersion(product.getBarcode());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sqlUpdate);
			statement.setInt(1, product.getAmountInStock());
			statement.setString(2, product.getBarcode());
			statement.setInt(3, version + 1);
			statement.setString(4, product.getBarcode());
			statement.setInt(5, version);
			result = statement.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statement.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return result == 1 ? product : null;
	}

	public Copy updateDateSold(Copy copy) throws SQLException {
		String sqlGame = "UPDATE Game SET dateSold = ?, version = ? WHERE articleNumber LIKE ? AND version = ?";
		String sqlBook = "UPDATE Book SET dateSold = ?, version = ? WHERE articleNumber LIKE ? AND version = ?";
		int resultGame = 0;
		int resultBook = 0;
		int version = getVersion(copy.getArticleNumber());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statementGame = DBConnection.getInstance().getConnection().prepareStatement(sqlGame);
			PreparedStatement statementBook = DBConnection.getInstance().getConnection().prepareStatement(sqlBook);
			statementGame.setDate(1, copy.getDateSold());
			statementGame.setString(2, copy.getArticleNumber());
			statementBook.setDate(1, copy.getDateSold());
			statementBook.setString(2, copy.getArticleNumber());
			statementGame.setInt(3, version + 1);
			statementGame.setString(4, copy.getArticleNumber());
			statementGame.setInt(5, version);
			statementBook.setInt(3, version + 1);
			statementBook.setString(4, copy.getArticleNumber());
			statementBook.setInt(5, version);
			resultGame = statementGame.executeUpdate();
			resultBook = statementBook.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statementGame.close();
			statementBook.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return resultGame == 1 || resultBook == 1 ? copy : null;
	}

	public Product updateRRP(Product product) throws SQLException {
		String sqlUpdate = "UPDATE Product SET RRP = ?, version = ? WHERE barcode LIKE ? AND version = ?";
		int result = 0;
		int version = getVersion(product.getBarcode());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sqlUpdate);
			statement.setDouble(1, product.getRecommendedRetailPrice());
			statement.setString(2, product.getBarcode());
			statement.setInt(3, version + 1);
			statement.setString(4, product.getBarcode());
			statement.setInt(5, version);
			result = statement.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			statement.close();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
			throw e;
		}
		return result == 1 ? product : null;
	}
	
	private int getVersion(String productBarcode) throws SQLException {
		String sqlFindVersion = "SELECT version FROM product WHERE barcode = ?";
		int version = -1;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sqlFindVersion);
			statement.setString(1, productBarcode);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				version = resultSet.getInt(1);
			}
		} catch (SQLException e) {
            e.printStackTrace();
        }
        return version;
	}
}