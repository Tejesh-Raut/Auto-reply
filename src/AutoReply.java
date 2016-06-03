import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import services.BestMatch;
import services.KeepKeywords;

public class AutoReply 
{
	public static void main (String Args[]) throws Exception
	{
		InputStreamReader read = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(read);
		
		int ch = 1;

		Map<String, String> query = new HashMap<String, String>();
		Properties properties = new Properties();
		
		Scanner scanner = new Scanner( new File("NonKeywords.txt"), "UTF-8" );
		String NonKeywordsString = scanner.useDelimiter("\\A").next();
		scanner.close();
		String[] NonKeywords = NonKeywordsString.split("\\s+");
		
		//System.out.println(NonKeywords);
		
		while(ch != 7)
		{
			System.out.println("Enter \n 1 for saving the map \n 2 for loading the map \n 3 for adding entries to map \n 4 to display the entries in the map \n 5 to delete an entry from hashmap \n 6 to ask a query \n 7 to terminate the program \n 8 to refresh the keywords for queries \n 9 to add new nonkeywords");
			ch = Integer.parseInt(in.readLine());
			
			switch (ch)
			{
			case 1:
				System.out.println("Saving the map ...");
				{
					for (Map.Entry<String,String> entry : query.entrySet()) 
					{
					    properties.put(entry.getKey(), entry.getValue());
					}
					properties.store(new FileOutputStream("data.properties"), null);
				}
				System.out.println("Saved");
				break;
			case 2:
				System.out.println("Loading the map ...");
				{
					properties.load(new FileInputStream("data.properties"));
	
					for (String key : properties.stringPropertyNames())
					{
					   query.put(key, properties.get(key).toString());
					}
				}
				System.out.println("Loaded");
				break;
			case 3:
				{
					int c=0;
					while(c==0)
					{
						c=1;
						System.out.println("Enter the query: ");
						String q = in.readLine();
						System.out.println("Enter the reply: ");
						String r = in.readLine();
						String[] allwords = KeepKeywords.SeparateWords(q);
						String Keywords = KeepKeywords.Keywords(allwords, NonKeywords);
						query.put(Keywords, r);
						System.out.println("Entry added successfully");
						System.out.println("Enter 0 to add another entry or enter any other number to continue");
						c = Integer.parseInt(in.readLine());
					}
				}
				break;
			case 4:
				for (Map.Entry<String,String> entry : query.entrySet()) 
				{
				    System.out.println(entry.getKey()+"\t"+entry.getValue());
				}
				pressAnyKeyToContinue();
				break;
			case 5:
				{
					System.out.println("Enter the query to be deleted");
					String q = in.readLine();
					for(Iterator<Map.Entry<String, String>> it = query.entrySet().iterator(); it.hasNext(); ) 
					{
					      Map.Entry<String, String> entry = it.next();
					      if(entry.getKey().equals(q)) 
					      {
					        it.remove();
					      }
					}
				}
				break;
			case 6:
				{
					System.out.println("Enter your query: ");
					String q = in.readLine();
					String q1 = BestMatch.BestQuery(q, query);
					System.out.println(query.get(q1));
				}
				break;
			case 7:
				System.out.println("Closing the program .....");
				break;
			case 8:
				{
					System.out.println("Converting all existing queries to new Keywords");
					String q,r;
					Map<String, String> query1 = new HashMap<String, String>(query);
					for (Map.Entry<String,String> entry : query1.entrySet()) 
					{
						q=entry.getKey();
						r=entry.getValue();
						query.remove(q);
						String[] allwords = KeepKeywords.SeparateWords(q);
						String Keywords = KeepKeywords.Keywords(allwords, NonKeywords);
						//System.out.println(Keywords);
						query.put(Keywords, r);
					}
					System.out.println("Keywords for queries refreshed successfully");
				}
				break;
			case 9:
				{
					int c=0;
					String w;
					FileWriter fw = new FileWriter("NonKeywords.txt",true);
					while(c==0)
					{
						System.out.println("Enter a word to be added to non-keywords list ");
						w = in.readLine();
						fw.write(w+" ");
						System.out.println("Enter 0 to add a new word or enter any other number to finish");
						c = Integer.parseInt(in.readLine());
					}
					fw.close();
					
					scanner = new Scanner( new File("NonKeywords.txt"), "UTF-8" );
					NonKeywordsString = scanner.useDelimiter("\\A").next();
					scanner.close();
					NonKeywords = NonKeywordsString.split("\\s+");
				}
				break;
			default:
				System.out.println("Incorrect choice. Please try again");
				break;
			}//end of switch
		}//end of while
		
		System.out.println("Program closed");
	}//end of main
	
	public static void pressAnyKeyToContinue()
	 { 
	        System.out.println("Press any key to continue...");
	        try
	        {
	            System.in.read();
	        }  
	        catch(Exception e)
	        {}  
	 }
}//end of class AutoReply
