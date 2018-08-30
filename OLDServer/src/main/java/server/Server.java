/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Дима
 */
/**
 * Класс для графического интерфейса сервера JHelp Содержит метод main();
 *
 * @author dmitriy
 */
@Deprecated
public class Server extends JFrame {

    public static int i = 0;
    public static JButton on;
    public static JTextField portField;
    static JFrame frame;
    private JLabel label;
    public static JTextArea text;
    public static JTextField userField;
    public static JPasswordField passwordField;

    public static JTextField driverNameField;
    public static JTextField driverField;
    private JButton driverChoose;
    private final JLabel user;
    private final JLabel password;
    private final JButton connect;
    private JLabel url;
    public static JTextField urlField;
    public static String definition;
    /**
     *
     */
    Server() {
        setTitle("Server");
        setSize(555, 500);
        label = new JLabel("Port");
        portField = new JTextField(25);

        url = new JLabel("URL");
        urlField = new JTextField(25);

        user = new JLabel("User");
        userField = new JTextField(25);

        password = new JLabel("Password");
        passwordField = new JPasswordField(25);

       
     

        on = new JButton("Switch on");
        on.setActionCommand("on");

        on.addActionListener(new ServerListener());
        connect = new JButton("Connect");
        connect.setActionCommand("connect");
        connect.addActionListener(new ServerListener());
        text = new JTextArea(20, 20);
        driverChoose = new JButton("Choose");
        driverChoose.setActionCommand("c");
        driverChoose.addActionListener(new ServerListener());
        Container c = getContentPane();
        JPanel pan = new JPanel();

        pan.setLayout(new GridLayout(1, 3));
        pan.add(label);
        pan.add(portField);
        pan.add(on);
        JPanel pan2 = new JPanel();
        pan2.setLayout(new GridLayout(1, 3));
        pan2.add(url);
        pan2.add(urlField);
        pan2.add(new JLabel());
        JPanel pan3 = new JPanel();
        pan3.setLayout(new GridLayout(1, 3));
        pan3.add(user);
        pan3.add(userField);
        pan3.add(new JLabel());
        JPanel pan4 = new JPanel();
        pan4.setLayout(new GridLayout(1, 3));
        pan4.add(password);
        pan4.add(passwordField);
        pan4.add(new JLabel());

     
        Box b2 = Box.createVerticalBox();
        b2.add(pan);
        b2.add(Box.createVerticalStrut(15));
        b2.add(pan2);
        b2.add(Box.createVerticalStrut(5));
        b2.add(pan3);
        b2.add(Box.createVerticalStrut(5));
        b2.add(pan4);
        b2.add(Box.createVerticalStrut(10));
        b2.add(text);
        c.add(b2);
        frame=this;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Server sh = new Server();
        sh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sh.setVisible(true);

    }
}
