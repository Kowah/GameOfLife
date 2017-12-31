package gui;

import client.RefreshGameInstancesThread;
import command.GameInstanceJoinCommand;
import command.GameRequestCommand;
import server.GameInstance;
import server.GameInstanceReference;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Kovvah on 30/12/2017.
 */
public class SceneGameInstances extends JPanel {

    private RefreshGameInstancesThread refreshGameInstancesThread;
    private Application application;

    private JTable gameInstancesTable;
    private String[] columnNames = {"Name","Rows","Columns","Delay","Time lived","Clients connected"};
    private ArrayList<GameInstanceReference> gameInstances;
    private Object[][] dataTable = new Object[][]{};

    protected JPanel me;
    private JToolBar toolBar;
    private JButton buttonRandom;
    private JButton buttonGun;

    public SceneGameInstances(Application application){
        super();
        this.setSize(800,600);
        me = this;
        this.application = application;


        //Toolbar

        toolBar = new JToolBar();

        buttonRandom = new JButton("Random");
        buttonRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RandomDialog dialog = new RandomDialog(application,"Random Game Of Life",true);
                GameRequestCommand command = dialog.showRandomDialog();
                if(command!=null) {
                    application.getClient().sendCommand(command);
                }
            }
        });
        buttonGun = new JButton("Gun");
        buttonGun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        toolBar.add(buttonRandom);
        toolBar.add(buttonGun);

        application.getContentPane().add(toolBar, BorderLayout.PAGE_START);
        TableModel model = new DefaultTableModel(dataTable,columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        gameInstancesTable = new JTable(model);
        gameInstancesTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && row >= 0) {
                    application.getClient().sendCommand(new GameInstanceJoinCommand(gameInstances.get(row)));
                    application.remove(me);
                    toolBar.remove(buttonGun);
                    toolBar.remove(buttonRandom);
                    application.repaint();
                    application.add(new SceneGame(application,toolBar));
                    application.setVisible(true);
                }
            }
        });
        this.add(new JScrollPane((gameInstancesTable)),BorderLayout.CENTER);
        refreshGameInstancesThread = new RefreshGameInstancesThread(application.getClient(),this);
        refreshGameInstancesThread.start();
        this.setVisible(true);
    }

    public void refreshData(){
        gameInstances = application.getClient().getAccessibleGameInstances();
        dataTable = new Object[gameInstances.size()][columnNames.length];
        int index = 0;
        for(GameInstanceReference gameInstance: gameInstances){
            dataTable[index] = gameInstance.getTableFormat();
            index++;
        }

        DefaultTableModel newModel = new DefaultTableModel(dataTable,columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        gameInstancesTable.setModel(newModel);
        gameInstancesTable.repaint();
    }


}
