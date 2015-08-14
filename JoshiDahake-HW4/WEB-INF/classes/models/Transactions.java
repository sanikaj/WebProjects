package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import super_airways.Query_Database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sanika
 */
public class Transactions {
    private int account_id;

    private String holder_name;
    private String routing_number;
    private String bank_number;

    
    
    private String balance;
    Query_Database qd = new Query_Database();

    public Transactions(String holderName, String routingNumber, String balance) {
        this.holder_name = holderName;
        this.routing_number= routingNumber;
        this.balance = balance;
    }
    
    public Transactions() {
      qd.setConnection();
    } 
    
    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
    
    public String getHolder_name() {
        return holder_name;
    }

    public void setHolder_name(String holder_name) {
        this.holder_name = holder_name;
    }

    public String getRouting_number() {
        return routing_number;
    }

    public void setRouting_number(String routing_number) {
        this.routing_number = routing_number;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
    
    public String getBank_number() {
        return bank_number;
    }

    public void setBank_number(String bank_number) {
        this.bank_number = bank_number;
    }
    
    public boolean checkTransactions(int accountId, String holderName, String routingNumber) {
        boolean check = false;
        try {
            String SQL = "SELECT ACCOUNTID,HOLDERNAME,ROUTINGNUMBER,BANKNUMBER,BALANCE FROM sjoshi.accounts a WHERE a.HOLDERNAME = '" + holderName  + "' AND a.ROUTINGNUMBER = '" + routingNumber + "'" + "AND a.ACCOUNTID= " + accountId;
            ResultSet rset1 = qd.execute(SQL);
            boolean isResults = false;
            while (rset1.next()) {
                     this.setAccount_id(accountId);
                     this.setHolder_name(holderName);
                     this.setBalance(rset1.getInt("BALANCE")+ "");
                     this.setRouting_number(routingNumber);
                     this.setBank_number(rset1.getString("BANKNUMBER"));
                     isResults = true;
                }
            return isResults;
        } catch (SQLException sq) {
              System.out.println(sq.getStackTrace());
            }
        return true;
        
              
    }
}
