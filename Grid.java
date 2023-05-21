import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Grid
{
	private Letter[][] grid;
	private final int LENGTH = 4;
	private HashSet<String> wordList;
	private static final int[] X = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    private static final int[] Y = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    private static Trie TRIE;
	private static final int MAX = 8;
	private static final int MIN = 3;
	private HashMap<String, Path> map;
	
	public Grid() throws FileNotFoundException
	{
		grid = new Letter[LENGTH][LENGTH];
		Scanner s = new Scanner(System.in);
		System.out.println("Enter letters with no spaces.");
		String letters = s.next().toUpperCase();
		int counter = 0;
		for(int r = 0; r < LENGTH; r++)
		{
			for(int c = 0; c < LENGTH; c++)
			{
				Letter letter = new Letter(letters.substring(counter, counter + 1));
				grid[r][c] = letter;
				counter++;
			}
		}
		TRIE = new Trie();
		s.close();
	}
	
	public ArrayList<String> generateList()
	{
		wordList = new HashSet<>();
		map = new HashMap<>();
		for(int r = 0; r < LENGTH; r++)
		{
			for(int c = 0; c < LENGTH; c++)
			{
				Path p = new Path(c + 1, r + 1);
				generateListHelper(1, grid[r][c].getLetter(), r, c, p);
			}
		}
		ArrayList<String> actual = new ArrayList<>(wordList);
		Collections.sort(actual, new LengthComparator());
		return actual;
	}
	
	private void generateListHelper(int length, String word, int r, int c, Path p)
	{
		if(length <= MAX)
		{
			int value = TRIE.checks(word);
			if(length >= MIN)
			{
				if(value == 3 || value == 1)
				{
					wordList.add(word);
					Path path = new Path(p);
					map.put(word, path);
				}
			}
			
			if(value == 3 || value == 2)
			{
				Letter current = grid[r][c];
				current.setVisited(true);
				for(int i = 0; i < X.length; i++)
				{
					int newR = r + X[i];
			    	int newC = c + Y[i];
					if(inBounds(newR, newC) && !grid[newR][newC].isVisited())
					{
						String newWord = word + grid[newR][newC].getLetter();
						p.add(translate(X[i], Y[i]));
						generateListHelper(length + 1, newWord, newR, newC, p);
						p.remove();
					}
				}
				current.setVisited(false);
			}
		}
	}
	
	private boolean inBounds(int r, int c)
	{
		return r >= 0 && c >= 0 && r < LENGTH && c < LENGTH;
	}
	
	private String translate(int r, int c)
	{
		if(r == -1)
		{
			if(c == -1)
			{
				return "NW";
			}
			else if(c == 0)
			{
				return "N";
			}
			else
			{
				return "NE";
			}
		}
		else if(r == 0)
		{
			if(c == -1)
			{
				return "W";
			}
			else
			{
				return "E";
			}
		}
		else
		{
			if(c == -1)
			{
				return "SW";
			}
			else if(c == 0)
			{
				return "S";
			}
			else
			{
				return "SE";
			}
		}
	}
	
	public void cheat()
	{
		ArrayList<String> answers = generateList();
		for(String str : answers)
		{
			System.out.println(
					"Word: " + str +
					" Path: " + map.get(str)
					);
		}
	}
	
	private static class LengthComparator implements Comparator<String>
	{
		public int compare(String s1, String s2) 
		{
			return Integer.compare(s1.length(), s2.length());
		}
		
	}
}
