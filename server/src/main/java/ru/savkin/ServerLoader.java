package ru.savkin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public final class ServerLoader  {

    private  ServerSocket  serverSocket;

    public  void init() {
        try {
            serverSocket =  new ServerSocket(8888);
            handle();
            end();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private  void handle() throws IOException {
        while(true) {
            Socket socket = serverSocket.accept();
            new ClientHandler(socket);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    protected void end() throws IOException {

            serverSocket.close();

    }

    public static void main(String[] args) {
       new ServerLoader().init();
    }
}
