/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import super_airways.Query_Database;

/**
 *
 * @author sanika
 */
public class Accounts {
    private int accountId;
    private String holder_Name ;
    private int routing_Number;
    private String bank_Number;
    private int balance;
    Query_Database qd; 
    
    public Accounts() {
        qd= new Query_Database();
        qd.setConnection();
    }
    
    public Accounts(int accountId, String holder_name, int routing_Number, String bank_number, int balance) {
        this.accountId=accountId;
        this.holder_Name = holder_name;
        this.routing_Number = routing_Number;
        this.bank_Number = bank_number;
        this.balance = balance;
    }
    
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getHolder_Name() {
        return holder_Name;
    }

    public void setHolder_Name(String holder_Name) {
        this.holder_Name = holder_Name;
    }

    public int getRouting_Number() {
        return routing_Number;
    }

    public void setRouting_Number(int routing_Number) {
        this.routing_Number = routing_Number;
    }

    public String getBank_Number() {
        return bank_Number;
    }

    public void setBank_Number(String bank_Number) {
        this.bank_Number = bank_Number;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    public void updateAccountForUser(int account_id, float balance) {
        String SQL1= "SELECT BALANCE FROM sjoshi.accounts WHERE ACCOUNTID =" + "'" + account_id + "'";
        ResultSet rs1 = qd.execute(SQL1);
        try {
           if(rs1.next()) {
            Float balanceVal = rs1.getFloat("BALANCE");
            balanceVal -= balance;
            String SQL2 = "UPDATE sjoshi.accounts SET balance =" + balanceVal + " WHERE ACCOUNTID= " + "'" + account_id + "'";
            qd.executeAnUpdate(SQL2);
           }
        } catch (SQLException e) {
           System.out.println(e.getStackTrace());
        }
    }
}
