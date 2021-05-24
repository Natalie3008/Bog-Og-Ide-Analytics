package databaseLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelLayer.*;

public interface SaleDBIF {

	ArrayList<Sale> getSaleInformation() throws SQLException;
	
	Sale getOneSaleInformation(int ID) throws SQLException;
	
	ArrayList<Sale> buildSales(ResultSet resultSet) throws SQLException;
	
	Sale buildSale(ResultSet resultSet) throws SQLException;
	 
	Employee buildEmployee(long EmployeeCPR) throws SQLException;
	 
	ArrayList<OrderLine> buildOrderLines(ResultSet resultSet) throws SQLException;
	 
	ArrayList<Product> getProductsAnalytics(String choice, String type, int year, int month, int day, int targetedCategoryID)
				throws SQLException;
	 	 
	Sale createSale(Sale sale, Copy copy, OrderLine orderLine) throws SQLException;
	 
	boolean deleteSale(int ID) throws SQLException;
	
	
}
