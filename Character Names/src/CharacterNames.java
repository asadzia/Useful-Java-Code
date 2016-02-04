/**
 * The program for finding the character wigth the most number of dialogues in a shakesparian script.
 * @author Asad Zia
 * @Version 1.0
 * @Reference http://stackoverflow.com/questions/12310978/check-if-string-ends-with-certain-pattern
 * @Reference http://www.vogella.com/tutorials/JavaRegularExpressions/article.html
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* A hashmap can be used to shorten the program and to ameliorate efficiency */
public class CharacterNames 
{
	/* the variable for string the filename */
	private String fileName;
	/* the arraylist for storing the character names */
	private ArrayList<String> charNames;
	/* the arraylist for storing the frequency of the times the character has a dialogue */
	private ArrayList<Integer> charFreqs;
	
	/**
	 * the constructor
	 * @param name
	 */
	public CharacterNames (String name)
	{
		this.fileName = name;
		charNames = new ArrayList<String> ();
		charFreqs = new ArrayList<Integer> ();
	}
	
	/**
	 * the method to find the character name frequencies.
	 */
	public void findCharNames ()
	{
		charNames.clear();
		charFreqs.clear();
		String line = "";
		
		try 
		{
			BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			
			try 
			{
				while ((line = data.readLine()) != null)
				{
					String tokens [] = line.split("[.]\\s");
					//System.out.println(tokens[0]);
					for (String token : tokens)
					{
						/* since the character names are going to be denoted by capital letters */
						/* a plus sign is used to denote that more than one block alphabet is supposed to be considered */
						/* the second expression is needed in case a character has two names */
						/* in java, a double backslash is needed to denote special characters like \\s for whitespace */
						
						if (token.matches("([A-Z]+)") || token.matches("([A-Z]+)\\s([A-Z]+)")) //if (token.endsWith(".")) <--- another method.
						{
							if (charNames.contains(token))
							{
								int index = charNames.indexOf(token);
								int oldVal = charFreqs.get(index);
								charFreqs.set(index, oldVal + 1);
							}
							else
							{
								charNames.add(token);
								charFreqs.add(1);
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
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * the driver method.
	 * @param args
	 */
	public static void main (String args [])
	{
		CharacterNames myObj = new CharacterNames("data/example.txt");
		myObj.findCharNames();

		for (int i = 0; i < myObj.charFreqs.size(); ++i)
		{
			System.out.println(myObj.charNames.get(i) + "  " + myObj.charFreqs.get(i));
		}
		
	}
}
