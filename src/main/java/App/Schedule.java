package App;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Schedule extends JInternalFrame {

    private JLabel sche_id ,RouteNo,date ,DeptTime,BusNo , DriverNo ;
    private JComboBox cboBusNo,  cboRouteNo, cboDriverNo;
    private JTextField txtsche,txtDepTime;
    private JButton Check,  Schedule,  Cancel;
    private DateButton s_date;
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private static String info;

    public Schedule() {
        super("Scheduling Process", false, true, false, true);
        
        sche_id = new JLabel("Schedule Number ");
        BusNo = new JLabel("Bus Number ");      
        RouteNo = new JLabel("Route Number");       
        DriverNo = new JLabel("Driver Number");    
        date = new JLabel("Date Scheduled");   
        DeptTime = new JLabel("Departure Time");

        cboBusNo = new JComboBox();
        cboRouteNo = new JComboBox();
        cboDriverNo = new JComboBox();

        txtDepTime = new JTextField(10);
        txtsche=new JTextField(10);
        Check = new JButton("View Schedules");
        Schedule = new JButton("Schedule");
        Cancel = new JButton("Cancel");
        
        s_date = new DateButton();
        sche_id.setFont(new Font("sansserif", Font.ITALIC, 14));
        BusNo.setFont(new Font("sansserif", Font.ITALIC, 14));
       
        RouteNo.setFont(new Font("sansserif", Font.ITALIC, 14));
       
        DriverNo.setFont(new Font("sansserif", Font.ITALIC, 14));
        date.setFont(new Font("sansserif", Font.ITALIC, 14));
       
        DeptTime.setFont(new Font("sansserif", Font.ITALIC, 14));
       
  
        DeptTime.setForeground(Color.blue);
        BusNo.setForeground(Color.blue);

        RouteNo.setForeground(Color.blue);
     
        DriverNo.setForeground(Color.blue);
      
        date.setForeground(Color.blue);
 

        jPanel1 = new JPanel(new java.awt.GridLayout(9, 2));
        
        jPanel1.add(sche_id);
        jPanel1.add(txtsche);
        jPanel1.add(RouteNo);
        jPanel1.add(cboRouteNo);
        jPanel1.add(date);
        jPanel1.add(s_date);
        jPanel1.add(DeptTime);
        jPanel1.add(txtDepTime);
        jPanel1.add(BusNo);
        jPanel1.add(cboBusNo);
        jPanel1.add(DriverNo);
        jPanel1.add(cboDriverNo);
       
        
        
       

        cboRouteNo.addItem("Select");
        cboBusNo.addItem("Select");      
        cboDriverNo.addItem("Select");       
  

        jPanel3 = new javax.swing.JPanel(new java.awt.FlowLayout());

        jPanel3.add(jPanel1);

        jPanel4 = new javax.swing.JPanel(new java.awt.FlowLayout());

        jPanel4.add(Check);
        jPanel4.add(Schedule);
        jPanel4.add(Cancel);
        
        setSize(550, 330);
        setLocation(200,0);
        add(jPanel3);
        setResizable(false);
        try {

            Statement s = DBConnect.getDBConnection().createStatement();
        } catch (Exception excp) {
            excp.printStackTrace();
            info = info + excp.toString();
        }
        setCbx();
        setCombo();
        setrt();

        Schedule.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                 
                    Statement statement = DBConnect.getDBConnection().createStatement();
                    {
                        String temp = "INSERT INTO schedule VALUES (" +
                                txtsche.getText() + ", " +
                                cboRouteNo.getSelectedItem().toString()+ ",TO_DATE( '" +
                                s_date.getText() + "','DD/MM/YY'), '" +
                                txtDepTime.getText()+ "', '" +
                                cboBusNo.getSelectedItem().toString() + "', '" +
                                cboDriverNo.getSelectedItem().toString() + "')";

                        int result = statement.executeUpdate(temp);
                        String ObjButtons[] = {"Yes", "No"};
                        int PromptResult = JOptionPane.showOptionDialog(null, "Record succesfully added.Do you want to add another?",
                                "tobiluoch", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
            }
        }       catch (SQLException ex) {
                    Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        });
           
        Check.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                Show_schedules frm=new Show_schedules();
                Window.desktop.add(frm);
                frm.setVisible(true);
                try{
                    frm.setSelected(true);
                }catch(Exception ex){}
            new Show_schedules().setVisible(true);
            }
        });

        cboBusNo.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {


            }
        });
        
        cboRouteNo.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
              

            }
        });

        Cancel.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();

            }
        });




        jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

        jPanel5.add(jPanel3, BorderLayout.CENTER);
        jPanel5.add(jPanel4, BorderLayout.SOUTH);
        add(jPanel5);

    }

    private void setCbx() {
        try {
            ResultSet rst = DBConnect.getDBConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT Bus_id FROM Buses order by Bus_id");

            while (rst.next()) {
                cboBusNo.addItem(rst.getString(1));
            }
        } catch (Exception n) {
            n.printStackTrace();
        }
    }

    private void setrt() {
        try {
            ResultSet rst = DBConnect.getDBConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT id_route FROM Route ");

            while (rst.next()) {
                cboRouteNo.addItem(rst.getString(1));
            }
        } catch (Exception n) {
            n.printStackTrace();
        }
    }

    private void setCombo() {
        String dr;
        dr = "Driver";
        try {
            ResultSet rst = DBConnect.getDBConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery("Select driver_id from driver");
            while (rst.next()) {
                cboDriverNo.addItem(rst.getString(1));
                
            }
        } catch (Exception n) {
            n.printStackTrace();
        }


    }


}
