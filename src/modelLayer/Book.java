package modelLayer;

/* This class holds information on the Book objects in the 
 * system. A Book is an subclass of Product, meaning it takes 
 * over the constructor as well as the methods that can be 
 * found in the Product class. 
 */
public class Book extends Product {

	private String ISBN;
	private String author;
	private String genre;

	public Book(String barcode, String title, double costPrice, double recommendedRetailPrice, int amountInStock,
			String publicationDate, String description, String language, Supplier supplier, String ISBN, String author, String genre) {
		super(barcode, title, costPrice, recommendedRetailPrice, amountInStock, publicationDate, description, language, supplier);
		this.ISBN = ISBN;
		this.author = author;
		this.genre = genre;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}