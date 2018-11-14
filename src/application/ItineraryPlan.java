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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ItineraryPlan{
	
	ArrayList<Destination> destination = new ArrayList<Destination>();
	User user;
	int initialSize;
	
	Button addPlans;
	Button[] deletePlans;
	
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
	
	public BorderPane displayMyIt() {
		//add title
		BorderPane bp = new BorderPane();
		BorderPane.setMargin(bp,new Insets(80,100,10,100));
		
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
	
		VBox vbox = new VBox();
		
		int number = 1;
		double btDis = 0.0;
		double totalPrice = 0.0;
		double totalTransCost = 0.0;
		HBox labelsBox = new HBox();
		labelsBox.getChildren().addAll(new Label("Number"), new Label("From\t\t-"), new Label("To"),new Label("Cost(MYR)"), new Label("Distance(KM)"), 
				new Label("Trans Type"), new Label("Trans Cost(MYR)"));
				FlowPane flowPane = new FlowPane();
		flowPane.setMinSize(650, 300);
		labelsBox.setSpacing(20);
		vbox.getChildren().add(labelsBox);
        budget.setText("Your budget: RM" + user.getBudget() + (user.getCountry().equalsIgnoreCase("Malaysia")? "": "/" + convertCash(user, user.getBudget())));
      
		if(!(destination.isEmpty())) {
			if(destination.size()>1) {
				for(int x = 0; x < destination.size(); x++) {
					addDetails(number, x, btDis, df); //here
					
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

								if(destination.size()>1) {
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
											transTypeField[a].setText(transFee(btDis)[0]);
											transCostField[a].setText(transFee(btDis)[1]);
											System.out.println("price: " + destination.get(no).getPrice());
											totalPrice += destination.get(no).getPrice();
											System.out.println(totalPrice);
											totalTransCost += Double.parseDouble(transFee(btDis)[1]);
											getBudget(df, btDis, totalPrice, totalTransCost);
											numbers++;
											no++;
										}		
									}
								}
								else {
									deleteRow(0, 0);	
									totalPrice = 0.0;
									totalTransCost = 0.0;
									getBudget(df, btDis, totalPrice, totalTransCost);
								}
							}
						}
					});
					totalPrice += destination.get(x).getPrice();
					totalTransCost += Double.parseDouble(transFee(btDis)[1]);
					number++;
				}
				getBudget(df, btDis, totalPrice, totalTransCost);
			}	
			else {
				addDetails(0, 0, btDis, df);
				deletePlans[0].setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						deleteRow(0, 0);	
						getBudget(df, btDis, 0.0, 0.0);
					}
				});

				vbox.getChildren().add(detailsBox[0]);
				totalPrice += destination.get(0).getPrice();
				totalTransCost += Double.parseDouble(transFee(btDis)[1]);
				getBudget(df, btDis, totalPrice, totalTransCost);
				number++;				
			}
			vbox.getChildren().addAll(overBudget, budget, totalCost);
			overBudget.setAlignment(Pos.CENTER);
			budget.setAlignment(Pos.CENTER);
			totalCost.setAlignment(Pos.CENTER);
		}
		ScrollPane scrollBar = new ScrollPane(vbox);
		vbox.setAlignment(Pos.CENTER);
		flowPane.getChildren().addAll(buttonBox, vbox);
		flowPane.setMargin(buttonBox, new Insets(10, 10, 10, 10));	
		
		Text sceneTitle = new Text("My Itinerary Plan");		
		sceneTitle.setFont(Font.font("Arial", FontWeight.BOLD, 50));
		bp.setTop(sceneTitle);
		bp.setCenter(vbox);
		return bp;
	}

	
/* add details of itinerary plan
 */
	public void addDetails(int number, int x, double btDis, DecimalFormat df) {
		
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
		transTypeField[x] = new Label(transFee(btDis)[0]);
		transCostField[x] = new Label(transFee(btDis)[1]);
		deletePlans[x] = new Button("X");
		
		detailsBox[x] = new HBox();
		detailsBox[x].getChildren().addAll(numberDestination[x], fromField[x], toField[x], costField[x], distanceField[x], transTypeField[x], transCostField[x]
				, deletePlans[x]);
		detailsBox[x].setMargin(numberDestination[x], new Insets(10, 10, 10, 10));
		detailsBox[x].setMargin(fromField[x], new Insets(10, 10, 10, 10));
		detailsBox[x].setMargin(toField[x], new Insets(10, 10, 10, 10));
		detailsBox[x].setMargin(costField[x], new Insets(10, 10, 10, 10));
		detailsBox[x].setMargin(distanceField[x], new Insets(10, 10, 10, 10));
		detailsBox[x].setMargin(transTypeField[x], new Insets(10, 10, 10, 10));
		detailsBox[x].setMargin(transCostField[x], new Insets(10, 10, 10, 10));
		detailsBox[x].setMargin(deletePlans[x], new Insets(10, 10, 10, 10));
	}
	/* delete itinerary plan*/ 
	
	public void deleteRow(int r, int z) {
		detailsBox[z].getChildren().clear();
		if(r == 0 ) {
			destination = new ArrayList();
		}
		else {
			destination.remove(r);
		}		
	}
	
	/* get budget of itinerary plan */
	public void getBudget(DecimalFormat df, double btDis, double totalPrice, double totalTransCost) {
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
        System.out.println("tp[0][1]" + tp[0][1]);
        return tp[0];        
    }
}