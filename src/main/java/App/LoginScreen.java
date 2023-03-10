package App;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

        

public class LoginScreen extends JFrame {

    private JLabel lblUsername,  lblPasswd,  lblCat;
    public JTextField txtUser;
    private JPasswordField txtPasswd;
    private JButton btnLogin,  btnCancel;
    private JComboBox cmbCat;
    private Connection con;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    public LoginScreen() {
        super("System Login");
        this.getContentPane().setLayout(null);
        this.setSize(370, 250);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        lblUsername = new JLabel("Username");
        lblPasswd = new JLabel("Password");
        txtUser = new JTextField();
        txtPasswd = new JPasswordField();
        lblCat = new JLabel("Login As");
        cmbCat = new JComboBox();
        cmbCat.setModel(new DefaultComboBoxModel(new String[] {"Admin"}));
        btnLogin = new JButton("Login");
        btnCancel = new JButton("Cancel");

        lblUsername.setBounds(40, 30, 100, 25);
        lblPasswd.setBounds(40, 65, 100, 25);
        lblCat.setBounds(40, 100, 100, 25);
        txtUser.setBounds(150, 30, 160, 25);
        txtPasswd.setBounds(150, 65, 160, 25);
        cmbCat.setBounds(150, 100, 160, 25);
        btnLogin.setBounds(70, 150, 100, 25);
        btnCancel.setBounds(190, 150, 100, 25);

        lblUsername.setFont(new Font("monospaced", Font.BOLD, 16));
        lblPasswd.setFont(new Font("monospaced", Font.BOLD, 16));
        lblCat.setFont(new Font("monospaced", Font.BOLD, 16));
        cmbCat.setFont(new Font("monospaced", Font.BOLD, 16));
        txtUser.setFont(new Font("monospaced", Font.CENTER_BASELINE, 16));
        txtPasswd.setFont(new Font("monospaced", Font.CENTER_BASELINE, 16));
        getContentPane().add(lblUsername);
        getContentPane().add(txtUser);
        getContentPane().add(lblPasswd);
        getContentPane().add(txtPasswd);
        getContentPane().add(btnLogin);
        getContentPane().add(btnCancel);
        getContentPane().add(lblCat);
        getContentPane().add(cmbCat);
        getContentPane().add(btnLogin);
        getContentPane().add(btnCancel);

        ButtonListener listener = new ButtonListener();
        btnLogin.addActionListener(listener);
        btnCancel.addActionListener(listener);
        con = DBConnect.getDBConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(null, "Error on establishing database connection", "Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }//constructor closed

    public void login() {
        String username = txtUser.getText();
        String password = txtPasswd.getText();
        String SQL;
        String category = cmbCat.getSelectedItem().toString();
        SQL = "SELECT * FROM users WHERE username='" + username + "'  AND password='" +
                password + "'AND Category='" + category + "'";
        try {
            Statement stmt = con.createStatement();
            stmt.execute(SQL);
            ResultSet rs = stmt.getResultSet();
            boolean recordfound = rs.next();
            if (recordfound) {
                LoadMDIWindow();                
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, """
                                                    The system could not log you in.
                                                     Please make sure your username and password are correct""", "Login Failure", JOptionPane.INFORMATION_MESSAGE);
                txtUser.setText("");
                txtPasswd.setText("");
                txtUser.requestFocus();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error on login operation", "Login Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        }
    }

    public void LoadMDIWindow() {
        if (cmbCat.getSelectedItem().equals("Admin")) {
            new Window().LoginAdmin();            
        } 
    }
        
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnLogin) {
                if (txtUser.getText() == null || txtUser.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter username", "Missing field", JOptionPane.DEFAULT_OPTION);
                    txtUser.requestFocus();
                    return;
                }
                if (txtPasswd.getText() == null || txtPasswd.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter password", "Missing field", JOptionPane.DEFAULT_OPTION);
                    txtPasswd.requestFocus();
                    return;
                }
                login();
            } else if (e.getSource() == btnCancel) {
               System.exit(0);
    
     
            }
        }
    }

}

