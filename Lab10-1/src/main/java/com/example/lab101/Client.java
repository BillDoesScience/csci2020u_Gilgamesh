package com.example.lab101;


import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    //initialize socket and io streams
    private DataOutputStream dout = null;
    private Socket sock = null;

    public Client(String address, int port){
        try {
            sock = new Socket (address, port);
            System.out.println("Connected to server...");
            System.out.println("Input \"done\" to kill connection...");
        }
        catch(UnknownHostException h){
            System.out.println(h);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    public void CloseConnection() {
        try {
            dout.close();
            sock.close();
        }
        catch (IOException ex){
            System.out.println(ex);
        }
    }

    public void SendMessage(String msg) {
        try {
            dout.writeUTF(msg);
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    }
}