/**
 * A class for testing the program.
 * 
 * @author Asad Zia
 * @version 1.0
 */


import java.util.*;

public class Tester
{
	/**
	 * Testing the LogEntry class.
	 */
    public static void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    /**
     * Testing the LogAnalyzer class.
     */
    public static void testLogAnalyzer() {
        LogAnalyzer obj = new LogAnalyzer();
        obj.readFile("data/short-test_log");
        obj.printAll();
    }
    
    /**
     * The driver method.
     * @param args
     */
    public static void main (String args [])
    {
    	testLogAnalyzer();
    }
}
