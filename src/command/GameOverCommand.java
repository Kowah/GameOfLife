package command;

import utils.Connection;

/**
 * Created by Kovvah on 28/12/2017.
 */
public class GameOverCommand implements Command {

    @Override
    public void execute(Connection receiver) {
        System.out.println("There is no more life. Game is over.");
    }
}