package ru.savkin.serverdata;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * The class is been developing.
 * The class provides the method to connect with server.
 * @author  dmitrysavkin
 */
public class ClientLoader {

    private static Socket socket;

    public static void init() {
        try {
            socket =  new Socket("localhost",8888);
            handle();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void handle() throws IOException {
        DataOutputStream dos =  new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream());

        dos.writeUTF("alphabet");
        System.out.println(ois.readUTF());
        ois.close();
        dos.flush();
    }


    public static void main(String[] args) {
        ClientLoader.init();
    }
}
