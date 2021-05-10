package modelLayer;

/* The Copy class holds information of individual copies
 * of various Products in the system. These products can
 * be books or games. 
 */
import java.util.Date;

public class Copy {
	private String articleNumber; //unique number for every copy
	private Date dateSold;
	private Date receivedInStore;
	private int daysInStock;
	

	private Product product;

	public Copy(String articleNumber, Date dateSold, Date receivedInStore, int daysInStock, Product product) {
		this.articleNumber = articleNumber;
		this.dateSold = dateSold;
		this.receivedInStore = receivedInStore;
		this.product = product;
		this.daysInStock = daysInStock;
	}

	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	public Date getDateSold() {
		return dateSold;
	}

	public void setDateSold(Date dateSold) {
		this.dateSold = dateSold;
	}

	public Date getReceivedInStore() {
		return receivedInStore;
	}

	public void setReceivedInStore(Date receivedInStore) {
		this.receivedInStore = receivedInStore;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getDaysInStock() {
		return daysInStock;
	}

	public void setDaysInStock(int dateDifference) {
		this.daysInStock = dateDifference;
	}
	
}
