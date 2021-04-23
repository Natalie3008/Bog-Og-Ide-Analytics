package modelLayer;

import java.util.Date;

public abstract class Product 
{
	private String name;
	private String barcode;
	private double costPrice;
	private double recommendedRetailPrice;
	private int amountInStock;
	private String publicationDate;
	private String description;
	private Date dateSold;
	private Date recievedInStore;
	// falalala
	
	
	
	public Product(String name, double costPrice, double recommendedRetailPrice, int amountInStock, String publicationDate, Date recievedInStore, String description ) {
		
		this.name = name;
		this.costPrice = costPrice;
		this.recommendedRetailPrice = recommendedRetailPrice;
		this.amountInStock = amountInStock;
		this.publicationDate = publicationDate;
		this.recievedInStore = recievedInStore;
		this.description = desciption;
		
		
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getBarcode() {
		return barcode;
	}



	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}



	public double getCostPrice() {
		return costPrice;
	}



	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}



	public double getRecommendedRetailPrice() {
		return recommendedRetailPrice;
	}



	public void setRecommendedRetailPrice(double recommendedRetailPrice) {
		this.recommendedRetailPrice = recommendedRetailPrice;
	}



	public int getAmountInStock() {
		return amountInStock;
	}



	public void setAmountInStock(int amountInStock) {
		this.amountInStock = amountInStock;
	}



	public String getPublicationDate() {
		return publicationDate;
	}



	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Date getDateSold() {
		return dateSold;
	}



	public void setDateSold(Date dateSold) {
		this.dateSold = dateSold;
	}



	public Date getRecievedInStore() {
		return recievedInStore;
	}



	public void setRecievedInStore(Date recievedInStore) {
		this.recievedInStore = recievedInStore;
	}
	
	
	
	

}
