/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import super_airways.Query_Database;

/**
 *
 * @author sanika
 */
public class TravelPackages implements Serializable{
   private String destination;
   private String car_idOrhotel_id;
   private ArrayList<String> car_ids;
   private ArrayList<String> hotel_ids;
   private String travelPackageCostForCarPckg;
   private String travelPackageCostForHotelPckg;
   static final long serialVersionUID = 43L;

    Query_Database qd;
    public TravelPackages() {
        qd = new Query_Database();
        qd.setConnection();
    }
   
   public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

        
    public void getTravelPack(String carorHotelId,String travelPackageCostForCarPckg, String travelPackageCostForHotelPckg) {
       String SQL = "SELECT destination,car_idOrhotel_id FROM sjoshi.travelpackages WHERE car_idOrhotel_id=" + "'" + carorHotelId + "'";
       ResultSet rset1= qd.execute(SQL);
       try {
           while(rset1.next()) {
             this.car_idOrhotel_id = rset1.getString("car_idOrhotel_id");
             this.destination = rset1.getString("destination");
           }
           if(travelPackageCostForCarPckg != null) {
                this.travelPackageCostForCarPckg = travelPackageCostForCarPckg;
           }
           if(travelPackageCostForHotelPckg != null) {
               this.travelPackageCostForHotelPckg = travelPackageCostForHotelPckg;
           }
       } catch(SQLException sq) {
         System.out.println(sq.getStackTrace());  
       }
    }
    
    public String getCar_idOrhotel_id() {
        return car_idOrhotel_id;
    }

    public void setCar_idOrhotel_id(String car_idOrhotel_id) {
        this.car_idOrhotel_id = car_idOrhotel_id;
    }
  
    
    public String getTravelPackageCostForHotelPckg() {
        return travelPackageCostForHotelPckg;
    }

    public void setTravelPackageCostForHotelPckg(String travelPackageCostForHotelPckg) {
        this.travelPackageCostForHotelPckg = travelPackageCostForHotelPckg;
    }
    
    
    public String getTravelPackageCostForCarPckg() {
        return travelPackageCostForCarPckg;
    }

    public void setTravelPackageCostForCarPckg(String travelPackageCostForCarPckg) {
        this.travelPackageCostForCarPckg = travelPackageCostForCarPckg;
    }

    
    public Map<String,List<String>> retrieveAllDeals(String destination) {
        String SQL = "SELECT car_idOrhotel_id FROM sjoshi.travelpackages WHERE destination =" + "'" + destination + "';";
        car_ids= new ArrayList<String> ();
        hotel_ids = new ArrayList<String>();
        Map<String,List<String>> map =new HashMap();
        ResultSet rset1= qd.execute(SQL);
        int count =0;
        try {
        while(rset1.next()) {
            if(rset1.getString("car_idOrhotel_id").startsWith("c")) {
                car_ids.add(rset1.getString("car_idOrhotel_id"));
                count ++;
            } else if (rset1.getString("car_idOrhotel_id").startsWith("h")) {
                hotel_ids.add(rset1.getString("car_idOrhotel_id"));
                count++;
            }
    
        }
        if(count == 0) 
            return null;
        map.put("carList",car_ids);
        map.put("hotelList",hotel_ids);
        return map;
        } catch(SQLException sq) {
           System.out.println(sq.getStackTrace());
           return null;
        }
        }
    }
    

