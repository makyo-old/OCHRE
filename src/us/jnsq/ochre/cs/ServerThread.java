package us.jnsq.ochre.cs;

import java.net.*;
import java.io.*;

/**
 *
 * @author makyo
 */
public class ServerThread extends Thread {
    private Server server;
    private Socket socket = null;
    private OCHREProtocol protocol;

    public ServerThread(Socket socket, Server server) {
        super("ServerThread");
        this.socket = socket;
        this.server = server;
        
    }
    
    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            protocol = new OCHREProtocol();
            String input, output;
            
            output = protocol.setState(OCHREProtocol.JOINED);
            
        } catch (IOException e) {
            //
        }
    }
    
    public void setState(int state) {
        protocol.setState(state);
    }
}
