package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Sorting {
	
	public ArrayList<Destination> lowPrice(ArrayList<Destination> tmpArr){
		 Collections.sort(tmpArr, new PriceOrder());
		return tmpArr;
	}
	
	public ArrayList<Destination> highPrice(ArrayList<Destination> tmpArr){
		 Collections.sort(tmpArr, Collections.reverseOrder(new PriceOrder()));
		return tmpArr;
	}
	
	public ArrayList<Destination> filteredRatings(ArrayList<Destination> tmpArr){
		 Collections.sort(tmpArr, new RatingsOrder());
		return tmpArr;
	}
	
	public ArrayList<Destination> ascOrder(ArrayList<Destination> tmpArr){
		 Collections.sort(tmpArr, new AlphabetOrder());
		return tmpArr;
	}
	
	public ArrayList<Destination> desOrder(ArrayList<Destination> tmpArr){
		 Collections.sort(tmpArr, Collections.reverseOrder(new AlphabetOrder()));
		return tmpArr;
	}	
	
   public class AlphabetOrder implements Comparator<Destination> {	    
	    public int compare(Destination d1, Destination d2) {
	        return d1.getTitle().compareTo(d2.getTitle());
	    }	   
	}
   
   public class PriceOrder implements Comparator<Destination> {	    
	    public int compare(Destination d1, Destination d2) {	        
	        return Double.compare(d1.getPrice(), d2.getPrice());
	    }	   
	}

   public class RatingsOrder implements Comparator<Destination> {	    
	    public int compare(Destination d1, Destination d2) {	        
	        return Double.compare(d1.getRatings(), d2.getRatings());
	    }	    
	}
   
   public BorderPane viewOrder(ArrayList<Destination> entries, User curUser) {
	   BorderPane bpSort = new BorderPane();
	   ListView<String> list = new ListView<String>();
	   BorderPane.setMargin(bpSort,new Insets(20,100,10,100));
	   Text txtTitle = new Text();
	   VBox vboxLeft = new VBox();
	   VBox vboxRight = new VBox();
	   
	   HBox fullSortDetails = new HBox();
	   txtTitle.setFont(Font.font("Arial", FontWeight.BOLD, 50));
	   ObservableList<String> items = FXCollections.observableArrayList();
	   
	   switch(entries.get(0).getClass().getName()) {
			case "application.Restaurants":
				txtTitle.setText("Restaurants");
				break;
			case "application.Hotels":
				txtTitle.setText("Hotels");
				break;
			case "application.Attraction":
				txtTitle.setText("Attractions");
				break;
			default:
				txtTitle.setText("Destinations");
				break;
	   }
	   	   
	   for (Destination dest : entries) {
		   items.add( dest.getTitle() );
	   }	   
	   list.setItems(items);	

	   list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
           @Override
           public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//               System.out.println("ListView selection changed from oldValue = " 
//                       + oldValue + " to newValue = " + newValue);                
               Layout layout = new Layout();
               mainData dataList = new mainData(); 
               vboxRight.getChildren().clear();
               vboxRight.getChildren().addAll(layout.details( curUser, dataList.dataList(), newValue));        	  
           }           
       });
	   
	   vboxLeft.getChildren().addAll(txtTitle, showFilterOptions(curUser, vboxLeft, vboxRight, entries), list);
	   vboxRight.setAlignment(Pos.CENTER);	   
		
	   fullSortDetails.getChildren().addAll(vboxLeft, vboxRight);
	   fullSortDetails.setSpacing(100);
	   bpSort.setCenter(fullSortDetails);
	   return bpSort; 
   }
   
   public MenuBar showFilterOptions(User curUser, VBox vbleft, VBox vbright, ArrayList<Destination> dataList) {
	    Sorting st = new Sorting();
		Menu filterOptions = new Menu("Sort by : "); 					
	    MenuItem ratingsHigh = new MenuItem("Ratings (highest to lowest)");	    
	    MenuItem costLow = new MenuItem("Cost (lowest to highest)");
	    MenuItem costHigh = new MenuItem("Cost (highest to lowest)");
	    MenuItem asOrder = new MenuItem("A-Z");
	    MenuItem desOrder = new MenuItem("Z-A");	    	     		       		   
	    
	    ratingsHigh.setOnAction(e->{		    	
	    	vbleft.getChildren().clear();
	    	vbright.getChildren().clear();
	    	vbleft.getChildren().add(viewOrder(filteredRatings(dataList), curUser));			    	
	    });
	  
	    costLow.setOnAction(e->{	
	    	vbleft.getChildren().clear();
	    	vbright.getChildren().clear();
	    	vbleft.getChildren().add(viewOrder(lowPrice(dataList), curUser));		    	
	    });
	    
	    costHigh.setOnAction(e->{	
	    	vbleft.getChildren().clear();
	    	vbright.getChildren().clear();	    	
	    	vbleft.getChildren().add(viewOrder(highPrice(dataList), curUser));		    	
	    });
       
	    asOrder.setOnAction(e->{	
	    	vbleft.getChildren().clear();
	    	vbright.getChildren().clear();
	    	vbleft.getChildren().add(viewOrder(ascOrder(dataList), curUser));		    	
	    });
	    
	    desOrder.setOnAction(e->{	
	    	vbleft.getChildren().clear();
	    	vbright.getChildren().clear();
	    	vbleft.getChildren().add(viewOrder(desOrder(dataList), curUser));				    	
	    });
	    
	   filterOptions.getItems().addAll(ratingsHigh, costLow, costHigh, asOrder, desOrder);		    
       MenuBar menuBar = new MenuBar();        
       menuBar.getMenus().add(filterOptions); 
       return menuBar;
  }

	   

}
