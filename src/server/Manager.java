package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Manager implements Runnable {

    private Socket socketClient;
    private ObjectOutputStream outPutStream = null;
    private ObjectInputStream inputStream = null;

    public Manager(Socket socketClient) {
        this.socketClient = socketClient;
    }

    @Override
    public void run() {
        try {
            outPutStream = new ObjectOutputStream(socketClient.getOutputStream());
            inputStream = new ObjectInputStream(socketClient.getInputStream());
            while (true) {
                String command = (String) inputStream.readObject();
                Object result = ProcessingCommand.split(command);
                outPutStream.writeObject(result);
            }
        } catch (Exception e) {
            try {
                outPutStream.close();
                inputStream.close();
                socketClient.close();
            } catch (IOException e1) {
                e.printStackTrace();
            }
        }
    }
}