package command;

import client.Client;
import game.Game;
import utils.Connection;

/**
 * Created by Kovvah on 28/12/2017.
 */
public class GameCommand implements Command {

    private Game game;

    public GameCommand(Game game){
        this.game = game;
    }

    @Override
    public void execute(Connection receiver) {
        Client client = (Client) receiver;
        client.updateGame(game);
    }
}
