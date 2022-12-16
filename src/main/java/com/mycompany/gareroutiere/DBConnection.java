/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gareroutiere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author lamri
 */
public class DBConnection {
    Connection con;
    Statement stm;
    ResultSet rst;
    PreparedStatement pst;
    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gareroutiere", "root", "Rachid-123");
            stm = con.createStatement();
            System.out.println("Connexion au DB bien établie!!!");
        } catch (Exception e) {
            System.out.println("Erreur de chargement de pilote:" + e);
        }
    }
   
    
}
