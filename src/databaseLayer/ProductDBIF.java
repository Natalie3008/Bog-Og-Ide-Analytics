package databaseLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelLayer.Product;
import modelLayer.Supplier;

public interface ProductDBIF {
	Product buildObject(ResultSet resultSet, String selectedType) throws SQLException;
	Supplier buildSupplier(int supplierCVR) throws SQLException;
	ArrayList<Product> getProductInformation(String barcode) throws SQLException;
	ArrayList<Product> buildObjects(ResultSet resultSet, String category) throws SQLException;

}
