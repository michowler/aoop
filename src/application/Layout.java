package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Layout {
	public BorderPane homeMenu(ArrayList<User> curUser, Stage primaryStage) {
		BorderPane homepage = new BorderPane();			
		homepage.setId("mainNavBar");
		BorderPane bPaneTop = new BorderPane();		
		HBox menuHBoxLeft = new HBox();
		HBox menuHBoxRight = new HBox();		
		
		//Menu Statistics = new Menu(curUser.get(0).getUsername());
		Menu moreOptions = new Menu(curUser.get(0).getUsername()); 
		   
	    MenuItem settingsMenu = new MenuItem("Settings"); 
	    MenuItem itineraryMenu = new MenuItem("Itinerary Plan");
	    MenuItem contactMenu = new MenuItem("Contact");
	    MenuItem logoutMenu = new MenuItem("Logout"); 	     		
	    MenuItem statsMenu = new MenuItem("Statistics");
	    MenuItem personMenu = new MenuItem("Personalize");
	    
	    moreOptions.getItems().add(statsMenu);
	    moreOptions.getItems().add(personMenu);
	    moreOptions.getItems().add(itineraryMenu); 
	    moreOptions.getItems().add(contactMenu);
	    moreOptions.getItems().add(settingsMenu);
	    moreOptions.getItems().add(logoutMenu); 
        
        MenuBar menuBar = new MenuBar();        
        menuBar.getMenus().add(moreOptions); 
        menuBar.setId("menuBar");        
        
		Button homeButton = new Button("Home");
		
		Image imageButton = new Image(getClass().getResourceAsStream("home.png"));
		ImageView ivSearch =new ImageView(imageButton);
		ivSearch.setFitHeight(18);
		ivSearch.setFitWidth(18);
		homeButton.setGraphic(ivSearch);
		
//		TextField searchField = new TextField();		
//		Button searchButton = new Button("Search");				
//		searchField.setPromptText("enter destination");
		
//		ArrayList<String> searchingText = new ArrayList<String>();
//		searchButton.setOnAction(e->{			
//			searchButton.setText("Searching..");
//			StringTokenizer st = new StringTokenizer(searchField.getText());
//		     
//		     while (st.hasMoreElements()) {
//		    	 searchingText.add((String) st.nextElement());
//		    	 System.out.println("Next element : " + st.nextElement());
//		     }
//		});
//												
		HBox.setMargin(homeButton,new Insets(10,10,10,10));
		HBox.setMargin(menuBar,new Insets(10,10,10,10));							    	    
	    
	    menuHBoxLeft.getChildren().addAll(homeButton);
	    menuHBoxRight.getChildren().addAll(menuBar);
	    menuHBoxLeft.setSpacing(5);
	    bPaneTop.setLeft(menuHBoxLeft);
	    bPaneTop.setRight(menuHBoxRight);
	    homepage.setTop(bPaneTop);
	    homepage.setCenter(mainContainer());	  
	    
	    personMenu.setOnAction(e->{	    	
	    	personalisationMain personal = new personalisationMain();
	    	try {
				homepage.setCenter(personal.personalizeFilter());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//	  	    personal.personalizeFilter();
	    });
	   
	    homeButton.setOnAction(e->{
	    	//clear container, enter new container	    	
	    	homepage.setCenter(mainContainer());
	    });
	    
	    
	    statsMenu.setOnAction(e->{
	    	Charts cb = new Charts();
	    	homepage.setCenter(cb.displayStatistics());
	    });	    	    
	    
	    itineraryMenu.setOnAction(e->{	    	
	    	//plan.start(primaryStage);
	    	ItineraryPlan plan = new ItineraryPlan(curUser.get(0).getMyIt(), curUser.get(0));
	    	homepage.setCenter(plan.displayMyIt());	    		    	
	    });
	    
	    Forms form = new Forms();
	    
	    logoutMenu.setOnAction(e->{
	    	//switch to logoutScene			
			BorderPane logoutPane = new BorderPane();
			Scene sceneLogout = new Scene(logoutPane, 1280,720);
			primaryStage.setScene(sceneLogout);
			try {
				logoutPane.setCenter(form.loginForm(form.initUserDB(), primaryStage));
			} catch (IOException e1) {				
				e1.printStackTrace();
			}
			sceneLogout.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.show();
	    });
	    
	    contactMenu.setOnAction(e->{		    	
	    	homepage.setCenter(form.contactUsForm());
	    });
	    
	    return homepage;
		
	}
	
	public BorderPane mainContainer() {
        ScrollPane scrollPane = new ScrollPane();
        
        Button button = new Button("My Button");
        button.setPrefSize(400, 300);
 
        // Set content for ScrollPane
        scrollPane.setContent(button);
 
        // Always show vertical scroll bar
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        
        // Horizontal scroll bar is only displayed when needed
        scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		BorderPane homeContainer = new BorderPane();		
		BorderPane.setMargin(homeContainer,new Insets(80,100,10,100));
		HBox hboxMenu = new HBox();	
		
		Image imageLogo = new Image(getClass().getResourceAsStream("tour.png"));			     	   
	    ImageView iv = new ImageView(imageLogo);
	    iv.setFitHeight(150);
		iv.setFitWidth(180);
		
	    Button searchButton = new Button("Search Destinations");
	    Image searchImg = new Image(getClass().getResourceAsStream("search.png"));
		ImageView ivSearch =new ImageView(searchImg);
		ivSearch.setFitHeight(20);
		ivSearch.setFitWidth(20);
		searchButton.setGraphic(ivSearch);
		
	    searchButton.setOnAction(e->{
	    	SearchFilter searchDest = new SearchFilter();
	    	homeContainer.setCenter(searchDest.searchBox());	    	
	    });
				
		VBox vboxHotel = new VBox();
		VBox vboxRest = new VBox();
		VBox vboxPlace = new VBox();
		
		Button hotelButton = new Button("Hotels");
		Button restButton = new Button("Restaurants");
		Button placeButton = new Button("Attractions");
				
        Image hotelPic = new Image("application/hotel.jpg", 250,250, false, false);        
        ImageView iv1 = new ImageView();
        iv1.setImage(hotelPic);
        
        Image hotelimgButton = new Image(getClass().getResourceAsStream("logohotel.png"));
		ImageView ivHotel =new ImageView(hotelimgButton);
		ivHotel.setFitHeight(25);
		ivHotel.setFitWidth(22);
        hotelButton.setGraphic(ivHotel);
        
        Image attractionPic = new Image("application/attraction.jpg", 250,250, false, false);        
        ImageView iv2 = new ImageView();
        iv2.setImage(attractionPic);
        
        Image attimgButton = new Image(getClass().getResourceAsStream("logoplace.png"));
		ImageView ivAtt =new ImageView(attimgButton);
		ivAtt.setFitHeight(25);
		ivAtt.setFitWidth(18);
		placeButton.setGraphic(ivAtt);
		
        Image restPic = new Image("application/restaurant.jpg", 250,250, false, false);        
        ImageView iv3 = new ImageView();
        iv3.setImage(restPic);     
        
        Image restimgButton = new Image(getClass().getResourceAsStream("logorestaurant.png"));
		ImageView ivRest =new ImageView(restimgButton);
		ivRest.setFitHeight(25);
		ivRest.setFitWidth(25);
		restButton.setGraphic(ivRest);
		
        iv1.setPreserveRatio(true);
        iv2.setPreserveRatio(true);
        iv3.setPreserveRatio(true);        
		
		hotelButton.setOnAction(e->{
			//2 vbox = 1 hbox
//			mainData dataList = new mainData();
//			Sorting st = new Sorting();			
			//price, ratings, alphabet
//			homeContainer.setCenter(fullList(st.filteredPrice(dataList.hotelList())));
//			homeContainer.setCenter(fullList(st.filteredRatings(dataList.hotelList())));
			
		});
		
		restButton.setOnAction(e->{
			
		});
		
		placeButton.setOnAction(e->{
			
		});
		
		vboxHotel.setAlignment(Pos.BASELINE_CENTER);
		vboxRest.setAlignment(Pos.BASELINE_CENTER);
		vboxPlace.setAlignment(Pos.BASELINE_CENTER);		
		
		vboxHotel.getChildren().addAll(iv1, hotelButton);
		vboxRest.getChildren().addAll(iv2, placeButton);
		vboxPlace.getChildren().addAll(iv3, restButton);
				
		hboxMenu.getChildren().addAll( vboxHotel, vboxRest, vboxPlace);
		hboxMenu.setSpacing(20);
		//hboxMenu.setStyle("-fx-background-color: #FFFFFF;-fx-base : #ffaadd;");
		BorderPane.setMargin(homeContainer,new Insets(50,20,20,10));
		
		VBox menuBox = new VBox();
		menuBox.setAlignment(Pos.BASELINE_CENTER);
		menuBox.getChildren().addAll(iv,searchButton, hboxMenu);
		
		homeContainer.setAlignment(iv,Pos.BASELINE_LEFT);		
		homeContainer.setCenter(menuBox);		
		scrollPane.setContent(homeContainer);
		hboxMenu.setAlignment(Pos.BASELINE_CENTER);
		return homeContainer;	
		
	}
	
	public VBox fullList(ArrayList<Destination> dests) { //change to scrollpane
		VBox vboxList = new VBox();
		for(Destination d: dests) {			
			vboxList.getChildren().add(detailsDest(d));
		}
		return vboxList;
	}		
	
	public HBox detailsDest(Destination dest) {
		//destArr
		HBox hbox = new HBox();
		VBox imageBox = new VBox();
		VBox detailsBox = new VBox();
		//image
		Image pic = new Image(getClass().getResourceAsStream("logohotel.png"));
		ImageView ivDest =new ImageView(pic);
		ivDest.setFitHeight(25);
		ivDest.setFitWidth(22);        		
		
		Label titleLabel = new Label("");
		Label ratingLabel = new Label("");
		
		titleLabel.setText(dest.getTitle());
		ratingLabel.setText(Double.toString(dest.getRatings()));
		
		Button btnMore = new Button("more details");
		detailsBox.getChildren().addAll(titleLabel, ratingLabel, btnMore);
		detailsBox.setSpacing(20);
		imageBox.getChildren().addAll(ivDest);
		hbox.getChildren().addAll(imageBox, detailsBox);
		return hbox;
	}		
	
	public VBox details(ArrayList<Destination> destArr, String newV) {
		VBox detailBox = new VBox();
		Label title = new Label("Title: ");		
		Label titleD = new Label("");
		Label price = new Label("Price: ");
		Label priceD = new Label("");
		Label address = new Label("Address: ");
		Label addressD = new Label("");
		
		for (Destination dest : destArr ) {
			if (dest.getTitle().equals(newV)) {
				//if searched, then add things in vbox
				titleD.setText(dest.getTitle());
				priceD.setText(Double.toString(dest.getPrice()));
				addressD.setText(dest.getAddress());
			}
		}
		
		detailBox.getChildren().addAll(title, titleD, price, priceD, address, addressD);
				
		return detailBox;
	}

	
	

}
