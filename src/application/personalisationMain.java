package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class personalisationMain {		
	
	public BorderPane personalizeFilter() {
		BorderPane bp = new BorderPane();
		BorderPane.setMargin(bp,new Insets(80,100,10,100));
		bp.setMinHeight(100);
		
		Text txtTitle = new Text("Personalize");		
		txtTitle.setFont(Font.font("Arial", FontWeight.BOLD, 50));
		
		Forms form = new Forms();

		RadioButton rest = new RadioButton("Restaurant");
		RadioButton attr = new RadioButton("Attraction");
		RadioButton hotel = new RadioButton("Hotel");
		
		final ToggleGroup group = new ToggleGroup();
		
		rest.setToggleGroup(group);
		attr.setToggleGroup(group);
		hotel.setToggleGroup(group);
		
		Button ok = new Button("Choose");
		ok.setOnAction(value -> {
			try {
				if(group.getSelectedToggle() == rest) {
					//mainRestaurant.launch(mainRestaurant.class);					
					bp.setBottom(form.restaurantFilter());				
				}
				else if (group.getSelectedToggle() == attr) {					
					bp.setBottom(form.attractionFilter());
					
				} else {					
					bp.setBottom(form.hotelFilter());
				}		
			} catch (NullPointerException e) {				
				e.printStackTrace();
			} catch (RuntimeException e) {				
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}							
		});

		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.getChildren().addAll(new Label("Choose one category: "), rest, attr, hotel, ok);
		vb.setAlignment(Pos.CENTER);
		bp.setCenter(vb);
		bp.setAlignment(txtTitle,Pos.BASELINE_LEFT);
		bp.setTop(txtTitle);
		return bp;
		
	}

	
}
