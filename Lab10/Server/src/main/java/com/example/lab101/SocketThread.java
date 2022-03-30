package com.example.lab101;

import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketThread extends Thread {
    Socket curSocket;
    public SocketThread(Socket curSocket) {
        this.curSocket = curSocket;
    }

    public void run() {
        DataInputStream inStream = null;
        try {
            inStream = new DataInputStream(curSocket.getInputStream());
            //holds message sent by client
            String message = "";
            while (true) {
                if (this.isInterrupted()) {
                    inStream.close();
                    curSocket.close();
                }
                if(inStream.readUTF() == null){
                    inStream.close();
                    curSocket.close();
                    currentThread().interrupt();
                }
                try{
                    message = inStream.readUTF();
                    ServerController.AddList(message);
                    System.out.println(message);
                }
                catch(IOException ex) {
                    System.out.println(ex);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
