    package App;


import java.sql.DriverManager;
import java.sql.Connection;
import oracle.jdbc.OracleDriver;

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
