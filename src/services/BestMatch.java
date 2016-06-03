package services;

import java.util.Map;

public class BestMatch 
{
	public static String BestQuery(String q , Map<String, String> query)
	{
		String s,s1;
		s1="";
		String[] ss,qs;
		qs = q.split("\\s+");
		int c=0;
		int cmax = 0;
		for (Map.Entry<String,String> entry : query.entrySet()) 
		{
			c=0;
			s = entry.getKey();
			ss = s.split("\\s+");
			for (int i=0; i<qs.length; i++)
			{
				for (int j=0; j<ss.length; j++)
				{
					if(qs[i].equalsIgnoreCase(ss[j]))
					{
						c++;
					}
				}
			}
			if(cmax<c)
			{
				cmax = c;
				s1 = s;
			}
		    //System.out.println(entry.getKey()+entry.getValue());
		}
		return s1;
	}
}
