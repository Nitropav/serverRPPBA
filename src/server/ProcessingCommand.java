package server;

import database.DataBaseSales;

public class ProcessingCommand {

    public static Object split(String command) {
        String[] commandNumber = command.split(" ", 2);
        String[] commands;
        Object result = true;
        switch (commandNumber[0]) {
            case "login":
                commands = command.split(" ", 3);
                result = DataBaseSales.login(commands[1], commands[2]);
                break;
            case "gettype":
                result = DataBaseSales.getType();
                break;
            case "getshellpen":
                result = DataBaseSales.getShellPen();
                break;
            case "getshellpencil":
                result = DataBaseSales.getShellPencil();
                break;
            case "getkernelpen":
                result = DataBaseSales.getKernelPen();
                break;
            case "getkernelpencil":
                result = DataBaseSales.getKernelPencil();
                break;
            case "addnewproduct":
                commands = command.split(" ", 6);
                result = DataBaseSales.addNewProduct(commands[1], commands[2], commands[3], commands[4], commands[5]);
                break;
            case "addnewclient":
                commands = command.split(" ", 5);
                result = DataBaseSales.addNewClient(commands[1], commands[2], commands[3], commands[4]);
                break;
            case "updateclient":
                commands = command.split(" ", 6);
                result = DataBaseSales.updateClient(commands[1], commands[2], commands[3], commands[4], commands[5]);
                break;
            case "updateproduct":
                commands = command.split(" ", 7);
                result = DataBaseSales.updateProduct(commands[1], commands[2], commands[3], commands[4], commands[5], commands[6]);
                break;
            /*case "getmodels":
                commands = command.split(" ", 2);
                result = DataBaseSales.getModels(commands[1]);
                break;
            case "vieworder":
                result = DataBaseSales.getOrders();
                break;
            case "vieworderclient":
                commands = command.split(" ", 3);
                result = DataBaseSales.getOrderClient(commands[1], commands[2]);
                break;
            case "viewordersmanager":
                result = DataBaseSales.getOrdersManager();
                break;
            case "viewsupplierorders":
                result = DataBaseSales.getSupplierOrder();
                break;
            case "addorderclient":
                result = DataBaseSales.addOrderClient(command);
                break;*/
            /*case "removeorder":
                commands = command.split(" ", 2);
                result = DataBaseSales.removeOrder(Integer.valueOf(commands[1]));
                break;
            case "addmanager":
                commands = command.split(" ", 5);
                result = DataBaseSales.addManager(commands[1], commands[2], commands[3], commands[4]);
                break;
            case "addsupplier":
                commands = command.split(" ", 4);
                result = DataBaseSales.addSupplier(commands[1], commands[2], commands[3]);
                break;
            case "getclientdata":
                commands = command.split(" ", 3);
                result = DataBaseSales.getClientData(commands[1], commands[2]);
                break;
            case "getmanagerdata":
                commands = command.split(" ", 3);
                result = DataBaseSales.getManagerData(commands[1], commands[2]);
                break;
            case "addclient":
                System.out.println(command);
                commands = command.split(" ", 5);
                result = DataBaseSales.addClient(commands[1], commands[2], commands[3], commands[4]);
                break;
            case "getinsurance":
                result = DataBaseSales.getInsurance();
                break;
            case "getcountry":
                commands = command.split(" ", 2);
                result = DataBaseSales.getCountry(commands[1]);
                break;
            case "getsupplier":
                commands = command.split(" ", 2);
                result = DataBaseSales.getSupplier(commands[1]);
                break;
            case "addinsurance":
                commands = command.split(" ", 3);
                result = DataBaseSales.addInsurance(commands[1], commands[2]);
                break;
            case "markaccepted":
                commands = command.split(" ", 2);
                result = DataBaseSales.markAccepted(Integer.valueOf(commands[1]));
                break;
            case "markcompleted":
                commands = command.split(" ", 2);
                result = DataBaseSales.markCompleted(Integer.valueOf(commands[1]));
                break;
            case "getmakesstatistics":
                result = DataBaseSales.getMakeStatistics();
                break;*/
            default:
                result = false;
                break;
        }
        return result;
    }
}