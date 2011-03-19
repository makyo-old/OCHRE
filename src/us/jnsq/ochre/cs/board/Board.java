package us.jnsq.ochre.cs.board;

import java.util.Random;
import us.jnsq.ochre.cs.server.ServerClient;

/**
 *
 * @author makyo
 */
public class Board {
    private AbstractBoardObject[][][] board;
    private Player[] players;
    private Goal[] goals;

    public Board(ServerClient[] clients, boolean useZ) {
        Random rand = new Random();
        players = new Player[clients.length];
        board = new AbstractBoardObject
                [useZ ? rand.nextInt((int)Math.pow(players.length, 2)) + (players.length * 2) : 1]
                [rand.nextInt((int)Math.pow(players.length, 2)) + (players.length * 2)]
                [rand.nextInt((int)Math.pow(players.length, 2)) + (players.length * 2)];
        goals = new Goal[useZ
                ? rand.nextInt(board.length + board[0].length + board[0][0].length) + (board.length + board[0].length + board[0][0].length)
                : rand.nextInt(board[0].length + board[0][0].length) + (board[0].length + board[0][0].length)];
        
        
        for (int i = 0; i < clients.length; i++) {
            int z = useZ ? rand.nextInt(board.length) : 0;
            int y = rand.nextInt(board[z].length);
            int x = rand.nextInt(board[z][y].length);
            players[i] = clients[i].getPlayer();
            players[i].setX(x);
            players[i].setY(y);
            players[i].setZ(z);
            board[z][y][x] = players[i];
        }
        
        for (int i = 0; i < goals.length; i++) {
            int z = useZ ? rand.nextInt(board.length) : 0;
            int y = rand.nextInt(board[z].length);
            int x = rand.nextInt(board[z][y].length);
            while (board[z][y][x] != null) {
                z = useZ ? rand.nextInt(board.length) : 0;
                y = rand.nextInt(board[z].length);
                x = rand.nextInt(board[z][y].length);
            }
            goals[i] = new Goal();
            goals[i].setX(x);
            goals[i].setY(y);
            goals[i].setZ(z);
            goals[i].setAmount(rand.nextInt(99) + 1);
            board[z][y][x] = goals[i];
        }
    }
    
    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        
        for (int z = 0; z < board.length; z++) {
            toReturn.append("[");
            for (int y = 0; y < board[z].length; y++) {
                toReturn.append("[");
                for (int x = 0; x < board[z][y].length; x++) {
                    if (board[z][y][x] != null) {
                        toReturn.append(board[z][y][x].toString());
                    }
                    toReturn.append(x == board[z][y].length - 1 ? "" : ",");
                }
                toReturn.append("]").append(y == board[z].length - 1 ? "" : ",");
            }
            toReturn.append("]").append(z == board.length - 1 ? "" : ",");
        }
        
        return toReturn.toString();
    }
    
    public String boardForPlayer(Player p) {
        StringBuilder toReturn = new StringBuilder();
        
        for (int z = 0; z < board.length; z++) {
            toReturn.append("[");
            for (int y = 0; y < board[z].length; y++) {
                toReturn.append("[");
                for (int x = 0; x < board[z][y].length; x++) {
                    if (board[z][y][x] instanceof Player) {
                        if ((Player)board[z][y][x] == p) {
                            toReturn.append("you");
                        } else {
                            toReturn.append("player " + ((Player)board[z][y][x]).getId());
                        }
                    } else if (board[z][y][x] != null) {
                        toReturn.append(board[z][y][x].toString());
                    }
                    toReturn.append(x == board[z][y].length - 1 ? "" : ",");
                }
                toReturn.append("]").append(y == board[z].length - 1 ? "" : ",");
            }
            toReturn.append("]").append(z == board.length - 1 ? "" : ",");
        }
        
        return toReturn.toString();
        
    }
}
