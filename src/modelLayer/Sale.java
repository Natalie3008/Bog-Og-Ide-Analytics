package modelLayer;

import java.util.ArrayList;
import java.util.Date;

public class Sale {
	private int ID;
	private Date date;
	private String ageCategory;
	private String paymentMethod;
	private Employee employee;
	private ArrayList<OrderLine> orderLines;

	public Sale(int ID, Date date, String ageCategory, String paymentMethod, Employee employee) {
		this.ID = ID;
		this.date = date;
		this.ageCategory = ageCategory;
		this.paymentMethod = paymentMethod;
		this.employee = employee;
		orderLines = new ArrayList<OrderLine>();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public void setOrderLines(ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
	public ArrayList<OrderLine> getOrderLines() {
		return orderLines;
	}
	
	public boolean addOrderLine (OrderLine orderLine) {
		return orderLines.add(orderLine);
	}

}
