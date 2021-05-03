package modelLayer;

import java.util.Date;

public class Copy {
	private String articleNumber;
	private Date dateSold;
	private Date receivedInStore;
	private Product product;

	public Copy(String articleNumber, Date dateSold, Date receivedInStore, Product product) {
		this.articleNumber = articleNumber;
		this.dateSold = dateSold;
		this.receivedInStore = receivedInStore;
		this.product = product;
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

}
