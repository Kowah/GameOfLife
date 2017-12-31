package command;

import client.Client;
import server.ClientInstance;
import server.GameInstance;
import server.GameInstanceReference;
import utils.Connection;

/**
 * Created by Kovvah on 31/12/2017.
 */
public class GameInstanceJoinCommand implements Command {

    private GameInstanceReference gameInstance;

    public GameInstanceJoinCommand(GameInstanceReference gameInstance){
        this.gameInstance = gameInstance;
    }

    @Override
    public void execute(Connection receiver) {
        ClientInstance clientInstance = (ClientInstance) receiver;
        GameInstance gameInstanceToSubscribe = findGameInstanceById(clientInstance, this.gameInstance);
        clientInstance.setGameInstance(gameInstanceToSubscribe);
        gameInstanceToSubscribe.registerClientInstance(clientInstance);
    }


    //By using ID we avoid Thread identification issues
    public GameInstance findGameInstanceById(ClientInstance clientInstance, GameInstanceReference gameInstanceFromClient){
        for(GameInstance gameInstance: clientInstance.getServer().getGameInstances()){
            if(gameInstance.getCustomId().equals(gameInstanceFromClient.customIDReference)){
                return gameInstance;
            }
        }
        return null;
    }
}
