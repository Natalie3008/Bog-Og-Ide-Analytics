package modelLayer;

/* This class holds information on the Game objects in the 
 * system. A Game is an subclass of Product, meaning it takes 
 * over the constructor as well as the methods that can be 
 * found in the Product class. 
 */

public class Game extends Product {
	
	private String type; //such as board game, card game, puzzle etc.

	public Game(String barcode, String title, double costPrice, double recommendedRetailPrice, int amountInStock,
			String publicationDate, String description, String language, Supplier supplier, String type) {
		super(barcode, title, costPrice, recommendedRetailPrice, amountInStock, publicationDate, description, language, supplier);
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
