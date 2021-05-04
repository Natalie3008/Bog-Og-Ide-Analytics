package modelLayer;

import java.util.ArrayList;
import java.util.Date;

public class Sale {
	private int ID;
	private Date date;
	private TargetedCategory targetedCategory;
	private String paymentMethod;
	private double totalPrice;
	private Employee employee;
	private ArrayList<OrderLine> orderLines;

	public Sale(int ID, Date date, TargetedCategory targetedCategory, String paymentMethod, double totalPrice, Employee employee) {
		this.ID = ID;
		this.date = date;
		this.targetedCategory = targetedCategory;
		this.paymentMethod = paymentMethod;
		this.totalPrice = totalPrice;
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

	public TargetedCategory getAgeCategory() {
		return targetedCategory;
	}

	public void setAgeCategory(TargetedCategory targetedCategory) {
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
