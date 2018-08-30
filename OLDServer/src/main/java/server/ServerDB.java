/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author dm
 */
@Deprecated
class ServerDB {

    static String query;
    static ArrayList d = new ArrayList();

    public static void edit(String term, String definition) {

        String idResult;
        Connection c = ConnectionBean.getC();
        ResultSet rs = null;
        String sql = "SELECT id  FROM tlbDefinition WHERE definition='" + Server.definition + "'";
        Statement st = null;
        try {
            st = c.createStatement();
        } catch (SQLException ex) {
            System.err.println("Error 3: " + ex.getMessage());

        }
        if (st == null) {
            System.out.println("Statement getInforamtion = null");
        } else {
            try {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    idResult = rs.getString("id");
                    sql = "UPDATE tlbDefinition SET definition='" + definition + "'"
                            + "WHERE tlbDefinition.id=" + idResult;
                    st.executeUpdate(sql);
                }
            } catch (SQLException ex) {
            }
        }
    }

    /**
     *
     * @param url
     * @param user
     * @param password
     * @return
     */
    public static boolean getConnect(String url, String user, String password) {
        Connection con = null;
        if (url == null || url.isEmpty()) {
            return false;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("org.apache.derby.jdbc.ClientDriver40");

        } catch (Exception ex) {
            System.err.println("Error 1: " + ex.getMessage());
        }

        try {
            con = DriverManager.getConnection(url, user, password);

            ConnectionBean.setC(con);
            return true;
        } catch (SQLException ex) {

            return false;
        }
    }

    /**
     *
     * @param search
     * @return
     */
    public static String getInformation(String search) {
        d.clear();

        ResultSet rs = null;
        String definition = null;
        Connection c = ConnectionBean.getC();

        String sql = "SELECT term, definition FROM tlbDefinition, tlbTerms"
                + "                     WHERE tlbTerms.id=tlbDefinition.tlbTerms_id"
                + "                     AND tlbTerms.term= '" + search + "'";
        Statement st = null;
        try {
            st = c.createStatement();
        } catch (SQLException ex) {
      

        }
        if (st == null) {

        } else {

            try {

                rs = st.executeQuery(sql);
                while (rs.next()) {
                    definition = rs.getString("definition").toString();
                    d.add(definition);
                }
            } catch (SQLException ex) {
            }


        }
        c = null;
        if (d.size() != 0) {
            return d.get(0).toString();
        } else {
            return null;
        }

    }

    public static void delete(String term, String definition) {
        int i = 0;
        Connection c = ConnectionBean.getC();
        String r = getInformation(term);
        if (r == null) {
            return;
        }
        String sql = "DELETE FROM tlbDefinition  WHERE"
                + " tlbDefinition.definition='" + definition + "'";
        System.out.println(sql);
        Statement st = null;
        try {
            st = c.createStatement();
        } catch (SQLException ex) {
            System.err.println("Error 3: " + ex.getMessage());

        }
        if (st == null) {
        } else {
            try {
                st.executeUpdate(sql);

            } catch (SQLException ex) {
            }
            sql = "SELECT tlbDefinition.id FROM tlbDefinition WHERE tlbDefinition.tlbTerms_id"
                    + "=(SELECT id FROM tlbTerms)";
            ResultSet rs = null;
            try {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    i++;
                }
            } catch (SQLException ex) {
            }
            if (i == 0) {
                sql = "DELETE FROM tlbTerms WHERE tlbTerms.term='" + term + "'";
                try {

                    st.executeUpdate(sql);
                } catch (SQLException ex) {
                }
            }
        }
        d.remove(definition);
        c = null;
    }

    public static void addInformation(String term, String definition) {
        Connection c = ConnectionBean.getC();
        //При условии, что в таблице установлен auto_increment
        String r = getInformation(term);
        for (int i = 0; i < d.size(); i++) {
            if (d.get(i).equals(definition)) {

                return;
            }

        }
        Statement st = null;
        String id = null;
        String sql = null;
        try {
            st = c.createStatement();
        } catch (SQLException ex) {
        }

//Если такого термина еще нет
        if (r == null) {
            sql = "INSERT INTO tlbTerms VALUES(null,'" + term + "')";

            try {


                st.executeUpdate(sql);
            } catch (SQLException ex) {
                System.err.println("Error 3: " + ex.getMessage());

            }
        }

        try {

            sql = "SELECT id FROM tlbTerms WHERE term='" + term + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getString("id");
            }
            sql = "INSERT INTO tlbDefinition VALUES(null,'" + definition + "'," + id + ")";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
        }

        c = null;
    }

    public static String next() {
        if (d.size() == 0) {
            return "Not found";
        }
        Server.i++;

        if (Server.i == d.size()) {
            Server.i = 0;
        }
        return d.get(Server.i).toString();

    }

    public static String previous() {
        if (d.size() == 0) {
            return "Not found";
        }
        Server.i--;

        if (Server.i < 0) {
            Server.i = (d.size() - 1);
        }
        return d.get(Server.i).toString();
    }
}
