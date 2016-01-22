/**
 * The class for finding a link in a file with a specific word.
 * This is done by parsing the webpage and extracting the required links accordingly.
 * @author Asad Zia
 *@Version 1.0
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FindLinks {
	private static String myURL;
	
	/**
	 * The constructor
	 * @param string
	 */
	public FindLinks (String string)
	{
		this.setMyURL(string);
	}
	
	/**
	 * The method which finds the link
	 * @param searchString
	 * @throws MalformedURLException
	 */
	public static void ProcessURL (String searchString) throws MalformedURLException
	{		
		try {
			URL webURL = new URL (myURL);
			URLConnection conn = webURL.openConnection();
			
			/* reading the web page */
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line;
			int n;
			
			while ((line = br.readLine()) != null)
			{
				if ((n = line.lastIndexOf("<a href=\"")) != -1)
				{
					int q = line.indexOf("\">");
					String newline = line.substring(n + 9, q);
					String newline_copy = newline;
					
					if (newline.toLowerCase().indexOf(searchString) != -1)
					{
						System.out.println(newline_copy);
					}
				}
				
				//System.out.println(line);
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * The getter function for myURL
	 * @return myURL
	 */
	public String getMyURL() {
		return myURL;
	}
	
	/**
	 * The setter function for myURL
	 * @param string
	 */
	public void setMyURL(String string) {
		FindLinks.myURL = string;
	}
	
	/**
	 * The main function
	 * @param args
	 * @throws MalformedURLException
	 */
	public static void main (String args []) throws MalformedURLException
	{
		/* find all links with the term youtube in it */
		new FindLinks("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
		FindLinks.ProcessURL("youtube");
	}
}
