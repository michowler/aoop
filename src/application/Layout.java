package application;

import java.io.IOException;
import java.util.ArrayList;

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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Layout {
	public BorderPane homePage(ArrayList<User> curUser, Stage primaryStage) {
		BorderPane homepage = new BorderPane();	
		
		BorderPane bPaneTop = new BorderPane();		
		HBox menuHBoxLeft = new HBox();
		HBox menuHBoxRight = new HBox();
		HBox menuSearch = new HBox();
		
		Menu moreOptions = new Menu(curUser.get(0).getUsername()); 
		   
	    MenuItem m1 = new MenuItem("SETTINGS"); 
	    MenuItem itineraryMenu = new MenuItem("ITINERARY PLAN");
	    MenuItem contactMenu = new MenuItem("CONTACT US");
	    MenuItem logoutMenu = new MenuItem("LOGOUT"); 	     
			   
	    moreOptions.getItems().add(m1); 
	    moreOptions.getItems().add(itineraryMenu); 
	    moreOptions.getItems().add(contactMenu); 
	    moreOptions.getItems().add(logoutMenu); 
        
        MenuBar menuBar = new MenuBar();        
        menuBar.getMenus().add(moreOptions); 
        menuBar.setId("menuBar");
        menuBar.setStyle("-fx-text-fill: white");
        
		Button homeButton = new Button("HOME");
		TextField searchField = new TextField();		
		Button searchButton = new Button("SEARCH");
		searchField.setPromptText("search something");		
				
		HBox.setMargin(homeButton,new Insets(10,10,10,10));
		HBox.setMargin(searchField,new Insets(10,10,10,10));
		HBox.setMargin(searchButton,new Insets(10,10,10,0));
		HBox.setMargin(menuBar,new Insets(10,10,10,10));
		
	    menuSearch.getChildren().addAll(searchField, searchButton);
	    menuHBoxLeft.getChildren().addAll(homeButton, menuSearch);
	    menuHBoxRight.getChildren().addAll(menuBar);
	    menuHBoxLeft.setSpacing(5);
	    bPaneTop.setLeft(menuHBoxLeft);
	    bPaneTop.setRight(menuHBoxRight);
	    homepage.setTop(bPaneTop);
	    homepage.setCenter(mainContainer());
	    
	    homeButton.setOnAction(e->{
	    	//clear container, enter new container	    	
	    	homepage.setCenter(mainContainer());
	    });
	    
	    ItPlan plan = new ItPlan();
	    
	    itineraryMenu.setOnAction(e->{	    	
	    	//plan.start(primaryStage);
	    	homepage.setCenter(plan.displayPlan(curUser.get(0)));	    		    	
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
		Charts chart = new Charts();
		BorderPane homeContainer = new BorderPane();
		HBox hboxMenu = new HBox();
		
		VBox vboxHotel = new VBox();
		VBox vboxRest = new VBox();
		VBox vboxPlace = new VBox();
				
        Image hotelPic = new Image("application/hotel.jpg", 390, 500, false, false);        
        ImageView iv1 = new ImageView();
        iv1.setImage(hotelPic);
        Image attractionPic = new Image("application/attraction.jpg", 390, 500, false, false);        
        ImageView iv2 = new ImageView();
        iv2.setImage(attractionPic);
        Image restPic = new Image("application/restaurant.jpg", 390, 500, false, false);        
        ImageView iv3 = new ImageView();
        iv3.setImage(restPic);
      
        iv1.setPreserveRatio(true);
        iv2.setPreserveRatio(true);
        iv3.setPreserveRatio(true);        
		
		Button hotelButton = new Button("Hotels");
		Button restButton = new Button("Restaurants");
		Button placeButton = new Button("Attractions");
		
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
		
		homeContainer.setBottom(hboxMenu);
		hboxMenu.setAlignment(Pos.BASELINE_CENTER);
		return homeContainer;		
	}

	
	
	

	
	

}
