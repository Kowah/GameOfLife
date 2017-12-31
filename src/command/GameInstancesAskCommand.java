package command;

import server.ClientInstance;
import server.GameInstance;
import server.GameInstanceReference;
import utils.Connection;

import java.util.ArrayList;

/**
 * Created by Kovvah on 31/12/2017.
 */
public class GameInstancesAskCommand implements Command{

    @Override
    public void execute(Connection receiver) {
        ClientInstance clientInstance = (ClientInstance) receiver;
        ArrayList<GameInstanceReference> gameInstances =  clientInstance.getServer().getGameInstancesReferences();
        clientInstance.sendCommand(new GameInstancesSendCommand(gameInstances));
    }
}
