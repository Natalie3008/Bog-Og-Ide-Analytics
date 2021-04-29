package modelLayer;

public class BookCopy {
	private String articleNumber;
	private Book book;

	public BookCopy(String articleNumber, Book book) {
		this.articleNumber = articleNumber;
		this.book = book;
	}

	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
