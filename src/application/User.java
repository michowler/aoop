package application;

import java.util.ArrayList;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

public class User {
	private String username;
	private String password;
	private String location;
	private String country;
	private double latitude;
	private double longtitude;
	private double budget;
	private ArrayList<Pane> myContainer = new ArrayList<Pane>();
	private ArrayList<Destination> myIt = new ArrayList<Destination>();
	private ArrayList<Destination> myHistory = new ArrayList<Destination>();

	public User() {
		this.username = "guest";
		this.password = "123123";
		this.location = "Kuala Lumpur";
		this.country = "Malaysia";
		this.latitude = 3.08;
		this.longtitude = 108.41;
		this.budget = 0.0;
		this.myIt = new ArrayList<Destination>();
		this.myHistory = new ArrayList<Destination>();
	}
	
	/**
	 * User constructor
	 * @param username username
	 * @param password password
	 * @param location location
	 * @param budget budget
	 * @param country country
	 * @param latitude latitude
	 * @param longtitude longitude
	 */
	public User(String username, String password, String location, double budget, String country, double latitude, double longtitude) {
		this.username = username;
		this.password = password;
		this.location = location;
		this.budget = budget;
		this.country = country;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.myIt = new ArrayList<Destination>();
		this.myHistory = new ArrayList<Destination>();		
	}
	
	/**
	 * @return user username
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * @param username username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return user location
	 */
	public String getLocation() {
		return this.location;
	}
	
	/**
	 * @param location location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * @param myIt my itinerary array list
	 */
	public void setMyIt(ArrayList<Destination> myIt){
	    this.myIt = myIt;
	}
	
	public void addMyIt(Destination dest){
	    this.myIt.add(0,dest);
	}
	
	public void removeMyIt(int dest){
	    this.myIt.remove(dest);
	}
	
	public void removeMyIt(Destination dest){
	    this.myIt.remove(dest);
	}


	/**
	 * @return get user itinerary arraylist
	 */
	public ArrayList<Destination> getMyIt(){
	    return myIt;
	}
	
	/**
	 * @param myIt my itinerary array list
	 */
	public void setMyHistory(ArrayList<Destination> myHistory){
	    this.myHistory = myHistory;
	}

	/**
	 * @return get user itinerary arraylist
	 */
	public ArrayList<Destination> getMyHistory(){
	    return myHistory;
	}
	
	public ArrayList<Pane> getMyContainer(){
	    return myContainer;
	}
	
	public void setMyContainer(ArrayList<Pane> myContainer){
	    this.myContainer = myContainer;
	}
	
	/**
	 * @return user password
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @param latitude latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * @return user position latitude
	 */
	public double getLatitude() {
		return this.latitude;
	}
	
	/**
	 * @return user position longitude
	 */
	public double getLongtitude() {
		return this.longtitude;
	}
	
	/**
	 * @param longtitude longitude
	 */
	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	
	/**
	 * @param budget budget
	 */
	public void setBudget(double budget) {
		this.budget = budget;
	}
	
	/**
	 * @return user budget
	 */
	public double getBudget() {
		return this.budget;
	}
	
	/**
	 * @return user country
	 */
	public String getCountry() {
		return this.country;
	}
	
	/**
	 * @param country country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "==========================================================\n-----------------------USER INFO-------------------------\n==========================================================\nUsername: " + getUsername() + "\n" + "Current location: " + getLocation() + "\nBudget: RM " + getBudget() + "\nCountry: " + getCountry() + "\n==========================================================";
		
	}
	
	
	
}


