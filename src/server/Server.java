package server;

import utils.Logger;
import utils.Parameters;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Kovvah on 26/12/2017.
 */
public class Server {

    private ArrayList<GameInstance> gameInstances = new ArrayList<>();

    public static void main(String[] args) {
        Server server = new Server();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(Parameters.SERVER_SOCKET_PORT);
            while(true){
                Socket client = serverSocket.accept();
                ClientInstance clientInstance = new ClientInstance(client,  server);
                clientInstance.start();
            }
        } catch (IOException e) {
            Logger.getInstance().error("THE PORT "+Parameters.SERVER_SOCKET_PORT+ " SEEMS TO BE ALREADY USED");
            e.printStackTrace();
        }
    }

    public void registerGameInstance(GameInstance gameInstance){
        gameInstances.add(gameInstance);
    }

    public void unregisterGameInstance(GameInstance gameInstance){
        gameInstances.remove(gameInstance);
    }

    public ArrayList<GameInstanceReference> getGameInstancesReferences(){
        ArrayList<GameInstanceReference> references = new ArrayList<>();
        for(GameInstance gameInstance: gameInstances){
            references.add(gameInstance.getGameInstanceReference());
        }
        return references;
    }

    public ArrayList<GameInstance> getGameInstances() {
        return gameInstances;
    }
}
