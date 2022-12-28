package App;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateEntry extends JInternalFrame {

    private JLabel Busid, Model, Matricule ,Capacity,  Nclass;
    private JTextField txtBusid,  txtModel,  txtMatricule,  txtCapacity,  txtclass;
    private JButton btnUpdate,  btnSearch,  btnClear,  btnDelete;
    String busid, model, matricule ,capacity,  nclass;
    private JButton btnCancel;
    private JPanel fieldsPanel;
    private JPanel buttonPanel;
//    private DateButton date_bought;
//    private DateButton date_ins;
//    private DateButton date_expiry;
    private static JTextArea txtInfo = new JTextArea(15, 40);
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    public UpdateEntry(String busid, String model, String matricule, String capacity,
            String nclass) {
        super("Update Bus Details",false,true,false,true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        //setLocationRelativeTo(null);        
        Busid = new JLabel(" Bus Number ");
        Matricule = new JLabel(" Matricule ");
        Model = new JLabel(" Model ");
        Capacity = new JLabel(" Capacity ");
        Nclass = new JLabel(" Class ");
        

        txtBusid = new JTextField(10);
        txtModel = new JTextField(10);
        txtMatricule = new JTextField(10);
        txtCapacity = new JTextField(10);
        txtclass = new JTextField(10);

//        date_bought = new DateButton();
//        date_ins = new DateButton();
//        date_expiry = new DateButton();

        txtBusid.setText(busid);
        txtModel.setText(model);
        txtMatricule.setText(matricule);
        txtCapacity.setText(capacity);
        txtclass.setText(nclass);
        //date_bought.setText(db);
        //txtIns.setText(is);
        //date_ins.setText(ie);
        //date_expiry.setText(id);

//        date_ins.setForeground(Color.red);
//        date_bought.setForeground(Color.red);
//        date_expiry.setForeground(Color.red);

        btnUpdate = new JButton("Update");
        btnSearch = new JButton("Search");
        btnClear = new JButton("Clear");
        btnDelete = new JButton("Delete");
        btnCancel = new JButton("Cancel");

        fieldsPanel = new JPanel(new GridLayout(5, 2));
        fieldsPanel.setPreferredSize(new Dimension(400, 250));
        fieldsPanel.add(Busid);
        fieldsPanel.add(txtBusid);
        fieldsPanel.add(Model);
        fieldsPanel.add(txtModel);
        fieldsPanel.add(Matricule);
        fieldsPanel.add(txtMatricule);
        fieldsPanel.add(Capacity);
        fieldsPanel.add(txtCapacity);
        fieldsPanel.add(Nclass);
        fieldsPanel.add(txtclass);
       
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        
        getContentPane().add(fieldsPanel,BorderLayout.CENTER);
        getContentPane().add(buttonPanel,BorderLayout.PAGE_END);
        pack();
        
        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                if (txtBusNo.getText() == null ||txtBusNo.getText().equals("")) {
//                    JOptionPane.showMessageDialog(null, "Error?? BusNo can't be null", "Error", JOptionPane.DEFAULT_OPTION);
//                    txtBusNo.requestFocus();
//                    return;
//                }
//                if (txtRegNo.getText() == null || txtRegNo.getText().equals("")) {
//                    JOptionPane.showMessageDialog(null, "Error?? RegNo can't be null", "Error",JOptionPane.DEFAULT_OPTION);
//                    txtRegNo.requestFocus();
//                    return;
//                }
//                if (txtModel.getText() == null ||txtModel.getText().equals("")) {
//                    JOptionPane.showMessageDialog(null, "Error?? Model Field is required", "Error",JOptionPane.DEFAULT_OPTION);
//                    txtModel.requestFocus();
//                    return;
//                }
//                if (txtCapacity.getText() == null || txtCapacity.getText().equals("")) {
//                    JOptionPane.showMessageDialog(null, "Error?? Enter bus capacity", "Error",JOptionPane.DEFAULT_OPTION);
//                    txtCapacity.requestFocus();
//                    return;
//                }
//                if (txtIns.getText() == null || txtIns.getText().equals("")) {
//                    JOptionPane.showMessageDialog(null, "Error?? Insurance status entry is required","Error", JOptionPane.DEFAULT_OPTION);
//                    txtIns.requestFocus();
//                    return;
//                }
                try {
                    Statement statement = DBConnect.getDBConnection().createStatement();
                    {
                        String sql = "UPDATE buses SET " +
                                "  Model    ='" + txtModel.getText() +
                                "',Matricule='" + txtMatricule.getText() +
                                "',Capacity ='" + txtCapacity.getText() +
                                "',Class    ='" + txtclass.getText() +
                                "' WHERE bus_id LIKE'" + txtBusid.getText() + "'";
                        statement.executeUpdate(sql);
                        dispose();
                    }
                } catch (SQLException sqlex) {
                    JOptionPane.showMessageDialog(null, "Error on updation"+sqlex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });


        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        Statement stmt = DBConnect.getDBConnection().createStatement();
                        if (txtBusid.equals("")) {}
                        else{
                            String query = "DELETE  FROM Buses where Bus_id ='" + txtBusid.getText() + "'";
                            int result = stmt.executeUpdate(query);
                            if (result == 0) {
                                JOptionPane.showMessageDialog(null, "Record Deleted","DELETION", JOptionPane.DEFAULT_OPTION);
                            } else {
                                txtInfo.append("\nDeletion failed\n");
                                txtBusid.setText("");
                                txtMatricule.setText("");
                                txtModel.setText("");
                                txtCapacity.setText("");
                                txtclass.setText("");
                            }
                            stmt.close();
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Cannot delete record now, May be related with other tables"  ,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                
            }
        });
        btnClear.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtBusid.setText("");
                txtModel.setText("");
                txtMatricule.setText("");
                txtCapacity.setText("");
                txtclass.setText("");
             


            }
        });
        btnSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtBusid.equals("")) {

                        Statement statement = DBConnect.getDBConnection().createStatement();
                        String query = ("SELECT * FROM Buses where Bus_id ='" + txtBusid.getText() + "'");

                        ResultSet rs = statement.executeQuery(query);
                        display(rs);
                        statement.close();
                    }
                } catch (SQLException sqlex) {
                }



                setVisible(true);

            }
        });

        
    }//constructor closed

    public void display(ResultSet rs) {
        try {
            //rs.next();

            boolean recordNumber = rs.next();
            if (recordNumber) {
                busid = rs.getString(1);
                matricule = rs.getString(3);
                model = rs.getString(2);
                capacity = rs.getString(4);
                nclass=rs.getString(5);

          

                txtBusid.setText(busid);
                txtModel.setText(model);
                txtCapacity.setText(capacity);
                txtMatricule.setText(matricule);
                txtclass.setText(nclass);
//                date_bought.setText(db);
//                txtIns.setText(is);
//                date_ins.setText(ie);
//                date_expiry.setText(id);
            } else {
                JOptionPane.showMessageDialog(null, "Record Not found", "ERROR",
                        JOptionPane.DEFAULT_OPTION);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateEntry.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}//class closed
