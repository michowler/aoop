package application;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class restaurantData {

HashMap<String, List<String>> rest = new HashMap<String, List<String>>();
	
{
rest.put("McDonald KL Sentral", Arrays.asList(new String[] {"fastfood", "halal", "vegan"}));//
rest.put("Vin's Restaurant and Bar", Arrays.asList(new String[] {"asian", "alcohol", "western"}));
rest.put("Dining in the Dark", Arrays.asList(new String[] {"asian", "vegan", "western", "malaysian"}));
rest.put("Sitka Restaurant", Arrays.asList(new String[] {"asian", "halal", "vegan", "malaysian"}));//
rest.put("The Owl's Cafe", Arrays.asList(new String[] {"western", "halal", "vegan", "malaysian"}));//
rest.put("Cantaloupe at Tropika Sky Dining", Arrays.asList(new String[] {"alcohol", "vegan", "western", "skyview", "malaysian"}));
rest.put("El Cerdo Restaurant", Arrays.asList(new String[] {"asian", "vegan"}));
rest.put("Marble 8 On 56", Arrays.asList(new String[] {"alcohol", "western", "halal", "vegan", "sky view"}));//
rest.put("Fuel Shack", Arrays.asList(new String[] {"fastfood", "western", "halal"}));//
rest.put("Marini's on 57", Arrays.asList(new String[] {"asian", "western", "vegan", "sky view"}));
}


private HashMap<String, List<String>> getRest() {
	return this.rest;
}

/*private <K, V> List<K> getKeysForValues(HashMap<K, V> rest, V value) 
{
	List<K> listOfKeys = null;
	 
	//Check if Map contains the given value
	if(mapOfWords.containsValue(value))
	{
		// Create an Empty List
		listOfKeys = new ArrayList<>();
				
		// Iterate over each entry of map using entrySet
		for (Map.Entry<K, V> entry : mapOfWords.entrySet()) 
		{
			// Check if value matches with given value
			if (entry.getValue().equals(value))
			{
				// Store the key from entry to the list
				listOfKeys.add(entry.getKey());
			}
		}
	}
	// Return the list of keys whose value matches with given value.
	return listOfKeys;	
}*/




}
