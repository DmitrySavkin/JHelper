/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author dmitriy
 */
public class ServerListener implements ActionListener {

    public static String s = " ";
    ServerThread ser = null;
    ServerClient cs = null;


    public void actionPerformed(ActionEvent ae) {

        if (ae.getActionCommand().equals("on")) {
            String password = "";
            String port = Server.portField.getText();
            String url = Server.urlField.getText();
            String user = Server.userField.getText();
            char rhy[] = Server.passwordField.getPassword();


            if (port.equals("")) {
                s += Error.PORT_ERROR;
                return;
            }

            if (url.equals("")) {
                s += Error.URL_ERROR;
                return;
            }

            if (user.equals("")) {
                s += Error.USER_ERROR;
                return;
            }
            for (int i = 0; i < rhy.length; i++) {
                password = password + rhy[i];

            }
            if (password.equals("")) {
                s += Error.PASSWORD_ERROR;
                return;
            }
            try {

                Integer p = Integer.parseInt(port);
                cs = new ServerClient(p);

                boolean flag = ServerDB.getConnect(url, user, password);

                if (flag) {


                    Server.on.setText("Exit");
                    Server.on.setActionCommand("exit");
                    s += "Connect ";
                } else {
                    s += "Database not found";
                }
            } catch (NumberFormatException e) {
                s += "Port isn't correct \n";
            }

        }

        if (ae.getActionCommand().equals("exit")) {

            //Не знаю, как выключить все потоки с возможностью подключиться вновь
            System.exit(0);

        }

        Server.text.setText(s);
    }
}
