/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package us.jnsq.ochre.cs;

/**
 *
 * @author makyo
 */
public class OCHREProtocol {
    public static final int JOINED = 0;
    public static final int IDENTIFIED = 1;
    public static final int ACCEPTED = 2;
    public static final int DENIED = 3;
    public static final int YOUR_TURN = 4;
    public static final int NOT_YOUR_TURN = 5;
    
    private int state;

    /**
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public String setState(int state) {
        this.state = state;
        
        switch (this.state) {
            case JOINED:
                return "IDENTIFY";
            case IDENTIFIED:
                return "WAIT";
            case ACCEPTED:
                return "IDENTIFICATION ACCEPTED";
            case DENIED:
                return "IDENTIFICATION DENIED";
            case YOUR_TURN:
                return "PROMPT";
            case NOT_YOUR_TURN:
                return "DENIED: NOT YOUR TURN";
            default:
                return "";
                
        }
    }
}
