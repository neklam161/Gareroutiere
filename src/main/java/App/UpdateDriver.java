package App;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;

public class UpdateDriver extends JInternalFrame {

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private JLabel Driverno,Fname,  Lname, address , telephone;
    private JTextField txtdriverno,    txtFname,  txtLname,    txttelephone,   txtaddress;
    private JButton Update,  Search,  Clear,  Delete,  AddPic;
    String fname, lname, tele,adres;
    int number;
    private JButton Cancel;
    private JPanel jPanel1,  pics;
    private JPanel pic;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JComboBox cbogender;
    //private DateButton dobs;
    private static JTextArea txtInfo = new JTextArea(15, 40);
    private Connection dbconn;
    private static String info;
    final JFileChooser fc = new JFileChooser();
    String getPicture;

    public UpdateDriver(String driverno,  String fname, String lname,String adres,String tele) {
        super("Update Drivers Details.",false,true,false,true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        Driverno = new JLabel("Driver Number ");  
        Fname = new JLabel("First Name ");
        Lname = new JLabel("Last Name ");
        address = new JLabel("Address");
        telephone = new JLabel("Telephone Number");
        
        
        

        
        txtdriverno = new JTextField(10);    
        txtFname = new JTextField(10);
        txtLname = new JTextField(10);
         txtaddress = new JTextField(10);
        txttelephone = new JTextField(10);
        
       

        
        txtdriverno.setText(driverno);
        txtFname.setText(fname);
        txtLname.setText(lname);
        txttelephone.setText(tele);     
        txtaddress.setText(adres);      
     

        Update = new JButton("Update");
        Search = new JButton("Search");
        Clear = new JButton("Clear");
        Delete = new JButton("Delete");
        Cancel = new JButton("Cancel");
        AddPic = new JButton("Select pic");

        pics = new JPanel();
        pics.setPreferredSize(new Dimension(150, 250));
     
        jPanel1 = new javax.swing.JPanel(new java.awt.GridLayout(6, 2));
        jPanel1.setPreferredSize(new Dimension(350, 250));
      
        jPanel1.add(Driverno);
        jPanel1.add(txtdriverno);
        jPanel1.add(Fname);
        jPanel1.add(txtFname);
        jPanel1.add(Lname);
        jPanel1.add(txtLname);
        jPanel1.add(address);
        jPanel1.add(txtaddress);
        jPanel1.add(telephone);
        jPanel1.add(txttelephone);
        

        jPanel4 = new JPanel();
        jPanel4.add(Update);
        jPanel4.add(Cancel);
        jPanel4.add(Search);
        jPanel4.add(Delete);
        jPanel4.add(Clear);

        jPanel3 = new JPanel();
        jPanel3.add(jPanel1);
        jPanel3.add(pics);
        jPanel3.add(jPanel4);
        add(jPanel3);
        setSize(400, 250);
        setLocation(300,0);
        setResizable(false);
        
        
        txtFname.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLetter(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {

                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null, "This Field Only acept text", "Error",
                            JOptionPane.DEFAULT_OPTION);
                    e.consume();
                }
            }
        });
        txtLname.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLetter(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null, "This Field Only acept text", "Error",
                            JOptionPane.DEFAULT_OPTION);
                    e.consume();
                }
            }
        });
        txttelephone.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent e) {
                JTextField textField =
                        (JTextField) e.getSource();
                String content = textField.getText();
                if (content.length() != 0) {
                    try {
                        Integer.parseInt(content);
                    } catch (NumberFormatException nfe) {
                        getToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Invalid data entry", "Error",
                                JOptionPane.DEFAULT_OPTION);
                        textField.requestFocus();
                        txttelephone.setText("");
                    }
                }
            }
        });

        try {

            Statement s = DBConnect.getDBConnection().createStatement();
        } catch (Exception excp) {
            excp.printStackTrace();
        }
        Update.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtdriverno.getText() == null ||txtdriverno.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Employee Number", "Error", JOptionPane.DEFAULT_OPTION);
                    txtdriverno.requestFocus();
                    return;
                }
                
                if (txtFname.getText() == null ||txtFname.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Employee First Name", "Error",JOptionPane.DEFAULT_OPTION);
                    txtFname.requestFocus();
                    return;
                }
                if (txtLname.getText() == null ||txtLname.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Employee Last Name", "Error", JOptionPane.DEFAULT_OPTION);
                    txtLname.requestFocus();
                    return;
                }
                if (txttelephone.getText() == null ||txttelephone.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter telphone number","Error", JOptionPane.DEFAULT_OPTION);
                    txttelephone.requestFocus();
                    return;
                }
                
                if (txtaddress.getText() == null || txtaddress.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Address","Error", JOptionPane.DEFAULT_OPTION);
                    txtaddress.requestFocus();
                    return;
                }
                

                try {
                    Statement statement = DBConnect.getDBConnection().createStatement();
                    {

                        String temp = "UPDATE driver SET " +
                                "fname      ='" + txtFname.getText() +
                                "',lname      ='" + txtLname.getText() +
                                "',address    ='" + txtaddress.getText() +
                                "',telephone  ='" + txttelephone.getText() +
                                "' WHERE driver_id LIKE'" + txtdriverno.getText() + "'";

                        int result = statement.executeUpdate(temp);

                        setVisible(false);
                        dispose();
                    }

                } catch (SQLException sqlex) {
                    JOptionPane.showMessageDialog(null,"Error on database operation"+sqlex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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

                txtdriverno.setText("");
               
                txtFname.setText("");
                txtLname.setText("");
                txtaddress.setText("");
                txttelephone.setText("");
              

            }
        });

        Delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent r) {

                int DResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete Record?");
                if (DResult == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Deletion Cancel","DELETION", JOptionPane.DEFAULT_OPTION);
                }

                if (DResult == JOptionPane.YES_OPTION) {
                    try {
                        Statement statement = DBConnect.getDBConnection().createStatement();
                        if (!txtdriverno.equals("")) {

                            String query = ("DELETE  FROM driver where driver_id='" + txtdriverno.getText() + "'");
                            int result = statement.executeUpdate(query);
                            if (result == 0) {
                                JOptionPane.showMessageDialog(null, "Record Deleted","DELETION", JOptionPane.DEFAULT_OPTION);
                            } else {
                                //txtInfo.append( "\nDeletion failed\n" );
                                txtdriverno.setText("");
                                txtFname.setText("");
                                txtLname.setText("");
                             txtaddress.setText("");
                                txttelephone.setText("");
                            }
                            statement.close();
                        }
                    } catch (SQLException sqlex) {
                        sqlex.printStackTrace();
                    }
                }
            }
        });
        Search.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtdriverno.equals("")) {

                        Statement statement = DBConnect.getDBConnection().createStatement();
                        String query = ("SELECT * FROM driver where driver_id ='" + txtdriverno.getText() + "'");
                        ResultSet rs = statement.executeQuery(query);
                        display(rs);
                        
                        statement.close();
                    }
                } catch (SQLException sqlex) {
                    txtInfo.append(sqlex.toString() + sqlex.getMessage());
                }
                setVisible(true);
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

            boolean recordNumber = rs.next();
            if (recordNumber) {
                number = rs.getInt(1);
                fname = rs.getString(2);
                lname = rs.getString(3); 
                tele = rs.getString(4);
              
                adres = rs.getString(5);
               
                txtFname.setText(fname);
                txtLname.setText(lname);
                txtaddress.setText(adres);
                txttelephone.setText(tele);
            } else {
                JOptionPane.showMessageDialog(null, "Record Not found", "ERROR",JOptionPane.DEFAULT_OPTION);
            }
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }

    }



}
