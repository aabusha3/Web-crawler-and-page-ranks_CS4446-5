import java.util.Scanner;
import java.util.StringTokenizer;
import java.net.*;

public class InOut {

	private static int counter = 0;
	public static String[] readQuery() {
		String words = "";
		while (words == null || words.length() == 0) {
			Scanner keyboard = new Scanner(System.in);
			System.out.print(" Enter query: ");	
			words = keyboard.nextLine();
		}
		StringTokenizer tokenizer = new StringTokenizer(words);
		String[] query = new String[tokenizer.countTokens()];
		for (int i = 0; i < query.length; ++i) query[i] = tokenizer.nextToken();
		return query;
	}
	
	public static void printFileName(URL name) {
		if (counter == 0) {
			System.out.println("List of documents containing the query words");
			System.out.println("===========================================");
		}
		
		System.out.println(name.toString());
		++counter;
	}
	
	public static void endListFiles() {
		System.out.println("\nNumber of files that contain the query words: "+counter);
	}
}
