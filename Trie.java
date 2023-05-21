import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trie 
{
	private final static int ALPHABET = 26;
	private static Node root;
	
	public Trie() throws FileNotFoundException
	{
		root = new Node();
		File file = new File("C:\\Users\\ayujv\\eclipse-workspace\\WordHuntSolver\\dictionary.txt");
		Scanner s = new Scanner(file);
		while(s.hasNextLine())
		{
			String word = s.nextLine();
			if(word.length() > 2)
			{
				add(word);
			}
		}
		s.close();
	}
	
	public void add(String word)
	{
		word = word.toLowerCase();
		int length = word.length();
		Node node = root;
		for(int depth = 0; depth < length; depth++)
		{
			int index = word.charAt(depth) - 'a';
			if(node.childeren[index] == null)
			{
				node.childeren[index] = new Node();
			}
			node = node.childeren[index];
		}
		
		node.end = true;
	}
	
	public int checks(String word)
	{
		boolean hasMore = false;
		word = word.toLowerCase();
		int length = word.length();
		Node node = root;
		for(int depth = 0; depth < length; depth++)
		{
			int index = word.charAt(depth) - 'a';
			Node child = node.childeren[index];
			if(child == null)
			{
				return 0;
			}
			node = child;
		}
		
		boolean end = node.end;
		for(int i = 0; i < ALPHABET && !hasMore; i++)
		{
			if(node.childeren[i] != null)
			{
				hasMore = true;
			}
		}
		
		if(hasMore && end)
		{
			return 3;
		}
		else if(hasMore)
		{
			return 2;
		}
		else
		{
			return 1;
		}
	}
	
	private class Node
	{
		private Node[] childeren;
		private boolean end;
		private Node()
		{
			childeren = new Node[ALPHABET];
			end = false;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		Trie trie = new Trie();
		trie.add("ANT");
		trie.add("AN");
		trie.add("ANT");
		trie.add("ANTS");
		System.out.println(trie.checks("AN"));
	}
}
