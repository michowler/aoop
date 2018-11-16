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
import javafx.scene.text.Text;
 
public class ItPlan {
	
	DecimalFormat df = new DecimalFormat("#.##"); 
	
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
//			ArrayList<Destination> tmpDel = new ArrayList<Destination>();
			double totalDestCost =0.0;
			double totalCost =0.0;
			double totalTransFee =0.0;
			
			if(curUser.getMyIt().size() > 0) {
	            for(int i = 0; i<curUser.getMyIt().size()-1; i++){
	            	final int index = i;  //important to get clicked button index	            	
	            	
	            	if(curUser.getMyIt().size() > 1) {	            		
	                    tmpArr.add(curUser.getMyIt().get(i+1));
	                    itPlanBox.getChildren().addAll(displayNewDestination(curUser.getMyIt().get(i), tmpArr.get(i)), delButton[i]);
	                    //addCost(curUser.getMyIt().get(i), tmpArr.get(i));
	                    totalDestCost += tmpArr.get(i).getPrice();	//cost destination	                    
	                    totalTransFee += Double.parseDouble(transFee(distance(curUser.getMyIt().get(i).getLatitude(), curUser.getMyIt().get(i).getLongtitude(), tmpArr.get(i).getLatitude(), tmpArr.get(i).getLongtitude()))[1]);
	                    
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
	                    	double totalDestCostN = 0.0;
	                    	double totalTransFeeN = 0.0;
	                    	double totalCostN = 0.0;
	                    	
	                    	for(int k = 0;k <curUser.getMyIt().size()-1; k++){
	                    		itPlanBox.getChildren().addAll(displayNewDestination(curUser.getMyIt().get(k), tmpArr.get(k)), delButton[k+1]);
	                    		totalTransFeeN += Double.parseDouble(transFee(distance(curUser.getMyIt().get(k).getLatitude(), curUser.getMyIt().get(k).getLongtitude(), tmpArr.get(k).getLatitude(), tmpArr.get(k).getLongtitude()))[1]);	                    		
	                    		totalDestCostN += tmpArr.get(k).getPrice();	                    		
	                    	}	  	         
	                    	
	                    	totalCostN = totalDestCostN+ totalTransFeeN;
	                    	df.format(totalCostN); 
	                    	itPlanBox.getChildren().add(budgetDetails(totalCostN, curUser)); 
	                    });
	            	}                        	
	            }  
	            //totalTransFee += Double.parseDouble(transFee(distance(curUser.getLatitude(), curUser.getLongtitude(), tmpArr.get(0).getLatitude(), tmpArr.get(0).getLongtitude()))[1]);
	            System.out.println(totalDestCost); //correct
	            System.out.println(totalTransFee);
	            
	            df.format(totalCost);
	            totalCost =	totalDestCost + totalTransFee;       
	            itPlanBox.getChildren().add(budgetDetails(totalCost, curUser));	            
	        }						 
	        
		}
		plan.setCenter(itPlanBox);
		
		return plan;
	}
	
	public HBox displayNewDestination(Destination dest1, Destination dest2) {
		HBox placeDetails = new HBox();				
		Label fromDest = new Label(dest1.getTitle());
		Label toDest = new Label(dest2.getTitle());
		Label costDest = new Label(df.format(dest2.getPrice())); //cost of the second place		
		Label btwDistance = new Label(df.format(distance(dest1.getLatitude(), dest1.getLongtitude(), dest2.getLatitude(), dest2.getLongtitude())));
		Label transTypeDest = new Label(
			transFee(
					distance(dest1.getLatitude(), dest1.getLongtitude(), dest2.getLatitude(), dest2.getLongtitude())
			)[0]				
		);
		
		String tCost = transFee(
				distance(dest1.getLatitude(), dest1.getLongtitude(), dest2.getLatitude(), dest2.getLongtitude())
		)[1];
				
		Label transCostDest = new Label(
				df.format(Double.parseDouble(tCost))
		);						
		placeDetails.setSpacing(20);
		placeDetails.getChildren().addAll(fromDest, toDest, costDest, btwDistance, transTypeDest, transCostDest);
			
		return placeDetails;
	}
	
	public VBox budgetDetails(double totalPrice, User user) {
		VBox budgetDetails = new VBox();						
		Label overBudget = new Label();		
		Label totalCost = new Label("Total Cost: " + 0);
		
		HBox info = new HBox();
	
		DecimalFormat df = new DecimalFormat("#.##"); 
		
		totalCost.setText("Total Cost: RM" + df.format(totalPrice) + (user.getCountry().equalsIgnoreCase("Malaysia")?"":" / " +convertCash(user, totalPrice)));
		  if(user.getBudget()>= (totalPrice)) {
			  overBudget.setText("You are still within budget!");
		  }
		  else if (user.getBudget()< (totalPrice)) {
			  overBudget.setText("You have exceeded your budget!");
		  }
		  else if (user.getBudget() == 0) {
			  overBudget.setText("Well..You don't have a budget. Have a nice trip!");
		  }
				
		info.setSpacing(20);
		info.getChildren().addAll(totalCost);
		budgetDetails.getChildren().addAll(overBudget,info);
		return budgetDetails;
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
        taxiFee = distance * 2.1;
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
		mainData dataSet = new mainData();
		dataSet.hotelList();
		dataSet.restList();
		dataSet.attrList();

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
