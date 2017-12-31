package gui;

import client.Client;
import command.GameLeaveCommand;
import game.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kovvah on 30/12/2017.
 */
public class SceneGame extends JPanel{

    private Application application;
    private Game game;
    private RefreshGameThread refreshGameThread;

    private SceneGame me;

    public SceneGame(Application application, JToolBar toolbar){
        me = this;
        this.application = application;
        this.game = application.getClient().getGame();
        this.refreshGameThread = new RefreshGameThread(this);
        JButton buttonBack = new JButton("Back");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshGameThread.stopThread();
                application.remove(me);
                application.remove(toolbar);
                application.repaint();
                application.add(new SceneGameInstances(application));
                application.getClient().sendCommand(new GameLeaveCommand());
                application.setVisible(true);
            }
        });
        toolbar.add(buttonBack, BorderLayout.PAGE_START);
        this.setVisible(true);
        refreshGameThread.start();
    }

    public void paint(Graphics g){
        super.paint(g);
        this.game = application.getClient().getGame();
        if(game != null) {
            Graphics2D g2D = (Graphics2D) g;

            int rectWidth = getWidth() / game.getColumns();
            int rectHeight = getHeight() / game.getColumns();

            int finalrect = rectWidth > rectHeight ? rectWidth : rectHeight;

            for (int row = 0; row < game.getRows(); row++) {
                for (int col = 0; col < game.getColumns(); col++) {
                    int x = row * rectWidth;
                    int y = col * rectHeight;
                    g2D.drawRect(x, y, finalrect, finalrect);
                    if (game.getGrid()[row][col])
                        g2D.fillRect(x, y, finalrect, finalrect);

                }
            }
        }
    }
}
