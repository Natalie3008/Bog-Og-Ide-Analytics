package controlLayer;

import java.sql.*;
import java.util.ArrayList;

import databaseLayer.*;
import modelLayer.*;

public class SaleCtrl {
	private SaleDB saleDb;
	private ProductCtrl productCtrl;

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

}
