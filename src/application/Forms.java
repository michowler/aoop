//pop and push scenes 
//set scenes as functions

package application;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class Forms {
	
	BorderPane loginPage;
	BorderPane registerPage; 
	
	public BorderPane contactUsForm()  {
		
		BorderPane contactForm = new BorderPane();
		BorderPane.setMargin(contactForm,new Insets(150,100,10,100));
		TextField contactEmail = new TextField();
		contactEmail.setPromptText("Enter your email");
		TextArea contactMsg = new TextArea();
		contactMsg.setPromptText("Your message");
		contactEmail.setMaxWidth(350);
		contactMsg.setPrefRowCount(5);
		contactMsg.setMaxHeight(350);  //sets height of the TextArea to 400 pixels 
		contactMsg.setMaxWidth(650); 
		Label contactLabel = new Label("Contact us for questions or feedbacks!");
		contactLabel.setStyle("-fx-text-fill: white;");
		String fileName = "contactUs.txt";
		Button submit = new Button("SUBMIT");
		
		VBox contactList = new VBox();
		contactList.setSpacing(20);
		contactList.getChildren().addAll(contactLabel, contactEmail, contactMsg, submit); 
	
		submit.setOnAction(e->{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Contact Us");			
			alert.setHeaderText("Thank you for contacting us!");
			alert.showAndWait();			        
		        
			java.util.Date date= new java.util.Date();			
			// Initialize variable and store new Timestamp object
			java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
			// Concatenate 
			printToFile(fileName, timestamp + "\n" + contactEmail.getText() + " sent: " + contactMsg.getText());
			//add container to an array
			
		});
		contactForm.setCenter(contactList);
		return contactForm;
	}
	
	public BorderPane registerForm() {
		BorderPane registerPage = new BorderPane();//main container
//		this.username = "guest";
//		this.password = "123123";
//		this.location = "Kuala Lumpur";
//		this.country = "Malaysia";
//		this.latitude = 3.08;
//		this.longtitude = 108.41;
		
		Label username = new Label("Username: ");	
		Label password = new Label("Password: ");
		Label address = new Label("Address:");
		Label country = new Label("Country: ");
		Label budget = new Label("Initial Budget:");

		TextField usrField= new TextField();
		TextField psField= new TextField();		
		TextField addField= new TextField();
		TextField countryField= new TextField();
		TextField budgetField= new TextField();
		
		ComboBox<String> cboxCountry = new ComboBox<>();
		cboxCountry.getItems().addAll("Malaysia", "Singapore", "Indonesia", "Japan", "America", "India");						
		
		Button registerButton = new Button("Register");
	
		//show.setOnAction(e -> handle());
		
		registerButton.setOnAction(new EventHandler<ActionEvent>()
        {            
            @Override
            public void handle(ActionEvent event)
            {    
//            	User guestUser = new User();
            	//User currentUser = new User(usrField.getText(), psField.getText(), addField.getText(), budgetField.getText(), countryField.getText(), 102.32, 153.41);

            	//append new user to file or array

//            	student1.toString();
//            	
//            	try(FileWriter output = new FileWriter("person.txt", true);
//            		    BufferedWriter bw = new BufferedWriter(output);
//            		    PrintWriter out = new PrintWriter(bw))
//				{
//					//System.out.println(student1.name);			
//					out.println(student1.toString()+"\n");
//					
//				}
//				catch(Exception e){
//					System.out.println("Error!");
//				}
            }
        });
		
		//myUsers.add(new User("yokelvin", "helloworld","1, Persiaran Kewajipan, Usj 1, 47600 Subang Jaya, Selangor", 300, "Taiwan", 3.07, 101.52));
		Insets insets = new Insets(10);
		HBox panehbox1 = new HBox();
		HBox panehbox2 = new HBox();
		HBox panehbox3 = new HBox();
		HBox panehbox4 = new HBox();
		HBox panehbox5 = new HBox();
		HBox panehbox6 = new HBox();

		VBox vboxRegister = new VBox();
		panehbox1.getChildren().addAll(username, usrField);
		panehbox2.getChildren().addAll(password, psField);
		panehbox3.getChildren().addAll(address,addField);
		panehbox4.getChildren().addAll(country, countryField);
		panehbox5.getChildren().addAll(budget, budgetField);
		panehbox6.getChildren().addAll(registerButton);	
		panehbox6.setAlignment(Pos.BASELINE_RIGHT);
		vboxRegister.getChildren().addAll(panehbox1, panehbox2, panehbox3, panehbox4, panehbox5, panehbox6);
		vboxRegister.setSpacing(10);
		registerPage.setCenter(vboxRegister);
		BorderPane.setMargin(vboxRegister, insets);
		return registerPage;
		
	}
	
	public ArrayList<User> initUserDB() throws IOException{
		ArrayList<User> myUsers = new ArrayList<User>(); //creating existing users array		
		FileInputStream fstream = new FileInputStream("src/application/userdb.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		String usrname = "", psw = "", location = "", country = "";
		double lat = 0, lg = 0, budget = 0;
		
		while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console
			Scanner readUserDB = new Scanner(strLine);
			readUserDB.useDelimiter(";");
			while(readUserDB.hasNext())
        	{   
        		usrname = readUserDB.next();
        		psw = readUserDB.next();
        	    location = readUserDB.next();
        	    country = readUserDB.next();	            	    
            	myUsers.add(new User(usrname, psw, location, budget, country, lat, lg));
        	}	                
    		
            readUserDB.close();
		}
		br.close();
		return myUsers;
	}
	
	public BorderPane loginForm(ArrayList<User> myUsers, Stage primaryStage) { //creating existing users array
		BorderPane pane = new BorderPane();
		Insets insets = new Insets(300);		
		
		Label username = new Label("Username: ");	
		Label password = new Label("Password: ");

		TextField usrField= new TextField();
		PasswordField pwField = new PasswordField();		
		
		Button loginButton = new Button("Login");

		HBox panehbox1 = new HBox();
		HBox panehbox2 = new HBox();
		HBox panehbox3 = new HBox();
		HBox panehbox4 = new HBox();

		VBox vboxLogin = new VBox();
		BorderPane.setMargin(vboxLogin, insets);
		panehbox1.getChildren().addAll(username, usrField);
		panehbox2.getChildren().addAll(password, pwField);	
		panehbox3.getChildren().addAll(loginButton);	
		panehbox3.setAlignment(Pos.BASELINE_LEFT);
		panehbox1.setSpacing(10);
		panehbox2.setSpacing(10);
		panehbox3.setSpacing(10);		
		
		vboxLogin.getChildren().addAll(panehbox1, panehbox2, panehbox3, panehbox4);
		vboxLogin.setSpacing(10);
		pane.setCenter(vboxLogin);
		vboxLogin.setId("vboxLogin");
		
		//show.setOnAction(e -> handle());
		loginButton.setOnAction(new EventHandler<ActionEvent>()
        {            
            @Override
            public void handle(ActionEvent event)
            {                	
            	//read and save users from file and store in tmparray then check from array
            	//if found then return homepage container(logged in)            	
            	//else append text or notification to try again
            	panehbox4.getChildren().clear();
            	ArrayList<User> curUser = new ArrayList<User>();
        		for(User user: myUsers){                		
        			if(usrField.getText().equals(user.getUsername()) && pwField.getText().equals(user.getPassword())) {
        					curUser.add(new User(user.getUsername(), user.getPassword(),user.getLocation(), user.getBudget(), user.getCountry(), user.getLatitude(), user.getLongtitude()));
        					BorderPane loginPane = new BorderPane();
        					Layout home = new Layout();
        					loginPane.setTop(home.homePage(curUser, primaryStage));
        					loginPane.setCenter(null);
        					Scene sceneLogin = new Scene(loginPane, 1280,720);
        					primaryStage.setScene(sceneLogin);         					
        					sceneLogin.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        			}else {        				
        				panehbox4.getChildren().clear();
        				Text errorMsg = new Text("Error, please try again!");
        				errorMsg.setStyle("-fx-color:red");
        				panehbox4.getChildren().add(errorMsg); 
        				       			       		        				
        			}
        			
        		}
            }
            
        });
				
		return pane;
		
	}

	 public void printToFile (String myfileName, String message) {        

	        try {
	            File outfile = new File(myfileName);
	           
	            if (!outfile.exists()) {
	                System.out.println("No file exists. Writing into a new file");
	                outfile.createNewFile();
	            }
	            
	            FileWriter fw = new FileWriter(outfile.getAbsoluteFile(), true);
	            BufferedWriter bw = new BufferedWriter(fw);
	            bw.write(message);
	          	            
	            bw.flush(); 
	            bw.close();

	            System.out.println("Done");

	            } catch (IOException e) {
	                e.printStackTrace();                    
	        } 	        
	 }   
	 
	 public personalisationMain filterByTags() {
		 personalisationMain filterForm = new personalisationMain();
		 return filterForm;
	 }
	 
	 public VBox personalisationFilter() {			
			
			RadioButton rest = new RadioButton("Restaurant");
			RadioButton attr = new RadioButton("Attraction");
			RadioButton hotel = new RadioButton("Hotel");
			
			final ToggleGroup group = new ToggleGroup();
			
			rest.setToggleGroup(group);
			attr.setToggleGroup(group);
			hotel.setToggleGroup(group);
			
			Button ok = new Button("OK");
			
			ok.setOnAction(value -> {
				
					if(group.getSelectedToggle() == rest) {
						//mainRestaurant.launch(mainRestaurant.class);
						try {
							restaurantFilter();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if (group.getSelectedToggle() == attr) {
						try {
							attractionFilter();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
							hotelFilter();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						};
					}
				
			});
			
			VBox vb = new VBox();
			vb.getChildren().addAll(new Label("Choose one"), rest, attr, hotel, ok);
			vb.setAlignment(Pos.CENTER);
			
			return vb;
	 }
	 
	 public VBox attractionFilter() {
		//declare attractiondata as ad
			attractionData ad = new attractionData();
			
			//create button
			Button submit = new Button("SUBMIT");
			//create label
			Label l1 = new Label("Write a description of what you are looking for: (example- fun, chill, mosque, historical, family..)");
			Label l2 = new Label("");
			//create textfield
			TextField tf = new TextField();
			tf.setMinSize(200, 100);
				
			VBox vb = new VBox();
			vb.setMinWidth(80);
			vb.setMinHeight(100);
			vb.setAlignment(Pos.CENTER);
			
			
			//store keys
			ArrayList<String> listOfKeys = new ArrayList<String>();
			//store characteristics of restaurant that user typed
			ArrayList<String> listOfItems = new ArrayList<String>();
			
			submit.setDefaultButton(true); //can press enter instead of clicking submit

			
			submit.setOnAction(value -> {
			//clear listofkeys and listofitems before starting again
			listOfKeys.clear(); 
			listOfItems.clear();
			l2.setText(null);	
			
			//store response by user 
			String response = tf.getText();
			
			try {
				File file = new File("unknownAttraction.txt");
				
				//if file does not exist, create new file
				if(!file.exists()) {
					System.out.print("File does not exist. creating a new one");
					file.createNewFile();
				}
				
				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
				BufferedWriter bw = new BufferedWriter(fw);
				
				 addTokenFoundInList(listOfItems,response, ad.attr, bw);
				
					bw.flush();
					bw.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			
			//remove duplicates by putting them into set
			ad.removeDuplicate(listOfItems);
				
				int i = ad.attr.size();
				for(Map.Entry<String, List<String>> entry : ad.attr.entrySet())
				{	//if entry contains value equal to the arraylist listofitems
					if(entry.getValue().containsAll(listOfItems))
					{
						//store the keys with the values into listofkeys
						listOfKeys.add(entry.getKey());
					}
			
				}
			
				//remove duplicates
				ad.removeDuplicate(listOfKeys);
			
	           if(listOfKeys.size() != 10) { //if none of the keys contain the value 
					
					l2.setText(listOfKeys.toString().replace("[", "").replace("]", ""));
					
				
				} else { //if at least 1 of the keys contain the value
				
			
				l2.setText("Not found. Will be updated soon!");
				
			    }
			});	
			
			VBox vb2 = new VBox();
			vb2.setAlignment(Pos.CENTER);
			vb2.getChildren().addAll(l1, tf, vb, submit);
			return vb2;
	 }
	 
	public VBox hotelFilter() {

		//declare hoteldata as hd
		hotelData hd = new hotelData();
		
		//create button
		Button submit = new Button("SUBMIT");
		//create label
		Label l1 = new Label("Write a description of what you are looking for: (example- wifi, aircond, near city etc..)");
		Label l2 = new Label("");
		//create textfield
		TextField tf = new TextField();
		tf.setMinSize(200, 100);
		
		VBox vb = new VBox();
		vb.setMinWidth(80);
		vb.setMinHeight(100);
		vb.setAlignment(Pos.CENTER);
		
		//store keys
		ArrayList<String> listOfKeys = new ArrayList<String>();
		//store characteristics of restaurant that user typed
		ArrayList<String> listOfItems = new ArrayList<String>();
				
		submit.setDefaultButton(true); //can press enter instead of clicking submit

		
			//store response by user 
			submit.setOnAction(value -> {
			//clear listofkeys and listofitems before starting again
			listOfKeys.clear(); 
			listOfItems.clear();
			l2.setText(null);
			
			String response = tf.getText();
			try {
				File file = new File("unknownHotel.txt");
			
				//if file does not exist, create new file
				if(!file.exists()) {
					System.out.print("File does not exist. creating a new one");
					file.createNewFile();
				}
				
				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
				BufferedWriter bw = new BufferedWriter(fw);
				
				//add words have same values as keys in hotel
				addTokenFoundInList(listOfItems, response, hd.hotel, bw);
					
					bw.flush();
					bw.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			//remove duplicates by putting them into set
			hd.removeDuplicate(listOfItems);
				
				int i = hd.hotel.size();
				for(Map.Entry<String, List<String>> entry : hd.hotel.entrySet())
				{	//if entry contains value equal to the arraylist listofitems
					if(entry.getValue().containsAll(listOfItems))
					{
						//store the keys with the values into listofkeys
						listOfKeys.add(entry.getKey());
						
					}
				}				

			
				//remove duplicates
				hd.removeDuplicate(listOfKeys);
				
				if(listOfKeys.size() != 10) { //if none of the keys contain the value 
					
					l2.setText(listOfKeys.toString().replace("[", "").replace("]", ""));
					
				
				} else { //if at least 1 of the keys contain the value
				
			
				l2.setText("Not found. Will be updated soon!");
				
			    }
		});

		VBox vb2 = new VBox();
		vb2.setAlignment(Pos.CENTER);
		vb2.getChildren().addAll(l1, tf, vb, submit);
		return vb2;
	 }
	
	public VBox restaurantFilter() {
		//declare restaurantdata as rd
		restaurantData rd = new restaurantData();
		
		//declare hoteldata as hd
		hotelData hd = new hotelData();
		
		//create button
		Button submit = new Button("SUBMIT");
	    //create label
		Label l1 = new Label("Write a description of what you are looking for: (example- vegan, malaysian, halal, alcohol, skyview, fastfood etc..)");
	   	Label l2 = new Label("");
		//create textfield
		TextField tf = new TextField();
		tf.setMinSize(200, 100);
				
		VBox vb = new VBox();
		vb.setMinWidth(80);
		vb.setMinHeight(100);
		vb.setAlignment(Pos.CENTER);	
		vb.getChildren().add(l2);
		//store keys
		ArrayList<String> listOfKeys = new ArrayList<String>();
		//store characteristics of restaurant that user typed
		ArrayList<String> listOfItems = new ArrayList<String>();
		
		submit.setDefaultButton(true); //can press enter instead of clicking submit
		
		submit.setOnAction(value -> {
		//clear listofkeys and listofitems before starting again
		listOfKeys.clear(); 
		listOfItems.clear();
	    l2.setText(null);
		String response = tf.getText();
		
		try {
			File file = new File("unknownRestaurant.txt");
			
			//if file does not exist, create new file
			if(!file.exists()) {
				System.out.print("File does not exist. creating a new one");
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			addTokenFoundInList(listOfItems, response, rd.rest, bw);

				bw.flush();
				bw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
		//remove duplicates by putting them into set
		rd.removeDuplicate(listOfItems);
			
			
			for(Map.Entry<String, List<String>> entry : rd.rest.entrySet())
			{	//if entry contains value equal to the arraylist listofitems
				if(entry.getValue().containsAll(listOfItems))
				{
					//store the keys with the values into listofkeys
					listOfKeys.add(entry.getKey());
					
				}
				
				
			}
				//remove duplicates
				rd.removeDuplicate(listOfKeys);	
				
				if(listOfKeys.size() != 10) { //if none of the keys contain the value 
					
					l2.setText(listOfKeys.toString().replace("[", "").replace("]", ""));
					
				
				} else { //if at least 1 of the keys contain the value
				
			
				l2.setText("Not found. Will be updated soon!");
				
			    }
		});
		
		VBox vb2 = new VBox();
			
		vb2.setAlignment(Pos.CENTER);
		vb2.getChildren().addAll(l1, tf, vb, submit);
		return vb2;
	}
	 
	public static void addTokenFoundInList(ArrayList<String> list, String response, HashMap<String, List<String>> place, BufferedWriter bw) throws IOException {
		//separate each word and ignore . and ,
		StringTokenizer st = new StringTokenizer(response, "., ");
		while(st.hasMoreTokens()) {
			//move to next word/token
			String t = st.nextToken();
			//iterate through map
			for (Map.Entry<String,List<String> > entry : place.entrySet()) 
			{	//if map contains the word found in user's input
				if(entry.getValue().contains(t))
				{
					//store values found in key to arraylist
					list.add(t);
					
				} 
				else {
					//if token not found in hashmap, write to unknown file
					bw.write(t + "\n");
				}
			}	
		
		}

	}
	 
}


	
	 


