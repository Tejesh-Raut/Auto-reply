import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import services.BestMatch;
import services.KeepKeywords;

/**
 * @author Tejesh_Raut
 *
 */
public class AutoReply
{
	public static void main (String Args[]) throws Exception
	{
		//InputStreamReader read = new InputStreamReader(System.in);
		//BufferedReader in = new BufferedReader(read);
		
		//int ch = 1;

		Map<String, String> query = new HashMap<String, String>();
		Map<String, String> original = new HashMap<String, String>();
		Properties properties = new Properties();
		Properties originalProperties = new Properties();
		
		Scanner scanner = new Scanner( new File("NonKeywords.txt"), "UTF-8" );
		String NonKeywordsString = scanner.useDelimiter("\\A").next();
		scanner.close();
		String[] NonKeywords = NonKeywordsString.split("\\s+");
		
		//*JTextArea jTextArea = new JTextArea(20, 37);
		//*JTextArea inputArea = new JTextArea(10, 37);
		//jTextArea.append( "Hello World.-----------------------------------------------------" );
		final JFrame frame = new JFrame("Auto Reply selector");
		frame.setVisible(true);
		frame.setSize(550,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//*TextAreaOutputStream taOutputStream = new TextAreaOutputStream(jTextArea, "");

	    
		
		JPanel panel1 = new JPanel(new GridLayout(5, 1, 10, 20));
		JPanel panel2 = new JPanel(new GridLayout(5, 1, 10, 20));
		//*JPanel panel3 = new JPanel(new BorderLayout());
		JPanel panel = new JPanel(new BorderLayout(10,10));
		panel.add(panel1, BorderLayout.WEST);
		panel.add(panel2, BorderLayout.EAST);
		//*panel.add(panel3, BorderLayout.CENTER);
		frame.add(panel);
		
		
		//*panel3.add(inputArea, BorderLayout.SOUTH);
		//*panel3.add( jTextArea, BorderLayout.NORTH );
		//*panel3.add(new JScrollPane(jTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		//panel3.add(new JScrollPane(inputArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	    //*System.setOut(new PrintStream(taOutputStream));
		
		final JButton button1 = new JButton("Ask a query");
		button1.setPreferredSize(new Dimension(250, 50));
		panel1.add(button1);
		//*button1.setActionCommand("1");
		
		final JButton button2 = new JButton("Display the entries");
		button2.setPreferredSize(new Dimension(250, 50));
		panel2.add(button2);
		//*button2.setActionCommand("2");
		
		final JButton button3 = new JButton("Add entries");
		panel1.add(button3);
		//*button3.setActionCommand("3");
		
		final JButton button4 = new JButton("Load from file");
		panel2.add(button4);
		//*button4.setActionCommand("4");
		
		final JButton button5 = new JButton("Save to file");
		panel1.add(button5);
		//*button5.setActionCommand("5");
		
		final JButton button6 = new JButton("Delete an entry");
		panel2.add(button6);
		//*button6.setActionCommand("6");
		
		final JButton button7 = new JButton("Refresh the keywords");
		panel1.add(button7);
		//*button7.setActionCommand("7");
		
		final JButton button8 = new JButton("Add new nonkeywords");
		panel2.add(button8);
		//*button8.setActionCommand("8");
		
		final JButton button9 = new JButton("Add new synonyms");
		panel1.add(button9);
		//*button9.setActionCommand("9");
		
		final JButton button10 = new JButton("Terminate the program");
		panel2.add(button10);
		//*button10.setActionCommand("10");
		
		button1.setEnabled(true);
		button2.setEnabled(true);
		button3.setEnabled(true);
		button4.setEnabled(true);
		button5.setEnabled(true);
		button6.setEnabled(true);
		button7.setEnabled(true);
		button8.setEnabled(true);
		button9.setEnabled(true);
		button10.setEnabled(true);
		
		button1.setBackground(Color.cyan);
		button2.setBackground(Color.cyan);
		button3.setBackground(Color.cyan);
		button4.setBackground(Color.cyan);
		button5.setBackground(Color.cyan);
		button6.setBackground(Color.cyan);
		button7.setBackground(Color.cyan);
		button8.setBackground(Color.cyan);
		button9.setBackground(Color.cyan);
		button10.setBackground(Color.cyan);
		
		final Map<String, String> query1 = query;//Create a final copy the map 'query'
		final Map<String, String> original1 = original;
		final String[] NonKeywords1 = NonKeywords;
		button1.addActionListener(new ActionListener() //search for a query
		{
			@Override
            public void actionPerformed(ActionEvent e)
            {
				button1.setBackground(Color.red);
            	String q = JOptionPane.showInputDialog(null,"Enter your query: ");
            	try 
            	{
					String q1 = BestMatch.BestQuery(q,NonKeywords1, query1);
					JTextArea msg = new JTextArea(query1.get(q1), 10, 50);
					msg.setLineWrap(true);
					msg.setWrapStyleWord(true);
	                msg.setEditable(false);
					JScrollPane scrollPane = new JScrollPane(msg);
					JOptionPane.showMessageDialog(null, scrollPane);
				} 
            	catch (Exception e1) 
            	{
					JOptionPane.showMessageDialog (null, "File not found", "Reply", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
        		button1.setBackground(Color.cyan);
            }
        });
		
		button2.addActionListener(new ActionListener() //Display the entries
		{
            @Override
            public void actionPerformed(ActionEvent e)
            {
				button2.setBackground(Color.red);
            	String reply ="";
            	for (Map.Entry<String,String> entry : query1.entrySet()) //Displaying the entries stored in query1
				{
				    reply = reply +(original1.get(entry.getKey())+"\n----------------------------------------------------------------------------------------------------\n"+entry.getValue()+"\n********************************************************************************\n\n\n");
				}
            	JFrame frame = new JFrame("Reply");
            	JTextArea textArea = new JTextArea(40, 100);
            	textArea.setLineWrap(true);
            	textArea.setWrapStyleWord(true);
                textArea.setText(reply);
                textArea.setEditable(false);
                // wrap a scrollpane around it
                JScrollPane scrollPane = new JScrollPane(textArea);
                // display them in a message dialog
                JOptionPane.showMessageDialog(frame, scrollPane);
            	//JOptionPane.showMessageDialog (null, reply, "Reply", JOptionPane.INFORMATION_MESSAGE);
				button2.setBackground(Color.cyan);
            }
        });
		
		button3.addActionListener(new ActionListener() //Add new entry
		{
            @SuppressWarnings("static-access")
			@Override
            public void actionPerformed(ActionEvent e)
            {
				button3.setBackground(Color.red);
				String q,r;
				q = JOptionPane.showInputDialog(null,"Enter your query: ");
				r = new MyJOptionPane().showInputDialog("Enter your reply: ");
				String[] allwords;
				try 
				{
					allwords = KeepKeywords.SeparateWords(q);
					String Keywords = KeepKeywords.Keywords(allwords, NonKeywords1);
					query1.put(Keywords, r);
					original1.put(Keywords, q);
					JOptionPane.showMessageDialog (null, "Entry added successfully", "Reply", JOptionPane.INFORMATION_MESSAGE);
				} 
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}
				button3.setBackground(Color.cyan);
            }
        });
		
		final Properties properties1 = properties;
		final Properties originalProperties1 = originalProperties;
		button4.addActionListener(new ActionListener() //Load from file
		{
            @Override
            public void actionPerformed(ActionEvent e)
            {
				button4.setBackground(Color.red);
				{
					try 
					{
						properties1.load(new FileInputStream("reply.properties"));
						originalProperties1.load(new FileInputStream("query.properties"));
					} 
					catch (FileNotFoundException e1) 
					{
						JOptionPane.showMessageDialog (null, "File not found", "Reply", JOptionPane.INFORMATION_MESSAGE);
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
					for (String key : properties1.stringPropertyNames())
					{
					   query1.put(key, properties1.get(key).toString());
					}
					for (String key : originalProperties1.stringPropertyNames())
					{
						original1.put(key, originalProperties1.get(key).toString());
					}
				}
				JOptionPane.showMessageDialog (null, "Loaded", "Reply", JOptionPane.INFORMATION_MESSAGE);
				button4.setBackground(Color.cyan);
            }
        });
		
		button5.addActionListener(new ActionListener() //save to file
		{
            @Override
            public void actionPerformed(ActionEvent e)
            {
				button5.setBackground(Color.red);
            	Properties properties2 = new Properties();
            	Properties originalProperties2 = new Properties();
				{
					for (Map.Entry<String,String> entry : query1.entrySet()) 
					{
					    properties2.put(entry.getKey(), entry.getValue());
					}
					for (Map.Entry<String,String> entry : original1.entrySet()) 
					{
					    originalProperties2.put(entry.getKey(), entry.getValue());
					}
					try 
					{
						properties2.store(new FileOutputStream("reply.properties"), null);
						originalProperties2.store(new FileOutputStream("query.properties"), null);
					} 
					catch (FileNotFoundException e1) 
					{
						e1.printStackTrace();
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog (null, "Saved", "Reply", JOptionPane.INFORMATION_MESSAGE);
				button5.setBackground(Color.cyan);
            }
        });
		
		button6.addActionListener(new ActionListener() //Delete an entry
		{
            @Override
            public void actionPerformed(ActionEvent e)
            {
				button6.setBackground(Color.red);
				String q = JOptionPane.showInputDialog(null,"Enter the query to be deleted: ");
				try 
            	{
					String q1 = BestMatch.BestQuery(q, NonKeywords1, query1);
					int dialogButton = JOptionPane.YES_NO_OPTION;
					JTextArea msg = new JTextArea("Would You like to remove following entry: \n"+original1.get(q1)+"\n----------------------------------------------------------------------------------------------------\n"+query1.get(q1), 10, 50);
					msg.setLineWrap(true);
					msg.setWrapStyleWord(true);
	                msg.setEditable(false);
					JScrollPane scrollPane = new JScrollPane(msg);
					int dialogResult = JOptionPane.showConfirmDialog (null, scrollPane,"Warning",dialogButton);
					if(dialogResult == 0) //yes option
					{
						for(Iterator<Map.Entry<String, String>> it = query1.entrySet().iterator(); it.hasNext(); ) 
						{
						      Map.Entry<String, String> entry = it.next();
						      if(entry.getKey().equals(q1)) 
						      {
						        it.remove();
						      }
						}
						for(Iterator<Map.Entry<String, String>> it = original1.entrySet().iterator(); it.hasNext(); ) 
						{
						      Map.Entry<String, String> entry = it.next();
						      if(entry.getKey().equals(q1)) 
						      {
						        it.remove();
						      }
						}
						JOptionPane.showMessageDialog (null, "Entry deleted", "Reply", JOptionPane.INFORMATION_MESSAGE);
					} 
					else //no option
					{
						JOptionPane.showMessageDialog (null, "Entry not deleted", "Reply", JOptionPane.INFORMATION_MESSAGE);
					} 
				} 
            	catch (Exception e1) 
            	{
					JOptionPane.showMessageDialog (null, "File not found", "Reply", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
				button6.setBackground(Color.cyan);
            }

        });
		
		button7.addActionListener(new ActionListener() //Refresh keywords
		{
            @Override
            public void actionPerformed(ActionEvent e)
            {
				button7.setBackground(Color.red);
				String q,qoriginal,r;
				Map<String, String> query2 = new HashMap<String, String>(query1);
				for (Map.Entry<String,String> entry : query2.entrySet()) 
				{
					q=entry.getKey();
					qoriginal = original1.get(q);
					r=entry.getValue();
					query1.remove(q);
					original1.remove(q);
					String[] allwords;
					try 
					{
						allwords = KeepKeywords.SeparateWords(qoriginal);
						String Keywords = KeepKeywords.Keywords(allwords, NonKeywords1);
						query1.put(Keywords, r);
						original1.put(Keywords, qoriginal);
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog (null, "Keywords for queries refreshed successfully", "Reply", JOptionPane.INFORMATION_MESSAGE);
				button7.setBackground(Color.cyan);
            }
        });
		
		final Scanner[] scanner1 = {scanner};
		final String[] NonKeywordsString1 = {NonKeywordsString};
		final String[][] NonKeywords2 = {NonKeywords1};
		button8.addActionListener(new ActionListener() //add new nonkeyword
		{
            @Override
            public void actionPerformed(ActionEvent e)
            {
				button8.setBackground(Color.red);
				String w;
				FileWriter fw;
				try 
				{
					fw = new FileWriter("NonKeywords.txt",true);
					w = JOptionPane.showInputDialog(null,"Enter a word to be added to non-keywords list: ");
					fw.write(w+" ");
					fw.close();
					JOptionPane.showMessageDialog (null, "Nonkeyword added successfully", "Reply", JOptionPane.INFORMATION_MESSAGE);
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				
				try 
				{
					scanner1[0] = new Scanner( new File("NonKeywords.txt"), "UTF-8" );
					NonKeywordsString1[0] = scanner1[0].useDelimiter("\\A").next();
					scanner1[0].close();
					NonKeywords2[0] = NonKeywordsString1[0].split("\\s+");
				} 
				catch (FileNotFoundException e1) 
				{
					JOptionPane.showMessageDialog (null, "File not found", "Reply", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
				button8.setBackground(Color.cyan);
            }
        });
		
		button9.addActionListener(new ActionListener() // add new synonyms
		{
            @Override
            public void actionPerformed(ActionEvent e)
            {
				button9.setBackground(Color.red);
				FileWriter fw;
				try 
				{
					fw = new FileWriter("Synonyms.txt",true);
					String s = JOptionPane.showInputDialog(null,"Enter synonyms separated by spaces: ");
					fw.write(s+"\n");
					fw.close();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				button9.setBackground(Color.cyan);
            }
        });
		
		button10.addActionListener(new ActionListener() //exit button
		{
            @Override
            public void actionPerformed(ActionEvent e)
            {
				button10.setBackground(Color.red);
            	frame.dispose();
            	System.exit(0);
				button10.setBackground(Color.cyan);
            }
        });
		
		/*
		while(ch != 10)
		{
			//ch = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter \n 1 to ask a query \n 2 to display the entries in the map \n 3 for adding entries to map \n 4 for loading the map \n 5 for saving the map \n 6 to delete an entry from hashmap \n 7 to refresh the keywords for queries \n 8 to add new nonkeywords \n 9 to add synonyms \n 10 to terminate the program "));
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
					properties.load(new FileInputStream("reply.properties"));
	
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
					properties.store(new FileOutputStream("reply.properties"), null);
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
					Map<String, String> query2 = new HashMap<String, String>(query);
					for (Map.Entry<String,String> entry : query2.entrySet()) 
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
		*/
	}//end of main
	/*
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
	 */
}//end of class AutoReply
@SuppressWarnings("serial")
class MyJOptionPane extends JOptionPane
{
  public static String showInputDialog(final String message)
  {
    String data = null;
    class GetData extends JDialog implements ActionListener
    {
      JTextArea ta = new JTextArea(10,50);
	  JScrollPane scrollPane = new JScrollPane(ta);
      JButton btnOK = new JButton("   OK   ");
      JButton btnCancel = new JButton("Cancel");
      String str = null;
      public GetData()
      {
        setModal(true);
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(400,300);
        getContentPane().add(new JLabel(message),BorderLayout.NORTH);
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        JPanel jp = new JPanel();
        btnOK.addActionListener(this);
        btnCancel.addActionListener(this);
        jp.add(btnOK);
        jp.add(btnCancel);
        getContentPane().add(jp,BorderLayout.SOUTH);
        pack();
        setVisible(true);
      }
      public void actionPerformed(ActionEvent ae)
      {
        if(ae.getSource() == btnOK) str = ta.getText();
        dispose();
      }
      public String getData(){return str;}
    }
    data = new GetData().getData();
    return data;
  }
}