package controlLayer;

import java.sql.*;
import java.util.ArrayList;

import databaseLayer.*;
import modelLayer.*;

public class SaleCtrl {
	private SaleDB saleDb;
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

	public ArrayList<Product> getProductsAnalytics(String choice, String type, int year, int month, int day) throws SQLException {
		ArrayList<Product> foundBooks = new ArrayList<Product>();
		foundBooks = saleDb.getProductsAnalytics(choice, type, year, month, day);
		return foundBooks;
	}

	public ArrayList<OrderLine> getSalesAnalytics() throws SQLException {
		ArrayList<OrderLine> foundOrderLines = new ArrayList<OrderLine>();
		foundOrderLines = saleDb.getSalesAnalytics();
		return foundOrderLines;
	}
}