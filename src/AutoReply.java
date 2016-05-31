import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class AutoReply 
{
	public static void main (String Args[]) throws IOException
	{
		InputStreamReader read = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(read);
		
		//System.out.println("Enter a string: ");
		//String s=in.readLine();
		
		//System.out.println("You entered the string: "+s);
		int ch = 1;

		Map<String, String> query = new HashMap<String, String>();
		Properties properties = new Properties();
		
		while(ch != 6)
		{
			System.out.println("Enter 1 for saving the map \n 2 for loading the map \n 3 for adding entries to map \n 4 to display the entries in the map \n 5 to delete an entry from hashmap \n 6 to terminate the program");
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
				int c=0;
				while(c==0)
				{
					c=1;
					System.out.println("Enter the query: ");
					String q = in.readLine();
					System.out.println("Enter the reply: ");
					String r = in.readLine();
					query.put(q, r);
					System.out.println("Entry added successfully");
					System.out.println("Enter 0 to add another entry or enter any other number to continue");
					c = Integer.parseInt(in.readLine());
				}
				break;
			case 4:
				for (Map.Entry<String,String> entry : query.entrySet()) 
				{
				    System.out.printf("%-30.30s  %-30.30s%n", entry.getKey(), entry.getValue());
				}
				break;
			case 5:
				System.out.println();
				String q = in.readLine();
				for(Iterator<Map.Entry<String, String>> it = query.entrySet().iterator(); it.hasNext(); ) 
				{
				      Map.Entry<String, String> entry = it.next();
				      if(entry.getKey().equals(q)) 
				      {
				        it.remove();
				      }
				}
				break;
			case 6:
				System.out.println("Closing the program .....");
				break;
			default:
				System.out.println("Incorrect choice. Please try again");
				break;
			}//end of switch
		}//end of while
		
		System.out.println("Program closed");
	}//end of main
}//end of class AutoReply
