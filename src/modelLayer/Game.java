package modelLayer;

import java.util.Date;

public class Game extends Product
{	
	private String type;
	
	public Game(String title, String barcode, double costPrice, double recommendedRetailPrice, int amountInStock, String publicationDate, String description, Date receivedInStore, String type)
	{
		super(title, barcode, costPrice, recommendedRetailPrice, amountInStock, publicationDate, description, receivedInStore);
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}
}
