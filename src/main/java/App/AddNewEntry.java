package App;
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AddNewEntry extends JInternalFrame {

    private JLabel DriverNo,   Fname,  Lname,  telephone, address;
    private JTextField txtDriverno, txtFname,  txtLname,  txttelephone,  txtaddress;
    private JButton jButton1;
    private JButton jButton2;
    private JButton Clear;
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
   

    private Connection dbconn;
    private static String info;
    

    public AddNewEntry() {

        super("Add New Driver",false,true,false,true);
        setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);
        DriverNo = new JLabel("Driver Number ");
        Fname = new JLabel("First Name ");
        Lname = new JLabel("Last Name ");
        telephone = new JLabel("Telephone Number");
        address = new JLabel("Address");
        txtDriverno = new JTextField(10);
        txtFname = new JTextField(10);
        txtLname = new JTextField(10);
        txttelephone = new JTextField(10);
       
        txtaddress = new JTextField(10);

        jButton1 = new JButton("Add Record");
        jButton2 = new JButton("Cancel");
        Clear = new JButton("Clear");
       
        
        

        

        jPanel1 = new JPanel(new java.awt.GridLayout(10, 2));
        jPanel1.setPreferredSize(new Dimension(400, 250));
        jPanel1.add(DriverNo);
        jPanel1.add(txtDriverno);       
        jPanel1.add(Fname);
        jPanel1.add(txtFname);
        jPanel1.add(Lname);
        jPanel1.add(txtLname);
        jPanel1.add(address);
        jPanel1.add(txtaddress);
        jPanel1.add(telephone);
        jPanel1.add(txttelephone);
        

        jPanel4 = new JPanel();
        jPanel4.add(jButton1);
        jPanel4.add(jButton2);
        jPanel4.add(Clear);

        jPanel3 = new JPanel();
        jPanel3.add(jPanel1);
   
        jPanel3.add(jPanel4);
        add(jPanel3);
        setSize(400, 250);
        setResizable(false);
       
        setLocation(300,0);
        try {
            Statement s = DBConnect.getDBConnection().createStatement();
        } catch (Exception excp) {
            excp.printStackTrace();
        }
        //generator();
        
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
                        JOptionPane.showMessageDialog(null, "Invalid data entry", "Error",JOptionPane.DEFAULT_OPTION);
                        textField.requestFocus();
                        txttelephone.setText("");
                    }
                }
            }
        });
        jButton1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtDriverno.getText() == null ||
                        txtDriverno.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Employee Number",
                            "Error", JOptionPane.DEFAULT_OPTION);
                    txtDriverno.requestFocus();
                    return;
                }

                

                if (txtFname.getText() == null ||
                        txtFname.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enetr Employee First Name",
                            "Error", JOptionPane.DEFAULT_OPTION);
                    txtFname.requestFocus();
                    return;
                }

                if (txtLname.getText() == null ||
                        txtLname.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enetr Employee Last Name",
                            "Error", JOptionPane.DEFAULT_OPTION);
                    txtLname.requestFocus();
                    return;
                }
                if (txttelephone.getText() == null ||
                        txttelephone.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter telphone number",
                            "Error", JOptionPane.DEFAULT_OPTION);
                    txttelephone.requestFocus();
                    return;
                }
                
                if (txtaddress.getText() == null ||
                        txtaddress.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Address","Error", JOptionPane.DEFAULT_OPTION);
                    txtaddress.requestFocus();
                    return;
                }

                

                try {
                    Statement statement = DBConnect.getDBConnection().createStatement();
                    {
                        String temp = "INSERT INTO driver VALUES ('" +
                                txtDriverno.getText() + "', '" +
                                txtFname.getText() + "', '" +
                                txtLname.getText() + "', '" +
                                txtaddress.getText() + "', '" +
                                txttelephone.getText() + "')";
                        
                        int result = statement.executeUpdate(temp);
                        String ObjButtons[] = {"Yes", "No"};
                        int PromptResult = JOptionPane.showOptionDialog(null, "Record succesfully added.Do you want to add another?",
                                "tobiluoch", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                        if (PromptResult == 0) {
                            

                            
                            txtFname.setText("");
                            txtLname.setText("");

                           
                            txttelephone.setText("");
                          

                            txtaddress.setText("");

                        } else {
                            new Driver().setVisible(true);
                            setVisible(false);

                        }
                    }

                } catch (SQLException sqlex) {
                    sqlex.printStackTrace();


                }
            }
        });

        jButton2.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                setVisible(true);
                dispose();
            }
        });
        
        Clear.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {

                
                txtFname.setText("");
                txtLname.setText("");
                txttelephone.setText(""); 
                txtaddress.setText("");

            }
        });

        jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

        jPanel5.add(jPanel3, BorderLayout.CENTER);
        jPanel5.add(jPanel4, BorderLayout.SOUTH);
        getContentPane().add(jPanel5);
        pack();

    }



   
}
