/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author dm
 */
@Deprecated
public class ServerClient implements Runnable {

    int p;
    public boolean listening = true;
    Thread t;

    ServerClient(int port) {
        this.p = port;
        t = new Thread(this);
        t.start();
    }

    public void createServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(p);
            System.out.println("SERVER");
            ServerListener.s += "Server is working. ";
        } catch (IOException e) {
        }
        while (listening) {

            try {
                new ServerThread(serverSocket.accept()).start();

            } catch (IOException ex) {
            }
        }

        try {
            serverSocket.close();
        } catch (IOException ex) {
        }

    }


    public void run() {
        createServer();
    }

 
    }

