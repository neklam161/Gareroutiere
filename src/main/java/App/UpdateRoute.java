package App;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;

public class UpdateRoute extends JInternalFrame {

    private JLabel routeNo,  From,  To,  Distance;
    private JTextField txtRouteNo,    txtFrom,  txtTo,  txtDistance ;
    private JButton Update,  Search,  Clear, Delete;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    String route, from, to, distance;
    private JButton Cancel;
    private JPanel jPanel1;
    final JFileChooser fc = new JFileChooser();
    String getPicture;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private Connection dbconn;
    private static String info;

    public UpdateRoute(String route,String from, String to,String distance){
        super("Update Routes", false, true, false, true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        routeNo = new JLabel("Route id ");
        From = new JLabel("From");
        To = new JLabel("To");
        Distance = new JLabel("Distance");

        txtRouteNo = new JTextField(10);
        txtFrom = new JTextField(10);
        txtTo = new JTextField(10);
        txtDistance = new JTextField(15);
        txtRouteNo.setText(route);
        txtFrom.setText(from);
        txtTo.setText(to);
        txtDistance.setText(distance);
       

        Update = new JButton("Update");
        Search = new JButton("Search");
        Clear = new JButton("Clear");
        Cancel = new JButton("Cancel");
        Delete = new JButton("Delete");
        
        
        jPanel1 = new javax.swing.JPanel(new java.awt.GridLayout(6, 2));
        jPanel1.add(routeNo);
        jPanel1.add(txtRouteNo);
        jPanel1.add(From);
        jPanel1.add(txtFrom);
        jPanel1.add(To);
        jPanel1.add(txtTo);
        jPanel1.add(Distance);
        jPanel1.add(txtDistance);
      

        jPanel3 = new javax.swing.JPanel(new java.awt.FlowLayout());

        jPanel3.add(jPanel1);


        jPanel4 = new javax.swing.JPanel(new java.awt.FlowLayout());

        jPanel4.add(Update);
        jPanel4.add(Cancel);
        jPanel4.add(Search);
        jPanel4.add(Clear);
        jPanel4.add(Delete);
        setResizable(false);
        setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
        try {

            Statement s = DBConnect.getDBConnection().createStatement();
        } catch (Exception excp) {
            excp.printStackTrace();
        }
       
        txtTo.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLetter(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {

                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null, "This Field Only acept text", "ERROR",
                            JOptionPane.DEFAULT_OPTION);
                    e.consume();
                }
            }
        });
        txtFrom.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLetter(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {

                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null, "This Field Only acept text", "ERROR",
                            JOptionPane.DEFAULT_OPTION);
                    e.consume();
                }
            }
        });
        
        txtDistance.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {

                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Distance in digit", "ERROR",
                            JOptionPane.DEFAULT_OPTION);
                    e.consume();
                }
            }
        });





        Update.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtRouteNo.getText() == null ||
                        txtRouteNo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter route number", "Error",
                            JOptionPane.DEFAULT_OPTION);
                    txtRouteNo.requestFocus();
                    return;
                }
                
                if (txtFrom.getText() == null ||
                        txtFrom.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter From", "tobiluoch",
                            JOptionPane.DEFAULT_OPTION);
                    txtFrom.requestFocus();
                    return;
                }
                if (txtTo.getText() == null ||
                        txtTo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter To", "tobiluoch",
                            JOptionPane.DEFAULT_OPTION);
                    txtTo.requestFocus();
                    return;
                }
                if (txtDistance.getText() == null ||
                        txtDistance.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Distance", "tobiluoch",
                            JOptionPane.DEFAULT_OPTION);
                    txtDistance.requestFocus();
                    return;
                }
                try {
                    Statement statement = DBConnect.getDBConnection().createStatement();
                    {
                        String temp = "UPDATE Route SET " +
                                " ville_depart  ='" + txtFrom.getText() +
                                "',ville_arrive    ='" + txtTo.getText() +
                                "',Distance   ='" + txtDistance.getText() +
                                "' WHERE id_route LIKE'" + txtRouteNo.getText() + "'";
                        int result = statement.executeUpdate(temp);
                        setVisible(false);
                        dispose();
                    }

                } catch (SQLException sqlex) {
                    sqlex.printStackTrace();
                }

            }
        });

        Cancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        Clear.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtRouteNo.setText("");           
                txtFrom.setText("");
                txtTo.setText("");
                txtDistance.setText("");

            }
        });


        Search.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtRouteNo.equals("")) {

                        Statement statement = DBConnect.getDBConnection().createStatement();
                        String query = ("SELECT * FROM Route where id_route ='" + txtRouteNo.getText() + "'");
                        ResultSet rs = statement.executeQuery(query);
                        display(rs);
                        statement.close();
                    } else //{
                    {
                        JOptionPane.showMessageDialog(null, "Enter route number to search", "Error",
                                JOptionPane.DEFAULT_OPTION);
                    }
                    return;
                //}
                } catch (SQLException sqlex) {
                    sqlex.printStackTrace();
                }



                setVisible(true);

            }
        });
        Delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        Statement stmt = DBConnect.getDBConnection().createStatement();
                        if (txtRouteNo.equals("")){}
                        else{
                            String query = "DELETE  FROM route where id_route ='" + txtRouteNo.getText() + "'";
                            int result = stmt.executeUpdate(query);
                            System.out.println(result);
                            if (result == 0) {
                                JOptionPane.showMessageDialog(null, "Record Deleted","DELETION", JOptionPane.DEFAULT_OPTION);
                            } else {
                                
                                txtFrom.setText("");
                                txtTo.setText("");
                                txtDistance.setText("");
                            }
                            stmt.close();
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Cannot delete record now, May be related with other tables"  ,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                
            }
        });

        jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

        jPanel5.add(jPanel3, java.awt.BorderLayout.CENTER);
        jPanel5.add(jPanel4, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel5);

        pack();
        setVisible(true);
    }

    public void display(ResultSet rs) {
        try {
            //rs.next();

            boolean recordNumber = rs.next();
            if (recordNumber) {

                route = rs.getString(1);
               
                from = rs.getString(2);
                to = rs.getString(3);
                distance = rs.getString(4);
            

                txtRouteNo.setText(route);
           
                txtFrom.setText(from);
                txtDistance.setText(distance);
                txtTo.setText(to);
             

            } else {
                JOptionPane.showMessageDialog(null, "Record Not found", "ERROR",
                        JOptionPane.DEFAULT_OPTION);
            }
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }

    }

}
