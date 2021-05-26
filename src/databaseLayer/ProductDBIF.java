package databaseLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelLayer.Book;
import modelLayer.Copy;
import modelLayer.Game;
import modelLayer.Product;
import modelLayer.Supplier;

public interface ProductDBIF {
	
	Product buildObject(ResultSet resultSet, String selectedType) throws SQLException;
	ArrayList<Copy> buildCopies(Product product, String barcode, String type) throws SQLException;
	Supplier buildSupplier(int supplierCVR) throws SQLException;
	Product getOneProductInformation(String barcode) throws SQLException;
	ArrayList<Product> getProductInformation() throws SQLException;
	ArrayList<Product> buildObjects(ResultSet resultSet, String category) throws SQLException;
	Product createBook(Book book, Copy copy) throws SQLException;
	Product createGame(Game game, Copy copy) throws SQLException;
	boolean deleteProduct(String articleNumber, String barcode) throws SQLException;
	Product updateAmountInStock(Product product) throws SQLException;
	Copy updateDateSold(Copy copy) throws SQLException;
	Product updateRRP(Product product) throws SQLException;
}
