package modelLayer;

public class GameCopy 
{
	private String articleNumber;
	private Game game;
	
	public GameCopy(String articleNumber, Game game)
	{
		this.articleNumber = articleNumber;
		this.game = game;
	}

	public String getArticleNumber() 
	{
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) 
	{
		this.articleNumber = articleNumber;
	}

	public Game getGame() 
	{
		return game;
	}

	public void setGame(Game game) 
	{
		this.game = game;
	}

}
