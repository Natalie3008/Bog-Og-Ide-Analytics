package controlLayer;

import java.sql.*;
import java.util.ArrayList;

import databaseLayer.*;
import modelLayer.*;

public class ProductCtrl {
	private ProductDB productDb;

	public ProductCtrl() {
		productDb = new ProductDB();
	}

// Method to get the product information by giving the barcode 
	public Product getOneProductInformation(String barcode) {
		Product foundProduct = null;

		try {
			foundProduct = productDb.getOneProductInformation(barcode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundProduct;

	}

	public ArrayList<Product> getProductInformation() {
		ArrayList<Product> allProducts = new ArrayList<Product>();

		try {
			allProducts.addAll(productDb.getProductInformation());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allProducts;
	}

// Method to create new Book and insert it in the database

	public boolean createBook(Book book, Copy copy) {
		boolean result = true;

		try {
			productDb.createBook(book, copy);
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	// Method to create new Game and insert it in the database

	public boolean createGame(Game game, Copy copy) {
		boolean result = true;

		try {
			productDb.createGame(game, copy);
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	// Method to remove product from database by giving barcode in the method
	// parameter
	public boolean deleteProduct(String articleNumber, String barcode) {
		boolean result = true;
		try {
			productDb.deleteProduct(articleNumber, barcode);
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public boolean updateAmountInStock(Product product) {
		boolean result = true;
		try {
			productDb.updateAmountInStock(product);
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public boolean updateDateSold(Copy copy) {
		boolean result = true;
		try {
			productDb.updateDateSold(copy);
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public boolean updateRRP(Product product) {
		boolean result = true;
		try {
			productDb.updateRRP(product);
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
}