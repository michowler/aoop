package application;

import java.util.ArrayList;

public class Restaurants extends Destination {
	private String restaurantType;
	
	public Restaurants() {
		restaurantType = "";
	}
	
	/**
	 * Restaurants constructors
	 * @param title title
	 * @param cost cost
	 * @param latitude latitude
	 * @param longtitude longitude
	 * @param ratings ratings
	 * @param address address
	 * @param restaurantType restaurant type
	 */
	public Restaurants(String title , double cost , double latitude , double longtitude , double ratings, String address, String restaurantType) {
		super(title,cost,latitude,longtitude,ratings, address);
		this.restaurantType = restaurantType;
	}
	
	/**
	 * @return restaurant type
	 */
	public String getRestaurantType() {
		return restaurantType;
	}
	
	/**
	 * @param restaurantType restaurant type
	 */
	public void setRestaurantType(String restaurantType) {
		this.restaurantType = restaurantType;
	}

	/* (non-Javadoc)
	 * @see Destination#toString()
	 */
	public String toString() {
		return "==========================================================\nPlace: " + getTitle() + "\n" + "Address: " + getAddress() + "\nPrice estimated: RM " + getPrice() + "\nRestaurant Type: " + getRestaurantType() + "\nRatings: " + getRatings() + "\n==========================================================";
	}
	
	/**
	 * This method prints all the available restaurants
	 * @param myRests all restaurants array
	 */
	public void printRestaurants(Restaurants[] myRests) 
	{
			int n = 1;
			System.out.println("=======================================================================");
			System.out.println("List of all available Restaurants: ");
			System.out.println("=======================================================================");
		    System.out.printf("%-5s %-35s","NUM", "TITLE");		  
		    System.out.println();
		    System.out.println("-----------------------------------------------------------------------");
			
			for(int k = 0 ; k < myRests.length ;k++)
			{
				System.out.format("%-5d %-35s",
						n , myRests[k].getTitle());
				System.out.println("");
					n++;
			}
			System.out.println("-----------------------------------------------------------------------");
	}
	
	/**
	 * This method prints all available restaurants after filtering base on food category type
	 * @param tmpArray temp array
	 * @param returnNames return category name
	 */
	public void printRestaurants(Restaurants tmpArray[] , String returnNames)  //overloading
	{
		int n = 1;
		System.out.println("=======================================================================");
		System.out.println("List of all available Restaurants according to "+ returnNames + " : ");
		System.out.println("=======================================================================");
	    System.out.printf("%-5s %-35s %-35s","NUM", "TITLE", "RESTAURANT TYPE");	   
	    System.out.println();
	    System.out.println("-----------------------------------------------------------------------");
		
		for(int k = 0 ; k < tmpArray.length ;k++)
		{
			System.out.format("%-5d %-35s %-35s",
					n , tmpArray[k].getTitle(),tmpArray[k].getRestaurantType());
			System.out.println("");
				n++;
		}
		System.out.println("-----------------------------------------------------------------------");
	}
	
	/**
	 * This method is used to filter the restaurants base on category type
	 * @param chosenCategory category name
	 * @param myRests all restaurant array
	 * @param myIt itinerary array
	 */
	public void filterRestaurantsbyCategory(String chosenCategory, Restaurants[] myRests, ArrayList<Destination> myIt)
	{
		int n = 0;
		for(int k = 0 ; k < myRests.length ;k++)
		{
			if((myRests[k].getRestaurantType()).equals(chosenCategory))
			{
				n++; //size of tmpArr or filtered array
			}

		}
		Restaurants[] tmpArray = new Restaurants[n]; //array for filtered data
		n = 0;
		for(int k = 0 ; k < myRests.length ;k++)
		{
			if((myRests[k].getRestaurantType()).equals(chosenCategory))
			{
				tmpArray[n] = myRests[k];
				n++;
			}
		}
		
		if(chosenCategory != null)
		{
			printRestaurants(tmpArray,"Type(Category)");	
			selectingOption(tmpArray, myIt);
		}
			
	}
	
}
