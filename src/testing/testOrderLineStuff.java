package testing;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import databaseLayer.SaleDB;
import modelLayer.OrderLine;
import modelLayer.Product;

public class testOrderLineStuff {
	private SaleDB saleDb;

	@Before
	public void setUp() throws Exception {
		saleDb = new SaleDB();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIfOrderLineWorks() throws SQLException {
		ArrayList<OrderLine> fuckMe = saleDb.getBestSellersProducts();
		System.out.println(fuckMe.size());
	}
	
	@Test public void testIfBestProfitsWorks() throws SQLException {
		ArrayList<Product> fuckMeToo = saleDb.mostProfitableProducts();
		System.out.println(fuckMeToo.size());
	}

}
