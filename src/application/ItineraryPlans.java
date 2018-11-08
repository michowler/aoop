package application;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

//user manage + view itinerary plan
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ItineraryPlans {
//	public void seedItPlan(ArrayList<Destination> destination, User user) {
//		
//			if(!(destination.isEmpty())) {
//			for(int x = 0; x < destination.size(); x++) {
//				destination.add(destination.get(x));
//			}
//		}
//		System.out.println("destination: \n" + destination);		
//	}
	
	public VBox displayItPlan(ArrayList<Destination> destination, User user) {		
		if(!(destination.isEmpty())) {
			for(int x = 0; x < destination.size(); x++) {
				ItineraryPlan.destination.add(destination.get(x));
			}
		}
		destination = ItineraryPlan.destination;
		
		int count = 0;
		int initialSize = user.getMyIt().size();
		
		Button addPlans = new Button("+");
		Button[] deletePlans = new Button[user.getMyIt().size()];	
		
		Label overBudget = new Label();
		Label budget = new Label("Your budget: " + user.getBudget());
		Label totalCost = new Label("Total Cost: " + 0);
		Label[] numberDestination = new Label[user.getMyIt().size()];
		Label[] costField = new Label[user.getMyIt().size()];
		Label[] fromField = new Label[user.getMyIt().size()];
		Label[] toField = new Label[user.getMyIt().size()];
		Label[] distanceField = new Label[user.getMyIt().size()];
		Label[] transTypeField = new Label[user.getMyIt().size()];
		Label[] transCostField = new Label[user.getMyIt().size()];
		
		VBox[] numBox = new VBox[user.getMyIt().size()+1];
		VBox[] costBox = new VBox[user.getMyIt().size()+1];
		VBox[] fromBox = new VBox[user.getMyIt().size()+1];
		VBox[] toBox = new VBox[user.getMyIt().size()+1];
		VBox[] distanceBox = new VBox[user.getMyIt().size()+1];
		VBox[] transTypeBox = new VBox[user.getMyIt().size()+1];
		VBox[] transCostBox = new VBox[user.getMyIt().size()+1];
		VBox[] deleteButtonBox = new VBox[user.getMyIt().size()];
		
		HBox[] detailsBox = new HBox[user.getMyIt().size()+1];
		
		DecimalFormat df = new DecimalFormat("#.##");   
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
		
		numBox[0] = new VBox();
		numBox[0].getChildren().add(new Label("Number"));
		costBox[0] = new VBox();
		costBox[0].getChildren().add(new Label("Cost(MYR)"));
		fromBox[0] = new VBox();
		fromBox[0].getChildren().add(new Label("From\t\t-"));
		toBox[0] = new VBox();
		toBox[0].getChildren().add(new Label("To"));
		distanceBox[0] = new VBox();
		distanceBox[0].getChildren().add(new Label("Distance(KM)"));
		transTypeBox[0] = new VBox();
		transTypeBox[0].getChildren().add(new Label("Trans Type"));
		transCostBox[0] = new VBox();
		transCostBox[0].getChildren().add(new Label("Trans Cost(MYR)"));
		VBox vbox = new VBox();
		
		int number = 1;
		double btDis = 0.0;
		double totalPrice = 0.0;
		double totalTransCost = 0.0;
		HBox labelsBox = new HBox();
		FlowPane flowPane = new FlowPane();
		flowPane.setMinSize(650, 300);
		detailsBox[0] = new HBox();
		detailsBox[0].getChildren().addAll(numBox[0], fromBox[0], toBox[0], costBox[0], distanceBox[0], transTypeBox[0], transCostBox[0]);
		detailsBox[0].setMargin(numBox[0], new Insets(10, 10, 10, 10));
		detailsBox[0].setMargin(fromBox[0], new Insets(10, 10, 10, 10));
		detailsBox[0].setMargin(toBox[0], new Insets(10, 10, 10, 10));
		detailsBox[0].setMargin(costBox[0], new Insets(10, 10, 10, 10));
		detailsBox[0].setMargin(distanceBox[0], new Insets(10, 10, 10, 10));
		detailsBox[0].setMargin(transTypeBox[0], new Insets(10, 10, 10, 10));
		detailsBox[0].setMargin(transCostBox[0], new Insets(10, 10, 10, 10));

		vbox.getChildren().add(detailsBox[0]);
        budget.setText("Your budget: RM" + user.getBudget() + (user.getCountry().equalsIgnoreCase("Malaysia")? "": "/" + convertCash(user, user.getBudget())));
              
		if(!(user.getMyIt().isEmpty())) {
			if(user.getMyIt().size()>1) {
				for(int x = 0; x < user.getMyIt().size(); x++) {
					addDetails(number, x, btDis, df, user);
					
					vbox.getChildren().add(detailsBox[x+1]);
					int z = x+1;
					int destSize = user.getMyIt().size();
					
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
								
								if(user.getMyIt().size()>1) {
									//to get the actual index of destination. 
									//when a row is cleared, the index of the button doesn't change accordingly. 
									for(int r = 0; r < user.getMyIt().size(); r++) {
										if(toField[z-1].getText().equals(user.getMyIt().get(r).getTitle())) {
											//destination is removed is the text in toField is the same as destination.get(r).getTitle()
											//destination.size() is then decreased
											deleteRow(r, z, user);
											break;
										}
									}
								
									//a < initialSize because the size of the label and box is the intial size of the destination ArrayList,
									//so initialSize is used to go through the entire label and box array.
									//destination is removed, so size decreases. 
									//if destination.size() is used, it can't go through the entire label and box array.
									for(int a = 0; a < initialSize; a++) {
										//to re-organize the itinerary plan in ascending order.
										//and to update the itinerary plan
										if(!(numBox[a+1].getChildren().isEmpty())) {
											if(numbers==1) {
												btDis = distance(user.getLatitude(), user.getLongtitude(), user.getMyIt().get(no).getLatitude(), 
														user.getMyIt().get(no).getLongtitude());
												fromField[a].setText("Your Location: " + user.getLocation());
											}else {
												btDis = distance(user.getMyIt().get(no-1).getLatitude(), user.getMyIt().get(no-1).getLongtitude(), 
														user.getMyIt().get(no).getLatitude(), 
														user.getMyIt().get(no).getLongtitude());
												fromField[a].setText(user.getMyIt().get(no-1).getTitle());
											}
											numberDestination[a].setText(Integer.toString(numbers));
											toField[a].setText(user.getMyIt().get(no).getTitle());
											costField[a].setText(Double.toString(user.getMyIt().get(no).getPrice()));
											distanceField[a].setText(df.format(btDis));
											transTypeField[a].setText(transFee(btDis)[0]);
											transCostField[a].setText(transFee(btDis)[1]);
											totalPrice += user.getMyIt().get(no).getPrice();
											totalTransCost += Double.parseDouble(transFee(btDis)[1]);
											getBudget(df, btDis, totalPrice, totalTransCost, user);
											numbers++;
											no++;
										}		
									}
								}
								else {
									deleteRow(0, 1, user);	
									totalPrice = 0.0;
									totalTransCost = 0.0;
									getBudget(df, btDis, totalPrice, totalTransCost, user);
								}
							}
						}
					});
					totalPrice += user.getMyIt().get(x).getPrice();
					totalTransCost += Double.parseDouble(transFee(btDis)[1]);
					number++;
				}
				getBudget(df, btDis, totalPrice, totalTransCost, user);
			}	
			else {
				addDetails(1, 0, btDis, df, user);
				deletePlans[0].setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						deleteRow(0, 1, user);	
						getBudget(df, btDis, 0.0, 0.0, user);
					}
				});

				vbox.getChildren().add(detailsBox[1]);
				totalPrice += user.getMyIt().get(0).getPrice();
				totalTransCost += Double.parseDouble(transFee(btDis)[1]);
				getBudget(df, btDis, totalPrice, totalTransCost, user);
				number++;				
			}
			vbox.getChildren().addAll(overBudget, budget, totalCost);
			overBudget.setAlignment(Pos.CENTER);
			budget.setAlignment(Pos.CENTER);
			totalCost.setAlignment(Pos.CENTER);
		}
		ScrollPane scrollBar = new ScrollPane(vbox);
		vbox.setAlignment(Pos.CENTER);
		return vbox;
	}
	
/* add details of itinerary plan
 */
	public void addDetails(int number, int x, double btDis, DecimalFormat df, User user) {
		int count = 0;
		int initialSize = user.getMyIt().size();
		
		Button addPlans = new Button("+");
		Button[] deletePlans = new Button[user.getMyIt().size()];	
		
		Label overBudget = new Label();
		Label budget = new Label("Your budget: " + user.getBudget());
		Label totalCost = new Label("Total Cost: " + 0);
		Label[] numberDestination = new Label[user.getMyIt().size()];
		Label[] costField = new Label[user.getMyIt().size()];
		Label[] fromField = new Label[user.getMyIt().size()];
		Label[] toField = new Label[user.getMyIt().size()];
		Label[] distanceField = new Label[user.getMyIt().size()];
		Label[] transTypeField = new Label[user.getMyIt().size()];
		Label[] transCostField = new Label[user.getMyIt().size()];
		
		VBox[] numBox = new VBox[user.getMyIt().size()+1];
		VBox[] costBox = new VBox[user.getMyIt().size()+1];
		VBox[] fromBox = new VBox[user.getMyIt().size()+1];
		VBox[] toBox = new VBox[user.getMyIt().size()+1];
		VBox[] distanceBox = new VBox[user.getMyIt().size()+1];
		VBox[] transTypeBox = new VBox[user.getMyIt().size()+1];
		VBox[] transCostBox = new VBox[user.getMyIt().size()+1];
		VBox[] deleteButtonBox = new VBox[user.getMyIt().size()];
		
		HBox[] detailsBox = new HBox[user.getMyIt().size()+1];
		
		if(number==1) {
			btDis = distance(user.getLatitude(), user.getLongtitude(), user.getMyIt().get(x).getLatitude(), user.getMyIt().get(x).getLongtitude());
			fromField[x] = new Label("Your Location: " + user.getLocation());
		}
		else {
			btDis = distance(user.getMyIt().get(x-1).getLatitude(), user.getMyIt().get(x-1).getLongtitude(), user.getMyIt().get(x).getLatitude(), user.getMyIt().get(x).getLongtitude());
			fromField[x] = new Label(user.getMyIt().get(x-1).getTitle());
		}
		
		numberDestination[x] = new Label(Integer.toString(number));
		detailsBox[x] = new HBox();
		toField[x] = new Label(user.getMyIt().get(x).getTitle());
		costField[x] = new Label(Double.toString(user.getMyIt().get(x).getPrice()));
		distanceField[x] = new Label(df.format(btDis));
		transTypeField[x] = new Label(transFee(btDis)[0]);
		transCostField[x] = new Label(transFee(btDis)[1]);
		deletePlans[x] = new Button("X");
		
		numBox[x+1] = new VBox();
		costBox[x+1] = new VBox();
		fromBox[x+1] = new VBox();
		toBox[x+1] = new VBox();
		distanceBox[x+1] = new VBox();
		transTypeBox[x+1] = new VBox();
		transCostBox[x+1] = new VBox();
		deleteButtonBox[x] = new VBox();
		numBox[x+1].getChildren().add(numberDestination[x]);
		fromBox[x+1].getChildren().add(fromField[x]);
		toBox[x+1].getChildren().add(toField[x]);
		costBox[x+1].getChildren().add(costField[x]);
		distanceBox[x+1].getChildren().add(distanceField[x]);
		transTypeBox[x+1].getChildren().add(transTypeField[x]);
		transCostBox[x+1].getChildren().add(transCostField[x]);
		deleteButtonBox[x].getChildren().add(deletePlans[x]);
		detailsBox[x+1] = new HBox();
		detailsBox[x+1].getChildren().addAll(numBox[x+1], fromBox[x+1], toBox[x+1], costBox[x+1], distanceBox[x+1], transTypeBox[x+1], 
				transCostBox[x+1], deleteButtonBox[x]);
		detailsBox[x+1].setMargin(numBox[x+1], new Insets(10, 10, 10, 10));
		detailsBox[x+1].setMargin(fromBox[x+1], new Insets(10, 10, 10, 10));
		detailsBox[x+1].setMargin(toBox[x+1], new Insets(10, 10, 10, 10));
		detailsBox[x+1].setMargin(costBox[x+1], new Insets(10, 10, 10, 10));
		detailsBox[x+1].setMargin(distanceBox[x+1], new Insets(10, 10, 10, 10));
		detailsBox[x+1].setMargin(transTypeBox[x+1], new Insets(10, 10, 10, 10));
		detailsBox[x+1].setMargin(transCostBox[x+1], new Insets(10, 10, 10, 10));
		detailsBox[x+1].setMargin(deleteButtonBox[x], new Insets(10, 10, 10, 10));
	}
	/* delete itinerary plan*/ 
	
	public void deleteRow(int r, int z, User user) {
		
		VBox[] numBox = new VBox[user.getMyIt().size()+1];
		VBox[] costBox = new VBox[user.getMyIt().size()+1];
		VBox[] fromBox = new VBox[user.getMyIt().size()+1];
		VBox[] toBox = new VBox[user.getMyIt().size()+1];
		VBox[] distanceBox = new VBox[user.getMyIt().size()+1];
		VBox[] transTypeBox = new VBox[user.getMyIt().size()+1];
		VBox[] transCostBox = new VBox[user.getMyIt().size()+1];
		VBox[] deleteButtonBox = new VBox[user.getMyIt().size()];
		
		HBox[] detailsBox = new HBox[user.getMyIt().size()+1];
		
		ArrayList<Destination> destination = user.getMyIt();
		if(r == 0 ) {
			destination = new ArrayList();
		}
		else {
			user.getMyIt().remove(r);
		}		
		numBox[z].getChildren().clear();
		fromBox[z].getChildren().clear();
		toBox[z].getChildren().clear();
		costBox[z].getChildren().clear();
		distanceBox[z].getChildren().clear();
		transTypeBox[z].getChildren().clear();
		transCostBox[z].getChildren().clear();
		deleteButtonBox[z-1].getChildren().clear();
		detailsBox[z].getChildren().clear();
		detailsBox[z].setMargin(numBox[z], new Insets(0));
		detailsBox[z].setMargin(fromBox[z], new Insets(0));
		detailsBox[z].setMargin(toBox[z], new Insets(0));
		detailsBox[z].setMargin(costBox[z], new Insets(0));
		detailsBox[z].setMargin(distanceBox[z], new Insets(0));
		detailsBox[z].setMargin(transTypeBox[z], new Insets(0));
		detailsBox[z].setMargin(transCostBox[z], new Insets(0));
		detailsBox[z].setMargin(deleteButtonBox[z-1], new Insets(0));
	}
	/* get budget of itinerary plan */
	public void getBudget(DecimalFormat df, double btDis, double totalPrice, double totalTransCost, User user) {
		Label overBudget = new Label();		
		Label totalCost = new Label("Total Cost: " + 0);
		
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
	
	
	public static String[] transFee(double distance) {
        ArrayList<String> transFee = new ArrayList<String>();
        String[][] tp = new String[10][10];
        double busFee =0, taxiFee=0, trainFee=0, fee=0;
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
}
