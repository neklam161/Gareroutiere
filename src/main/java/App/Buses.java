package App;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.AbstractTableModel;

public class Buses extends JPanel {

    private static JTable tblBusList;
    private JScrollPane jsp;
    private JButton btnAddNew,  btnRefresh,  btnClose,  btnUpdate;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private Statement stmt;
    private static int selectedRow;
    /*JFrame JFParentFrame;
    private static int rowCnt = 0;    
    private static JTextArea txtInfo = new JTextArea(15, 40);
    private Connection dbconn;
    private static String info;*/

    public Buses() {
        setSize(1000, 400);
        setLayout(new BorderLayout());

        tblBusList = new JTable(new AbstractTable());
        for (int i = 0; i < 4; i++) {
            tblBusList.getColumnModel().getColumn(i);
//            if (i == 4) {
//                sdf.format(i);
//            }//if btnClosed
        }//for btnClosed
        jsp = new JScrollPane(tblBusList);
        tablePanel = new JPanel(new GridLayout());
        tablePanel.add(jsp);

        btnAddNew = new JButton("Add New");
        btnUpdate = new JButton("Update");
        btnRefresh = new JButton("Refresh");
        btnClose = new JButton("Close");
        
        buttonPanel = new javax.swing.JPanel(new java.awt.FlowLayout());
        buttonPanel.add(btnAddNew);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnRefresh);
        
        buttonPanel.add(btnClose);

        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);

        try {
            stmt = DBConnect.getDBConnection().createStatement();
        } catch (SQLException excp) {
            JOptionPane.showMessageDialog(null, "Error on database connection", "Statement error", JOptionPane.ERROR_MESSAGE);
        }

        reloaded();
        btnAddNew.addActionListener((java.awt.event.ActionEvent e) -> {
            AddEntry frm = new AddEntry();
            frm.setLocation(300, 0);
            Window.desktop.add(frm);
            frm.setVisible(true);
            try {
                frm.setSelected(true);
            } catch (Exception ex) {
            }
        });
        btnClose.addActionListener((java.awt.event.ActionEvent e) -> {
            setVisible(false);
        });
        btnRefresh.addActionListener((ActionEvent e) -> {
            setVisible(false);
            reloaded();
            setVisible(true);
        });
       
        btnUpdate.addActionListener((java.awt.event.ActionEvent e) -> {
            String Busid,Model,Matricule ,capacity,nclass;
            Busid = tblBusList.getValueAt(getSelectedRow(), 0).toString();
            Model = tblBusList.getValueAt(getSelectedRow(), 1).toString();
            Matricule = tblBusList.getValueAt(getSelectedRow(), 2).toString();
            capacity = tblBusList.getValueAt(getSelectedRow(), 3).toString();
            nclass=tblBusList.getValueAt(getSelectedRow(), 4).toString();
            UpdateEntry frm = new UpdateEntry(Busid, Model, Matricule, capacity,nclass);
            Window.desktop.add(frm);
            frm.setLocation(300, 0);
            frm.setVisible(true);
        });
    }//constructor closed
    public static int getSelectedRow() {
        tblBusList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.ListSelectionModel rowSel = tblBusList.getSelectionModel();
        rowSel.addListSelectionListener((javax.swing.event.ListSelectionEvent e) -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            
            javax.swing.ListSelectionModel sel = (ListSelectionModel) e.getSource();
            if (!sel.isSelectionEmpty()) {
                selectedRow = sel.getMinSelectionIndex();
            }
        });

        return selectedRow;
    }

    class AbstractTable extends AbstractTableModel {

        private String[] columnNames = {"Busid","Model","Matricule" ,"Capacity","class"
        };
        private Object[][] data = new Object[50][50];

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
    }

    public void reloaded() {
        try {
            String sql = ("SELECT * FROM Buses ORDER BY Bus_id");
            int Numrow = 0;
            ResultSet result = stmt.executeQuery(sql);
            
            while (result.next()) {
                tblBusList.setValueAt(result.getString(1).trim(), Numrow, 0);
                tblBusList.setValueAt(result.getString(2).trim(), Numrow, 1);
                tblBusList.setValueAt(result.getString(3).trim(), Numrow, 2);
                tblBusList.setValueAt(result.getString(4).trim(), Numrow, 3);
                tblBusList.setValueAt(result.getString(5).trim(), Numrow, 4);
                Numrow++;
            }//while closed
            
        } catch (SQLException sqlex) {
            JOptionPane.showMessageDialog(null, "Error on retrieving values", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

