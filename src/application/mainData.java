package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class mainData {
	//you can use this arraylist ANY other ( .java ) files, here's how:
	//1. Example, in layout.java you wanna use the data
	//2. mainData hotelArray = new mainData();
	//3. hotelArray.hotelList(); is the arraylist for hotels
	
   public static <T> void printArray( ArrayList<T> inputArray ) {      
      for(T des : inputArray) {
         System.out.printf("%s ", des);
      }
      System.out.println();
	}
   
   @SuppressWarnings("unchecked")
public ArrayList<Destination> dataList(){
	    ArrayList<Destination> myData = new ArrayList<Destination>();
	    myData.add(new Hotels("Shangri-La-Hotel" , 489.00 ,3.1541 , 101.7063, 4.5 ,"11, Jalan Sultan Ismail, Kuala Lumpur, 50250 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/hotel1.jpg", 5));
	    myData.add(new Hotels("Sunway Putra Hotel",478.00, 3.1666 ,101.6928 , 4.3 ,"100 Jalan Putra, Chow Kit, 50350 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/hotel2.jpg" , 5));
	    myData.add(new Hotels("Mandarin Oriental Kuala Lumpur", 564.00 ,3.1560,101.7120, 4.6 ,"Kuala Lumpur City Centre, 50088 Kuala Lumpur, Federal Territory of Kuala Lumpur","img/hotel3.jpg", 5));
	    myData.add(new Hotels("Impiana KLCC Hotel" , 245.00 , 3.1536 ,101.7114 , 4.2 ,"Impiana Klcc Hotel & Spa, 13, Jalan Pinang, Kuala Lumpur, 50450 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/hotel4.jpg", 4));
	    myData.add(new Hotels("Furama Bukit Bintang" , 178.00 ,  3.1400 ,101.7101, 4.1 ,"136, Jalan Changkat Thambi Dollah, Pudu, 55100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur","img/hotel5.jpg", 4)); 
	    myData.add(new Hotels("33 Star Hotel", 67.00 , 3.1478 , 101.7118 , 3.3 ,"56-58, Jalan Sultan Ismail, Bukit Bintang, 50250 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/hotel6.jpg",2 ));
	    myData.add(new Hotels("Areena Star Hotel", 120.00,  3.1488 ,101.6989, 3.6 ,"49, Jalan Hang Lekiu, Kuala Lumpur, 50050 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/hotel7.jpg", 3)); 
		myData.add(new Hotels("Starpoints Hotel Kuala Lumpur", 268.00, 3.1541 ,101.6975, 3.6 ,"149, Jalan Masjid India, City Centre, 50100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur","img/hotel8.jpg",3)); 
		myData.add(new Hotels("Hotel Capitol Kuala Lumpur", 153.00 ,  3.1448 , 101.7098,4.0 ,"Bukit Bintang Street, Bukit Bintang, 55100 Kuala Lumpur, Federal Territory of Kuala Lumpur","img/hotel9.jpg", 3)); 
		myData.add(new Hotels("My Hotel", 119.00 , 3.1319, 101.6876 , 3.8 ,"1, Jalan Tun Sambanthan 4, Brickfields, 50470 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/hotel10.jpg",2));
		myData.add(new Restaurants("McDonald's KL Sentral", 20.00,3.1346 , 101.6866,3.6 ,"15, Jalan Stesen Sentral 5, Kuala Lumpur Sentral, 50470 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/restaurant1.jpg", "Fast Food"));
		myData.add(new Restaurants("Vin's Restaurant and Bar", 100.00,  3.1494 , 101.6298,4.4 ,"6, Lorong Datuk Sulaiman 1, Taman Tun Dr Ismail, 60000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur","img/restaurant2.jpg","Casual Dining"));
		myData.add(new Restaurants("Dining in the Dark", 300.00, 3.1475 ,101.7082, 4.6 ,"50A, Changkat Bukit Bintang, Bukit Bintang, 50200 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/restaurant3.jpg","Fine Dining"));
		myData.add(new Restaurants("Sitka Restaurant", 80.00, 3.1496 ,101.6613, 4.0 ,"Plaza Batai, 8 - 5, Jalan Batai, Bukit Damansara, 50490 Kuala Lumpur, Federal Territory of Kuala Lumpur", "img/restaurant4.jpg","Casual Dining"));
		myData.add(new Restaurants("The Owls Cafe", 150.00,3.0594 , 101.6733,  4.3 ,"Jalan Jalil Jaya 6, Bukit Jalil, 57000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/restaurant5.jpg","Casual Dining"));
		myData.add(new Restaurants("Cantaloupe at Troika Sky Dining", 350.00,  3.1576 ,101.7182, 4.5 ,"Level 23A Tower B, The Troika, 19 Persiaran KLCC, 50450 Kuala Lumpur","img/restaurant6.jpg", "Fine Dining"));
		myData.add(new Restaurants("El Cerdo Restaurant", 120.00,  3.1473 ,101.7078, 4.4 ,"43 & 45, Changkat Bukit Bintang, Bukit Bintang, 50200 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur","img/restaurant7.jpg", "Casual Dining"));
		myData.add(new Restaurants("Marble 8", 270.00,3.1568 ,101.7117,  4.1 ,"Petronas, Menara 3, Persiaran KLCC, Kuala Lumpur City Centre, 50088 Kuala Lumpur","img/restaurant8.jpg", "Fine Dining"));
		myData.add(new Restaurants("Fuel Shack", 70.00,  3.1444 ,101.7101, 3.7 ,"Lot G-28, Plaza Low Yat, No.7 Jalan Bukit Bintang,, 7, Jalan Bintang, Bukit Bintang, 55100 Kuala Lumpur, Federal Territory of Kuala Lumpur","img/restaurant9.jpg", "Fast Food"));
		myData.add(new Restaurants("Marini's on 57", 200.00,  3.1466 ,101.6958, 4.5 ,"Menara 3 Petronas, Persiaran KLCC, Kuala Lumpur City Centre, 50088 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/restaurant10.jpg","Fine Dinning"));
		myData.add(new Attraction("Petronas Towers", 70.00, 3.1577,101.7122 , 4.60, 4.3, "Petronas Twin Tower, Kuala Lumpur City Centre, 50450 Kuala Lumpur, Federal Territory of Kuala Lumpur", "img/attraction1.jpg","Landmark"));
		myData.add(new Attraction("Kuala Lumpur Tower", 80.00, 3.1537, 101.7041,4.40, 4.1,"2, Jalan Puncak, Kuala Lumpur, 50250 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/attraction2.jpg","Landmark"));
		myData.add(new Attraction("Suria Aquarium", 50.00,  3.1580,101.7117,4.30, 4.2, "KL Convention Centre, Jalan Pinang, Kuala Lumpur City Centre, 50088 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur","img/attraction3.jpg","Place of Interest"));
		myData.add(new Attraction("Central Market", 15.00,  3.1457, 101.6955,4.20, 4.9, "Jalan Hang Kasturi, City Centre, 50050 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/attraction4.jpg","Place of Interest"));
		myData.add(new Attraction("Merdeka Square", 30.00,  3.1478,101.6934, 4.30, 3.9, "Jalan Raja, Kuala Lumpur City Centre, 50050 Kuala Lumpur, Federal Territory of Kuala Lumpur","img/attraction5.jpg","Landmark"));
		myData.add(new Attraction("National Mosque", 25.00, 3.1421, 101.6918, 4.50, 5.9, "Jalan Perdana, Tasik Perdana, 50480 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur","img/attraction6.jpg","Landmark"));
		myData.add(new Attraction("Jamek Mosque", 10.00,  3.1489,101.6956, 3.70, 3.5, " Jalan Tun Perak, City Centre, 50050 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/attraction7.jpg","Landmark"));
		myData.add(new Attraction("Petaling Street Market", 20.00, 3.1457, 101.6955,3.90, 2.5, "Jalan Petaling, City Centre, 50000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/attraction8.jpg","Place of Interest"));
		myData.add(new Attraction("Lake Gardens", 3.00, 3.1430,101.6847, 4.40, 4.6, "Jalan Kebun Bunga, Tasik Perdana, 55100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur" ,"img/attraction9.jpg","Place of Interest"));
		myData.add(new Attraction("Sri Mahamariamman Temple", 5.00, 3.1440,  101.6965,4.30, 3.2, "Jalan Tun H S Lee, City Centre, 50000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/attraction10.jpg","Place of Interest"));
		Sorting st = new Sorting();
		Collections.sort(myData, st.new AlphabetOrder());		
	    return myData; 
   }
  
	public ArrayList<Destination> hotelList(){ 

		ArrayList<Destination> myHotels = new ArrayList<Destination>();
		myHotels.add(new Hotels("Shangri-La-Hotel" , 489.00 ,3.1541 , 101.7063, 4.5 ,"11, Jalan Sultan Ismail, Kuala Lumpur, 50250 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/hotel1.jpg", 5));
	    myHotels.add(new Hotels("Sunway Putra Hotel",478.00, 3.1666 ,101.6928 , 4.3 ,"100 Jalan Putra, Chow Kit, 50350 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/hotel2.jpg" , 5));
	    myHotels.add(new Hotels("Mandarin Oriental Kuala Lumpur", 564.00 ,3.1560,101.7120, 4.6 ,"Kuala Lumpur City Centre, 50088 Kuala Lumpur, Federal Territory of Kuala Lumpur","img/hotel3.jpg", 5));
	    myHotels.add(new Hotels("Impiana KLCC Hotel" , 245.00 , 3.1536 ,101.7114 , 4.2 ,"Impiana Klcc Hotel & Spa, 13, Jalan Pinang, Kuala Lumpur, 50450 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/hotel4.jpg", 4));
	    myHotels.add(new Hotels("Furama Bukit Bintang" , 178.00 ,  3.1400 ,101.7101, 4.1 ,"136, Jalan Changkat Thambi Dollah, Pudu, 55100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur","img/hotel5.jpg", 4)); 
	    myHotels.add(new Hotels("33 Star Hotel", 67.00 , 3.1478 , 101.7118 , 3.3 ,"56-58, Jalan Sultan Ismail, Bukit Bintang, 50250 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/hotel6.jpg",2 ));
	    myHotels.add(new Hotels("Areena Star Hotel", 120.00,  3.1488 ,101.6989, 3.6 ,"49, Jalan Hang Lekiu, Kuala Lumpur, 50050 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/hotel7.jpg", 3)); 
		myHotels.add(new Hotels("Starpoints Hotel Kuala Lumpur", 268.00, 3.1541 ,101.6975, 3.6 ,"149, Jalan Masjid India, City Centre, 50100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur","img/hotel8.jpg",3)); 
		myHotels.add(new Hotels("Hotel Capitol Kuala Lumpur", 153.00 ,  3.1448 , 101.7098,4.0 ,"Bukit Bintang Street, Bukit Bintang, 55100 Kuala Lumpur, Federal Territory of Kuala Lumpur","img/hotel9.jpg", 3)); 
		myHotels.add(new Hotels("My Hotel", 119.00 , 3.1319, 101.6876 , 3.8 ,"1, Jalan Tun Sambanthan 4, Brickfields, 50470 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/hotel10.jpg",2));
		
		return myHotels; 
	}
	
	public ArrayList<Destination> restList(){ 

		ArrayList<Destination> myRests = new ArrayList<Destination>();
		myRests.add(new Restaurants("McDonald's KL Sentral", 20.00,3.1346 , 101.6866,3.6 ,"15, Jalan Stesen Sentral 5, Kuala Lumpur Sentral, 50470 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/restaurant1.jpg", "Fast Food"));
		myRests.add(new Restaurants("Vin's Restaurant and Bar", 100.00,  3.1494 , 101.6298,4.4 ,"6, Lorong Datuk Sulaiman 1, Taman Tun Dr Ismail, 60000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur","img/restaurant2.jpg","Casual Dining"));
		myRests.add(new Restaurants("Dining in the Dark", 300.00, 3.1475 ,101.7082, 4.6 ,"50A, Changkat Bukit Bintang, Bukit Bintang, 50200 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/restaurant3.jpg","Fine Dining"));
		myRests.add(new Restaurants("Sitka Restaurant", 80.00, 3.1496 ,101.6613, 4.0 ,"Plaza Batai, 8 - 5, Jalan Batai, Bukit Damansara, 50490 Kuala Lumpur, Federal Territory of Kuala Lumpur", "img/restaurant4.jpg","Casual Dining"));
		myRests.add(new Restaurants("The Owls Cafe", 150.00,3.0594 , 101.6733,  4.3 ,"Jalan Jalil Jaya 6, Bukit Jalil, 57000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/restaurant5.jpg","Casual Dining"));
		myRests.add(new Restaurants("Cantaloupe at Troika Sky Dining", 350.00,  3.1576 ,101.7182, 4.5 ,"Level 23A Tower B, The Troika, 19 Persiaran KLCC, 50450 Kuala Lumpur","img/restaurant6.jpg", "Fine Dining"));
		myRests.add(new Restaurants("El Cerdo Restaurant", 120.00,  3.1473 ,101.7078, 4.4 ,"43 & 45, Changkat Bukit Bintang, Bukit Bintang, 50200 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur","img/restaurant7.jpg", "Casual Dining"));
		myRests.add(new Restaurants("Marble 8", 270.00,3.1568 ,101.7117,  4.1 ,"Petronas, Menara 3, Persiaran KLCC, Kuala Lumpur City Centre, 50088 Kuala Lumpur","img/restaurant8.jpg", "Fine Dining"));
		myRests.add(new Restaurants("Fuel Shack", 70.00,  3.1444 ,101.7101, 3.7 ,"Lot G-28, Plaza Low Yat, No.7 Jalan Bukit Bintang,, 7, Jalan Bintang, Bukit Bintang, 55100 Kuala Lumpur, Federal Territory of Kuala Lumpur","img/restaurant9.jpg", "Fast Food"));
		myRests.add(new Restaurants("Marini's on 57", 200.00,  3.1466 ,101.6958, 4.5 ,"Menara 3 Petronas, Persiaran KLCC, Kuala Lumpur City Centre, 50088 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/restaurant10.jpg","Fine Dinning"));
		
		return myRests; 
	}
	
	public ArrayList<Destination> attrList(){ 

		ArrayList<Destination> myAtts = new ArrayList<Destination>();
		myAtts.add(new Attraction("Petronas Towers", 70.00, 3.1577,101.7122 , 4.60, 4.3, "Petronas Twin Tower, Kuala Lumpur City Centre, 50450 Kuala Lumpur, Federal Territory of Kuala Lumpur", "img/attraction1.jpg","Landmark"));
		myAtts.add(new Attraction("Kuala Lumpur Tower", 80.00, 3.1537, 101.7041,4.40, 4.1,"2, Jalan Puncak, Kuala Lumpur, 50250 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/attraction2.jpg","Landmark"));
		myAtts.add(new Attraction("Suria Aquarium", 50.00,  3.1580,101.7117,4.30, 4.2, "KL Convention Centre, Jalan Pinang, Kuala Lumpur City Centre, 50088 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur","img/attraction3.jpg","Place of Interest"));
		myAtts.add(new Attraction("Central Market", 15.00,  3.1457, 101.6955,4.20, 4.9, "Jalan Hang Kasturi, City Centre, 50050 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/attraction4.jpg","Place of Interest"));
		myAtts.add(new Attraction("Merdeka Square", 30.00,  3.1478,101.6934, 4.30, 3.9, "Jalan Raja, Kuala Lumpur City Centre, 50050 Kuala Lumpur, Federal Territory of Kuala Lumpur","img/attraction5.jpg","Landmark"));
		myAtts.add(new Attraction("National Mosque", 25.00, 3.1421, 101.6918, 4.50, 5.9, "Jalan Perdana, Tasik Perdana, 50480 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur","img/attraction6.jpg","Landmark"));
		myAtts.add(new Attraction("Jamek Mosque", 10.00,  3.1489,101.6956, 3.70, 3.5, " Jalan Tun Perak, City Centre, 50050 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/attraction7.jpg","Landmark"));
		myAtts.add(new Attraction("Petaling Street Market", 20.00, 3.1457, 101.6955,3.90, 2.5, "Jalan Petaling, City Centre, 50000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/attraction8.jpg","Place of Interest"));
		myAtts.add(new Attraction("Lake Gardens", 3.00, 3.1430,101.6847, 4.40, 4.6, "Jalan Kebun Bunga, Tasik Perdana, 55100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur" ,"img/attraction9.jpg","Place of Interest"));
		myAtts.add(new Attraction("Sri Mahamariamman Temple", 5.00, 3.1440,  101.6965,4.30, 3.2, "Jalan Tun H S Lee, City Centre, 50000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "img/attraction.jpg","Place of Interest"));
		
		return myAtts; 
	}
	
	public ArrayList<Destination> seedData(ArrayList<Destination> myIt) {		
		mainData dataSet = new mainData();
		dataSet.hotelList();
		dataSet.restList();
		dataSet.attrList();
		
		System.out.println(dataSet.restList().get(5).getClass());

		myIt.add(dataSet.attrList().get(2));
		myIt.add(dataSet.attrList().get(2));
		myIt.add(dataSet.restList().get(4));
		myIt.add(dataSet.attrList().get(6));
		myIt.add(dataSet.attrList().get(8));
		myIt.add(dataSet.restList().get(5));
		myIt.add(dataSet.hotelList().get(1));
		return myIt;

	}

}
