package testing;

import static org.junit.Assert.assertEquals;


import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import databaseLayer.SaleDB;
import modelLayer.*;

public class testAnalytics {
	
	private SaleDB saleDB;

	@Before
	public void setUp() throws Exception {
	saleDB = new SaleDB();
//The data that is used in these tests has been inserted into the database using the testData sql scripts
	}


	//All parameters are correct
	@Test
	public void testProductsAnalytics() throws SQLException {
		// Arrange
		ArrayList<Product> foundProducts = new ArrayList<Product>();

		// Act
		try {
			foundProducts.addAll(saleDB.getProductsAnalytics("Slow", "Book", 0, 0, 0, 2));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Assert
		System.out.println("Size of bestsellers" + foundProducts.size());
		assertEquals("Returns Bestsellers", foundProducts.size(), 0);
	}

	//Incorrect parameter used - choice
	@Test
	public void testIncorrectParameterChoice() throws SQLException {
		// Arrange
		ArrayList<Product> foundProducts = new ArrayList<Product>();

		// Act
		try {
			foundProducts.addAll(saleDB.getProductsAnalytics("No Sale", "Book", 0, 0, 0, 2));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Assert
		assertEquals("Returns an empty list", foundProducts.get(0));
	}
	
	//Incorrect parameter used - time
	@Test
	public void testIncorrectParameterTime() throws SQLException {
		// Arrange
		ArrayList<Product> foundProducts = new ArrayList<Product>();

		// Act
		try {
			foundProducts.addAll(saleDB.getProductsAnalytics("", "Book", 2000, 0, 0, 2));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Assert
		assertEquals("Returns an empty list", foundProducts.get(0));	
	}

	//Incorrect targetedCategoryID - not in the database
	@Test
	public void testNotInDatabase() throws SQLException {
		// Arrange
				ArrayList<Product> foundProducts = new ArrayList<Product>();

				// Act
				try {
					foundProducts.addAll(saleDB.getProductsAnalytics("Fast", "Book", 2000, 0, 0, 315));
				} catch (SQLException e) {
					e.printStackTrace();
				}

				//Assert
				assertEquals("Returns an empty list", foundProducts.get(0));					
	}
	
	//Incorrect targetedCategoryID - null
	@Test
	public void testIncorrectTargetedCategoryID () throws SQLException {
		// Arrange
		ArrayList<Product> foundProducts = new ArrayList<Product>();

		// Act
		try {
			foundProducts.addAll(saleDB.getProductsAnalytics("Fast", "Book", 2000, 0, 0, -1)); 
		} catch (SQLException e) {															
			e.printStackTrace();
		}

		//Assert
		assertEquals("Returns an empty list", foundProducts.get(0));				
	}
		
	@After
	public void tearDown() throws Exception {
	}
}
