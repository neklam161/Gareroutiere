/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GareRoutiere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;

/**
 *
 * @author lamri
 */
public class DBConnect {
    
    public static Connection getDBConnection() {
        Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gareroutiere", "root", "");
            return con;
        } catch (Exception e) {
            System.out.println("Erreur de chargement de pilote:" + e);
            return null;
        }
    }
}
