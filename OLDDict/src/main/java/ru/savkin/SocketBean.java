/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.savkin;

import java.net.Socket;

/**
 *
 * @author dm
 */
public class SocketBean {
 private static Socket sock;   

    /**
     * @return the sock
     */
    public static Socket getSock() {
        return sock;
    }

    /**
     * @param sock the sock to set
     */
    public static  void setSock(Socket sock) {
        SocketBean.sock = sock;
    }
}
