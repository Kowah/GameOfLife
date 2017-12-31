package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

/**
 * Created by Kovvah on 31/12/2017.
 */
public class AppendableObjectInputStream extends ObjectInputStream {

    public AppendableObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    protected void readStreamHeader()
            throws IOException, StreamCorruptedException
    {
    }
}

