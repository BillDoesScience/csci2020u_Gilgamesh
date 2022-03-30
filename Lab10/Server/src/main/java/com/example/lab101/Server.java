package com.example.lab101;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server extends Thread {

    //Initialize input stream and socket
    public Socket sock = null;
    private ServerSocket serve = null;
    public Boolean ServerStatus = true;
    ArrayList<Thread> threads = new ArrayList<Thread>();

    public Server(int port){
        //start server and wait for client
        try {
            serve = new ServerSocket(port);
            System.out.println("Starting Server...");
            new Thread(() -> {
                try {
                    System.out.println("Waiting for client connection...");
                    while (ServerStatus) {
                        System.out.println("Looping");
                        if (isInterrupted()) {
                            sock.close();
                            CloseServer();
                        }
                        sock = serve.accept();
                        System.out.println("Connected to Client...");
                        Thread t = new SocketThread(sock);
                        threads.add(t);
                        t.start();
                        System.out.println("Connected to Client...");
                    }
                } catch (SocketTimeoutException ex) {
                    System.out.println("Socket Closed");
                }
                catch(IOException e){
                    System.out.println(e);
                }
            }).start();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public void CloseServer() {
        try {
            if (sock == null) {
                serve.close();
            } else {
                sock.close();
                serve.close();
            }

        }
        catch (IOException ex){
            System.out.println(ex);
        }

        ServerStatus = false;
        for (Thread thread: threads) {
            thread.interrupt();
        }
        System.out.println("Closed connection");
    }
}
