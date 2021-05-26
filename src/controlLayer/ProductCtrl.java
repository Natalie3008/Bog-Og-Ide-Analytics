package controlLayer;

import java.sql.*;
import java.util.ArrayList;

import databaseLayer.*;
import modelLayer.*;
// This is the product controller class. Here are methods for manipulating product objects.
public class ProductCtrl {
	private ProductDBIF productDb;

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
	
	// Method to get all product information.
	public ArrayList<Product> getProductInformation() {
		ArrayList<Product> allProducts = new ArrayList<Product>();
		try {
			allProducts.addAll(productDb.getProductInformation()); // this method goes to Product DB and uses a method from there, which accesses the database
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
	
	// CRUD  - update amount in stock
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
	
	// CRUD - update copy's datesold
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
	
	// CRUD - updating recommended retail price for product
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