package testing;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlLayer.ProductCtrl;
import databaseLayer.ProductDB;
import databaseLayer.SaleDB;
import modelLayer.Book;
import modelLayer.Copy;
import modelLayer.Employee;
import modelLayer.Game;
import modelLayer.Product;
import modelLayer.Supplier;

class testGetAllProductInformation {
	
	private ProductDB productDB;
	private SaleDB saleDB;
	private Book book;
	private Game game;
	private Employee employee;
	private ProductCtrl productCtrl;
	private Product product;
	private Copy copy;

	
	// TODO comment
	@Before
	public void setUp() throws Exception {
		productDB = new ProductDB();
		saleDB = new SaleDB();
		productCtrl = new ProductCtrl();
		Supplier bookSupplier = new Supplier(123456789, "Johnny Supplies", "Papi Jakey", "Somewheere 1", "+4560513942",
				"bonkMeDaddy@gmail.com", "I give u book");
		Supplier gameSupplier = new Supplier(987654321, "Supplier Johnny", "daddy Lars", "Kattesundet 27",
				"+4560514859", "plsDontHitMe@gmail.com", "I give u gaem");
		book = new Book("1234", "pretty book", "novel", 14.4, 55.7, 10, "07/11/2020", "Description of pretty book",
				bookSupplier, "ABC123", "Foon <3 ", "heavily erotic fanfic");
		game = new Game("09876", "Tittie", "puzzle", 150.00, 250.50, 1, "21/11/2020", "description of tiddy",
				gameSupplier, "good puzzle");
	}
	
	// TODO comment
	@Test
	public void testReturnAllProducts() {	
		
	//Arrange
	ArrayList<Product> getProductInformation = new ArrayList<Product>();
			
	//Act	
	try {
		getProductInformation.addAll(productDB.getProductInformation());
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
			
	//Assert
	assertEquals("Found list", getProductInformation.size(), 2);
	}
	
	// TODO comment
	@Test
	public void testIncorrectReturnData() {
		//Arrange
		ArrayList<Product> getProductInformation = new ArrayList<Product>();
				
		//Act	
		try {
			getProductInformation.addAll(productDB.getProductInformation());
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
				
		//Assert
		assertEquals("Found list", getProductInformation.size(), 5);
		}	

	// TODO comment
	@After
	public void tearDown() throws Exception 
	{
		
	}
}
