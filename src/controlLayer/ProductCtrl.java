package controlLayer;

import java.sql.*;
import databaseLayer.*;
import modelLayer.*;

public class ProductCtrl {
	private ProductDB productDb;
	
	public ProductCtrl() {
		productDb = new ProductDB();
	}
	
/** Method to get the product information by giving title and barcode  	*/
	public Product getProductInformation(String barcode) {
		Product p = null;
		
		try {
			p= productDb.getProductInformation(barcode);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return p;

	}
	
/** Method to create new Book and insert it in the database*/
	
	public boolean createBook(String title, String barcode, double costPrice, double recommendedRetailPrice, int amountInStock, String publicationDate, String description, 
			Date dateSold, Date receivedInStore, String ISBN, String author, String genre) {
		boolean result=true;
		
		try {
			productDb.createBook(title, barcode, costPrice, recommendedRetailPrice, amountInStock, publicationDate, description, dateSold, receivedInStore, ISBN, author, genre);
		}
		catch(SQLException e){
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/** Method to create new Game and insert it in the database*/	
	
	public boolean createGame(String title, String barcode, double costPrice, double recommendedRetailPrice, int amountInStock, String publicationDate, String description, 
			Date dateSold, Date receivedInStore, String type) {
		boolean result = true;
		
		try {
			productDb.createGame(title, barcode, costPrice, recommendedRetailPrice, amountInStock, publicationDate, description, dateSold, receivedInStore,type);
		}
		catch(SQLException e){
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/** Method to remove product from database by giving barcode in the method parameter */
	public boolean removeProduct(String barcode){
		boolean res = true;
		try{
			productDb.deleteProduct(barcode);
		}
		catch(SQLException e){
			e.printStackTrace();
			res = false;
		}
		return res;
	}
	
	
	public boolean updateAmountInStock(Product product){
		boolean res = true;
		try{
			productDb.updateAmountInStock(product);
		}
		catch(SQLException e){
			e.printStackTrace();
			res = false;
		}
		return res;
	}
	
	
	
}
