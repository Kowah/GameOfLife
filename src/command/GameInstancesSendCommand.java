package command;

import client.Client;
import server.GameInstance;
import server.GameInstanceReference;
import utils.Connection;

import java.util.ArrayList;

/**
 * Created by Kovvah on 31/12/2017.
 */
public class GameInstancesSendCommand implements Command {

    private ArrayList<GameInstanceReference> gameInstances;

    public GameInstancesSendCommand(ArrayList<GameInstanceReference> gameInstances) {
        this.gameInstances = gameInstances;
    }

    @Override
    public void execute(Connection receiver) {
        Client client = (Client) receiver;
        client.setAccessibleGameInstances(gameInstances);
    }
}
