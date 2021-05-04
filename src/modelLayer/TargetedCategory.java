package modelLayer;

import java.util.ArrayList;

public class TargetedCategory {
	private int ID;
	private int minimumAge;
	private int maximumAge;
	private String gender;
	private String other;
	private ArrayList<Sale> sales;

	public TargetedCategory(int ID, int minimumAge, int maximumAge, String gender, String other,
			ArrayList<Sale> sales) {
		this.ID = ID;
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
}
