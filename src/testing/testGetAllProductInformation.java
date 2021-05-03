package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import controlLayer.*;
import databaseLayer.*;
import modelLayer.*;

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
	
	
	}
	
	// TODO comment
	@Test
	public void testReturnAllProducts() {	
		
	//Arrange
	ArrayList<Product> getProductInformation = new ArrayList<Product>();
	Product found = null;
			
	//Act	
	try {
		getProductInformation.addAll(productDB.getProductInformation());
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
			
	//Assert
	assertEquals("Found list", product.getCopies(), found);
	}
	
	// TODO comment
	@Test
	public void testIncorrectReturnData() {
		
	//Arrange
	ArrayList<Product> getProductInformation = new ArrayList<Product>();
	Product found = null;	
			
	//Act	
	getProductInformation = productCtrl.getProductInformation();
	for (Product p : getProductInformation) {
		if (p.getBarcode() == product.getBarcode()){
			found = p;
		}
	}				
	//Assert
	assertEquals("Found list", null, found);
	}
	
	// TODO comment
	@Test
	public void testIncompleteList() {
		
	//Arrange
			
			
	//Act	
	
			
	//Assert
	assertEquals();
	}
	
	// TODO comment
	@Test
	public void testNothingReturn() {
		
	//Arrange
			
			
	//Act	
	
			
	//Assert
	assertEquals();
	}

	// TODO comment
	@After
	public void tearDown() throws Exception 
	{
		
	}
}
