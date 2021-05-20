package modelLayer;

import java.util.ArrayList;
// This holds information about customer target groups, such as specific age or gender categories. 
public class TargetedCategory {
	private int ID; // ID of the Targeted category
	private String title; // name of the category
	private int minimumAge; 
	private int maximumAge; // expected age ranges for the customer category
	private String gender; 
	private String other; //any other important criteria for the TargetedCategory
	private ArrayList<Sale> sales;

	public TargetedCategory(int ID, String title, int minimumAge, int maximumAge, String gender, String other) {
		this.ID = ID;
		this.title = title;
		this.minimumAge = minimumAge;
		this.maximumAge = maximumAge;
		this.gender = gender;
		this.other = other;
		sales = new ArrayList<Sale>();
	}

	public TargetedCategory(int ID) {
		this.ID = ID;
		sales = new ArrayList<Sale>();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMinimumAge() {
		return minimumAge;
	}

	public void setMinimumAge(int minimumAge) {
		this.minimumAge = minimumAge;
	}

	public int getMaximumAge() {
		return maximumAge;
	}

	public void setMaximumAge(int maximumAge) {
		this.maximumAge = maximumAge;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public ArrayList<Sale> getSales() {
		return sales;
	}

	public void setSales(ArrayList<Sale> sales) {
		this.sales = sales;
	}

	public boolean addToSales(Sale sale) {
		return sales.add(sale);
	}
	
	@Override 
	public String toString() {
		return String.format(title.toUpperCase());
	}
	
}