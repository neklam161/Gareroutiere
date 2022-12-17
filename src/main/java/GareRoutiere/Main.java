

package GareRoutiere;
//import java.awt.EventQueue;
//import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginPage().setVisible(true);
            //new GareRoutiere().setVisible(true);
        });
    }
}
