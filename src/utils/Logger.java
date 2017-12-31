package utils;

import java.io.PrintStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kovvah on 26/12/2017.
 */
public class Logger implements Serializable {

    private PrintStream out;
    private PrintStream err;

    public static Logger INSTANCE = new Logger();

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

    private Logger (){
        out = System.out;
        err = System.out;
    }

    public static Logger getInstance(){
        return INSTANCE;
    }
    private void log(PrintStream stream, String message){
        stream.println(message);
    }

    public void print(String message){
        log(out,"[" + dateFormat.format(new Date()) + "] "+"INFO: "+message);
    }

    public void error(String message){
        log(err,"[" + dateFormat.format(new Date()) + "] "+ "ERROR: "+ message);
    }
}
