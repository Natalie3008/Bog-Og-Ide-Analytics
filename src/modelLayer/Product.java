package modelLayer;

import java.util.Date;

public abstract class Product 
{
	private String title;
	private String barcode;
	private double costPrice;
	private double recommendedRetailPrice;
	private int amountInStock;
	private String publicationDate;
	private String description;
	private Date dateSold;
	private Date recievedInStore;
	private Supplier supplier;
	
	
	public Product(String title, String barcode, double costPrice, double recommendedRetailPrice, int amountInStock, String publicationDate, String description, Date recievedInStore, Supplier supplier) 

	{	
		this.title = title;
		this.costPrice = costPrice;
		this.recommendedRetailPrice = recommendedRetailPrice;
		this.amountInStock = amountInStock;
		this.publicationDate = publicationDate;
		this.description = description;		
		this.recievedInStore = recievedInStore;
		this.supplier = supplier;
		// something so it wil let me commit aaaa
	}

	public Supplier getSupplier()
	{
		return supplier;
	}

	public void setSupplier(Supplier supplier) 
	{
		this.supplier = supplier;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getBarcode() 
	{
		return barcode;
	}

	public void setBarcode(String barcode) 
	{
		this.barcode = barcode;
	}

	public double getCostPrice() 
	{
		return costPrice;
	}

	public void setCostPrice(double costPrice) 
	{
		this.costPrice = costPrice;
	}

	public double getRecommendedRetailPrice() 
	{
		return recommendedRetailPrice;
	}

	public void setRecommendedRetailPrice(double recommendedRetailPrice) 
	{
		this.recommendedRetailPrice = recommendedRetailPrice;
	}

	public int getAmountInStock() 
	{
		return amountInStock;
	}

	public void setAmountInStock(int amountInStock) 
	{
		this.amountInStock = amountInStock;
	}

	public String getPublicationDate() 
	{
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) 
	{
		this.publicationDate = publicationDate;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public Date getDateSold() 
	{
		return dateSold;
	}

	public void setDateSold(Date dateSold) 
	{
		this.dateSold = dateSold;
	}

	public Date getRecievedInStore() 
	{
		return recievedInStore;
	}

	public void setRecievedInStore(Date recievedInStore) 
	{
		this.recievedInStore = recievedInStore;
	}

}
