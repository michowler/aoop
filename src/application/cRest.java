package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
public class cRest {
public static void main(String[] args) {
	restaurantData rd = new restaurantData();
	Scanner in = new Scanner(System.in);
	/*
	
	String ans1 = "";
	String ans2 = "";
	String ans3 = "";
	String ans4 = "";
	String ans5 = "";
	
	System.out.println("Is halal food a priority when looking for food?");
	String resp = in.next();
	
	if(resp.equalsIgnoreCase("yes")) 
	{
		ans1 = "halal";
	} else 
	{
		System.out.print("Do you like alcohol?");
		resp = in.next();
		if(resp.equalsIgnoreCase("yes"))
		{
			ans1 = "alcohol";
		}
	}
	
	System.out.println("Are you vegan?");
	resp = in.next();
	if(resp.equalsIgnoreCase("yes")) {
		ans2 = "vegan";
	}
	
	System.out.println("Which of the following type of dishes stand out to you\n1.Western Cuisine\n2.Asian Cuisine\n3.Authentic Malaysian Cuisine");
	resp = in.next();
	switch(resp.toLowerCase()) {
	case "western" : ans3 = "western"; 
						break;
	case "asian" : ans3 = "asian";
						break;
	default : ans3 = "malaysian";
	break;
	}
	*/
	
	System.out.println("Write a description of what you are looking for: (example- vegan, malaysian, halal, alcohol, skyview, fastfood etc..);");
	String resp = in.next();
	//String resp = "vegan malaysian";
	StringTokenizer st = new StringTokenizer(resp, ",.");
	
	ArrayList<String> listOfKeys = new ArrayList<String>();
	
	while(st.hasMoreTokens()) {
		
		String t = st.nextToken();
		for (Map.Entry<String,List<String> > entry : rd.rest.entrySet()) 
		{
			if(entry.getValue().contains(t))
			{
				
				listOfKeys.add(entry.getKey());
				
			} else {
						
				listOfKeys.remove(entry.getKey());
			}
		}
		
		
	}
	
	Set<String> temp = new HashSet<>();
	temp.addAll(listOfKeys);
	listOfKeys.clear();
	listOfKeys.addAll(temp);
	//System.out.println(rd.rest);
	
	
		// Iterate over each entry of map using entrySet
	/*for (Map.Entry<String,List<String> > entry : rd.rest.entrySet()) 
		{
		System.out.println(entry.getKey());
		System.out.println(entry.getValue());
			// Check if value matches with given value
			if (entry.getValue().contains(ans1))
			{
				// Store the key from entry to the list
				
				if (entry.getValue().contains(ans2))
				{
					if (entry.getValue().contains(ans3))
					{
						listOfKeys.add(entry.getKey());
					}
				}
			}
		}
	*/
	System.out.println(listOfKeys.toString().replace("[", "").replace("]", ""));
	//}
	}


}


