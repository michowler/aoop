package application;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ShowMessage
{
  public void showMessage(Destination dest)
  {
	  Alert alert = new Alert(Alert.AlertType.INFORMATION);
	  alert.setTitle("Information");
	  alert.setHeaderText("Added a destination!");
	  alert.setResizable(false);
	  String s ="You have added " + dest.getTitle() + " to your itinerary plan.";
	  alert.setContentText(s);
  }

}