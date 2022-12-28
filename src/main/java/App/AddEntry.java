package App;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AddEntry extends JInternalFrame {

    private JLabel lblBusid,  lblMatricule,  lblModel,  lblCapacity,  lblclass;
    private JTextField txtBusid,  txtMatricule,  txtModel,  txtCapacity,  txtclass;
    private JButton btnAddNew,  btnCancel,  btnClear;
    private JPanel fieldsPanel;
    private JPanel buttonPanel;

    public AddEntry() {
        super("New Bus Entry", false, true, false, true);
        setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        //setLocationRelativeTo(null);
//        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
//        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
//        frame.setLocation(x, y);

        lblBusid = new JLabel("Bus Number ");
        lblModel = new JLabel("Model ");
        lblMatricule = new JLabel("Matricule ");
        lblCapacity = new JLabel("Capacity ");
        lblclass = new JLabel("Class ");
        

        txtBusid = new JTextField(10);
        txtMatricule = new JTextField(10);
        txtModel = new JTextField(10);
        txtCapacity = new JTextField(10);
        txtclass=new JTextField(10);
        
        
        txtBusid.setForeground(Color.blue);
        btnAddNew = new JButton("Add Record");
        btnCancel = new JButton("Cancel");
        btnClear = new JButton("Clear");


        fieldsPanel = new JPanel(new GridLayout(5, 2));
        buttonPanel = new JPanel(new FlowLayout());

        fieldsPanel.setPreferredSize(new Dimension(400, 250));
        fieldsPanel.add(lblBusid);
        fieldsPanel.add(txtBusid);
        fieldsPanel.add(lblModel);
        fieldsPanel.add(txtModel);
        fieldsPanel.add(lblMatricule);
        fieldsPanel.add(txtMatricule);
        fieldsPanel.add(lblCapacity);
        fieldsPanel.add(txtCapacity);
        fieldsPanel.add(lblclass);
        fieldsPanel.add(txtclass);
        


        buttonPanel.add(btnAddNew);
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnClear);

        getContentPane().add(fieldsPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
        pack();
       

        btnAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

               
                try {
                    Statement stmt = DBConnect.getDBConnection().createStatement();

                    String sql = "INSERT INTO Buses VALUES ('" +
                            txtBusid.getText() + "', '" +
                            txtModel.getText() + "', '" +
                            txtMatricule.getText()+ "', '" +
                            txtCapacity.getText() + "', '" +
                            txtclass.getText() + "')";


                    stmt.executeUpdate(sql);
                    String ObjButtons[] = {"Yes", "No"};
                    JOptionPane.showOptionDialog(null, "Record succesfully added.Do you want to add another?",
                            "Success", JOptionPane.INFORMATION_MESSAGE, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                    
                } catch (SQLException sqlex) {
                    JOptionPane.showMessageDialog(null, "Error on database operation"+sqlex.getMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
                }//try catch closed
            }
        });

        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                txtBusid.setText("");
                
                txtModel.setText("");
                txtMatricule.setText("");
                txtCapacity.setText("");
                txtclass.setText("");
                
            }
        });
    }

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
       
    }


