package application;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
 
public class ItPlan {
	private int i = -1; 
	public BorderPane displayPlan(User curUser) {
		seedData(curUser.getMyIt());
		
		BorderPane plan = new BorderPane();
		VBox itPlanBox = new VBox();		
		HBox menuPlan = new HBox();
		
		Label fr = new Label("from: ");
		Label to = new Label("to: ");
		Label costLabel = new Label("cost: ");
		Label dsLabel = new Label("distance: ");
		Label typeLabel = new Label("Trans type: ");
		Label transCostLabel = new Label("Trans cost: ");
		
		//https://stackoverflow.com/questions/47109404/javafx-hbox-with-a-close-button
		menuPlan.getChildren().addAll(fr, to, costLabel, dsLabel,typeLabel, transCostLabel );
		itPlanBox.getChildren().add(menuPlan);
		
		if (curUser.getMyIt().isEmpty()) {
			itPlanBox.getChildren().clear(); 
			itPlanBox.getChildren().add(new Label ("nothing here, please add some destinations to your itinerary")); 
		}else {	
			int sizeOfIt = curUser.getMyIt().size();
			Button[] delButton = new Button[sizeOfIt];
			for (int m=0; m <sizeOfIt; m++) {
				delButton[m]  = new Button("x");
			}
			
			Destination dest = new Destination(curUser.getLatitude(), curUser.getLongtitude());			
			curUser.addMyIt(dest);
			
			ArrayList<Destination> tmpArr = new ArrayList<Destination>();
			ArrayList<Destination> tmpDel = new ArrayList<Destination>();
			
			if(curUser.getMyIt().size() > 0) {
	            for(int i = 0; i<curUser.getMyIt().size()-1; i++){
	            	final int index = i;  
	            	
	            	if(curUser.getMyIt().size() > 1) {	            		
	                    tmpArr.add(curUser.getMyIt().get(i+1));
	                    itPlanBox.getChildren().addAll(displayNewDestination(curUser.getMyIt().get(i), tmpArr.get(i)), delButton[i]);	                    	                 	
//	                    totalTransCost += Double.parseDouble(transFee(btDis)[1]);	                    
	                    delButton[i].setOnAction(e->{
	                    	String tmpDest = curUser.getMyIt().get(index+1).getTitle();
	                    	System.out.println(tmpDest);
	                    	
	                    	System.out.println(curUser.getMyIt().get(index+1));
	                    	curUser.removeMyIt(index+1);
	                    	tmpArr.remove(index);
	                    		                    	
	                    	for(int k = 0;k <curUser.getMyIt().size()-1; k++){	                    		
	                    		tmpArr.add(curUser.getMyIt().get(k+1));
	                    	}
	                    	
	                    	itPlanBox.getChildren().clear(); 
	                    	for(int k = 0;k <curUser.getMyIt().size()-1; k++){
	                    		itPlanBox.getChildren().addAll(displayNewDestination(curUser.getMyIt().get(k), tmpArr.get(k)), delButton[k]);
	                    	}

	                    });
	            	}                        	
	            }  
//	            totalTransCost += Double.parseDouble(transFee(btUser)[1]);            
	        }						 
	        
		}
		plan.setCenter(itPlanBox);
		return plan;
	}
	
	public HBox displayNewDestination(Destination dest1, Destination dest2) {
		HBox placeDetails = new HBox();
		Label fromDest = new Label(dest1.getTitle());
		Label toDest = new Label(dest2.getTitle());
		Label costDest = new Label(Double.toString(dest2.getPrice())); //cost of the second place
		Label btwDistance = new Label(Double.toString(distance(dest1.getLatitude(), dest1.getLongtitude(), dest2.getLatitude(), dest2.getLongtitude())));
		Label transTypeDest = new Label(
			transFee(
					distance(dest1.getLatitude(), dest1.getLongtitude(), dest2.getLatitude(), dest2.getLongtitude())
			)[0]
				
		);
		Label transCostDest = new Label(
			transFee(
					distance(dest1.getLatitude(), dest1.getLongtitude(), dest2.getLatitude(), dest2.getLongtitude())
			)[1]
		);		
		placeDetails.getChildren().addAll(fromDest, toDest, costDest, btwDistance, transTypeDest, transCostDest);
		
		return placeDetails;
	}
	
	public static double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;		
		dist = dist * 1.1609344;
		return (dist);
	}
	
	/**
	 * This method changes the degree to radians
	 * @param deg the degree
	 * @return deg
	 */
	public static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	
	/**
	 * This method changes the radians to degree
	 * @param rad the radian
	 * @return rad
	 */
	public static double rad2deg(double rad) {
		 return (rad * 180.0 / Math.PI);
	}

	/**
	 * This method is used to calculate any amount to the expected currency value base on user details
	 * @param user user object
	 * @param cost the cost
	 * @return converted amount 
	 */
	public static String convertCash(User user, Double cost) {
		DecimalFormat df = new DecimalFormat("#.##");
		double curCash = 0.0;
		String cur = " ";
		if(user.getCountry().toLowerCase().equals("malaysia")) {			
			curCash = cost;
		}else if (user.getCountry().toLowerCase().equals("japan")){
			cur = "YEN";
			curCash = cost * 27.66;
		}else if (user.getCountry().toLowerCase().equals("singapore")){
			cur = "SGD";
			curCash = cost * 0.33 ;
		}else if (user.getCountry().toLowerCase().equals("taiwan")){
			cur = "NTD";
			curCash = cost * 7.47;
		}		
		return df.format(curCash) + " " + cur ;
	}
	
	
	public static String[] transFee(double distance) {
        
        String[][] tp = new String[10][10];
        double busFee =0, taxiFee=0, trainFee=0;
        String type ="";
        
        busFee = distance * 1.2;
        taxiFee = distance * 2.3;
        trainFee = distance * 1.3;
        
        if(distance <= 1.5) {
        	type = "Walking";
            tp[0][0] = type;
            tp[0][1] = "0";
        }else if(distance <= 3) {
        	type = "Bus";
        	tp[0][0] = type;
            tp[0][1] = Double.toString(busFee);
        }else if(distance <= 5) {
        	type = "Train";
        	tp[0][0] = type;
            tp[0][1] = Double.toString(trainFee);;
        }else if(distance > 5) {
        	type = "Taxi";
        	tp[0][0] = type;
            tp[0][1] = Double.toString(taxiFee);;
        }
        return tp[0];        
    }
	
	public ArrayList<Destination> seedData(ArrayList<Destination> myIt) {		
		Attraction [] myAtts = new Attraction[10];
		Restaurants[] myRests = new Restaurants[10];
		Hotels[] myHotels = new Hotels[10];
		
		myHotels[0] = new Hotels("Shangri-La-Hotel" , 489.00 ,3.1541 , 101.7063, 4.5 ,"11, Jalan Sultan Ismail, Kuala Lumpur, 50250 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", 5);
		myHotels[1] = new Hotels("Sunway Putra Hotel",478.00, 3.1666 ,101.6928 , 4.3 ,"100 Jalan Putra, Chow Kit, 50350 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", 5);
		myHotels[2] = new Hotels("Mandarin Oriental Kuala Lumpur", 564.00 ,3.1560,101.7120, 4.6 ,"Kuala Lumpur City Centre, 50088 Kuala Lumpur, Federal Territory of Kuala Lumpur", 5);
		myHotels[3] = new Hotels("Impiana KLCC Hotel" , 245.00 , 3.1536 ,101.7114 , 4.2 ,"Impiana Klcc Hotel & Spa, 13, Jalan Pinang, Kuala Lumpur, 50450 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", 4);
		myHotels[4] = new Hotels("Furama Bukit Bintang" , 178.00 ,  3.1400 ,101.7101, 4.1 ,"136, Jalan Changkat Thambi Dollah, Pudu, 55100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur",4); 
		myHotels[5] = new Hotels("33 Star Hotel", 67.00 , 3.1478 , 101.7118 , 3.3 ,"56-58, Jalan Sultan Ismail, Bukit Bintang, 50250 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", 2 );
		myHotels[6] = new Hotels("Areena Star Hotel", 120.00,  3.1488 ,101.6989, 3.6 ,"49, Jalan Hang Lekiu, Kuala Lumpur, 50050 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", 3); 
		myHotels[7] = new Hotels("Starpoints Hotel Kuala Lumpur", 268.00, 3.1541 ,101.6975, 3.6 ,"149, Jalan Masjid India, City Centre, 50100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur",3); 
		myHotels[8] = new Hotels("Hotel Capitol Kuala Lumpur", 153.00 ,  3.1448 , 101.7098,4.0 ,"Bukit Bintang Street, Bukit Bintang, 55100 Kuala Lumpur, Federal Territory of Kuala Lumpur",3); 
		myHotels[9] = new Hotels("My Hotel", 119.00 , 3.1319, 101.6876 , 3.8 ,"1, Jalan Tun Sambanthan 4, Brickfields, 50470 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", 2);
		
		myRests[0] = new Restaurants("McDonald's KL Sentral", 20.00,3.1346 , 101.6866,3.6 ,"15, Jalan Stesen Sentral 5, Kuala Lumpur Sentral, 50470 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "Fast Food");
		myRests[1] = new Restaurants("Vin's Restaurant and Bar", 100.00,  3.1494 , 101.6298,4.4 ,"6, Lorong Datuk Sulaiman 1, Taman Tun Dr Ismail, 60000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur","Casual Dining");
		myRests[2] = new Restaurants("Dining in the Dark", 300.00, 3.1475 ,101.7082, 4.6 ,"50A, Changkat Bukit Bintang, Bukit Bintang, 50200 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "Fine Dining");
		myRests[3] = new Restaurants("Sitka Restaurant", 80.00, 3.1496 ,101.6613, 4.0 ,"Plaza Batai, 8 - 5, Jalan Batai, Bukit Damansara, 50490 Kuala Lumpur, Federal Territory of Kuala Lumpur", "Casual Dining");
		myRests[4] = new Restaurants("The Owls Cafe", 150.00,3.0594 , 101.6733,  4.3 ,"Jalan Jalil Jaya 6, Bukit Jalil, 57000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "Casual Dining");
		myRests[5] = new Restaurants("Cantaloupe at Troika Sky Dining", 350.00,  3.1576 ,101.7182, 4.5 ,"Level 23A Tower B, The Troika, 19 Persiaran KLCC, 50450 Kuala Lumpur", "Fine Dining");
		myRests[6] = new Restaurants("El Cerdo Restaurant", 120.00,  3.1473 ,101.7078, 4.4 ,"43 & 45, Changkat Bukit Bintang, Bukit Bintang, 50200 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "Casual Dining");
		myRests[7] = new Restaurants("Marble 8", 270.00,3.1568 ,101.7117,  4.1 ,"Petronas, Menara 3, Persiaran KLCC, Kuala Lumpur City Centre, 50088 Kuala Lumpur", "Fine Dining");
		myRests[8] = new Restaurants("Fuel Shack", 70.00,  3.1444 ,101.7101, 3.7 ,"Lot G-28, Plaza Low Yat, No.7 Jalan Bukit Bintang,, 7, Jalan Bintang, Bukit Bintang, 55100 Kuala Lumpur, Federal Territory of Kuala Lumpur", "Fast Food");
		myRests[9] = new Restaurants("Marini's on 57", 200.00,  3.1466 ,101.6958, 4.5 ,"Menara 3 Petronas, Persiaran KLCC, Kuala Lumpur City Centre, 50088 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "Fine Dinning");
		
		myAtts[0] = new Attraction("Petronas Towers", 70.00, 3.1577,101.7122 , 4.60, 4.3, "Petronas Twin Tower, Kuala Lumpur City Centre, 50450 Kuala Lumpur, Federal Territory of Kuala Lumpur", "Landmark");
		myAtts[1] = new Attraction("Kuala Lumpur Tower", 80.00, 3.1537, 101.7041,4.40, 4.1,"2, Jalan Puncak, Kuala Lumpur, 50250 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "Landmark");
		myAtts[2] = new Attraction("Suria Aquarium", 50.00,  3.1580,101.7117,4.30, 4.2, "Kuala Lumpur Convention Centre Complex, Jalan Pinang, Kuala Lumpur City Centre, 50088 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur","Place of Interest");
		myAtts[3] = new Attraction("Central Market", 15.00,  3.1457, 101.6955,4.20, 4.9, "Jalan Hang Kasturi, City Centre, 50050 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "Place of Interest");
		myAtts[4] = new Attraction("Merdeka Square", 30.00,  3.1478,101.6934, 4.30, 3.9, "Jalan Raja, Kuala Lumpur City Centre, 50050 Kuala Lumpur, Federal Territory of Kuala Lumpur","Landmark");
		myAtts[5] = new Attraction("National Mosque", 25.00, 3.1421, 101.6918, 4.50, 5.9, "Jalan Perdana, Tasik Perdana, 50480 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur","Landmark");
		myAtts[6] = new Attraction("Jamek Mosque", 10.00,  3.1489,101.6956, 3.70, 3.5, " Jalan Tun Perak, City Centre, 50050 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "Landmark");
		myAtts[7] = new Attraction("Petaling Street Market", 20.00, 3.1457, 101.6955,3.90, 2.5, "Jalan Petaling, City Centre, 50000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "Place of Interest");
		myAtts[8] = new Attraction("Lake Gardens", 3.00, 3.1430,101.6847, 4.40, 4.6, "Jalan Kebun Bunga, Tasik Perdana, 55100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur" ,"Place of Interest");
		myAtts[9] = new Attraction("Sri Mahamariamman Temple", 5.00, 3.1440,  101.6965,4.30, 3.2, "Jalan Tun H S Lee, City Centre, 50000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "Place of Interest");
		myIt.add(myAtts[2]);
		myIt.add(myRests[4]);
		myIt.add(myAtts[6]);
		myIt.add(myAtts[8]);
		myIt.add(myRests[5]);
		myIt.add(myHotels[1]);
		return myIt;

	}
}
