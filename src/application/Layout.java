package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Layout extends Application {
	public void start(Stage primaryStage) throws Exception {
		AnchorPane root = new AnchorPane();
		
		BorderPane bPaneTop = new BorderPane();		
		HBox menuHBoxLeft = new HBox();
		HBox menuHBoxRight = new HBox();
		
		Menu moreOptions = new Menu("username"); 
		  
	    MenuItem m1 = new MenuItem("settings"); 
	    MenuItem m2 = new MenuItem("itinerary plan");
	    MenuItem m3 = new MenuItem("logout"); 	     
			   
	    moreOptions.getItems().add(m1); 
	    moreOptions.getItems().add(m2); 
	    moreOptions.getItems().add(m3); 
        
        MenuBar menuBar = new MenuBar();        
        menuBar.getMenus().add(moreOptions); 
        
		Button homeButton = new Button("Home");
		TextField searchField = new TextField();		
		Button searchButton = new Button("Search");
		
//	    bPane.setBottom(new TextField("Bottom")); 
//	    bPane.setLeft(new TextField("Left")); 
//	    bPane.setRight(new TextField("Right")); 
//	    bPane.setCenter(new TextField("Center")); 
	      
	    menuHBoxLeft.getChildren().addAll(homeButton, searchField, searchButton, menuBar);
	    menuHBoxRight.getChildren().addAll(menuBar);
	    menuHBoxLeft.setSpacing(5);
	    bPaneTop.setLeft(menuHBoxLeft);
	    bPaneTop.setRight(menuHBoxRight);
	    AnchorPane.setTopAnchor(bPaneTop, 100.0);	     
	    
	    Scene scene = new Scene(root, 1280,720);  
	    scene.getStylesheets().add("application/application.css");
	    
	    primaryStage.setTitle("Tour Me Around: Home"); 
	    primaryStage.setScene(scene);          
	    primaryStage.show(); 
		
		
		
		
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	
	

}
