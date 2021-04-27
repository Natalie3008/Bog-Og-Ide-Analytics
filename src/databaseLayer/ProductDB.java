package databaseLayer;

import modelLayer.Product;
import modelLayer.Book;
import modelLayer.Game;
import java.sql.*;

public class ProductDB {

	private Product buildObject(ResultSet rs, String selectedType) throws SQLException {

		Product builtProduct = null;

		try {

			if (selectedType.equals("Book")) {

				builtProduct = new Book(rs.getString("Title"), rs.getString("barcode"), rs.getDouble("costPrice"),
						rs.getDouble("recommendedRetailPrice"), rs.getInt("amountInStock"),
						rs.getString("publicationDate"), rs.getString("description"), rs.getDate("receivedInStore"),
						rs.getString("ISBN"), rs.getString("author"), rs.getString("genre"));
				
				
				
			 /* if (selectedType.equals("Game")) {

					builtProduct = new Book(rs.getString("Title"), rs.getString("barcode"), rs.getDouble("costPrice"),
							rs.getDouble("recommendedRetailPrice"), rs.getInt("amountInStock"),
							rs.getString("publicationDate"), rs.getString("description"), rs.getDate("receivedInStore"),
							rs.getString("ISBN"), rs.getString("author"), rs.getString("genre"));
				*/
				
				// Game model layer kinda empty, won't touch this part yes

			}

		}

		catch (SQLException e) {

			e.printStackTrace();

		}
		
		return builtProduct;

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

}
