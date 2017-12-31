package gui;

/**
 * Created by Kovvah on 31/12/2017.
 */
public class RefreshGameThread extends Thread{

    private SceneGame sceneGame;
    private boolean stop;

    public RefreshGameThread(SceneGame sceneGame){
        this.sceneGame = sceneGame;
    }

    public void run(){
        while(!stop){
            sceneGame.repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopThread(){
        stop = true;
    }
}