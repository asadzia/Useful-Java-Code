/**
 * The class for processing various CSV files
 * @author Asad Zia
 * @Version 1.0
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class CSVProcessing implements CSVConstants {
	private CSVParser myParser;
	private String fileName;
	
	/**
	 * The constructor
	 * @param myFile
	 */
	public CSVProcessing (String myFile)
	{
		this.fileName = myFile;
		setMyParser(new CSVParser(fileName));
	}
	
	/**
	 * The method for parsing the CSV to return information about a country.
	 * @param country
	 * @return String
	 */
	public String countryInfo (String country)
	{
		String line = "";
		BufferedReader myData = myParser.ParseFile();
		
		try {
			while ((line = myData.readLine()) != null)
			{
				String tokens [] = line.split(DELIMITER);
				String exports = "";
				String value = "";
				
				if (tokens[0].equals(country))
				{
					for (String token : tokens)
					{
						/* NOTE: When comparing double quotes as characters, compare directly. 
						 * In case of strings, compare by using the "\"" notation */
						char start = token.charAt(0);
						char end = token.charAt(token.length() - 1);
						
						/* processing CSV for the export values */
						if (start == '"' && (token.charAt(1) == '$') || end != '"' && Character.isLetter(token.charAt(1)) == false)
						{
							value = value.concat(token).concat(",");
						}
						else if (end == '"' && Character.isLetter(token.charAt(1)) == false)
						{
							/* in order to prevent adding an extra comma at the end of the result */
							value = value.concat(token);
						}
						
						/* processing CSV file for the export items*/
						else if (start == '"' && Character.isLetter(token.charAt(1)))
						{
							/* need to assign the exports variable because strings are immutable in java */
							exports = exports.concat(token).concat(", ");
						}
						else if (Character.isLetter(token.charAt(1)) && end == '"')
						{
							/* in order to prevent adding an extra comma at the end of the result */
							exports = exports.concat(token);
						}
						else if (token.equals(tokens[0]) == false && end != '"' && Character.isLetter(token.charAt(1)))
						{
							/* need to assign the exports variable because strings are immutable in java */
							exports = exports.concat(token).concat(", ");
						}
					}
					/* CSV files have data wrapped inside double quotes in their fields */
					/* To make the output clean, those extra double quotes are removed as follows */
					exports = exports.replace("\"", "");
					value = value.replace("\"", "");
					return (country + ": " + exports + ": " + value);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				/* after closing the BufferedReader, all reference is lost so an IOException has to be raised in case a read call is used */
				myData.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/* In case the country is not found in the CSV file */
		return "COUNTRY NOT FOUND";
	}
	
	/**
	 * The method for checking if a country exist with two particular export items.
	 * @param itemOne
	 * @param itemTwo
	 * @return String
	 */
	public String listExportersTwoProducts (String itemOne, String itemTwo)
	{
		String line = "";
		String result = "";
		String exports = "";
		BufferedReader myData = myParser.ParseFile();
		int flag = 0;

			try {
				while ((line = myData.readLine()) != null)
				{
					String tokens [] = line.split(DELIMITER);
					
					for (String token : tokens)
					{
						/* NOTE: When comparing double quotes as characters, compare directly. 
						 * In case of strings, compare by using the "\"" notation */
						char start = token.charAt(0);
						char end = token.charAt(token.length() - 1);
						
						/* processing CSV file for the export items*/
						if (start == '"' && Character.isLetter(token.charAt(1)))
						{
							/* need to assign the exports variable because strings are immutable in java */
							exports = exports.concat(token).concat(",");
						}
						else if (Character.isLetter(token.charAt(1)) && end == '"')
						{
							/* in order to prevent adding an extra comma at the end of the result */
							exports = exports.concat(token);
						}
						else if (token.equals(tokens[0]) == false && end != '"' && Character.isLetter(token.charAt(1)))
						{
							/* need to assign the exports variable because strings are immutable in java */
							exports = exports.concat(token).concat(",");
						}
					}
					/* CSV files have data wrapped inside double quotes in their fields */
					/* To make the output clean, those extra double quotes are removed as follows */
					exports = exports.replace("\"", "");
					
					/* replace the whitespace so that there is no problem in processing queries for export items
					 * If there are whiteSpaces there would be problems in searching for the export items. */
					exports = exports.replace(" ", "");

					String export_goods [] = exports.split(DELIMITER);
					
					/* convert the String array into a list to see if the export items are contained in the list of exports
					 * for a particular country.
					 */
					if (Arrays.asList(export_goods).contains(itemOne) && Arrays.asList(export_goods).contains(itemTwo))
					{
						result = result.concat(tokens[0]).concat("\n");
						flag = 1;
					}
					/* reset the value of the exports variable to prcoess the next line in the CSV file */
					exports = "";
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			if (flag == 1)
			{
				return result;
			}
			
			return "EXPORTS NOT FOUND";
	}
	
	/**
	 * The method which returns the number of countries which export a specific item.
	 * @param itemName
	 * @return result
	 */
	public int numberOfExporters (String itemName)
	{
		String line = "";
		int result = 0;
		String exports = "";
		BufferedReader myData = myParser.ParseFile();
		int flag = 0;
		
		try {
			while ((line = myData.readLine()) != null)
			{
				String tokens [] = line.split(DELIMITER);
				
				for (String token : tokens)
				{
					/* NOTE: When comparing double quotes as characters, compare directly. 
					 * In case of strings, compare by using the "\"" notation */
					char start = token.charAt(0);
					char end = token.charAt(token.length() - 1);
					
					/* processing CSV file for the export items*/
					if (start == '"' && Character.isLetter(token.charAt(1)))
					{
						/* need to assign the exports variable because strings are immutable in java */
						exports = exports.concat(token).concat(",");
					}
					else if (Character.isLetter(token.charAt(1)) && end == '"')
					{
						/* in order to prevent adding an extra comma at the end of the result */
						exports = exports.concat(token);
					}
					else if (token.equals(tokens[0]) == false && end != '"' && Character.isLetter(token.charAt(1)))
					{
						/* need to assign the exports variable because strings are immutable in java */
						exports = exports.concat(token).concat(",");
					}
				}
				/* CSV files have data wrapped inside double quotes in their fields */
				/* To make the output clean, those extra double quotes are removed as follows */
				exports = exports.replace("\"", "");
				
				/* replace the whitespace so that there is no problem in processing queries for export items
				 * If there are whiteSpaces there would be problems in searching for the export items. */
				exports = exports.replace(" ", "");

				String export_goods [] = exports.split(DELIMITER);
				
				/* convert the String array into a list to see if the export items are contained in the list of exports
				 * for a particular country.
				 */
				if (Arrays.asList(export_goods).contains(itemName))
				{
					++result;
					flag = 1;
				}
				/* reset the value of the exports variable to prcoess the next line in the CSV file */
				exports = "";
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		if (flag == 1)
		{
			return result;
		}
		return 0;
	}
	
	public String bigExporters (String amount)
	{
		String line = "";
		String result = "";
		String value = "";
		BufferedReader myData = myParser.ParseFile();
		int flag = 0;
		
		String val_noDollarA = amount.replace("$", "");
		String val_intValA = val_noDollarA.replace(",", "");

			try {
				while ((line = myData.readLine()) != null)
				{
					String tokens [] = line.split(DELIMITER);
					
					for (String token : tokens)
					{
						/* NOTE: When comparing double quotes as characters, compare directly. 
						 * In case of strings, compare by using the "\"" notation */
						char start = token.charAt(0);
						char end = token.charAt(token.length() - 1);
						
						/* processing CSV for the export values */
						if (start == '"' && (token.charAt(1) == '$') || end != '"' && Character.isLetter(token.charAt(1)) == false)
						{
							value = value.concat(token).concat(",");
						}
						else if (end == '"' && Character.isLetter(token.charAt(1)) == false)
						{
							/* in order to prevent adding an extra comma at the end of the result */
							value = value.concat(token);
						}
					}
					
					value = value.replace("\"", "");
					String val_noDollar = value.replace("$", "");
					String val_intVal = val_noDollar.replace(",", "");
					
					if (value != "")
					{
						if (Long.parseLong(val_intVal) > Long.parseLong(val_intValA))
						{
							result = result.concat(tokens[0] + " " + value + "\n");
							flag = 1;
						}
					}
					value = "";
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			if (flag == 1)
			{
				return result;
			}
			
		return "NOT FOUND";
	}
	
	/**
	 * The getter for myParser
	 * @return myParser
	 */
	public CSVParser getMyParser() {
		return myParser;
	}
	
	/**
	 * The setter for myParser
	 * @param myParser
	 */
	public void setMyParser(CSVParser myParser) {
		this.myParser = myParser;
	}
	
	/**
	 * The driver method
	 * @param args
	 */
	public static void main (String args [])
	{
		CSVProcessing myCSV = new CSVProcessing("exports/exports_small.csv");
		System.out.println(myCSV.countryInfo("Germany"));
		System.out.println(myCSV.listExportersTwoProducts("gold", "diamonds"));
		System.out.println(myCSV.numberOfExporters("gold"));
		System.out.print(myCSV.bigExporters("$999,999,999"));
	}
}
