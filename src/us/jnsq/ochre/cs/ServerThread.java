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
    private PrintWriter out;
    private BufferedReader in;

    public ServerThread(Socket socket, Server server) {
        super("ServerThread");
        this.socket = socket;
        this.server = server;
        
    }
    
    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            protocol = new OCHREProtocol();
            String input, output;
            
            out.print(protocol.setState(OCHREProtocol.JOINED) + "\n");
            if (protocol.identify(in.readLine())) {
                out.print(protocol.setState(OCHREProtocol.ACCEPTED));
            } else {
                out.print(protocol.setState(OCHREProtocol.DENIED) + "\n\n");
                out.close();
                in.close();
                socket.close();
            }
        } catch (IOException e) {
            //
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
}
