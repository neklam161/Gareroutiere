package App;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class Driver extends JPanel {

    private JScrollPane jsp;
    private static JTable tabledriver;
    private JButton btnAddEntry,  btnRefresh,  btnUpdate,  btnClose,  btnPrint;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    //private JButton searchButton;
    private static int selectedRow;
    private static JTextArea txtInfo = new JTextArea(15, 40);

    public Driver() {
        setSize(1000, 400);
        setLayout(new BorderLayout());
        tabledriver = new JTable(new AbstractTable());
        for (int i = 0; i < 5; i++) {
            tabledriver.getColumnModel().getColumn(i);
        }
        jsp = new JScrollPane(tabledriver);
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(jsp, BorderLayout.CENTER);

        btnAddEntry = new JButton("Add Entry");
        btnRefresh = new JButton("Refresh");
        btnUpdate = new JButton("Update");
       
        btnClose = new JButton("Close");

        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnAddEntry);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnRefresh);
        
        buttonPanel.add(btnClose);
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);
        try {
            DBConnect.getDBConnection().createStatement();
        } catch (SQLException excp) {
            JOptionPane.showMessageDialog(null, "Error on database connection", "Statement error", JOptionPane.ERROR_MESSAGE);
        }//try catch closed
        load_taleEmpList();

        btnAddEntry.addActionListener((java.awt.event.ActionEvent e) -> {
            AddNewEntry frm=new AddNewEntry();
            Window.desktop.add(frm);
            frm.setLocation(300, 0);
            frm.setVisible(true);
            try{
                frm.setSelected(true);
            }catch(Exception ex){}
        });

        btnUpdate.addActionListener((java.awt.event.ActionEvent e) -> {
            UpdateDriver frm = new UpdateDriver(tabledriver.getValueAt(getSelectedRow(), 0).toString(),
                    tabledriver.getValueAt(getSelectedRow(), 1).toString(),
                    tabledriver.getValueAt(getSelectedRow(), 2).toString(),
                    tabledriver.getValueAt(getSelectedRow(), 3).toString(),
                    tabledriver.getValueAt(getSelectedRow(), 4).toString());
            Window.desktop.add(frm);
            frm.setVisible(true);
            try{
                frm.setSelected(true);
            }catch(Exception ex){}
        });
        btnClose.addActionListener((ActionEvent e) -> {
            setVisible(false);
        });
       
        btnRefresh.addActionListener((java.awt.event.ActionEvent e) -> {
            load_taleEmpList();
        });
        tablePanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);
        tablePanel.setPreferredSize(new java.awt.Dimension(750, 450));
        tablePanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);
        tablePanel.setPreferredSize(new java.awt.Dimension(1000, 300));
        tablePanel.setBackground(new java.awt.Color(200, 200, 200));
        tablePanel.setBounds(2, 200, 770, 2);
        add(tablePanel);
    }

    public static int getSelectedRow() {
        tabledriver.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        javax.swing.ListSelectionModel rowSel = tabledriver.getSelectionModel();
        rowSel.addListSelectionListener((ListSelectionEvent e) -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            javax.swing.ListSelectionModel sel = (javax.swing.ListSelectionModel) e.getSource();
            if (!sel.isSelectionEmpty()) {
                selectedRow = sel.getMinSelectionIndex();
            }
        });
        return selectedRow;
    }

    class AbstractTable extends AbstractTableModel {

        private String[] columnNames = {"Driver Number", "FirstName", "Last Name",  "Address","Telephone" };
        private Object[][] data = new Object[100][100];

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }//AbstrctTable Class closed
    public void load_taleEmpList() {
        try {
            Statement statement = DBConnect.getDBConnection().createStatement();
            String temp = ("SELECT * FROM driver ORDER BY driver_id");
            int Numrow = 0;
            ResultSet result = statement.executeQuery(temp);
            while (result.next()) {
                tabledriver.setValueAt(result.getString(1), Numrow, 0);
                tabledriver.setValueAt(result.getString(2), Numrow, 1);
                tabledriver.setValueAt(result.getString(3), Numrow, 2);
                tabledriver.setValueAt(result.getString(4), Numrow, 3);
                tabledriver.setValueAt(result.getString(5), Numrow, 4);
                Numrow++;
            }
        } catch (SQLException sqlex) {
            txtInfo.append(sqlex.toString());
        }
    }
}


