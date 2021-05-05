package modelLayer;

// This is Order Line class. It has ID, quantity of sold items and particular copies of items that were sold.

public class OrderLine {
	private int ID;
	private int quantity;
	private Copy copy;

	public OrderLine(int ID, int quantity, Copy copy) {
		this.ID = ID;
		this.quantity = quantity;
		this.copy = copy;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Copy getCopy() {
		return copy;
	}

	public void setCopy(Copy copy) {
		this.copy = copy;
	}

}
