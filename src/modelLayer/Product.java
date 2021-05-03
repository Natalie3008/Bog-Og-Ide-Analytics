package modelLayer;

import java.util.ArrayList;

public abstract class Product {
	private String barcode;
	private String title;
	private double costPrice;
	private double recommendedRetailPrice;
	private int amountInStock;
	private String publicationDate;
	private String description;
	private Supplier supplier;
	private ArrayList<Copy> copies;

	public Product(String barcode, String title, double costPrice, double recommendedRetailPrice, int amountInStock,
			String publicationDate, String description, Supplier supplier)

	{
		this.barcode = barcode;
		this.title = title;
		this.costPrice = costPrice;
		this.recommendedRetailPrice = recommendedRetailPrice;
		this.amountInStock = amountInStock;
		this.publicationDate = publicationDate;
		this.description = description;
		this.supplier = supplier;
		copies = new ArrayList<Copy>();
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public boolean addCopy(Copy copy) {
		return copies.add(copy);
	}

	public ArrayList<Copy> getCopies() {
		return copies;
	}

	public void setCopies(ArrayList<Copy> copies) {
		this.copies = copies;
	}

}
