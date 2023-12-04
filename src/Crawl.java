import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Crawl {

  /* Do not change this base URL. All URLs for ths assignment are relative to this address */
   private static String baseURL = "https://www.csd.uwo.ca/faculty/solis/cs9668/test/";
   
   public static void main (String[] args) {
       URL theUrl, u;
       ArrayList<URL> foundURLS = new ArrayList<>();
       HashSet<String> uniQue = new HashSet<>();
       BufferedReader input;
       String s;
       String[] query;   // User specified query
       boolean inFile;   // Set tru if any of the query words is in the document
       int counter = 0;  // Counter for the number of hyperlinks

       try {
           theUrl = new URL(baseURL+"test.html");  // This is the complete URL of the test page
           query = InOut.readQuery();
           System.out.println(Arrays.toString(query));

           input = new BufferedReader(new InputStreamReader(theUrl.openStream())); // Open URL for reading
           inFile = false;

           while ((s = input.readLine()) != null) {  // Read the document specified by theUrl
               u = extractURL(s);
               if (u != null) {
                   foundURLS.add(u);
                   ++counter;
               }
               // Check if any of the query words is in this line of the document
               for (String value : query) {
                   if ((s.toLowerCase()).contains(value.toLowerCase()))
                       uniQue.add(value.toLowerCase());

                   if (uniQue.size() == query.length)
                       inFile = true;
               }
           }
           if (inFile) InOut.printFileName(theUrl); // You MUST use this method to print an URL
           input.close();
           InOut.endListFiles(); // You MUST invoke this method before your program terminates
           System.out.println("Number of hyperlinks found in the page: "+counter);

       } catch (MalformedURLException mue) {
           System.out.println("Malformed URL");
       } catch (IOException ioe) {
           System.out.println("IOException "+ioe.getMessage());
       }
   }
   
   /* If there is an URL embedded in the text passed as parameter, the URL will be extracted and
      returned; if there is no URL in the text, the value null will be returned              */   
   public static URL extractURL(String text) throws MalformedURLException {
   	String textUrl;
      	int index = text.lastIndexOf("a href=");
   	if (index > -1) {
   		textUrl = baseURL+text.substring(index+8,text.length()-2);   // Form the complete URL	
   		return new URL(textUrl);
   	}
   	else return null;
   }
} 


