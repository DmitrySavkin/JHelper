package ru.savkin;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {


    private ServerLoader serverLoader;

    public Window() {
        serverLoader = new ServerLoader();
        setTitle("Server");
        setSize(500,500);
        super.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
   }

   private void addElement(String title, GridBagConstraints c, int x, int y) {

   }

   private void addPassWord(String title, GridBagConstraints c) {
       JLabel password = new JLabel(title);
       JPasswordField passwordField = new JPasswordField(25);

   }


    public static void main(String[] args) {
        Window window = new Window();
    }
}
