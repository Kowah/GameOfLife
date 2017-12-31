package client;

import command.Command;
import game.Game;
import server.GameInstance;
import server.GameInstanceReference;
import utils.Connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Kovvah on 26/12/2017.
 */
public class Client extends Connection {

    private Game game;
    private ArrayList<GameInstanceReference> accessibleGameInstances = new ArrayList<GameInstanceReference>();

    public Game getGame() {
        return game;
    }

    public void updateGame(Game game){
        synchronized (game){
            this.game = game;
        }
    }

    public Client(Socket socket) {
        super(socket);
    }

    public void run(){
        while(true){
            Command command = receiveCommand();
            if(command!=null)
                command.execute(this);
        }
    }

    public static Client createClient(InetAddress ip, int port){
        Client client = null;
        try {
            Socket socket = new Socket(ip,port);
            client = new Client(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }

    public void setAccessibleGameInstances(ArrayList<GameInstanceReference> accessibleGameInstances) {
        this.accessibleGameInstances = accessibleGameInstances;
    }

    public ArrayList<GameInstanceReference> getAccessibleGameInstances() {
        return accessibleGameInstances;
    }
}

