package application;
import java.util.ArrayList;
import java.util.Scanner;

public class Destination {
	private String title;
	private double price;
	private double latitude;
	private double longtitude;	
	private double ratings;
	private String state;
	private String address;
	
	public Destination() {
	}
	
	public Destination(double latitude, double longtitude) {	
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.title = "Your location";		
		
	}
	
	/**
	 * Destination constructor
	 * @param title title
	 * @param price price
	 * @param latitude latitude
	 * @param longtitude longitude
	 * @param ratings ratings
	 * @param address address
	 */
	public Destination(String title, double price, double latitude, double longtitude, double ratings, String address) {
		this.title = title;
		this.price = price;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.ratings = ratings;
		this.state = "Kuala Lumpur";		
		this.address = address;
	}

	/**
	 * @param title destination title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @param state destination state
	 */
	public void setState(String state) { 
		this.state = state;
	}
	
	/**
	 * @return title of destination
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * @return price of destination
	 */
	public double getPrice(){
		return this.price;
	}
	
	/**
	 * @return latitude of destination
	 */
	public double getLatitude(){
		return this.latitude;
	}
	
	/**
	 * @return longitude of destination
	 */
	public double getLongtitude(){
		return this.longtitude;
	}
	
	/**
	 * @return rating of destination
	 */
	public double getRatings(){
		return this.ratings;
	}
	
	/**
	 * @return state of destination
	 */
	public String getState() {
		return this.state;
	}
	
	/**
	 * @return address of destination
	 */
	public String getAddress() {
		return this.address;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "==========================================================\nPlace: "
				+ getTitle() + "\n" + "Address: " + getAddress() + "\nPrice estimated: RM " 
				+ getPrice() + "\nRatings: " + getRatings() + 
				"\n==========================================================";
	}

	/**
	 * This method is to sort the destinations array by price from lowest to highest
	 * @param destArr destination array
	 */
	public void sortPrice(Destination[] destArr) {
		Destination temp = null;			

		System.out.println("=======================================================================");
		System.out.println("List of all available " + destArr[0].getClass().getSimpleName() + " according to price");
		System.out.println("=======================================================================");
	    System.out.printf("%-5s %-35s %-35s","NUM", "TITLE", "PRICE (MYR)");
	    System.out.println();
	    System.out.println("-----------------------------------------------------------------------");
		for(int i=0; i < destArr.length; i++) {	
			for(int j=i +1; j < destArr.length; j++) {
				if (destArr[i].price > destArr[j].price) {
					temp = destArr[i];	
					destArr[i] = destArr[j];
					destArr[j] = temp;
				}
			}			
			System.out.format("%-5d %-35s %-35s",
					i+1 , destArr[i].title,destArr[i].price);		
			System.out.println("");
		}	
		System.out.println("-----------------------------------------------------------------------");
	}
	
	/**
	 * This method is to sort the destinations array by ratings from highest to lowest
	 * @param destArr destination array
	 */
	public void sortRatings(Destination[] destArr) {
		Destination temp = null;
		System.out.println("=======================================================================");
		System.out.println("List of all available " + destArr[0].getClass().getSimpleName() + " according to ratings");
		System.out.println("=======================================================================");
	    System.out.printf("%-5s %-35s %-35s","NUM", "TITLE", "RATINGS");
	    System.out.println();
	    System.out.println("-----------------------------------------------------------------------");
		for(int i=0; i < destArr.length; i++) {	
			for(int j=i +1; j < destArr.length; j++) {
				if (destArr[i].ratings < destArr[j].ratings) {
					temp = destArr[i];	
					destArr[i] = destArr[j];
					destArr[j]= temp;
				}
			}			
			System.out.format("%-5d %-35s %-35s",
					i+1 , destArr[i].title,destArr[i].ratings);
			System.out.println("");
		}	
		System.out.println("-----------------------------------------------------------------------");
	}
	
	/**
	 * This method is used to select the destination for filtered destinations
	 * @param tmpArray temp array to store destinations
	 * @param myIt itinerary array list
	 */
	public void selectingOption(Destination[] tmpArray, ArrayList<Destination> myIt)
	{
		int userinput = 0;
		Scanner input = new Scanner(System.in);	
		do {
			System.out.println("Enter an option to add into itinerary (0 to exit): ");		
			userinput = input.nextInt();
			System.out.println("-----------------------------------------------------------------------");				
			if (userinput == 0) {
				break;
			}else if (userinput > tmpArray.length || userinput < 0 ) {
				System.out.println("Invalid option!");
			}else {
				if (myIt.contains(tmpArray[userinput-1]) == false) {
					myIt.add(tmpArray[userinput-1]);					
					System.out.println("Added " + tmpArray[userinput-1].getTitle() + " to your itinerary !!!");					
					break;
				}else {
					System.out.println("Destination already added into itinerary!");
				}
			}
		}while(userinput != 0);
	}
	
	
	
}
