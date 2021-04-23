package modelLayer;

public class OrderLine 
{
	private int ID;
	private int quantity;
	private BookCopy bookCopy;
	private GameCopy gameCopy;
	
	public OrderLine(int ID, int quantity, BookCopy bookCopy, GameCopy gameCopy)
	{
		this.ID = ID;
		this.quantity = quantity;
		this.bookCopy = bookCopy;
		this.gameCopy = gameCopy;
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

	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}

	public GameCopy getGameCopy() {
		return gameCopy;
	}

	public void setGameCopy(GameCopy gameCopy) {
		this.gameCopy = gameCopy;
	}
	
}
