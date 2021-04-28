package modelLayer;

import java.util.Date;

public class Sale 
{
	private int ID;
	private double amount;
	private Date date;
	private String ageCategory;
	private String paymentMethod;
	private Employee employee;
	
	public Sale(int ID, double amount, Date date, String ageCategory, String paymentMethod, Employee employee)
	{
		this.ID = ID;
		this.amount = amount;
		this.date = date;
		this.ageCategory = ageCategory;
		this.paymentMethod = paymentMethod;
		this.employee = employee;
	}

	public int getID() {
		return ID;
		}

	public void setID(int iD) {
		ID = iD;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(String ageCategory) {
		this.ageCategory = ageCategory;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) 
	{
		this.employee = employee;
	}


}
