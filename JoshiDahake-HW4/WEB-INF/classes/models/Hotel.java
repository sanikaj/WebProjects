/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import super_airways.Query_Database;

/**
 *
 * @author sanika
 */
public class Hotel {
    private String hotel_id;
    private String hotel_name;
    private String room_type;
    private Float room_cost;
    private ArrayList<Hotel> hotels;
    Query_Database qd;
   
    public Hotel() {
        
    }
    
    public Hotel(String hotel_id, String hotel_name, String room_type, Float room_cost) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.room_type = room_type;
        this.room_cost = room_cost;
    }
    
    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public Float getRoom_cost() {
        return room_cost;
    }

    public void setRoom_cost(Float room_cost) {
        this.room_cost = room_cost;
    }
    
    public ArrayList<Hotel> retrieveHotels(List hotel_ids) {
        int count=0;
        hotels = new ArrayList<Hotel>();
        String ids = new String();
        ids= "";
        for(int i=0 ;i < hotel_ids.size() ; i++) {
            ids=  ids + "'" + hotel_ids.get(i) + "'" + ",";
        }
        if (ids.length() > 0 && ids.charAt(ids.length()-1)==',') {
            ids = ids.substring(0, ids.length()-1);
        }
        String SQL = "SELECT hotel_id,hotel_name,room_type,room_cost FROM sjoshi.hotel WHERE hotel_id IN("  + ids  + ");";
        qd= new Query_Database();
        ResultSet rset1 =  qd.execute(SQL);
                   
        try {
        while(rset1.next()) {
            Hotel hotel = new Hotel();
            hotel.hotel_name = rset1.getString("hotel_name");
            hotel.room_type = rset1.getString("room_type");
            hotel.room_cost = rset1.getFloat("room_cost");
            hotel.setHotel_id(rset1.getString("hotel_id"));
            hotels.add(hotel);
            count ++;
        }
         if(count == 0)
             return null;
         return hotels;
        } catch(SQLException e) {
           System.out.println(e.getStackTrace());
           return null;
    }
    }
}

