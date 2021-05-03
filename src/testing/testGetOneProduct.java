package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import controlLayer.*;
import databaseLayer.*;
import modelLayer.*;

class testGetOneProduct {

	private ProductDB productDB;
	private SaleDB saleDB;
	private Book book;
	private Game game;
	private Employee employee;
	private ProductCtrl productCtrl;
	private Product product;
	private Copy copy;
	
	
	@Before
	public void setUp() throws Exception 
	{
		productDB = new ProductDB();
		saleDB = new SaleDB();
		book = new Book("title", "1234", 14.4, 55.7, 10,
				"07/11/2020", "Description", Supplier supplier, String ISBN,
				String author, String genre);
		//book = new Book("Title for Book", "1234", 100.00, 175.99, 5, "05/11/2003", "This is a book description", 01/04/2021, 12/04/2021, "ISBN1234", "Author Book", "Book Genre");
		game = new Game("Title", "09876", 150.00, 250.50, 1, "21/11/2020", "description", get.Supplier());
		productCtrl = new ProductCtrl();
		productCtrl.addBook("Title for Book", "1234", 100.00, 175.99, 5, "05/11/2003", "This is a book description", 01/04/2021, 12/04/2021, "ISBN1234", "Author Book", "Book Genre");
		productCtrl.addGame("Game Title", "09876", "345.32", "699.99", 43, "13/01/2018", "Game Description", 11/03/2021, 28/11/2020, "Game type");
		
	}
		
	// TODO comment
	@Test
	public void testRegisteredArrayList()
	{
		//Arrange
		Product foundProduct = null;
		
		//Act	
		foundProduct = productCtrl.getOneProductInformation("1234");
		
		//Assert
		assertEquals("Correct product returned", book.getTitle());
	}
	
	
	// TODO comment
	@Test
	public void testIncorrectData()
	{
		//Arrange
		Product foundProduct = null;
				
		//Act	
		foundProduct = productCtrl.getOneProductInformation("IncorrectBarcode");
		
		//Assert
		assertEquals("Incorrect Product!", book.getTitle(), copy.getDateSold());
	}
	
	// TODO comment
	@Test
	public void testNoProductReturned()
		{
		//Arrange
		Product foundProduct = null;
				
		//Act	
		foundProduct = productCtrl.getOneProductInformation("NoBarcode");
		
		assertEquals("No Item found" , null, foundProduct);	
		}
	
	
	
	// TODO comment
	@After
	public void tearDown() throws Exception 
	{
		productDB.delete("1234");
		productDB.delete("09876");
	}


}
