package testing;

import static org.junit.Assert.assertEquals;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlLayer.*;
import databaseLayer.ProductDB;
import databaseLayer.SaleDB;
import modelLayer.*;

public class testAnalytics {
	
	private ProductDB productDB;
	private SaleDB saleDB;
	private OrderLine orderLine;
	private Sale sale;
	private TargetedCategory targetedCategory;
	private Employee employee;
	private Supplier supplier;
	private Product product;
	private Book book;
	private Game game;
	

	@Before
	public void setUp() throws Exception {
	productDB = new ProductDB();
	saleDB = new SaleDB();
	orderLine = new OrderLine(sale, 123, product);
	sale = new Sale(986532);
	//targetedCategory = new TargetedCategory(333, "Students", 19, 29, "Male", "None");
	//employee = new Employee(12345678, "Tanya Harding", "Bispensgade 34, 3TV, 9000, Aalborg", 620316941, 
	//		"tanyaharding@gmail.com", "Student aid");
	
//	product = new Product("1234");
//	supplier = new Supplier(123456789, "Johnny Supplies", "Martin Smile", "Vesterbro 27",
//			"+45605139782", "smileyMartey@gmail.com", "I give u book");
//	Supplier bookSupplier = new Supplier(123456789, "Johnny Supplies", "Martin Smile", "Vesterbro 27",
//			"+45605139782", "smileyMartey@gmail.com", "I give u book");
//	Supplier gameSupplier = new Supplier(987654321, "Supplier Johnny", "Bob Smile", "Vesterbro 27", "+4560514859",
//			"bobIsInLove@gmail.com", "I give u game");
//	book = new Book("1234", "Spork", 14.4, 55.7, 10, "07/11/2020", "Description of pretty book", "English",
//			bookSupplier, "ABC123", "Foon ", "novel");
//	game = new Game("9876", "Exploding puppies", 150.00, 250.50, 1, "21/11/2020", "description of pretty game",
//			"English", gameSupplier, "puzzle");
	
//The data that is used in these tests has been inserted into the database using the testData sql scripts
	}


	//All parameters are correct
	@Test
	public void testProductsAnalytics() {
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
	public void testIncorrectParameterChoice() {
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
	public void testIncorrectParameterTime() {
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
	public void testNotInDatabase() {
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
	public void testIncorrectTargetedCategoryID () {
		// Arrange
		ArrayList<Product> foundProducts = new ArrayList<Product>();

		// Act
		try {
			foundProducts.addAll(saleDB.getProductsAnalytics("Fast", "Book", 2000, 0, 0, -1)); //by default in the UI, when a targeted category
		} catch (SQLException e) {															//has not been selected, it sets the ID to -1
			e.printStackTrace();
		}

		//Assert
		assertEquals("Returns an empty list", foundProducts.get(0));
					
	}
	
	//One to test if it works for getSalesAnalytics() 
	@Test
	public void testSalesAnalytics() {
		// Arrange
		ArrayList<OrderLine> foundOrderLines = new ArrayList<OrderLine>();
		

		// Act
		try {
			foundOrderLines.addAll(saleDB.getSalesAnalytics());
		} catch (SQLException e) {															
			e.printStackTrace();
		}

		//Assert
		System.out.println("size of salesAnalytics" + foundOrderLines.size());
		assertEquals("Return Bestsellers", foundOrderLines.size(), 0);
		}
	

		
	@After
	public void tearDown() throws Exception {
	}


}
