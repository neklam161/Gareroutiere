package App;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Window extends JFrame implements WindowListener {

    private JMenu mnuOperations,  mnuFiles,  mnuProcesses,  mnuTools;
    private JMenuItem mnuExit;
    private JMenuItem mnuBuses,  mnuEmps,  mnuRoutes;
    private JMenuItem mnuScheduling;
    private JMenuItem mnuCalculator,  mnuNotepad;
    private JLabel welcome;
    public static JDesktopPane desktop;

    
    public Window() {
        super("Gestion de la Gare Routiere");
        this.setJMenuBar(CreateJMenuBar());
        //this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //this.setIconImage(new ImageIcon(ClassLoader.getSystemResource("images/appicon.png")).getImage());
        this.setLocation(0, 0);
        this.setSize(1020,500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addWindowListener(this);

     
        welcome = new JLabel("Welcome:  Today is " + new java.util.Date() + " ", JLabel.CENTER);
        welcome.setFont(new Font("Arial", Font.PLAIN, 20));
        welcome.setForeground(Color.DARK_GRAY);
        desktop = new JDesktopPane() {
            ImageIcon backgroundImage = new ImageIcon("D:/5282.jpg");
            Image image = backgroundImage.getImage();

            Image newimage = image.getScaledInstance(1000, 500, Image.SCALE_SMOOTH);

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(newimage, 0, 0, this);
            }
        };
        
                desktop.setBorder(BorderFactory.createEmptyBorder());
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

     
        getContentPane().add(welcome, BorderLayout.PAGE_END, JLabel.CENTER);
        
        getContentPane().add(desktop, BorderLayout.CENTER);
      
        setVisible(true);
    }//Constructor closed
    protected JMenuBar CreateJMenuBar() {
        JMenuBar menubar = new JMenuBar();
       
        mnuOperations = new JMenu("Operations");
        
        mnuOperations.setFont(new Font("Arial", Font.PLAIN, 18));
   
        mnuOperations.setEnabled(false);

       

        mnuExit = new JMenuItem("Exit");
        mnuExit.setFont(new Font("Arial", Font.PLAIN, 18));
 
        
    
        mnuExit.setActionCommand("exit");
        mnuExit.addActionListener(menulistener);

        
        mnuOperations.addSeparator();
        mnuOperations.add(mnuExit);
        menubar.add(mnuOperations);

       
        mnuFiles = new JMenu("Files");
        mnuFiles.setFont(new Font("Arial", Font.PLAIN, 18));
        

        mnuFiles.setEnabled(false);

        mnuBuses = new JMenuItem("Buses");
    
        mnuBuses.setEnabled(true);
        mnuBuses.setFont(new Font("Arial", Font.PLAIN, 18));
 
        
       
        mnuBuses.setActionCommand("buses");
        mnuBuses.addActionListener(menulistener);

        mnuEmps = new JMenuItem("Driver");
      
        mnuEmps.setEnabled(true);
        mnuEmps.setFont(new Font("Arial", Font.PLAIN, 18));
        mnuEmps.setMnemonic('E');
        
       
        mnuEmps.setActionCommand("employees");
        mnuEmps.addActionListener(menulistener);

        mnuRoutes = new JMenuItem("Routes");
        mnuRoutes.setEnabled(true);
        
        mnuRoutes.setFont(new Font("Arial", Font.PLAIN, 18));
        mnuRoutes.setMnemonic('R');
        
        mnuRoutes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        mnuRoutes.setActionCommand("routes");
        mnuRoutes.addActionListener(menulistener);

       

        mnuFiles.add(mnuBuses);
        mnuFiles.add(mnuEmps);
        mnuFiles.add(mnuRoutes);
       
        menubar.add(mnuFiles);

       
        mnuProcesses = new JMenu("Processes ");
        mnuProcesses.setFont(new Font("Arial", Font.PLAIN, 18));
      
        mnuProcesses.setMnemonic('P');

        mnuScheduling = new JMenuItem("Scheduling");
        mnuScheduling.setEnabled(false);
       
        mnuScheduling.setFont(new Font("Arial", Font.PLAIN, 18));
        mnuScheduling.setMnemonic('S');
        
       
        mnuScheduling.setActionCommand("scheduling");
        mnuScheduling.addActionListener(menulistener);

       

        

        mnuProcesses.add(mnuScheduling);
        menubar.add(mnuProcesses);


        mnuTools = new JMenu("Tools ");
        mnuTools.setFont(new Font("Arial", Font.PLAIN, 18));
       
        mnuTools.setMnemonic('T');

        mnuCalculator = new JMenuItem("Calculator");
        
        mnuCalculator.setFont(new Font("Arial", Font.PLAIN, 18));
        mnuCalculator.setMnemonic('C');
        
        
        mnuCalculator.setActionCommand("calculator");
        mnuCalculator.addActionListener(menulistener);

        mnuNotepad = new JMenuItem("Notepad");
 
        mnuNotepad.setFont(new Font("Arial", Font.PLAIN, 18));
        mnuNotepad.setMnemonic('N');
      
        
        mnuNotepad.setActionCommand("notepad");
        mnuNotepad.addActionListener(menulistener);

        mnuTools.add(mnuCalculator);
        mnuTools.add(mnuNotepad);
        menubar.add(mnuTools);

        return menubar;
    }

   
    ActionListener menulistener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            String ActCmd = e.getActionCommand();
            if (ActCmd.equalsIgnoreCase("calculator")) {
                try {
                    Runtime.getRuntime().exec("calc.exe");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error,Cannot start calculator", "Applicaton Error", JOptionPane.ERROR_MESSAGE);
                }//try catch closed
            } else if (ActCmd.equalsIgnoreCase("notepad")) {
                try {
                    Runtime.getRuntime().exec("notepad.exe");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error,Cannot start notepad", "Applicaton Error", JOptionPane.ERROR_MESSAGE);
                }//try catch closed
            } else if (ActCmd.equalsIgnoreCase("exit")) {
                ConfirmExit();
            } else if (ActCmd.equalsIgnoreCase("buses")) {
                Buses frm = new Buses();
                desktop.add(frm);
                frm.setVisible(true);
            } else if (ActCmd.equalsIgnoreCase("employees")) {
                Driver frm = new Driver();
                desktop.add(frm);
                frm.setVisible(true);
            } else if (ActCmd.equalsIgnoreCase("routes")) {
                Route frm = new Route();
                desktop.add(frm);
                frm.setVisible(true);
            }  else if (ActCmd.equalsIgnoreCase("scheduling")) {
                Schedule frm = new Schedule();
                desktop.add(frm);
                frm.setVisible(true);
            }  
        }
    };

    public void windowOpened(WindowEvent e) {

    }

    public void windowClosing(WindowEvent e) {


        ConfirmExit();
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(
            WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    private void ConfirmExit() {
        String ObjButtons[] = {"Yes", "No"};
        int PromptResult = JOptionPane.showOptionDialog(null, "Are you sure to exit?",
                "Confirm exit", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
        if (PromptResult == 0) {
            System.exit(0);
        }//if closed
    }//ConfirmExit() closed
    protected boolean isLoaded(String FormTitle) {
        JInternalFrame Form[] = desktop.getAllFrames();
        for (int i = 0; i < Form.length; i++) {
            if (Form[i].getTitle().equalsIgnoreCase(FormTitle)) {
                Form[i].show();
                try {
                    Form[i].setIcon(true);
                    Form[i].setSelected(true);
                } catch (Exception e) {
                }
                return true;
            }
        }
        return false;
    }

    public void LoginAdmin() {
        mnuOperations.setEnabled(true);
        mnuFiles.setEnabled(true);      
        mnuScheduling.setEnabled(true);
        mnuRoutes.setEnabled(true);
        mnuEmps.setEnabled(true);
        mnuBuses.setEnabled(true);
        
    }
    }

