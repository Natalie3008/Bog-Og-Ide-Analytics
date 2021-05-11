package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modelLayer.*;
import controlLayer.*;

public class testGetOneProduct {
	private Book book;
	private Game game;
	private ProductCtrl productCtrl;

	@Before
	public void setUp() throws Exception {
		productCtrl = new ProductCtrl();
		Supplier bookSupplier = new Supplier(123456789, "Johnny Supplies", "Martin Smile", "Vesterbro 27",
				"+45605139782", "smileyMartey@gmail.com", "I give u book");
		Supplier gameSupplier = new Supplier(987654321, "Supplier Johnny", "Bob Smile", "Vesterbro 27", "+4560514859",
				"bobIsInLove@gmail.com", "I give u game");
		book = new Book("1234", "Spork", 14.4, 55.7, 10, "07/11/2020", "Description of pretty book", "English",
				bookSupplier, "ABC123", "Foon ", "novel");
		game = new Game("9876", "Exploding puppies", 150.00, 250.50, 1, "21/11/2020", "description of pretty game",
				"English", gameSupplier, "puzzle");
	}

	// expected to run smoothly
	@Test
	public void testCorrectProductReturned() {
		// Arrange
		Product foundProduct = null;

		// Act
		foundProduct = productCtrl.getOneProductInformation("1234"); // without CRUD, this will return nothing, will
																		// come back to it after CRUDs are done

		// Assert
		assertEquals("Correct product returned", foundProduct.getTitle(), "pretty book");
	}

	// expected to fail
	@Test
	public void testIncorrectData() {
		// Arrange
		Product foundProduct = null;

		// Act
		foundProduct = productCtrl.getOneProductInformation("1234");

		// Assert
		assertEquals("Incorrect Product!", foundProduct.getTitle(), "ugly book");
	}

	// expected to be good
	@Test
	public void testNoProductReturned() {
		// Arrange
		Product foundProduct = null;

		// Act
		foundProduct = productCtrl.getOneProductInformation(null);

		assertEquals("No Item found", foundProduct, null);
	}

	// fuk u useless fuk (for now at least)
	@After
	public void tearDown() throws Exception {
	}

}
