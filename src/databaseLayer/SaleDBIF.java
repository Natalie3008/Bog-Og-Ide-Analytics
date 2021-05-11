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
	 
	 Employee buildEmployee(int EmployeeCPR) throws SQLException;
	 
	 ArrayList<OrderLine> buildOrderLines(ResultSet resultSet) throws SQLException;
	 
	 ArrayList<Product> getProductsAnalytics(String choice, String type, int year, int month, int day)
				throws SQLException;
	 
	 ArrayList<OrderLine> getSalesAnalytics();
	 
	 boolean createSale(Sale sale, Copy copy) throws SQLException;
	 
	 boolean deleteSale(int ID) throws SQLException;
	
	
}
