package modelLayer;

import java.util.Date;

public class Book extends Product
{
	
	private String ISBN;
	private String author;
	private String genre;

	
	
	public Book(String title, String barcode, double costPrice, double recommendedRetailPrice, int amountInStock, String publicationDate, String description, Date receivedInStore, 
			String ISBN, String author, String genre)
	{
		super (title, barcode, costPrice, recommendedRetailPrice, amountInStock, publicationDate, description, receivedInStore);
		this.ISBN = ISBN;
		this.author = author;
		this.genre = genre;
	}

	public String getISBN() 
	{
		return ISBN;
	}


	public void setISBN(String iSBN) 
	{
		ISBN = iSBN;
	}

	public String getAuthor() 
	{
		return author;
	}

	public void setAuthor(String author) 
	{
		this.author = author;
	}

	public String getGenre() 
	{
		return genre;
	}

	public void setGenre(String genre) 
	{
		this.genre = genre;
	}

}