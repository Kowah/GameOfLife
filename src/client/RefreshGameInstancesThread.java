package client;

import command.GameInstancesAskCommand;
import gui.SceneGameInstances;

/**
 * Created by Kovvah on 31/12/2017.
 */
public class RefreshGameInstancesThread extends Thread {

    private Client client;
    private SceneGameInstances sceneGameInstances;

    public RefreshGameInstancesThread(Client client, SceneGameInstances sceneGameInstances){
        this.client = client;
        this.sceneGameInstances = sceneGameInstances;
    }

    public void run(){
        while(true){
            client.sendCommand(new GameInstancesAskCommand());
            try {
                Thread.sleep(1000);
                sceneGameInstances.refreshData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
