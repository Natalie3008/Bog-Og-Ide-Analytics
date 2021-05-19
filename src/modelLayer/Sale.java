package modelLayer;

import java.util.ArrayList;
import java.sql.Date;

// Sale class holds information about sales in the system 
public class Sale {
	private int ID;
	private Date date;
	private TargetedCategory targetedCategory; // targeted customer group
	private String paymentMethod;
	private double totalPrice;
	private Employee employee; // employee who did the transaction
	private ArrayList<OrderLine> orderLines; // orderLine class that holds information about what items and how many were sold

	public Sale(int ID, Date date, TargetedCategory targetedCategory, String paymentMethod, double totalPrice, Employee employee) {
		this.ID = ID;
		this.date = date;
		this.targetedCategory = targetedCategory;
		this.paymentMethod = paymentMethod;
		this.totalPrice = totalPrice;
		this.employee = employee;
		orderLines = new ArrayList<OrderLine>();
	}
	
	public Sale (int ID) {
		this.ID = ID;
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

	public TargetedCategory getTargetedCategory() {
		return targetedCategory;
	}

	public void setTargetedCategory(TargetedCategory targetedCategory) {
		this.targetedCategory = targetedCategory;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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

	public boolean addOrderLine(OrderLine orderLine) {
		return orderLines.add(orderLine);
	}

}
