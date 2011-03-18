package us.jnsq.ochre.cs.server;

import us.jnsq.ochre.cs.board.Board;
import java.io.*;
import java.net.*;

/**
 *
 * @author makyo
 */
public class Server {
    public static final int PORT = 62473;

    private ServerSocket sock = null;
    private int maxClients = 5, currClientIndex = 0;
    private ServerClient[] clients;
    private Board board;
    private boolean gameOver = false;

    public Server(int numClients) {
        maxClients = numClients;
        this.clients = new ServerClient[maxClients];
    }

    public void startServer() throws IOException {
        sock = new ServerSocket(PORT);

        for (int i = 0; i < maxClients; i++) {
            ServerClient client = new ServerClient(sock.accept(), this);
            client.start();
            clients[i] = client;
        }
        
        board = new Board(clients, false);
    }

    public void runGame() {
        while (!gameOver) {
            for (ServerClient client : clients) {
                client.silentSetState(OCHREProtocol.NOT_YOUR_TURN);
            }
            ServerClient currClient = clients[currClientIndex];
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
