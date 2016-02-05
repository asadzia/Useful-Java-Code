/**
 * The class for counting the codons from a DNA string in a file.
 * @author Asad Zia
 * @Version 1.0
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

public class CountCodon 
{
	/* the variable for storing the count for each codon */
	private HashMap<String, Integer> map;
	/* the file path */
	private String fileName;
	
	/**
	 * the constructor
	 * @param name
	 */
	public CountCodon (String name)
	{
		this.fileName = name;
		map = new HashMap<String, Integer> ();
	}
	
	/**
	 * The method for returning the string with the most count.
	 * @return max_string
	 */
	public String getMostCommonCodon ()
	{
		int max = Integer.MIN_VALUE;
		String max_string = "";
		
		for (Entry<String, Integer> x : map.entrySet())
		{
			if (x.getValue() > max)
			{
				max = x.getValue();
				max_string = x.getKey();
			}
		}
		
		return max_string;
	}
	
	/**
	 * The method for printing the output
	 * @param start
	 */
	public void printCodonCounts (int start)
	{
		System.out.println("Reading frame starting with " + start + " results in " + map.size() + " unique codons\nand the most common codon is " + getMostCommonCodon() + " with count " + map.get(getMostCommonCodon()));
		System.out.println("Counts of codon between 1 and 5 inclusive are:\n");
		
		for (Entry<String, Integer> x : map.entrySet())
		{
			System.out.println(x.getValue() + "  " + x.getKey());
		}
		System.out.println();
	}
	
	/**
	 * The method for building the hashmap according the the values read from the DNA strand.
	 * @param start
	 */
	public void buildCodonMap (int start)
	{
		/* since the buildCodon method will be called multiple times for different start points 
		 * it has to be ensured that the map is empty for different start points to avoid erroneous results.
		 */
		map.clear();
		
		String line = "";
		
		try
		{
			BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			
			try 
			{
				while ((line = data.readLine()) != null)
				{
					String myCodon = line;
					
					/*
					 * The following three lines of code ensures that we start from the specified start point.
					 * After that the DNA processing for finding a codon is the same as shown in the while loop.
					 */
					String newCodon = myCodon.substring(start, start + 3);
					myCodon = myCodon.substring(start + 3);
					map.put(newCodon, 1);
					
					while (true)
					{
						/* since division does not support decimal values,
						 * if the codon length is less than 3, we will get 0
						 */
						if (myCodon.length() / 3 > 0)
						{
							newCodon = myCodon.substring(0, 3);

							myCodon = myCodon.substring(3);

							if (map.containsKey(newCodon))
							{
								map.put(newCodon, map.get(newCodon) + 1);
							}
							else
							{
								map.put(newCodon, 1);
							}
						}
						else
						{
							break;
						}
					}
					printCodonCounts(start);
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
	 * The driver method.
	 * @param args
	 */
	public static void main (String args [])
	{
		CountCodon obj = new CountCodon("data/smalldna.txt");
		obj.buildCodonMap(0);
		obj.buildCodonMap(1);
		obj.buildCodonMap(2);
	}
}