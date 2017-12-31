package command;

import server.ClientInstance;
import utils.Connection;

/**
 * Created by Kovvah on 29/12/2017.
 */
public class GameLeaveCommand implements Command{

    @Override
    public void execute(Connection receiver) {
        ((ClientInstance) receiver).leaveGameInstance();
    }
}
