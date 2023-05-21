import java.util.ArrayList;

public class Path 
{
	private ArrayList<String> directions;
	private int r;
	private int c;
	
	public Path(int r, int c)
	{
		this.r = r;
		this.c = c;
		directions = new ArrayList<>();
	}
	
	public Path(Path p)
	{
		this.r = p.r;
		this.c = p.c;
		directions = new ArrayList<>();
		for(String str : p.directions)
		{
			add(str);
		}
	}
	
	public void add(String str)
	{
		directions.add(str);
	}
	
	public String remove()
	{
		return directions.remove(directions.size() - 1);
	}
	
	@Override
	public String toString()
	{
		String path = "(" + r + ", " + c + ") ";
		for(String str : directions)
		{
			path+= str + " ";
		}
		
		return path;
	}
}
