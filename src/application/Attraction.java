package application;

import java.util.ArrayList;
import java.util.Scanner;

public class Attraction extends Destination {
	private String attType;
	
	public Attraction() {
	}
	
	/**
	 * Attraction constructor
	 * @param title title
	 * @param price price
	 * @param latitude latitude
	 * @param longtitude longitude
	 * @param ratings ratings
	 * @param distance distance
	 * @param address address
	 * @param attType attraction type
	 */
	public Attraction(String title, double price, double latitude, double longtitude, double ratings, double distance, String address, String image, String attType) {
		super(title, price, latitude, longtitude, ratings, address, image);	
		this.attType = attType;		
	}
	
	/**
	 * @return attraction type
	 */
	public String getAttType() {
		return this.attType;
	}
	
	/**
	 * @param attType attraction type
	 */
	public void setAttType(String attType) {
		this.attType = attType;
	}
	
	public String toString() {
		return "==========================================================\nPlace: " + getTitle() + "\n" + "Address: " + getAddress() + "\nPrice estimated: RM " + getPrice() + "\n Attraction Type: " + getAttType() + "\nRatings: " + getRatings() + "\n==========================================================";
	}
	
	/**
	 * This method prints all the attractions
	 * @param destArr destinations array
	 */
	public void printAttractions(Destination[] destArr) 
    {
            int n = 1;
            System.out.println("===================================================================");
            System.out.println("Attractions Available: ");
            System.out.println("===================================================================");
            System.out.printf("%-5s %-35s","NUM", "TITLE");
            System.out.println();
            System.out.println("-------------------------------------------------------------------");

            for(int k = 0 ; k < destArr.length ;k++)
            {
            	
            		System.out.format("%-5d %-35s",
                        n , destArr[k].getTitle());
                System.out.println("");
                    n++;
            }
            System.out.println("-------------------------------------------------------------------");            
    }	
	
	/**
	 * This method prints the attraction based on filtered attraction type
	 * @param tmpArray a temp array
	 * @param returnNames the category name
	 */
	public void printAttractions(Attraction[] tmpArray , String returnNames) //overloading printAttractions
	{
		int n = 1;
		System.out.println("=====================================================================================================");
		System.out.println("List of all available Attractions according to "+ returnNames + " : ");
		System.out.println("=====================================================================================================");
	    System.out.printf("%-5s %-35s %-35s","NUM", "TITLE","ATTRACTION TYPE");
	    System.out.println();
	    System.out.println("-----------------------------------------------------------------------------------------------------");
		
		for(int k = 0 ; k < tmpArray.length ;k++)
		{
			System.out.format("%-5d %-35s %-35s",
					n , tmpArray[k].getTitle(),tmpArray[k].getAttType());
			System.out.println("");
				n++;
		}
		System.out.println("-----------------------------------------------------------------------------------------------------");
	}
	
	/**
	 * This method filters the attractions by type
	 * @param chosenCategory attraction type name
	 * @param myAtt array of attractions
	 * @param myIt itinerary array list
	 */
	public void filterAttractionsbyCategory(String chosenCategory, Attraction[] myAtt, ArrayList<Destination> myIt)
	{
		int n = 0;
		for(int k = 0 ; k < myAtt.length ;k++)
		{
			if((myAtt[k].getAttType()).equals(chosenCategory))
			{
				n++;
			}

		}
		Attraction[] tmpArray = new Attraction[n];
		n = 0;
		for(int k = 0 ; k < myAtt.length ;k++)
		{
			if((myAtt[k].getAttType()).equals(chosenCategory))
			{
				tmpArray[n] = myAtt[k];
				n++;
			}

		}
		if(chosenCategory != null)
		{
			printAttractions(tmpArray ,"Type(Category)");
			selectingOption(tmpArray, myIt);
		}
		
	}
	

}




