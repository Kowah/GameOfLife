package utils;

import command.Command;
import server.AppendableObjectInputStream;
import server.AppendableObjectOutputStream;

import java.io.*;
import java.net.Socket;

/**
 * Created by Kovvah on 26/12/2017.
 */
public abstract class Connection extends Thread implements Serializable {

    protected Socket socket;

    public Connection(Socket socket){
        this.socket = socket;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            Logger.getInstance().error("Problême socket");
            e.printStackTrace();
        }
    }

    public AppendableObjectOutputStream getObjectOutput(){
        try {
            return new AppendableObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AppendableObjectInputStream getObjectInput(){
        try {
            return new AppendableObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendCommand(Command command){
        try {
            ObjectOutputStream out = getObjectOutput();
            out.writeObject(command);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Command receiveCommand(){
        Command command = null;
        ObjectInputStream in = getObjectInput();
        try {
            command = (Command) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return command;
    }
}
