package modelLayer;

import java.util.Date;

public class Copy {
	private String articleNumber;
	private Product product;
	private Date dateSold;
	private Date receivedInStore;

	public Copy(String articleNumber, Product product, Date dateSold, Date receivedInStore) {
		this.articleNumber = articleNumber;
		this.product = product;
		this.dateSold = dateSold;
		this.receivedInStore = receivedInStore;
	}

	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
}
