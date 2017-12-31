package gui;

import client.Client;
import utils.Parameters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Kovvah on 29/12/2017.
 */
public class Application extends JFrame {

    private final String title = "Game of Life";
    private final int width = 1000;
    private final int height = 1000;
    private Client client;
    private boolean connectionSuccessful;

    /* Swing elements */
    private JToolBar toolbar;


    public void initFrame(){
        setTitle(title);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public static void main(String args[]){
        Application app = new Application();
        app.initFrame();

        app.setVisible(true);

        while(!app.connectionSuccessful){{
            try{
                Thread t = app.getConnectionThread(app);
                t.start();
                if(!app.connectionSuccessful){
                    JOptionPane.showMessageDialog(app,
                            "Impossible to reach localhost server. Application will retry to connect in few seconds.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }
        app.add(new SceneGameInstances(app));
        app.setVisible(true);
    }

    private Thread getConnectionThread(Application app) {
        return new Thread(){
            public void run(){
                try {
                    app.client = Client.createClient(InetAddress.getLocalHost(), Parameters.SERVER_SOCKET_PORT);
                    client.start();
                    app.connectionSuccessful = true;
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
