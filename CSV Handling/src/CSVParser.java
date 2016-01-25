/**
 * The class for parsing the CSV file
 * @author Asad Zia
 * @Version 1.0
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CSVParser {
	private String FileName;
	
	/**
	 * The constructor
	 * @param myFile
	 */
	public CSVParser (String myFile)
	{
		this.FileName = myFile;
	}
	
	/**
	 * The method for parsing the file
	 * @return BufferedReader
	 */
	public BufferedReader ParseFile ()
	{
		try 
		{
			File myFile = new File(FileName);
			BufferedReader FileParser = new BufferedReader(new FileReader(myFile));
			return FileParser;
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * The getter for filename
	 * @return FileName
	 */
	public String getFileName() {
		return FileName;
	}
	
	/**
	 * The setter for filename
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		FileName = fileName;
	}
}