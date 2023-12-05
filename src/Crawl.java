import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Crawl {

  /* Do not change this base URL. All URLs for ths assignment are relative to this address */
   private static String baseURL = "https://www.csd.uwo.ca/faculty/solis/cs9668/test/";
   
   public static void main (String[] args) {
       URL theUrl;
       HashSet<String> foundURLS = new HashSet<>(), checkedURLS = new HashSet<>(), difURLS = new HashSet<>(), uniQue = new HashSet<>();
       HashSet<URL>printURLS = new HashSet<>();
       BufferedReader input;
       String s, u, theMod;
       String[] query;   // User specified query

       try {
           foundURLS.clear();
           checkedURLS.clear();
           foundURLS.add("test.html");
           query = InOut.readQuery();

           while (foundURLS.size() > checkedURLS.size()) {//loop through all new docs
               uniQue.clear();
               difURLS.addAll(foundURLS);
               difURLS.removeAll(checkedURLS);

               theMod = difURLS.stream().findFirst().get();
               theUrl = new URL(baseURL + theMod);
               checkedURLS.add(theMod);

               input = new BufferedReader(new InputStreamReader(theUrl.openStream()));

               while ((s = input.readLine()) != null) { //read current doc
                   u = extractURL(s);
                   if (u != null) foundURLS.add(u);

                   for (String value : query) {
                       if ((s.toLowerCase()).contains(value.toLowerCase()))
                           uniQue.add(value.toLowerCase());

                       if (uniQue.size() == query.length)
                           printURLS.add(theUrl);
                   }
               }

               input.close();
           }
           for (URL e : printURLS) InOut.printFileName(e);
           InOut.endListFiles();
           System.out.println("Number of documents: " + (foundURLS.size()-1));
           System.out.println("\n----------------------------------------------------------------------------");

       } catch (MalformedURLException mue) {
           System.out.println("Malformed URL");
       } catch (IOException ioe) {
           System.out.println("IOException "+ioe.getMessage());
       }
   }
   
   /* If there is an URL embedded in the text passed as parameter, the URL will be extracted and
      returned; if there is no URL in the text, the value null will be returned              */   
   public static String extractURL(String text) {
       String textUrl;
       int index = text.lastIndexOf("a href=");
       if (index > -1) {
           textUrl = text.substring(index+8,text.length()-2);
           return textUrl;
       }
       else return null;
   }
} 


