package command;

import utils.Connection;

import java.io.Serializable;

/**
 * Created by Kovvah on 28/12/2017.
 */
public interface Command extends Serializable{
    void execute(Connection receiver);
}
