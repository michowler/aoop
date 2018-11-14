package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
/**
 * @author ericjbruno
 */
public class SearchFilter {

    public BorderPane searchBox() {
    	BorderPane bp = new BorderPane();
    	BorderPane.setMargin(bp,new Insets(80,100,10,100));
    	ObservableList<String> entries = FXCollections.observableArrayList();    
    	ListView list = new ListView();
    	mainData dataList = new mainData();
        
        VBox vboxList = new VBox();
        VBox vboxDetails = new VBox();
        Button btn = new Button();
        btn.setText("Choose");

        TextField txt = new TextField();
        txt.setPromptText("Search destination");
        txt.textProperty().addListener(
            new ChangeListener() {
                public void changed(ObservableValue observable, 
                                    Object oldVal, Object newVal) {
                    handleSearchByKey2((String)oldVal, (String)newVal, (ListView) list, (ObservableList<String>) entries);
                }
            });
         
        // Set up the ListView
        list.setMaxHeight(180);
        // Populate the list's entries
        for ( int i = 0; i < dataList.dataList().size(); i++ ) {
            entries.add(dataList.dataList().get(i).getTitle());
        }
       
        list.setItems( entries );
        
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("ListView selection changed from oldValue = " 
                        + oldValue + " to newValue = " + newValue);                
                Layout layout = new Layout();
                mainData dataList = new mainData();  
                vboxDetails.getChildren().clear();
                vboxDetails.getChildren().addAll(layout.details(dataList.dataList(), newValue));
                
            }
            
        });
        
        btn.setOnAction(e->{
        	
        });
        
        vboxList.getChildren().addAll(txt, list, btn);        
        HBox hbox = new HBox();
        hbox.getChildren().addAll(vboxList, vboxDetails);        
        hbox.setSpacing(20);
        Text txtTitle = new Text("Search Destination");		
		txtTitle.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        bp.setTop(txtTitle);
        bp.setCenter(hbox);
        return bp;
        
    }
     
    public void handleSearchByKey(String oldVal, String newVal, ListView list, ObservableList<String> entries) {
        // If the number of characters in the text box is less than last time
        // it must be because the user pressed delete
        if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
            // Restore the lists original set of entries 
            // and start from the beginning
            list.setItems( entries );
        }
         
        // Change to upper case so that case is not an issue
        newVal = newVal.toUpperCase();
 
        // Filter out the entries that don't contain the entered text
        ObservableList<String> subentries = FXCollections.observableArrayList();
        for ( Object entry: list.getItems() ) {
            String entryText = (String)entry;
            if ( entryText.toUpperCase().contains(newVal) ) {
                subentries.add(entryText);
            }
        }
        list.setItems(subentries);
    }
 
    public void handleSearchByKey2(String oldVal, String newVal, ListView list, ObservableList<String> entries) {
        // If the number of characters in the text box is less than last time
        // it must be because the user pressed delete
        if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
            // Restore the lists original set of entries 
            // and start from the beginning
            list.setItems( entries );
        }
         
        // Break out all of the parts of the search text 
        // by splitting on white space
        String[] parts = newVal.toUpperCase().split(" ");
 
        // Filter out the entries that don't contain the entered text
        ObservableList<String> subentries = FXCollections.observableArrayList();
        for ( Object entry: list.getItems() ) {
            boolean match = true;
            String entryText = (String)entry;
            for ( String part: parts ) {
                // The entry needs to contain all portions of the
                // search string *but* in any order
                if ( ! entryText.toUpperCase().contains(part) ) {
                    match = false;
                    break;
                }
            }
 
            if ( match ) {
                subentries.add(entryText);
            }
        }
        list.setItems(subentries);
    }
}