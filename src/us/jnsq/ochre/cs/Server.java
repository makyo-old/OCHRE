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
    private int maxClients = 5, currClientIndex = 0;
    private ArrayList<ServerThread> clients;
    private boolean gameOver = false;

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

    public void runGame() {
        while (!gameOver) {
            for (ServerThread client : clients) {
                client.silentSetState(OCHREProtocol.NOT_YOUR_TURN);
            }
            ServerThread currClient = clients.get(currClientIndex);
            currClient.setState(OCHREProtocol.YOUR_TURN);
            currClient.listen();
            currClientIndex = (currClientIndex + 1) % maxClients;
            checkBoard();
        }
    }
    
    private void checkBoard() {
        //
    }
}
