package us.jnsq.ochre.cs.server;

import us.jnsq.ochre.cs.board.Player;
import java.net.*;
import java.io.*;

/**
 *
 * @author makyo
 */
public class ServerClient {
    private Server server;
    private Socket socket = null;
    private OCHREProtocol protocol;
    private Player player;
    private PrintWriter out;
    private BufferedReader in;

    public ServerClient(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        this.player = new Player();
        
    }
    
    public boolean start() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            protocol = new OCHREProtocol();
            String input, output;
            
            out.print(protocol.setState(OCHREProtocol.JOINED) + "\n");
            if (protocol.identify(in.readLine(), getPlayer())) {
                out.print(protocol.setState(OCHREProtocol.ACCEPTED));
                return true;
            } else {
                out.print(protocol.setState(OCHREProtocol.DENIED) + "\n\n");
                out.close();
                in.close();
                socket.close();
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }
    
    public void setState(int state) {
        out.println(protocol.setState(state));
    }
    
    public void silentSetState(int state) {
        protocol.setState(state);
    }
    
    public void listen() {
        //
    }
    
    private String readLine() {
        return "";
    }
    
    private void send(String data) {
        out.println(data + "\n");
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
}
