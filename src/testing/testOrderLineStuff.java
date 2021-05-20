package testing;

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
		ArrayList<OrderLine> testOne = saleDb.getSalesAnalytics();
		System.out.println(testOne.get(0).getSale().getTotalPrice());
	}
	
	@Test public void testIfBestProfitsWorks() throws SQLException {
		ArrayList<Product> testTwo = saleDb.getProductsAnalytics("Not sold", "Book", 0, 0, 0, -1);
		System.out.println(testTwo.size());
	}
	

}
