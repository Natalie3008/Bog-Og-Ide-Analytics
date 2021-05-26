package databaseLayer;

import modelLayer.Product;
import modelLayer.Book;
import modelLayer.Copy;
import modelLayer.Game;

import java.sql.*;
import java.util.ArrayList;

import modelLayer.Supplier;

public class ProductDB implements ProductDBIF {

	private static final String SELECT_BOOK_COPIES = "SELECT * FROM Book WHERE Book.barcode LIKE ?";
	private static final String SELECT_GAME_COPIES = "SELECT * FROM Game WHERE Game.barcode LIKE ?";
	private static final String SELECT_SUPLIER_WITH_CVR = "SELECT * FROM Supplier WHERE CVR = ?";

	private static final String SELECT_BOOKS_WITH_BARCODE = "SELECT * FROM Book JOIN Product ON Book.barcode = Product.barcode WHERE Book.barcode LIKE ?";
	private static final String SELECT_GAMES_WITH_BARCODE = "SELECT * FROM Game JOIN Product ON Game.barcode = Product.barcode WHERE Game.barcode LIKE ?";
	private static final String SELECT_BOOKS = "SELECT * FROM Book JOIN Product ON Book.barcode = Product.barcode";
	private static final String SELECT_GAMES = "SELECT * FROM Game JOIN Product ON Game.barcode = Product.barcode";

	private static final String INSERT_INTO_PRODUCT = "INSERT INTO Product (barcode, title, costPrice, RRP, amountInStock, publicationDate, description, supplierCVR, version) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_INTO_BOOK = "INSERT INTO Book (articleNumber, barcode, ISBN, author, genre, receivedInStore, dateSold, version) VALUES (?,?,?,?,?,?,?,?)";
	private static final String INSERT_INTO_GAME = "INSERT INTO Game (articleNumber, barcode, type, receivedInStore, dateSold, version) VALUES (?,?,?,?,?,?)";

	private static final String DELETE_PRODUCT_WITH_BARCODE = "DELETE FROM Product WHERE barcode LIKE ?";
	private static final String DELETE_BOOK_WITH_ARTICLE_NUMBER = "DELETE FROM Book WHERE articleNumber LIKE ?";
	private static final String DELETE_GAME_WITH_ARTICLE_NUMBER = "DELETE FROM Game WHERE articleNumber LIKE ?";

	private static final String UPDATE_PRODUCT_AMOUNT_IN_STOCK = "UPDATE Product SET amountInStock = ?, version = ? WHERE barcode LIKE ? AND version = ?";
	private static final String UPDATE_BOOK_DATESOLD = "UPDATE Book SET dateSold = ?, version = ? WHERE articleNumber LIKE ? AND version = ?";
	private static final String UPDATE_GAME_DATESOLD = "UPDATE Game SET dateSold = ?, version = ? WHERE articleNumber LIKE ? AND version = ?";
	private static final String UPDATE_PRODUCT_RRP = "UPDATE Product SET RRP = ?, version = ? WHERE barcode LIKE ? AND version = ?";

	private static final String SELECT_VERSION = "SELECT version FROM product WHERE barcode = ?";

	private PreparedStatement psSelectBookCopies;
	private PreparedStatement psSelectGameCopies;
	private PreparedStatement psSelectSupplierWithCVR;

	private PreparedStatement psSelectBooksWithBarcode;
	private PreparedStatement psSelectGamesWithBarcode;
	private PreparedStatement psSelectBooks;
	private PreparedStatement psSelectGames;

	private PreparedStatement psInsertIntoProduct;
	private PreparedStatement psInsertIntoBook;
	private PreparedStatement psInsertIntoGame;

	private PreparedStatement psDeleteProductWithBarcode;
	private PreparedStatement psDeleteBookWithArticleNumber;
	private PreparedStatement psDeleteGameWithArticleNumber;

	private PreparedStatement psUpdateProductAmountInStock;
	private PreparedStatement psUpdateBookDateSold;
	private PreparedStatement psUpdateGameDateSold;
	private PreparedStatement psUpdateProductRRP;

	private PreparedStatement psSelectVersion;
	
	public ProductDB() {
		initPreparedStatement();
	}
	
	private void initPreparedStatement() {
		Connection connection = DBConnection.getInstance().getConnection();
		try{
			psSelectBookCopies = connection.prepareStatement(SELECT_BOOK_COPIES);
			psSelectGameCopies = connection.prepareStatement(SELECT_GAME_COPIES);
			psSelectSupplierWithCVR = connection.prepareStatement(SELECT_SUPLIER_WITH_CVR);

			psSelectBooksWithBarcode = connection.prepareStatement(SELECT_BOOKS_WITH_BARCODE);
			psSelectGamesWithBarcode = connection.prepareStatement(SELECT_GAMES_WITH_BARCODE);
			psSelectBooks = connection.prepareStatement(SELECT_BOOKS);
			psSelectGames = connection.prepareStatement(SELECT_GAMES);

			psInsertIntoProduct = connection.prepareStatement(INSERT_INTO_PRODUCT);
			psInsertIntoBook = connection.prepareStatement(INSERT_INTO_BOOK);
			psInsertIntoGame = connection.prepareStatement(INSERT_INTO_GAME);

			psDeleteProductWithBarcode = connection.prepareStatement(DELETE_PRODUCT_WITH_BARCODE);
			psDeleteBookWithArticleNumber = connection.prepareStatement(DELETE_BOOK_WITH_ARTICLE_NUMBER);
			psDeleteGameWithArticleNumber = connection.prepareStatement(DELETE_GAME_WITH_ARTICLE_NUMBER);

			psUpdateProductAmountInStock = connection.prepareStatement(UPDATE_PRODUCT_AMOUNT_IN_STOCK);
			psUpdateBookDateSold = connection.prepareStatement(UPDATE_BOOK_DATESOLD);
			psUpdateGameDateSold = connection.prepareStatement(UPDATE_GAME_DATESOLD);
			psUpdateProductRRP = connection.prepareStatement(UPDATE_PRODUCT_RRP);

			psSelectVersion = connection.prepareStatement(SELECT_VERSION);
		}catch(SQLException e ){
			e.printStackTrace();
		}
	}

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
		ResultSet resultSet = null;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psSelectBookCopies.setString(1,  barcode);
			psSelectGameCopies.setString(1, barcode);
			DBConnection.getInstance().getConnection().commit();
			if (type.equals("Book")) {
				resultSet = psSelectBookCopies.executeQuery();
			} else if (type.equals("Game")) {
				resultSet = psSelectGameCopies.executeQuery();
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
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psSelectSupplierWithCVR.setLong(1, supplierCVR);
			DBConnection.getInstance().getConnection().commit();
			ResultSet resultSet = psSelectSupplierWithCVR.executeQuery();
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
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psSelectBooksWithBarcode.setString(1, barcode);
			psSelectGamesWithBarcode.setString(1, barcode);
			DBConnection.getInstance().getConnection().commit();
			ResultSet resultSetBook = psSelectBooksWithBarcode.executeQuery();
			if (resultSetBook.next()) {
				foundProduct = buildObject(resultSetBook, "Book");
			}
			ResultSet resultSetGame = psSelectGamesWithBarcode.executeQuery();
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

		try {
			ResultSet rsBook = psSelectBooks.executeQuery();
			foundProducts.addAll(buildObjects(rsBook, "Book"));
			ResultSet rsGame = psSelectGames.executeQuery();
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
		int resultProduct = 0;
		int resultBook = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psInsertIntoProduct.setString(1, book.getBarcode());
			psInsertIntoProduct.setString(2, book.getTitle());
			psInsertIntoProduct.setDouble(3, book.getCostPrice());
			psInsertIntoProduct.setDouble(4, book.getRecommendedRetailPrice());
			psInsertIntoProduct.setInt(5, book.getAmountInStock());
			psInsertIntoProduct.setString(6, book.getPublicationDate());
			psInsertIntoProduct.setString(7, book.getDescription());
			psInsertIntoProduct.setLong(8, book.getSupplier().getCVR());

			psInsertIntoBook.setString(1, copy.getArticleNumber());
			psInsertIntoBook.setString(2, book.getBarcode());
			psInsertIntoBook.setString(3, book.getISBN());
			psInsertIntoBook.setString(4, book.getAuthor());
			psInsertIntoBook.setString(5, book.getGenre());
			psInsertIntoBook.setDate(6, copy.getReceivedInStore());
			psInsertIntoBook.setDate(7, copy.getDateSold());

			resultBook = psInsertIntoBook.executeUpdate();
			resultProduct = psInsertIntoProduct.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		
		return resultProduct == 1 && resultBook == 1 ? book : null;
	}

	public Product createGame(Game game, Copy copy) throws SQLException {
		
		int resultProduct = 0;
		int resultGame = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psInsertIntoProduct.setString(1, game.getBarcode());
			psInsertIntoProduct.setString(2, game.getTitle());
			psInsertIntoProduct.setDouble(3, game.getCostPrice());
			psInsertIntoProduct.setDouble(4, game.getRecommendedRetailPrice());
			psInsertIntoProduct.setInt(5, game.getAmountInStock());
			psInsertIntoProduct.setString(6, game.getPublicationDate());
			psInsertIntoProduct.setString(7, game.getDescription());
			psInsertIntoProduct.setLong(8, game.getSupplier().getCVR());

			psInsertIntoGame.setString(1, copy.getArticleNumber());
			psInsertIntoGame.setString(2, game.getBarcode());
			psInsertIntoGame.setString(3, game.getType());
			psInsertIntoGame.setDate(4, copy.getReceivedInStore());
			psInsertIntoGame.setDate(5, copy.getDateSold());

			resultGame = psInsertIntoGame.executeUpdate();
			resultProduct = psInsertIntoProduct.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return resultProduct == 1 && resultGame == 1 ? game : null;
	}

	public boolean deleteProduct(String articleNumber, String barcode) throws SQLException {
		int resultBook = 0;
		int resultGame = 0;
		int resultProduct = 0;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);

			psDeleteProductWithBarcode.setString(1, barcode);
			psDeleteBookWithArticleNumber.setString(1, articleNumber);
			psDeleteGameWithArticleNumber.setString(1, articleNumber);
			resultProduct = psDeleteProductWithBarcode.executeUpdate();
			resultBook = psDeleteBookWithArticleNumber.executeUpdate();
			resultGame = psDeleteGameWithArticleNumber.executeUpdate();

			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return resultProduct > 1 || resultBook > 1 || resultGame > 1;
	}

	public Product updateAmountInStock(Product product) throws SQLException {
		int result = 0;
		int version = getVersion(product.getBarcode());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);

			psUpdateProductAmountInStock.setInt(1, product.getAmountInStock());
			psUpdateProductAmountInStock.setString(2, product.getBarcode());
			psUpdateProductAmountInStock.setInt(3, version + 1);
			psUpdateProductAmountInStock.setString(4, product.getBarcode());
			psUpdateProductAmountInStock.setInt(5, version);
			result = psUpdateProductAmountInStock.executeUpdate();

			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return result == 1 ? product : null;
	}

	public Copy updateDateSold(Copy copy) throws SQLException {
		int resultGame = 0;
		int resultBook = 0;
		int version = getVersion(copy.getArticleNumber());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);

			psUpdateBookDateSold.setDate(1, copy.getDateSold());
			psUpdateBookDateSold.setString(2, copy.getArticleNumber());
			psUpdateBookDateSold.setInt(3, version + 1);
			psUpdateBookDateSold.setString(4, copy.getArticleNumber());
			psUpdateBookDateSold.setInt(5, version);

			psUpdateGameDateSold.setDate(1, copy.getDateSold());
			psUpdateGameDateSold.setString(2, copy.getArticleNumber());
			psUpdateGameDateSold.setInt(3, version + 1);
			psUpdateGameDateSold.setString(4, copy.getArticleNumber());
			psUpdateGameDateSold.setInt(5, version);
			
			resultGame = psUpdateGameDateSold.executeUpdate();
			resultBook = psUpdateBookDateSold.executeUpdate();
			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return resultGame == 1 || resultBook == 1 ? copy : null;
	}

	public Product updateRRP(Product product) throws SQLException {
		int result = 0;
		int version = getVersion(product.getBarcode());
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);

			psUpdateProductRRP.setDouble(1, product.getRecommendedRetailPrice());
			psUpdateProductRRP.setString(2, product.getBarcode());
			psUpdateProductRRP.setInt(3, version + 1);
			psUpdateProductRRP.setString(4, product.getBarcode());
			psUpdateProductRRP.setInt(5, version);
			result = psUpdateProductRRP.executeUpdate();

			DBConnection.getInstance().getConnection().commit();
			DBConnection.getInstance().getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			DBConnection.getInstance().getConnection().rollback();
		}
		return result == 1 ? product : null;
	}
	
	private int getVersion(String productBarcode) throws SQLException {
		int version = -1;
		try {
			DBConnection.getInstance().getConnection().setAutoCommit(false);
			psSelectVersion.setString(1, productBarcode);
			ResultSet resultSet = psSelectVersion.executeQuery();
			if (resultSet.next()) {
				version = resultSet.getInt(1);
			}
		} catch (SQLException e) {
            e.printStackTrace();
        }
        return version;
	}
}