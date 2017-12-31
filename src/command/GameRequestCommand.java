package command;

import server.ClientInstance;
import server.GameInstance;
import utils.Connection;
import utils.LifePatterns;

/**
 * Created by Kovvah on 28/12/2017.
 */
public class GameRequestCommand implements Command {

    private int rows;
    private int columns;
    private int delay = 1000;
    private int numbers;

    private LifePatterns pattern = LifePatterns.Random;

    public GameRequestCommand(LifePatterns pattern){
        this.rows = pattern.getRows();
        this.columns = pattern.getColumns();
    }

    public GameRequestCommand(int rows, int columns, int numbers){
        this.rows = rows;
        this.columns = columns;
        this.numbers = numbers;
    }

    public GameRequestCommand(int rows, int columns, int delay, int numbers){
        this.rows = rows;
        this.columns = columns;
        this.delay = delay;
        this.numbers = numbers;
    }

    @Override
    public void execute(Connection receiver) {
        ClientInstance clientInstance = (ClientInstance) receiver;
        GameInstance gameInstance = new GameInstance(rows,columns,delay,numbers);
        clientInstance.getServer().registerGameInstance(gameInstance);
        gameInstance.start();
    }
}
