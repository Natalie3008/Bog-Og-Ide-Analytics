package controlLayer;

import java.sql.*;
import java.util.ArrayList;

import databaseLayer.*;
import modelLayer.*;

public class SaleCtrl {
	private SaleDBIF saleDb;
	private ProductCtrl productCtrl;
	private TargetedCategoryCtrl categoryCtrl;

	public SaleCtrl() {
		saleDb = new SaleDB();
		productCtrl = new ProductCtrl();
	}

	public ArrayList<Sale> getSaleInformation() throws SQLException {
		ArrayList<Sale> foundSales = new ArrayList<Sale>();
		foundSales = saleDb.getSaleInformation();
		return foundSales;
	}

	public Sale getOneSaleInformation(int ID) throws SQLException {
		Sale foundSale = null;
		foundSale = saleDb.getOneSaleInformation(ID);
		return foundSale;
	}

	public Product getOneProductInformation(String barcode) {
		Product foundProduct = productCtrl.getOneProductInformation(barcode);
		return foundProduct;
	}

	public ArrayList<Product> getProductInformation() {
		ArrayList<Product> foundProducts = new ArrayList<>();
		foundProducts = productCtrl.getProductInformation();
		return foundProducts;
	}

	public ArrayList<TargetedCategory> getAllCategories() throws SQLException {
		ArrayList<TargetedCategory> foundCategories = new ArrayList<>();
		foundCategories = categoryCtrl.getAllTargetedCategories();
		return foundCategories;
	}

	public ArrayList<Product> getProductsAnalytics(String choice, String type, int year, int month, int day,
			int targetedCategoryID) throws SQLException {
		ArrayList<Product> foundProducts = new ArrayList<Product>();
		foundProducts = saleDb.getProductsAnalytics(choice, type, year, month, day, targetedCategoryID);
		return foundProducts;
	}

	public boolean createSale(Sale sale, Copy copy, OrderLine orderLine) {
		boolean result = true;
		try {
			saleDb.createSale(sale, copy, orderLine);
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public boolean deleteSale(int ID) {
		boolean result = true;
		try {
			saleDb.deleteSale(ID);
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
}