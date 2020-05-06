package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private ServerSocket socketServer;

    private boolean stopped = false;

    @Override
    public void run() {
        openServerSocket();
        while (!stopped()) {
            Socket clientSocket;
            try {
                clientSocket = this.socketServer.accept();
                new Thread(new Manager(clientSocket)).start();
            } catch (IOException e) {
            }
        }
    }

    private void openServerSocket() {
        System.out.println("Opening server!");
        this.stopped = false;
        try {
            this.socketServer = new ServerSocket(2525);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void stop() {
        this.stopped = true;
        try {
            this.socketServer.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing!", e);
        }
    }

    private synchronized boolean stopped() {
        return this.stopped;
    }
}