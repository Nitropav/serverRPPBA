package server;

public enum EnumServerIns{
    INSTANCE;

    private Server instance;

    EnumServerIns() {
        instance = new Server();
    }

    public Server getInstance() {
        return instance;
    }
}