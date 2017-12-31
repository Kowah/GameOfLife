package server;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Kovvah on 31/12/2017.
 */
public class GameInstanceReference implements Serializable{

    public UUID customIDReference;
    public String name;
    public String rows;
    public String columns;
    public String delay;
    public String survive;
    public String timeLived;
    public String clientsConnected;

    public GameInstanceReference(UUID customIDReference,String name, String rows, String columns, String delay, String timeLived, String clientsConnected) {
        this.customIDReference = customIDReference;
        this.name = name;
        this.rows = rows;
        this.columns = columns;
        this.delay = delay;
        this.timeLived = timeLived;
        this.clientsConnected = clientsConnected;
    }

    public Object[] getTableFormat() {
        return new Object[]{name,rows,columns,delay, timeLived+ "", clientsConnected +""};
    }
}
