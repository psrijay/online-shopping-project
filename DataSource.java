/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iamsu
 */
public class DataSource {
    private Connection con;
    private PreparedStatement st;

    public Connection getCon() {
        return con;
    }

    public void setCon() {
        try {
            this.con = DriverManager.getConnection("jdbc:odbc:Eshp");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public PreparedStatement getSt() {
        return st;
    }

    public void setSt(String query) {
        try {
            this.st = con.prepareCall(query);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    
    
}
