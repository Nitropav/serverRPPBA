package server;

import database.DataBaseAvto;

public class ProcessingCommand {

    public static Object split(String command) {
        String[] commandNumber = command.split(" ", 2);
        String[] commands;
        Object result = true;
        switch (commandNumber[0]) {
            case "login":
                commands = command.split(" ", 3);
                result = DataBaseAvto.login(commands[1], commands[2]);
                break;
            case "getmakes":
                result = DataBaseAvto.getMakes();
                break;
            case "getmodels":
                commands = command.split(" ", 2);
                result = DataBaseAvto.getModels(commands[1]);
                break;
            case "vieworder":
                result = DataBaseAvto.getOrders();
                break;
            case "vieworderclient":
                commands = command.split(" ", 3);
                result = DataBaseAvto.getOrderClient(commands[1], commands[2]);
                break;
            case "viewordersmanager":
                result = DataBaseAvto.getOrdersManager();
                break;
            case "viewsupplierorders":
                result = DataBaseAvto.getSupplierOrder();
                break;
            case "addorderclient":
                result = DataBaseAvto.addOrderClient(command);
                break;
            case "addsupplierorder":
                result = DataBaseAvto.addSupplierOrder(command);
                break;
            case "removeorder":
                commands = command.split(" ", 2);
                result = DataBaseAvto.removeOrder(Integer.valueOf(commands[1]));
                break;
            case "addmanager":
                commands = command.split(" ", 5);
                result = DataBaseAvto.addManager(commands[1], commands[2], commands[3], commands[4]);
                break;
            case "addsupplier":
                commands = command.split(" ", 4);
                result = DataBaseAvto.addSupplier(commands[1], commands[2], commands[3]);
                break;
            case "getclientdata":
                commands = command.split(" ", 3);
                result = DataBaseAvto.getClientData(commands[1], commands[2]);
                break;
            case "getmanagerdata":
                commands = command.split(" ", 3);
                result = DataBaseAvto.getManagerData(commands[1], commands[2]);
                break;
            case "addclient":
                System.out.println(command);
                commands = command.split(" ", 5);
                result = DataBaseAvto.addClient(commands[1], commands[2], commands[3], commands[4]);
                break;
            case "getinsurance":
                result = DataBaseAvto.getInsurance();
                break;
            case "getcountry":
                commands = command.split(" ", 2);
                result = DataBaseAvto.getCountry(commands[1]);
                break;
            case "getsupplier":
                commands = command.split(" ", 2);
                result = DataBaseAvto.getSupplier(commands[1]);
                break;
            case "addinsurance":
                commands = command.split(" ", 3);
                result = DataBaseAvto.addInsurance(commands[1], commands[2]);
                break;
            case "markaccepted":
                commands = command.split(" ", 2);
                result = DataBaseAvto.markAccepted(Integer.valueOf(commands[1]));
                break;
            case "markcompleted":
                commands = command.split(" ", 2);
                result = DataBaseAvto.markCompleted(Integer.valueOf(commands[1]));
                break;
            case "getmakesstatistics":
                result = DataBaseAvto.getMakeStatistics();
                break;
            default:
                result = false;
                break;
        }
        return result;
    }
}