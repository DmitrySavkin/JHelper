/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author dm
 */
class ServerThread extends Thread {

    static ServerSocket ss = null;
    static int port;
    Thread t;
    private static Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        DataInputStream dataIn = null;
        DataOutputStream dataOut = null;
        try {
            String answer = null;
            String query;
            String command, term, definition = null;
            int index1, index2;
            System.out.println("Thred");
            dataIn =
                    new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            while (true) {
                query = dataIn.readUTF();
                //Вырезаем из строки команду, термин и, если есть, определение. Они разделены скобками ][
                index1 = query.indexOf("]");
                index2 = query.indexOf("[");
                command = query.substring(0, index1);
                term = query.substring(index1 + 1, index2);

                if (index2 != query.length() - 1) {

                    definition = query.substring(index2 + 1);
                }

                if (command.equals("add")) {
                    ServerDB.addInformation(term, definition);
                    //Для того чтобы работать с термином и понятием, сохраняем в строку при каждой операции
                    Server.definition = definition;
                }
                if (command.equals("search")) {

                    answer = ServerDB.getInformation(term);
                    Server.definition = answer;

                    if (answer == null) {
                        answer = "Don't search";
                    }
                    dataOut.writeUTF(answer);
                    dataOut.flush();
                }

                if (command.equals("delete")) {

                    ServerDB.delete(term, definition);
                    answer = ServerDB.next();
                    if (answer == null) {
                        answer = "Don't search";
                    }
                    Server.definition = answer;
                    System.out.println(answer);
                    dataOut.writeUTF(answer);
                    dataOut.flush();
                }
                if (command.equals("edit")) {

                    ServerDB.edit(term, definition);
                    answer = ServerDB.getInformation(term);
                    Server.definition = answer;
                }
                if (command.equals("next")) {
                    answer = ServerDB.next();
                    if (answer == null) {
                        answer = "No found";
                    }
                    Server.definition = answer;
                    dataOut.writeUTF(answer);
                    dataOut.flush();
                }
                if (command.equals("previous")) {
                    answer = ServerDB.previous();
                    if (answer == null) {
                        answer = "No Found";
                    }
                    Server.definition = answer;
                    dataOut.writeUTF(answer);
                    dataOut.flush();
                }

            }



        } catch (IOException e) {
            e.printStackTrace();
            ServerDB.d.clear();
        }
        try {
            dataIn.close();
            dataOut.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
