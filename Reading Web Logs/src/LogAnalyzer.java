/**
 * A class for analyzing the log file.
 * 
 * @author Asad Zia
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LogAnalyzer
{
	/* the variable for holding the log entries */
     private ArrayList<LogEntry> records;
     
     /**
      * the constructor
      */
     public LogAnalyzer() 
     {
         records = new ArrayList<LogEntry> ();
     }
        
     /**
      * the method for reading the file and parsing the log entries.
      * @param filename
      */
     public void readFile(String filename) 
     {
    	 WebLogParser parser = new WebLogParser();
    	 
		try 
		{
			BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			
			 String line = "";
	    	 
	    	 try 
	    	 {
				while ((line = data.readLine()) != null)
				 {
				 	LogEntry myEntry = parser.parseEntry(line);
				 	records.add(myEntry);
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
     * A method for counting the visits to a website for each IP.
     */
     public void countVisitsPerIP ()
     {
    	 for (int i = 0; i < records.size(); ++i)
    	 {
			 String IP = records.get(i).getIpAddress();
			 
    		 if (map.containsKey(IP))
    		 {
    			 map.put(IP, map.get(IP) + 1);
    		 }
    		 else
    		 {
    			 map.put(IP, 1);
    		 }
    	 }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
