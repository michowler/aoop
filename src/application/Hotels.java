package application;

import java.util.ArrayList;
import java.util.Scanner;

public class Hotels extends Destination{
	private int star;
	
	public Hotels() {
		star = 0;
	}
	
	/**
	 * Hotels constructor
	 * @param title title
	 * @param price price
	 * @param latitude latitude
	 * @param longtitude longitude
	 * @param ratings ratings
	 * @param address address
	 * @param star hotel star rating
	 */
	public Hotels(String title , double price , double latitude , double longtitude , double ratings ,String address, int star) {
		super(title,price,latitude,longtitude,ratings, address);
		this.star = star;
	}
	
	/**
	 * @return hotel star
	 */
	public int getStar() {
		return star;
	}
	
	/**
	 * @param star hotel star
	 */
	public void setStar(int star) {
		this.star = star;
	}
	
	/* (non-Javadoc)
	 * @see Destination#toString()
	 */
	public String toString() {
		return "==========================================================\nPlace: " + getTitle() + "\n" + "Address: " + getAddress() + "\nPrice estimated: RM " + getPrice() + "\nStar: " + getStar() + "star(s)" + "\nRatings: " + getRatings() + "\n==========================================================";
	}
	
	/**
	 * This method prints all available hotels
	 * @param hotelArr hotel array
	 */
	public void printHotels(Hotels[] hotelArr) 
	{
		int n = 1;
		System.out.println("=====================================================================================================");
		System.out.println("List of all available Hotels : ");
		System.out.println("=====================================================================================================");
	    System.out.printf("%-5s %-35s","NUM", "TITLE");
	    System.out.println();
	    System.out.println("-----------------------------------------------------------------------------------------------------");
		
		for(int k = 0 ; k < hotelArr.length ;k++)
		{
			System.out.format("%-5d %-35s",
					n , hotelArr[k].getTitle());
			System.out.println("");
				n++;
		}
		System.out.println("-----------------------------------------------------------------------------------------------------");
	}

	/**
	 * This method prints all hotels filtered according to star
	 * @param tmpArray temp array
	 * @param returnNames return the name of category
	 */
	public void printHotels(Hotels tmpArray[], String returnNames) 
	{
		int n = 1;
		System.out.println("=====================================================================================================");
		System.out.println("List of all available Hotels according to "+ returnNames + " : ");
		System.out.println("=====================================================================================================");
	    System.out.printf("%-5s %-35s %-12s","NUM", "TITLE", "STARS");
	    System.out.println();
	    System.out.println("-----------------------------------------------------------------------------------------------------");
		
	    for(int k = 0 ; k < tmpArray.length ;k++)
		{
			System.out.format("%-5d %-35s %-12d",
					n , tmpArray[k].getTitle(),tmpArray[k].getStar());
			System.out.println("");
				n++;
		}
		System.out.println("-----------------------------------------------------------------------------------------------------");
	}
	
	/**
	 * This method filters out the hotels by star from user input
	 * @param input user input
	 * @param hotelArr hotel array
	 * @param myIt itinerary array list
	 */
	public void filterHotelsbyStar(Scanner input, Hotels[] hotelArr, ArrayList<Destination> myIt)
	{
		System.out.println("=====================================================================================================");
		System.out.println("Enter number of Hotel Stars : ");
		
		int numberOfStars = input.nextInt();
		String str = input.nextLine();
		int n = 0;
		
			for(int k = 0 ; k < hotelArr.length ;k++)
			{
				if((hotelArr[k].getStar()) == numberOfStars)
				{
					n++;
				}
				
			}
			Hotels[] tmpArray = new Hotels[n];
			n = 0;
			for(int k = 0 ; k < hotelArr.length ;k++)
			{
				if((hotelArr[k].getStar()) == numberOfStars)
				{
					tmpArray[n] = hotelArr[k];
					n++;
				}

			}
			
			if(numberOfStars > 1 && numberOfStars < 6)
			{
				printHotels(tmpArray,"Number of Stars");
				selectingOption(tmpArray, myIt);
			}
			else
			{
				System.out.println("Invalid star option!\n Returning to hotel menu");
			}
		
		
	}


}
