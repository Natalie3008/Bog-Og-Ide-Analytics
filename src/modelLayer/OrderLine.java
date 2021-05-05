package modelLayer;

// This is Order Line class. It has ID, quantity of sold items and particular copies of items that were sold.

public class OrderLine {
	private Sale sale;
	private int quantity;
	private Product product;

	public OrderLine(Sale sale, int quantity, Product product) {
		this.sale = sale;
		this.quantity = quantity;
		this.product = product;
	}
	
	public OrderLine(Product product) {
		this.product = product;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		sale = sale;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setCopy(Product product) {
		this.product = product;
	}
}