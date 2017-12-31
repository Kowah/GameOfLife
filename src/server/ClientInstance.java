package server;

import java.io.Serializable;
import java.net.Socket;

import command.Command;
import command.GameCommand;
import command.GameOverCommand;
import game.Game;
import utils.Logger;
import utils.Connection;

/**
 * Created by Kovvah on 26/12/2017.
 */
public class ClientInstance extends Connection implements Serializable {

    private GameInstance gameInstance;
    private Server server;

    public Server getServer() {
        return server;
    }

    public ClientInstance(Socket socket, Server server) {
        super(socket);
        this.server = server;
    }

    @Override
    public void run() {
        Logger.getInstance().print("Client créé");
        while(true){
            Command command = receiveCommand();
            if(command!=null)
                command.execute(this);
        }
    }

    public void leaveGameInstance(){
        gameInstance.unregisterClientInstance(this);
    }

    public void sendGame(Game game){
        sendCommand(new GameCommand(game));
    }

    public void sendGameOver(){
        sendCommand(new GameOverCommand());
    }

    public void setGameInstance(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }
}
