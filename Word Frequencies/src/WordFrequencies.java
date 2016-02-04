/**
 * The class for finding the word frequencies in a file.
 * @author Asad Zia
 * @Version 1.0
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* A hashmap can be used to shorten the program and to ameliorate efficiency */
public class WordFrequencies implements Constants {
	/* stores the unique words from a file */
	private ArrayList <String> myWords;
	/* stores the frequencies of the words from the file */
	private ArrayList <Integer> myFreqs;
	/* stores the file path */
	private String fileName;
	
	/**
	 * The constructor for the class
	 */
	public WordFrequencies (String name) 
	{
		myWords = new ArrayList<String> ();
		myFreqs = new ArrayList<Integer> ();
		this.fileName = name;
	}
	
	/**
	 * The function for finding the unique words and counting them.
	 */
	public void UniqueWords ()
	{
		myWords.clear();
		myFreqs.clear();
		String read = "";
		
		try
		{
			@SuppressWarnings("resource")
			BufferedReader line = new BufferedReader(new InputStreamReader(new FileInputStream((fileName))));
			
			while ((read = line.readLine()) != null)
			{
				String tokens[] = read.split(DELIMITER);
				
				for (String word : tokens)
				{
					/* there might be empty lines in the text file */
					if (word.equals("") || word.matches(" ") || word.matches("."))
					{
						continue;
					}
					else
					{
						if (myWords.contains(word))
						{
							int index = myWords.indexOf(word);
							myFreqs.set(index, myFreqs.get(index) + 1);
						}
						else
						{
							myWords.add(word);
							myFreqs.add(1);
						}
					}
				}
 			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * the method for finding the index of most occuring word.
	 * @return
	 */
	public int findIndexofMax ()
	{
		/* for this one can simply use Collections.max(myFreqs); */
		/* But here's an actual implementation of Collections.max() ;) */
		
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < myFreqs.size(); ++i)
		{
			if (max < myFreqs.get(i))
			{
				max = myFreqs.get(i);
			}
		}
		return myFreqs.indexOf(max);
	}
	
	/**
	 * The driver method
	 * @param args
	 */
	public static void main (String args[])
	{
		WordFrequencies cc = new WordFrequencies("Data/Example.txt");
		cc.UniqueWords();
		System.out.println("Number of unique words: " + cc.myWords.size());
		System.out.println();
		
		for (int i = 0; i < cc.myFreqs.size(); ++i)
		{
			System.out.println(cc.myFreqs.get(i) + " " + cc.myWords.get(i));
		}
		
		int max = cc.findIndexofMax();
		
		System.out.println("\nThe Word that occurs most often is " + "'" + cc.myWords.get(max) + "'" + " with count " + cc.myFreqs.get(max));
	}
}
