package services;

public class KeepKeywords 
{
	public static String[] SeparateWords(String query) throws Exception
	{
		String s = query;
		s = s.replaceAll("[^'-\\w\\s\\d]","");//removes all characters except ', -, alphabets, spaces, digits
		s = s.replaceAll("'s", "");// remove all 's
		s = s.replaceAll("-", " ");// replace - with spaces
		String[] words = s.split("\\s+");
		return words;
	}
	
	public static String[] Keywords(String[] allwords) throws Exception
	{
		
		return allwords;
	}
}
