/**
 * A class which is used to transform words from a file into another form.
 * @author Asad Zia
 * @Vrsion 1.0
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WordPlay {
	private static String fileName;
	
	/**
	 * The constructor
	 * @param name
	 */
	public WordPlay (String name)
	{
		this.setFileName(name);
	}
	
	/**
	 * Amethod to check if a character is a vowel or not.
	 * @param ch
	 * @return bool
	 */
	public static boolean isVowel (char ch)
	{
		ch = Character.toLowerCase(ch);
		
		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * A method which replaces the vowels in a phrase with the character ch..
	 * @param ch
	 * @return
	 * @throws IOException 
	 */
	public void replaceVowels (char ch) throws IOException
	{
		@SuppressWarnings("resource")
		BufferedReader phrase = new BufferedReader(new FileReader(new File(fileName)));
		String line = "";
		
		/**
		 * TODO This is currently running in quadratic time. 
		 * Use a technique similar to the emphasize method to ameliorate efficieny.
		 */
		while ((line = phrase.readLine()) != null)
		{
			for (char x : line.toCharArray())
			{
				if (isVowel(x))
				{
					line = line.replace(x, ch);
				}
			}
			System.out.println(line);
		}
	}
	
	/**
	 * A method which replaces a character ch with a '+' at an even index
	 * and replaces the character ch with a '*' at an odd index.
	 * @param ch
	 * @throws IOException
	 */
	public void emphasize (char ch) throws IOException
	{
		String line = "";
		int count = 0;
		char [] myline = null;
		
		line = new String(Files.readAllBytes(Paths.get(fileName)));
		
		myline = line.toCharArray();
		
		for (char x : line.toCharArray())
		{
			if (Character.toLowerCase(x) == ch)
			{
				if (count % 2 == 0)
				{
					myline[count] = '+';
				}
				else
				{
					myline[count] = '*';
				}
			}
			count++;
		}
		
		System.out.println(myline);
	}
	
	/**
	 * The getter method
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * The setter method
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		WordPlay.fileName = fileName;
	}
	
	/**
	 * The driver method
	 * @param args
	 * @throws IOException 
	 */
	public static void main (String args []) throws IOException
	{
		WordPlay obj = new WordPlay ("The Raven.txt");
		obj.emphasize('a');	
	}
	
}