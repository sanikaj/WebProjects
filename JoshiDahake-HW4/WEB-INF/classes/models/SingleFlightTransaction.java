/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author nikhildahake
 */
public class SingleFlightTransaction implements Serializable{

    private int flight_no;
    private String date;
    private String departure_time;
    private String arrival_time;
    private int numberOfStops;
    private int noOfSeatsRequired;
    private float totalCost;
    static final long serialVersionUID = 43L;
    private String totalTravelPackageCost;
   
    void SingleFlightTransaction() {
    }

    public int getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(int flight_no) {
        this.flight_no = flight_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public int getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(int numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    public int getNoOfSeatsRequired() {
        return noOfSeatsRequired;
    }

    public void setNoOfSeatsRequired(int noOfSeatsRequired) {
        this.noOfSeatsRequired = noOfSeatsRequired;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public String getTotalTravelPackageCost() {
        return totalTravelPackageCost;
    }

    public void setTotalTravelPackageCost(String totalTravelPackageCost) {
        this.totalTravelPackageCost = totalTravelPackageCost;
    }
    
}
