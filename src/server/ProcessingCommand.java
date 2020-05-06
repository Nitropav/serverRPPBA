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
            case "getidproduct":
                commands = command.split(" ", 2);
                result = DataBaseSales.getIdProduct(commands[1]);
                break;
            case "getdate":
                result = DataBaseSales.getDate();
                break;
            case "getmodel":
                result = DataBaseSales.getModel();
                break;
            case "addproduction":
                commands = command.split(" ", 4);
                result = DataBaseSales.addProduction(commands[1], commands[2], commands[3]);
                break;
            case "getidclient":
                commands = command.split(" ", 2);
                result = DataBaseSales.getIdClient(commands[1]);
                break;
            case "addoredersch1":
                commands = command.split(" ", 6);
                result = DataBaseSales.addOrders(commands[1], commands[2], commands[3], commands[4], commands[5]);
                break;
            case "getlastnameclient":
                result = DataBaseSales.getLastName();
                break;
            case "getdelivery":
                result = DataBaseSales.getDelivery();
                break;
            case "getstates":
                result = DataBaseSales.getStates();
                break;
            case "getiddelivery":
                commands = command.split(" ", 2);
                result = DataBaseSales.getIdDelivery(commands[1]);
                break;
            case "getidstates":
                commands = command.split(" ", 2);
                result = DataBaseSales.getIdStates(commands[1]);
                break;
            case "addfilling":
                commands = command.split(" ", 3);
                result = DataBaseSales.addFilling(commands[1], Integer.valueOf(commands[2]));
                break;
            case "removeorder":
                commands = command.split(" ", 2);
                result = DataBaseSales.removeOrder(Integer.valueOf(commands[1]));
                break;
            case "getorderview":
                result = DataBaseSales.getOrders();
                break;
            case "shiporders":
                commands = command.split(" ", 2);
                result = DataBaseSales.shipOrders(Integer.valueOf(commands[1]));
                break;
            case "getshipOrders":
                result = DataBaseSales.getShipOrders();
                break;
            case "getrezerorders":
                result = DataBaseSales.getRezervOrders();
                break;
            case "rezerorders":
                commands = command.split(" ", 2);
                result = DataBaseSales.rezervOrders(Integer.valueOf(commands[1]));
                break;
            case "getdateresidual":
                result = DataBaseSales.getDateResidual();
                break;
            case "adddateoforders":
                commands = command.split(" ", 2);
                result = DataBaseSales.addDateOfOrders(commands[1]);
                break;
            case "notrezervorders":
                commands =command.split(" ", 2);
                result = DataBaseSales.notRezervOrders(Integer.valueOf(commands[1]));
                break;
            default:
                result = false;
                break;
        }
        return result;
    }
}