/**
 * The class is used to check the words which appear the most in a group of files.
 * @author Asad Zia
 *@Version 1.0
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class WordFiles 
{
	/* the hashmap used to carry all the file and word information */
	private HashMap<String, ArrayList<String>> map;
	
	/**
	 * The constructor.
	 */
	public WordFiles ()
	{
		map = new HashMap<String, ArrayList<String>> ();
	}
	
	/**
	 * A helper method to add words to the map
	 * @param myFile
	 */
	public void addWordsFromFile (File myFile)
	{
		String line = "";
		
		try 
		{
			BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream(myFile)));
			
			try 
			{
				System.out.println(myFile.getName() + " is:\n");
				
				while ((line = data.readLine()) != null)
				{
					System.out.println(line);
					
					String tokens [] = line.split(" ");
					
					for (String token : tokens)
					{
						if (map.containsKey(token))
						{
							map.get(token).add(myFile.getName());
						}
						else
						{
							ArrayList<String> myList = new ArrayList<String> ();
							myList.add(myFile.getName());
							map.put(token, myList);
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
	 * A method to populate the map with the words from the files.
	 */
	public void buildWordFileMap ()
	{
		/* clear map*/
		map.clear();
		
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		int ret = chooser.showOpenDialog(null);
		
		if (ret == JFileChooser.APPROVE_OPTION)
		{			
			File [] inputFiles = chooser.getSelectedFiles();
			
			for (int i = 0; i < inputFiles.length; ++i)
			{
				addWordsFromFile(inputFiles[i]);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "No Files Selected", "Message", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	/**
	 * A method to return the greatest number of times a word appears in all the files selected.
	 * @return
	 */
	public int maxNumber ()
	{
		int max = 0;
		
		for (java.util.Map.Entry<String, ArrayList<String>> x : map.entrySet())
		{
			if (x.getValue().size() > max)
			{
				max = x.getValue().size();
			}
		}
		return max;
	}
	
	/**
	 * A method used to return an arraylist of words which appear the amount of times (number) in all the files selected.
	 * @param number
	 * @return
	 */
	public ArrayList<String> wordsInNumfiles (int number)
	{
		ArrayList<String> result = new ArrayList<String> ();
		
		for (java.util.Map.Entry<String, ArrayList<String>> x : map.entrySet())
		{
			if (x.getValue().size() == number)
			{
				result.add(x.getKey());
			}
		}
		return result;
	}
	
	/**
	 * Prints the files consisting the string in the parameter.
	 * @param name
	 */
	public void printFilesIn (String name)
	{
		if (map.containsKey(name))
		{
			for (int i = 0; i < map.get(name).size(); ++i)			{
				System.out.println(map.get(name).get(i));
			}
			System.out.println();
		}
		else
		{
			System.out.println("\nThe word " + name + " does not exist!");
		}
	}
	
	/**
	 * The driver method for testing the program.
	 * @param args
	 */
	public static void main (String args [])
	{
		WordFiles obj = new WordFiles();
		obj.buildWordFileMap();
		
		int greatest = obj.maxNumber();
		ArrayList<String> myList = obj.wordsInNumfiles(greatest);
		
		System.out.println("\nThe greatest number of files a word appears in is " + greatest + ", and there are " + myList.size() + " such words:\n ");
		
		for (int i = 0; i < myList.size(); ++i)
		{
			System.out.println("'" + myList.get(i) + "'" + " which appears in files:");
			obj.printFilesIn(myList.get(i));
		}
	}
}