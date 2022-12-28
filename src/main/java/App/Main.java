package App;




//import java.awt.EventQueue;
//import javax.swing.JFrame;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main implements Runnable{
    private final JFrame frame;
    public Main(JFrame frm){
        this.frame=frm;
    }//constructor closed
    @Override
    public void run(){
        frame.setVisible(true);
    }//run method closed
    public static void main(String args[]){
        EventQueue.invokeLater(new Main(new LoginScreen()));
    }//main method closed

}//class closed
