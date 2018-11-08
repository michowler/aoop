package application;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class Plan {
	
//    private TableView<Destination> table = new TableView<Destination>();
//    private final ObservableList<Destination> data =
//        FXCollections.observableArrayList(
//        		
//        );
//
//
	
    public VBox displayTable(User curUser) {
        TableView<Destination> table = new TableView<Destination>();
 
        final Label label = new Label("My Itinerary Plan");
        label.setFont(new Font("Arial", 12));
 
        table.setEditable(true);
 
//        TableColumn numCol = new TableColumn("No. ");
//        numCol.setMinWidth(100);
//        numCol.setCellValueFactory(new PropertyValueFactory<Destination, String>("numCol"));
 
        TableColumn fromCol = new TableColumn("From ");
        fromCol.setMinWidth(100);
        fromCol.setCellValueFactory(new PropertyValueFactory<Destination, String>("title"));
 
        TableColumn toCol = new TableColumn("To ");
        toCol.setMinWidth(100);
        toCol.setCellValueFactory(new PropertyValueFactory<Destination, String>("price"));
        
        TableColumn costCol = new TableColumn("Cost ");
        costCol.setMinWidth(100);
        costCol.setCellValueFactory(new PropertyValueFactory<Destination, String>("latitude"));
        
        TableColumn dsCol = new TableColumn("Distance (KM) ");
        toCol.setMinWidth(100);
        toCol.setCellValueFactory(new PropertyValueFactory<Destination, String>("longtitude"));
        
        TableColumn transTypeCol = new TableColumn("Trans Type ");
        transTypeCol.setMinWidth(100);
        transTypeCol.setCellValueFactory(new PropertyValueFactory<Destination, String>("ratings"));
        
        TableColumn transCostCol = new TableColumn("Trans Cost ");
        transCostCol.setMinWidth(100);
        transCostCol.setCellValueFactory(new PropertyValueFactory<Destination, String>("address"));
 
//        table.setItems(data);
        for(Destination dest : curUser.getMyIt()) {
	        final ObservableList<Destination> data =
	            FXCollections.observableArrayList(
	            	new Destination(dest.getTitle(), dest.getPrice(), dest.getLatitude(), dest.getLongtitude(), dest.getRatings(), dest.getAddress())	            			            	            		
	            );
	        table.setItems(data);
        }
        
        table.getColumns().addAll( fromCol, toCol, costCol, dsCol, transTypeCol, transCostCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
        return vbox;
    }
}
