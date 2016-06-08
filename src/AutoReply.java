import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
		JTextArea jTextArea = new JTextArea(20, 37);
		JTextArea inputArea = new JTextArea(10, 37);
		//jTextArea.append( "Hello World.-----------------------------------------------------" );
		JFrame frame = new JFrame("Auto Reply selector");
		frame.setVisible(true);
		frame.setSize(1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TextAreaOutputStream taOutputStream = new TextAreaOutputStream(jTextArea, "");

	    
		
		JPanel panel1 = new JPanel(new GridLayout(5, 1, 10, 20));
		JPanel panel2 = new JPanel(new GridLayout(5, 1, 10, 20));
		JPanel panel3 = new JPanel(new BorderLayout());
		JPanel panel = new JPanel(new BorderLayout(10,10));
		panel.add(panel1, BorderLayout.WEST);
		panel.add(panel2, BorderLayout.EAST);
		panel.add(panel3, BorderLayout.CENTER);
		frame.add(panel);
		
		
		panel3.add(inputArea, BorderLayout.SOUTH);
		panel3.add( jTextArea, BorderLayout.NORTH );
		panel3.add(new JScrollPane(jTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		//panel3.add(new JScrollPane(inputArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	      System.setOut(new PrintStream(taOutputStream));
		
		JButton button1 = new JButton("Ask a query");
		button1.setPreferredSize(new Dimension(250, 50));
		panel1.add(button1);
		  //button.addActionListener (new Action1());
		
		JButton button2 = new JButton("Display the entries in the map");
		panel2.add(button2);
		  //button.addActionListener (new Action2());
		
		JButton button3 = new JButton("Add entries to map");
		panel1.add(button3);
		  //button.addActionListener (new Action1());
		
		JButton button4 = new JButton("Load the map");
		panel2.add(button4);
		  //button.addActionListener (new Action2());
		
		JButton button5 = new JButton("Save the map");
		panel1.add(button5);
		  //button.addActionListener (new Action1());
		
		JButton button6 = new JButton("Delete an entry from hashmap");
		panel2.add(button6);
		  //button.addActionListener (new Action2());
		JButton button7 = new JButton("Refresh the keywords");
		panel1.add(button7);
		  //button.addActionListener (new Action1());
		
		JButton button8 = new JButton("Add new nonkeywords");
		panel2.add(button8);
		  //button.addActionListener (new Action2());
		JButton button9 = new JButton("Add new synonyms");
		panel1.add(button9);
		  //button.addActionListener (new Action1());
		
		JButton button10 = new JButton("Terminate the program");
		panel2.add(button10);
		  //button.addActionListener (new Action2());
		
		
		while(ch != 10)
		{
			System.out.println("Enter \n 1 to ask a query \n 2 to display the entries in the map \n 3 for adding entries to map \n 4 for loading the map \n 5 for saving the map \n 6 to delete an entry from hashmap \n 7 to refresh the keywords for queries \n 8 to add new nonkeywords \n 9 to add synonyms \n 10 to terminate the program ");
			ch = Integer.parseInt(in.readLine());
			
			switch (ch)
			{
			case 1:
			{
				System.out.println("Enter your query: ");
				String q = in.readLine();
				String q1 = BestMatch.BestQuery(q, query);
				System.out.println(query.get(q1));
				pressAnyKeyToContinue();
			}
				break;
			case 2:
				for (Map.Entry<String,String> entry : query.entrySet()) 
				{
				    System.out.println(entry.getKey()+"\t"+entry.getValue());
				}
				pressAnyKeyToContinue();
				break;
			case 3:
			{
				int c=0;
				String q,r,line;
				while(c==0)
				{
					c=1;
					r="";
					System.out.println("Enter the query: ");
					q = in.readLine();
					System.out.println("Enter the reply: ");
					while ((line = in.readLine()) != null && line.length()!= 0)  
					{
						r = r+"\n"+line;
					}
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
			case 5:
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
			
			case 6:
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
			case 7:
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
			case 8:
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
			case 9:
				{
					int c = 0;
					FileWriter fw = new FileWriter("Synonyms.txt",true);
					while(c == 0)
					{
						System.out.println("Enter the synonyms in a line separated by spaces");
						String s = in.readLine();
						fw.write(s+"\n");
						System.out.println("Enter 0 to add a new word or enter any other number to finish");
						c = Integer.parseInt(in.readLine());
					}
					fw.close();
				}
				break;
			case 10:
				System.out.println("Closing the program .....");
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
