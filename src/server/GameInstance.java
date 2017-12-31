package server;

import game.Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Kovvah on 26/12/2017.
 */
public class GameInstance extends Thread implements Serializable{

    private ArrayList<ClientInstance> clientInstances;
    private Game game;
    private int delay;
    private int timeLiving = 1;
    private UUID customId = UUID.randomUUID();

    public UUID getCustomId() {
        return customId;
    }

    public GameInstance (int delay){
        clientInstances = new ArrayList<>();
        this.delay = delay;
    }
    public GameInstance (int rows, int columns, int delay, int numbers){
        clientInstances = new ArrayList<>();
        this.delay = delay;
        this.game = new Game(rows,columns,delay);
        this.game.generateRandomly(numbers);
        System.out.println(getName() + " crée");
    }

    public void registerClientInstance(ClientInstance clientInstance){
        clientInstances.add(clientInstance);
    }

    public void unregisterClientInstance(ClientInstance clientInstance){
        clientInstances.remove(clientInstance);
    }

    public void unregisterClientInstances(){
        clientInstances.clear();
    }

    public void sendGameToClientInstances(){
        for (ClientInstance clientInstance:clientInstances) {
            clientInstance.sendGame(game);
        }
    }

    public void sendGameOverToClientInstances(){
        for (ClientInstance clientInstance:clientInstances) {
            clientInstance.sendGameOver();
            clientInstance.getServer().getGameInstances().remove(this);
        }
    }

    @Override
    public void run() {
        sendGameToClientInstances();
        while(game.thereIsLife()){
            timeLiving++;
            game.evolve();
            sendGameToClientInstances();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
                e.printStackTrace();
            }
        }
        System.out.println("Game is over, there is no more life.");
        sendGameOverToClientInstances();
        unregisterClientInstances();
    }

    public Object[] getTableFormat() {
        return new Object[]{};
    }


    public GameInstanceReference getGameInstanceReference(){
        return new GameInstanceReference(getCustomId(),getName(),game.getRows()+"",game.getColumns()+"",delay+"", timeLiving+ "", clientInstances.size() +"");
    }
}
