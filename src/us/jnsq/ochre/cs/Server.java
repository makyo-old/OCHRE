package us.jnsq.ochre.cs;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author makyo
 */
public class Server {
    private ServerSocket sock = null;
    private int maxClients = 5;
    private ArrayList<ServerThread> clients;
    
    public Server(int clients) {
        maxClients = clients;
    }
    
    public void startServer() throws IOException {
        sock = new ServerSocket(62473);
        
        while (maxClients > 0) {
            ServerThread client = new ServerThread(sock.accept(), this);
            client.start();
            clients.add(client);
        }
    }
}
