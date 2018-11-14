package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sorting {

	public Sorting() {
		//price		
		
		//alphabet
		
		//ratings
	}
	
	public ArrayList<Destination> filteredPrice(ArrayList<Destination> tmpArr){
		 Collections.sort(tmpArr, new PriceOrder());
		return tmpArr;
	}
	
	public ArrayList<Destination> filteredRatings(ArrayList<Destination> tmpArr){
		 Collections.sort(tmpArr, new RatingsOrder());
		return tmpArr;
	}
	
   public class AlphabetOrder implements Comparator<Destination> {	    
	    public int compare(Destination d1, Destination d2) {
	        return d1.getTitle().compareTo(d2.getTitle());
	    }
	    //Collections.sort(myData, new AlphabetOrder());
	}
   
   public class PriceOrder implements Comparator<Destination> {	    
	    public int compare(Destination d1, Destination d2) {	        
	        return Double.compare(d1.getPrice(), d2.getPrice());
	    }
	    //Collections.sort(myData, new PriceOrder());
	}

   public class RatingsOrder implements Comparator<Destination> {	    
	    public int compare(Destination d1, Destination d2) {	        
	        return Double.compare(d1.getRatings(), d2.getRatings());
	    }
	    //Collections.sort(myData, Collections.reverseOrder(new RatingsOrder()));
	}
	   

}
