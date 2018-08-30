package ru.savkin;


import java.io.*;
import java.net.Socket;

/**
 * The one thead for each user can manipulate with user's query
 * @version  1.1.
 * @author  dmitry savkin.
 */
public class ClientHandler extends Thread {

    private final Socket client;


    public ClientHandler(Socket client) {
        this.client = client;
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {

                    DataInputStream ois = new DataInputStream(this.client.getInputStream());
                    DataOutputStream oos = new DataOutputStream(this.client.getOutputStream());
                    Definition def = API.getSearchResult(ois.readUTF());
                    oos.writeUTF(def.getDescription());
                    oos.flush();
                    ois.close();
                    oos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    /**
     * Gets socket
     * @return the current socket
     */
    public Socket getClient() {
        return client;
    }


}
