package App;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AddRoute extends JInternalFrame {

    private JLabel routeNo, From,  To,  Distance;
    private JTextField txtRouteNo,  txtFrom,  txtTo,  txtDistance;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private JButton AddNew;
    private JButton Cancel;
    private JButton Clear;
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;

    public AddRoute() {
        super("Add New Route", false, true, false, true);
        setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);

        routeNo = new JLabel("Route No ");
        
        From = new JLabel("From");
        To = new JLabel("To");
        Distance = new JLabel("Distance");
        
        txtRouteNo = new JTextField(10);
        
        txtFrom = new JTextField(10);
        txtTo = new JTextField(10);
        txtDistance = new JTextField(15);
        AddNew = new JButton("Add New");
        Cancel = new JButton("Cancel");
        Clear = new JButton("Clear");

        jPanel1 = new JPanel(new java.awt.GridLayout(4, 2));

        jPanel1.add(routeNo);
        jPanel1.add(txtRouteNo);
        
       
        jPanel1.add(From);
        jPanel1.add(txtFrom);
        jPanel1.add(To);
        jPanel1.add(txtTo);
        jPanel1.add(Distance);
        jPanel1.add(txtDistance);
        
        
        jPanel3 = new javax.swing.JPanel(new FlowLayout());

        jPanel3.add(jPanel1);


        jPanel4 = new javax.swing.JPanel(new FlowLayout());

        jPanel4.add(AddNew);
        jPanel4.add(Cancel);
        jPanel4.add(Clear);
        setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
        setResizable(false);

        try {
        } catch (Exception excp) {
        }


        generator();
        
        txtTo.addKeyListener(new KeyAdapter() {

            @Override
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

            @Override
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

            @Override
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


        AddNew.addActionListener((ActionEvent e) -> {
            if (txtRouteNo.getText() == null ||
                    txtRouteNo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter route number", "ERROR",
                        JOptionPane.DEFAULT_OPTION);
                txtRouteNo.requestFocus();
                return;
            }
            
            if (txtFrom.getText() == null ||
                    txtFrom.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter From", "ERROR",
                        JOptionPane.DEFAULT_OPTION);
                txtFrom.requestFocus();
                return;
            }
            if (txtTo.getText() == null ||
                    txtTo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter To", "ERROR",
                        JOptionPane.DEFAULT_OPTION);
                txtTo.requestFocus();
                return;
            }
            if (txtDistance.getText() == null ||
                    txtDistance.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter Distance", "ERROR",
                        JOptionPane.DEFAULT_OPTION);
                txtDistance.requestFocus();
                return;
            }
            
            try {
                Statement statement = DBConnect.getDBConnection().createStatement();
                {
                    String temp = "INSERT INTO Route VALUES ('" +
                            txtRouteNo.getText() + "', '" +
                            txtFrom.getText() + "', '" +
                            txtTo.getText() + "', '" +
                            txtDistance.getText() + "')";
                    
                    int result = statement.executeUpdate(temp);
                    String ObjButtons[] = {"Yes", "No"};
                    int PromptResult = JOptionPane.showOptionDialog(null, "Record succesfully added.Do you want to add another?",
                            "tobiluoch", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                    if (PromptResult == 0) {
                        //txtRouteNo.setText("");
                        generator();
                        
                        txtFrom.setText("");
                        txtTo.setText("");
                        txtDistance.setText("");
                        
                        
                    } else {
                        setVisible(false);
                    }
                }
                
            } catch (SQLException sqlex) {
            }
        });

        Cancel.addActionListener((java.awt.event.ActionEvent e) -> {
            setVisible(true);
            dispose();
        });

        Clear.addActionListener((java.awt.event.ActionEvent e) -> {
            txtRouteNo.setText("");
            
            txtFrom.setText("");
            txtTo.setText("");
            txtDistance.setText("");
        });

        jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

        jPanel5.add(jPanel3, BorderLayout.CENTER);
        jPanel5.add(jPanel4, BorderLayout.SOUTH);

        getContentPane().add(jPanel5);

        pack();
        setVisible(true);
    }



    private void generator() {

        try {
            ResultSet rst = DBConnect.getDBConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT id_route FROM Route");
            while (rst.next()) {
                String s;
                int number = rst.getInt(1);
                number = number + 1;

                s = "" + number;
                txtRouteNo.setText(s);

            }
        } catch (SQLException n) {
        }


    }
}
