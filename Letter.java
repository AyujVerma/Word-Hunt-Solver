
public class Letter 
{
	private String letter;
	boolean visited;
	
	public Letter(String letter)
	{
		this.letter = letter;
		visited = false;
	}

	public String getLetter() 
	{
		return letter;
	}

	public boolean isVisited() 
	{
		return visited;
	}

	public void setVisited(boolean visited) 
	{
		this.visited = visited;
	}
}
