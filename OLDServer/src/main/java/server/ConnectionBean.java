/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;

/**
 *
 * @author dm
 */
public class ConnectionBean {
    private static  Connection c;

    /**
     * @return the c
     */
    public static  Connection getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public static void setC(Connection c) {
        ConnectionBean.c = c;
    }
}
