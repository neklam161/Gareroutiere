package App;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import java.sql.DriverManager;
import java.sql.Connection;
import oracle.jdbc.OracleDriver;
/**
 *
 * @author lamri
 */
public class DBConnect {
    
    public static Connection getDBConnection() {
        Connection con;
        try {
            DriverManager.registerDriver(new OracleDriver());
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
            return con;
        } catch (Exception e) {
            System.out.println("Erreur de chargement de pilote:" + e);
            return null;
        }
    }
}
