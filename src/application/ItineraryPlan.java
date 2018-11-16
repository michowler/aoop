package application;
import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

//user manage + view itinerary plan
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ItineraryPlan{
	ArrayList<Destination> destination = new ArrayList<Destination>();
	User user;
	int initialSize;
	
	Button addPlans;
	Button[] deletePlans;
	double btDis = 0.0;
	Label overBudget;
	Label budget; 
	Label totalCost;
	Label[] numberDestination;
	Label[] costField; 
	Label[] fromField; 
	Label[] toField; 
	Label[] distanceField; 
	Label[] transTypeField; 
	Label[] transCostField; 
	DecimalFormat df = new DecimalFormat("#.##");   
	HBox[] detailsBox;
	public ItineraryPlan() {
		
	}
	
	public ItineraryPlan(ArrayList<Destination> destination, User user) {
		this.user = user;
		if(!(destination.isEmpty())) {
			for(int x = 0; x < destination.size(); x++) {
				this.destination.add(destination.get(x));
			}	
		}
	}
	
	public VBox displayMyIt() {
		
		overBudget = new Label();
		budget = new Label("Your budget: " + user.getBudget());
		totalCost = new Label("Total Cost: " + 0);
		initialSize = destination.size();
		detailsBox = new HBox[destination.size()+1];
		numberDestination = new Label[destination.size()];
		costField = new Label[destination.size()];
		fromField = new Label[destination.size()];
		toField = new Label[destination.size()];
		distanceField = new Label[destination.size()];
		transTypeField = new Label[destination.size()];
		transCostField = new Label[destination.size()];
		deletePlans = new Button[destination.size()];	
		
		
		HBox buttonBox = new HBox();
		Button home = new Button("Home");
		Button search = new Button("Search");
		Button myItinerary = new Button("My Itinerary");
		Button askQuestion = new Button("Ask Us A Question");
		Button logOut = new Button("Logout");
		home.setMinSize(115, 25);
		search.setMinSize(115, 25);
		
		myItinerary.setMinSize(115, 25);
		askQuestion.setMinSize(115, 25);
		logOut.setMinSize(115, 25);
		buttonBox.getChildren().addAll(home,search,myItinerary,askQuestion,logOut);
	
		VBox vbox = new VBox();
		
		int number = 1;
	
		double totalPrice = 0.0;
		double totalTransCost = 0.0;
		HBox labelsBox = new HBox();
		Label numberLabel = new Label("No");
		Label fromLabel = new Label("From\t\t-");
		Label toLabel = new Label("To");
		Label costLabel = new Label("Cost (MYR)");
		Label distanceLabel = new Label("Distance(KM)");
		Label transTypeLabel = new Label("Trans Type");
		Label transCostLabel = new Label("Trans Cost(MYR)");
		
		numberLabel.setStyle("-fx-font-weight: bold;");
		fromLabel.setStyle("-fx-font-weight: bold;");
		numberLabel.setStyle("-fx-font-weight: bold;");
		toLabel.setStyle("-fx-font-weight: bold;");
		costLabel.setStyle("-fx-font-weight: bold;");
		distanceLabel.setStyle("-fx-font-weight: bold;");
		transTypeLabel.setStyle("-fx-font-weight: bold;");
		transCostLabel.setStyle("-fx-font-weight: bold;");
		
		numberLabel.setMinWidth(40);
		fromLabel.setMinWidth(200);
		fromLabel.setAlignment(Pos.TOP_LEFT);
		toLabel.setMinWidth(200);
		toLabel.setAlignment(Pos.TOP_LEFT);
		costLabel.setMinWidth(70);
		distanceLabel.setMinWidth(100);
		transTypeLabel.setMinWidth(70);
		transCostLabel.setMinWidth(120);
		
		labelsBox.getChildren().addAll(numberLabel, fromLabel, toLabel, costLabel, distanceLabel, transTypeLabel, transCostLabel);
				FlowPane flowPane = new FlowPane();
		flowPane.setMinSize(650, 300);
		labelsBox.setSpacing(20);
		vbox.getChildren().add(labelsBox);
        budget.setText("Your budget: RM" + user.getBudget() + (user.getCountry().equalsIgnoreCase("Malaysia")? "": "/" + convertCash(user, user.getBudget())));
      
       /**
        * Check if destination.size() is empty
        */
		if(!(destination.isEmpty())) {
				for(int x = 0; x < destination.size(); x++) {
					addDetails(number, x); //here
					
					vbox.getChildren().add(detailsBox[x]);
					int z = x;
					
					//delete button
					deletePlans[x].setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							int dialogButton = JOptionPane.YES_NO_OPTION;
							int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?","Warning",dialogButton);
							
							//if user confirms that they want to delete than execute 
							if(dialogResult == JOptionPane.YES_OPTION) {
								int numbers = 1;
								int no = 0;
								double totalTransCost = 0.0;
								double totalPrice = 0.0;
								double btDis = 0.0;

								if(destination.size()>0) {
									//to get the actual index of destination. 
									//when a row is cleared, the index of the button doesn't change accordingly. 
									for(int r = 0; r < destination.size(); r++) {
										if(toField[z].getText().equals(destination.get(r).getTitle())) {
											//destination is removed is the text in toField is the same as destination.get(r).getTitle()
											//destination.size() is then decreased		
											deleteRow(r, z);
											
										}
									}
									//a < initialSize because the size of the label and box is the intial size of the destination ArrayList,
									//so initialSize is used to go through the entire label and box array.
									//destination is removed, so size decreases. 
									//if destination.size() is used, it can't go through the entire label and box array.
							
									for(int a = 0; a < initialSize; a++) {
										//to re-organize the itinerary plan in ascending order.
										//and to update the itinerary plan
										if(!(detailsBox[a].getChildren().isEmpty())) {
											if(numbers==1) {
												btDis = distance(user.getLatitude(), user.getLongtitude(), destination.get(no).getLatitude(), 
														destination.get(no).getLongtitude());
												fromField[a].setText("Your Location: " + user.getLocation());
											}else {
												btDis = distance(destination.get(no-1).getLatitude(), destination.get(no-1).getLongtitude(), 
														destination.get(no).getLatitude(), destination.get(no).getLongtitude());
												fromField[a].setText(destination.get(no-1).getTitle());
											}
											numberDestination[a].setText(Integer.toString(numbers));
											toField[a].setText(destination.get(no).getTitle());
											costField[a].setText(Double.toString(destination.get(no).getPrice()));
											distanceField[a].setText(df.format(btDis));
											transTypeField[a].setText(transType(btDis));
											transCostField[a].setText(df.format(transFee(btDis)));
											totalPrice += destination.get(no).getPrice();
											totalTransCost += transFee(btDis);
											getBudget(btDis, totalPrice, totalTransCost);
											numbers++;
											no++;
										}		
									}
								}
//								else {
//								deleteRow(0, 0);	
//								totalPrice = 0.0;
//								totalTransCost = 0.0;
//								getBudget(btDis, totalPrice, totalTransCost);
//								}
							}	
						}
					});
					totalPrice += destination.get(x).getPrice();
					totalTransCost += transFee(btDis);
					number++;
				}
				
		}else {
			Label empty = new Label("You have not added anything to your itinerary plan!");
			vbox.getChildren().add(empty);
			empty.setAlignment(Pos.CENTER);
		}
		getBudget(btDis, totalPrice, totalTransCost);	
		vbox.getChildren().addAll(overBudget, budget, totalCost);
		overBudget.setAlignment(Pos.CENTER);
		budget.setAlignment(Pos.CENTER);
		totalCost.setAlignment(Pos.CENTER);
		ScrollPane scrollBar = new ScrollPane(vbox);
		vbox.setAlignment(Pos.CENTER);
		flowPane.getChildren().addAll(buttonBox, vbox);
		flowPane.setMargin(buttonBox, new Insets(10, 10, 10, 10));	
	
		return vbox;
	}

	
	/**
 	* This method is to add the text and data to the itinerary plan
 	* @param number
 	* @param x
 	*/
	public void addDetails(int number, int x) {
		
		if(number==1) {
			btDis = distance(user.getLatitude(), user.getLongtitude(), destination.get(x).getLatitude(), destination.get(x).getLongtitude());
			fromField[x] = new Label("Your Location: " + user.getLocation()); 
		}
		else {
			btDis = distance(destination.get(x-1).getLatitude(), destination.get(x-1).getLongtitude(), destination.get(x).getLatitude(), destination.get(x).getLongtitude());
			fromField[x] = new Label(destination.get(x-1).getTitle());
		}
		numberDestination[x] = new Label();
		numberDestination[x].setText(Integer.toString(number));
		toField[x] = new Label(destination.get(x).getTitle());
		costField[x] = new Label(Double.toString(destination.get(x).getPrice()));
		distanceField[x] = new Label(df.format(btDis));
		transTypeField[x] = new Label(transType(btDis));
		transCostField[x] = new Label((df.format(transFee(btDis))));
		deletePlans[x] = new Button("X");
		numberDestination[x].setMinWidth(40);
		fromField[x].setMinWidth(200);
		toField[x].setMinWidth(200);
		costField[x].setMinWidth(70);
		distanceField[x].setMinWidth(100);
		transTypeField[x].setMinWidth(70);
		transCostField[x].setMinWidth(120);
		deletePlans[x].setMinWidth(20);
		detailsBox[x] = new HBox();
		detailsBox[x].getChildren().addAll(numberDestination[x], fromField[x], toField[x], costField[x], distanceField[x], transTypeField[x], transCostField[x]
				, deletePlans[x]);
		detailsBox[x].setSpacing(20);
	
	}
	/* delete itinerary plan*/ 

	public void deleteRow(int r, int z) {
		detailsBox[z].getChildren().clear();
		destination.remove(r);	
	}

	/* get budget of itinerary plan */
	public void getBudget(double btDis, double totalPrice, double totalTransCost) {
		totalCost.setText("Total Cost: RM" + df.format(totalPrice+totalTransCost) + (user.getCountry().equalsIgnoreCase("Malaysia")?"":" / " + 
				convertCash(user, totalPrice)));
		if(user.getBudget()>= (totalPrice+totalTransCost)) {
		  overBudget.setText("You are still within budget!");
		}
		else if (user.getBudget()< (totalPrice+totalTransCost)) {
		  overBudget.setText("You have exceeded your budget!");
		}
		else if (user.getBudget() == 0) {
		  overBudget.setText("Well..You don't have a budget. Have a nice trip!");
		}
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

/**
 * This method is used to return the transport type based on the distance given between two destinations.
 * @param distance
 * @return
 */
	public static String transType(double distance) {
       double busFee =0, taxiFee=0, trainFee=0, fee=0;
       String type ="";
       
       busFee = distance * 1.2;
       taxiFee = distance * 2.3;
       trainFee = distance * 1.3;
       
       if(distance <= 1.5) {
       	type = "Walking";
          
       }else if(distance <= 3) {
       	type = "Bus";
       	
       }else if(distance <= 5) {
       	type = "Train";
       	
       }else if(distance > 5) {
       	type = "Taxi";
       	
       }
       return type;        
   }
/**
 * This method is used to calculate the transportation fee based on the distance
 * @param distance
 * @return
 */
	public static double transFee(double distance) {
	
        double busFee =0, taxiFee=0, trainFee=0, fee=0, transFee = 0.0;
        String type ="";
        
        busFee = distance * 1.2;
        taxiFee = distance * 2.3;
        trainFee = distance * 1.3;
        
        if(distance <= 1.5) {
            transFee = 0.0;
        }else if(distance <= 3) {
        	transFee = busFee;
        }else if(distance <= 5) {
        	transFee = trainFee;
        }else if(distance > 5) {
        	transFee = taxiFee;
        }
        return transFee;        
	}
}

