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
public class Car {
    private String car_id;
    private String car_rental_company;
    private float car_cost;
    Query_Database qd;
    ArrayList<Car> cars;
    
    public Car() {
        
    }
    
    public Car(String car_id, String car_rental_company, Float car_cost) {
        this.car_id= car_id;
        this.car_rental_company= car_rental_company;
        this.car_cost = car_cost;
    }
    
    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getCar_rental_company() {
        return car_rental_company;
    }

    public void setCar_rental_company(String car_rental_company) {
        this.car_rental_company = car_rental_company;
    }

    public float getCar_cost() {
        return car_cost;
    }

    public void setCar_cost(float car_cost) {
        this.car_cost = car_cost;
    }
 
    public ArrayList<Car> retrieveCars(List car_ids) {
        int count=0;
        cars = new ArrayList<Car>();
        String ids = new String();
        ids= "";
        for(int i=0 ;i < car_ids.size() ; i++) {
            ids=  ids + "'" + car_ids.get(i) + "'" + ",";
        }
        if (ids.length() > 0 && ids.charAt(ids.length()-1)==',') {
            ids = ids.substring(0, ids.length()-1);
        }
        String SQL = "SELECT car_id,car_rental_company,car_cost FROM sjoshi.car WHERE car_id IN("  + ids  + ");";
        qd= new Query_Database();
        ResultSet rset1 =  qd.execute(SQL);
       
        try {
        while(rset1.next()) {
            Car car = new Car();
            car.car_id = rset1.getString("car_id");
            car.car_rental_company = rset1.getString("car_rental_company");
            car.car_cost = rset1.getFloat("car_cost");
            cars.add(car);
            count ++;
        }
         if(count == 0) {
             return null;
         }
         return cars;
        } catch(SQLException e) {
           System.out.println(e.getStackTrace());
           return null;
       }
    }     
        
}
