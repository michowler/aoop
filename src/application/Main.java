package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {  	        	
			BorderPane root = new BorderPane();
			Forms form = new Forms();
			root.setCenter(form.loginForm(form.initUserDB(), primaryStage));			
			Scene sceneLogout = new Scene(root, 1280,720);			
			primaryStage.setScene(sceneLogout);	
			primaryStage.setTitle("Tour Me Around");			
			sceneLogout.getStylesheets().add(getClass().getResource("application.css").toExternalForm());			
			primaryStage.show();
		} catch(Exception e) { 
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
