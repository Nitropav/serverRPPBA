package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseSales {
    private static Connection connection;

    public static void connect(String database, String user, String password, String port) {
        try {
            connection = DriverManager.getConnection(("jdbc:mysql://localhost:" + port + "/" +
                    database + "?serverTimezone=UTC"), user, password);
        } catch (SQLException sqlexc) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sqlexc.printStackTrace();
        }
    }

    public static String login(String login, String password) {
        ResultSet resultSet;
        String accountType = "";

        String select = "SELECT * FROM employee, passwords WHERE E_LOGIN = ? AND PASS = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                accountType = resultSet.getString("account_type");
            } else {
                accountType = "";
            }
        } catch (SQLException e) {
            System.out.println("login func exception");
            e.printStackTrace();
        }
        return accountType;
    }

    public static List<String> getType() {
        ResultSet resultSet;
        List<String> makes = new ArrayList<>(0);

        String select = "SELECT * FROM rppba.make ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                makes.add(resultSet.getString("make"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return makes;
    }

    public static boolean addNewProduct(String model, String price, String type, String shell, String kernel) {
        String insert = "INSERT INTO rppba.product (MODEL, PRICE, TYP, SHELL, KEREL) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, model);
            preparedStatement.setInt(2, Integer.valueOf(price));
            preparedStatement.setString(3, type);
            preparedStatement.setString(4, shell);
            preparedStatement.setString(5, kernel);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static List<String> getShellPen() {
        ResultSet resultSet;
        List<String> shell_pen = new ArrayList<>(0);

        String select = "SELECT * FROM rppba.shell_fpen ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                shell_pen.add(resultSet.getString("TYP"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shell_pen;
    }

    public static List<String> getShellPencil() {
        ResultSet resultSet;
        List<String> shell_pencil = new ArrayList<>(0);

        String select = "SELECT * FROM rppba.shell_fpencil ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                shell_pencil.add(resultSet.getString("TYP"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shell_pencil;
    }

    public static List<String> getKernelPen() {
        ResultSet resultSet;
        List<String> kernel_pen = new ArrayList<>(0);

        String select = "SELECT * FROM rppba.kernel_fpen ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                kernel_pen.add(resultSet.getString("TYP"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kernel_pen;
    }

    public static List<String> getKernelPencil() {
        ResultSet resultSet;
        List<String> kernel_pencil = new ArrayList<>(0);

        String select = "SELECT * FROM rppba.kernel_fpencil ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                kernel_pencil.add(resultSet.getString("TYP"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kernel_pencil;
    }

    public static boolean addNewClient(String name, String surname, String number, String email) {

        String insert = "INSERT INTO rppba.client (O_FNAME, O_LNAME, O_TEL, O_MAIL) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, number);
            preparedStatement.setString(4, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean updateClient(String ID, String name, String surname, String number, String email) {
        String update = "UPDATE rppba.client SET O_FNAME = ?, O_LNAME = ?, O_TEL = ?, O_MAIL = ? WHERE C_ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, number);
            preparedStatement.setString(4, email);
            preparedStatement.setInt(5, Integer.valueOf(ID));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean updateProduct(String ID, String model, String price, String type, String shell, String kernel) {
        String updateProd = "UPDATE rppba.product SET MODEL = ?, PRICE = ?, TYP = ?, SHELL = ?, KEREL = ? WHERE ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateProd);
            preparedStatement.setString(1, model);
            preparedStatement.setInt(2, Integer.valueOf(price));
            preparedStatement.setString(3, type);
            preparedStatement.setString(4, shell);
            preparedStatement.setString(5, kernel);
            preparedStatement.setInt(6, Integer.valueOf(ID));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getIdProduct(String model) {
        ResultSet resultSet;
        String id = "";
        String select = "SELECT ID FROM PRODUCT WHERE MODEL = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, model);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getString("ID");
            } else {
                id = "";
            }
        } catch (SQLException e) {
            System.out.println("Not ID");
            e.printStackTrace();
        }
        return id;
    }

    public static String getIdClient(String lastName) {
        ResultSet resultSet;
        String id = "";
        String select = "SELECT C_ID FROM client WHERE O_LNAME = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, lastName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getString("C_ID");
            } else {
                id = "";
            }
        } catch (SQLException e) {
            System.out.println("Not ID");
            e.printStackTrace();
        }
        return id;
    }

    public static List<String> getDate() {
        ResultSet resultSet;
        List<String> date = new ArrayList<>(0);

        String select = "SELECT * FROM rppba.s13dat WHERE D_KOD = 1 ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                date.add(resultSet.getString("DAT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static List<String> getModel() {
        ResultSet resultSet;
        List<String> modelList = new ArrayList<>(0);

        String select = "SELECT * FROM rppba.product ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                modelList.add(resultSet.getString("MODEL"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelList;
    }

    public static boolean addProduction(String date, String idProduct, String kol) {
        String insert = "INSERT INTO rppba.production (DAT, ID, KOL) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, idProduct);
            preparedStatement.setString(3, kol);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean addOrders(String address, String condittion, String cID, String dID, String sID) {
        String insert = "INSERT INTO rppba.orders(O_ADR, O_COST, O_CONDITION, C_ID, D_ID, S_ID) VALUES (?, 0, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, condittion);
            preparedStatement.setString(3, cID);
            preparedStatement.setString(4, dID);
            preparedStatement.setString(5, sID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static List<String> getStates() {
        ResultSet resultSet;
        List<String> statesList = new ArrayList<>(0);

        String select = "SELECT * FROM rppba.states ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                statesList.add(resultSet.getString("S_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statesList;
    }

    public static String getIdStates(String name) {
        ResultSet resultSet;
        String id = "";
        String select = "SELECT ST_ID FROM states WHERE S_NAME = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getString("ST_ID");
            } else {
                id = "";
            }
        } catch (SQLException e) {
            System.out.println("Not ID");
            e.printStackTrace();
        }
        return id;
    }

    public static List<String> getLastName() {
        ResultSet resultSet;
        List<String> lastNameList = new ArrayList<>(0);

        String select = "SELECT * FROM rppba.client ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lastNameList.add(resultSet.getString("O_LNAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastNameList;
    }

    public static List<String> getDelivery() {
        ResultSet resultSet;
        List<String> deliveryList = new ArrayList<>(0);

        String select = "SELECT * FROM rppba.delivery ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                deliveryList.add(resultSet.getString("D_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveryList;
    }

    public static String getIdDelivery(String name) {
        ResultSet resultSet;
        String id = "";
        String select = "SELECT D_ID FROM delivery WHERE D_NAME = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getString("D_ID");
            } else {
                id = "";
            }
        } catch (SQLException e) {
            System.out.println("Not ID");
            e.printStackTrace();
        }
        return id;
    }

    public static boolean addFilling(String idProduct, String count) {
        String string = "";
        String max = "SELECT MAX(O_ID) m FROM rppba.orders";
        try {
            Statement preparedStatement1 = connection.createStatement();
            ResultSet maxId = preparedStatement1.executeQuery(max);
            while (maxId.next()) {
                string = maxId.getString("m");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        String insert = "INSERT INTO rppba.filling(O_ID, ID, F_KOL) VALUES (?, ?, ?) ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, string);
            preparedStatement.setString(2, idProduct);
            preparedStatement.setString(3, count);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<String> getOrders() {
        ResultSet resultSet;
        ArrayList<String> ordersList = new ArrayList<>(0);

        String select = "SELECT o.O_ID, o.O_COST, o.O_ADR, c.O_LNAME, d.D_NAME, s.S_NAME, o.DAT FROM rppba.orders o, rppba.client c, rppba.delivery d, rppba.states s WHERE o.D_ID = d.D_ID AND o.S_ID = s.ST_ID AND o.C_ID = c.C_ID";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getString("O_ID")).append(" ").
                        append(resultSet.getString("O_COST")).append(" ").
                        append(resultSet.getString("O_ADR")).append(" ").
                        append(resultSet.getString("O_LNAME")).append(" ").
                        append(resultSet.getString("D_NAME")).append(" ").
                        append(resultSet.getString("S_NAME")).append(" ").
                        append(resultSet.getString("DAT")).append(" ");
                ordersList.add(order.toString());
            }
        } catch (SQLException e) {

        }
        return ordersList;
    }

    public static boolean removeOrder(int orderNumber) {

        String delete = "DELETE FROM rppba.orders WHERE O_ID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, orderNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean shipOrders(int id){
        String update = "UPDATE rppba.orders SET S_ID=3 where O_ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean rezervOrders(int id){
        String updateOrders = "update rppba.orders set S_ID=2 where O_ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateOrders);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static ArrayList<String> getShipOrders() {
        ResultSet resultSet;
        ArrayList<String> ordersList = new ArrayList<>(0);

        String select = "SELECT o.O_ID, o.O_COST, o.O_ADR, c.O_LNAME, d.D_NAME, s.S_NAME, o.DAT FROM rppba.orders o, rppba.client c, rppba.delivery d, rppba.states s WHERE o.D_ID = d.D_ID AND o.S_ID = s.ST_ID AND o.C_ID = c.C_ID AND S_ID = 2";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getString("O_ID")).append(" ").
                        append(resultSet.getString("O_COST")).append(" ").
                        append(resultSet.getString("O_ADR")).append(" ").
                        append(resultSet.getString("O_LNAME")).append(" ").
                        append(resultSet.getString("D_NAME")).append(" ").
                        append(resultSet.getString("S_NAME")).append(" ").
                        append(resultSet.getString("DAT")).append(" ");
                ordersList.add(order.toString());
            }
        } catch (SQLException e) {

        }
        return ordersList;
    }

    public static ArrayList<String> getRezervOrders() {
        ResultSet resultSet;
        ArrayList<String> ordersList = new ArrayList<>(0);

        String select = "SELECT o.O_ID, o.O_COST, o.O_ADR, c.O_LNAME, d.D_NAME, s.S_NAME, o.DAT FROM rppba.orders o, rppba.client c, rppba.delivery d, rppba.states s WHERE o.D_ID = d.D_ID AND o.S_ID = s.ST_ID AND o.C_ID = c.C_ID AND DAT = current_date AND S_ID = 1";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getString("O_ID")).append(" ").
                        append(resultSet.getString("O_COST")).append(" ").
                        append(resultSet.getString("O_ADR")).append(" ").
                        append(resultSet.getString("O_LNAME")).append(" ").
                        append(resultSet.getString("D_NAME")).append(" ").
                        append(resultSet.getString("S_NAME")).append(" ").
                        append(resultSet.getString("DAT")).append(" ");
                ordersList.add(order.toString());
            }
        } catch (SQLException e) {

        }
        return ordersList;
    }
    //----------------------------
    /*public static List<String> getModels(String make) {
        ResultSet resultSet;
        List<String> models = new ArrayList<>(0);

        String select = "SELECT * FROM " + make + "_models";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder temp = new StringBuilder();
                temp.append(resultSet.getString("price")).append(" ").
                        append(resultSet.getString("model"));
                models.add(temp.toString());
            }
        } catch (SQLException e) {
        }
        return models;
    }

public static boolean addManager(String name, String surname, String login, String password) {
        String insertUser = "INSERT INTO course.users (account_type, login, password) VALUES (?, ?, ?)";
        String insertManager = "INSERT INTO course.managers (name, surname, login, password) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatementUser = connection.prepareStatement(insertUser);
            PreparedStatement preparedStatementManager = connection.prepareStatement(insertManager);
            preparedStatementUser.setString(1, "manager");
            preparedStatementUser.setString(2, login);
            preparedStatementUser.setString(3, password);
            preparedStatementManager.setString(1, name);
            preparedStatementManager.setString(2, surname);
            preparedStatementManager.setString(3, login);
            preparedStatementManager.setString(4, password);
            preparedStatementUser.executeUpdate();
            preparedStatementManager.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static ArrayList<String> getOrders() {
        ResultSet resultSet;
        ArrayList<String> ordersList = new ArrayList<>(0);

        String select = "SELECT * FROM course.order";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getInt(1) + " " + resultSet.getString(2) +
                        " " + resultSet.getString(3) + " " + resultSet.getInt(5) + " " +
                        resultSet.getString(6) + " " + resultSet.getString(7) +
                        " " + resultSet.getString(4));
                ordersList.add(order.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    public static ArrayList<String> getOrderClient(String name, String surname) {
        ResultSet resultSet;
        ArrayList<String> ordersList = new ArrayList<>(0);

        String select = "SELECT * FROM course.order WHERE name=? and surname=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getString("make")).append(" ").
                        append(resultSet.getString("year")).append(" ").
                        append(resultSet.getString("price")).append(" ").
                        append(resultSet.getBoolean("order_accepted")).append(" ").
                        append(resultSet.getBoolean("order_completed")).append(" ").
                        append(resultSet.getString("color")).append(" ").
                        append(resultSet.getString("country")).append(" ").
                        append(resultSet.getString("insurance")).append(" ").
                        append(resultSet.getString("model"));
                ordersList.add(order.toString());
            }
        } catch (SQLException e) {

        }
        return ordersList;
    }

    public static ArrayList<String> getOrdersManager() {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT * FROM course.order";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getInt("number")).append(" ").
                        append(resultSet.getString("name")).append(" ").
                        append(resultSet.getString("surname")).append(" ").
                        append(resultSet.getString("make")).append(" ").
                        append(resultSet.getString("year")).append(" ").
                        append(resultSet.getString("price")).append(" ").
                        append(resultSet.getBoolean("order_accepted")).append(" ").
                        append(resultSet.getBoolean("order_completed")).append(" ").
                        append(resultSet.getString("color")).append(" ").
                        append(resultSet.getString("country")).append(" ").
                        append(resultSet.getString("insurance")).append(" ").
                        append(resultSet.getString("model"));
                list.add(order.toString());
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public static ArrayList<String> getSupplierOrder() {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT * FROM course.supplier_order";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getInt("id")).append(" ").
                        append(resultSet.getString("supplier")).append(" ").
                        append(resultSet.getString("make")).append(" ").
                        append(resultSet.getString("color")).append(" ").
                        append(resultSet.getInt("quantity")).append(" ").
                        append(resultSet.getString("model"));
                list.add(order.toString());
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public static ArrayList<String> getMakeStatistics() {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT make FROM course.order";
        double ravon = 0;
        double geely = 0;
        double haval = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("make").equalsIgnoreCase("haval")) haval++;
                if (resultSet.getString("make").equalsIgnoreCase("geely")) geely++;
                if (resultSet.getString("make").equalsIgnoreCase("ravon")) ravon++;
            }
            double ravonPart = ravon / (haval + ravon + geely);
            double geelyPart = geely / (haval + ravon + geely);
            double havalPart = haval / (haval + ravon + geely);
            list.add(Double.toString(ravonPart));
            list.add(Double.toString(geelyPart));
            list.add(Double.toString(havalPart));
        } catch (SQLException e) {
        }
        return list;
    }

    public static boolean addOrderClient(String order) {
        String[] orderDetails = order.split(" ", 12);
        String insert = "INSERT INTO course.order (make, year, name, surname, price, color, country, insurance, model)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, orderDetails[1]);
            preparedStatement.setString(2, orderDetails[2]);
            preparedStatement.setString(3, orderDetails[3]);
            preparedStatement.setString(4, orderDetails[4]);
            preparedStatement.setString(5, orderDetails[5]);
            preparedStatement.setString(6, orderDetails[6]);
            preparedStatement.setString(7, orderDetails[7]);
            preparedStatement.setString(8, orderDetails[8] + " " +
                    orderDetails[9] + " " + orderDetails[10]);
            preparedStatement.setString(9, orderDetails[11]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    public static boolean removeOrder(int orderNumber) {

        String delete = "DELETE FROM course.order WHERE number=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, orderNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean addClient(String name, String surname, String login, String password) {
        String insertUser = "INSERT INTO course.users (account_type, login, password) VALUES (?, ?, ?)";
        String insertClient = "INSERT INTO course.clients (name, surname, login, password) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatementUser = connection.prepareStatement(insertUser);
            PreparedStatement preparedStatementClient = connection.prepareStatement(insertClient);
            preparedStatementUser.setString(1, "client");
            preparedStatementUser.setString(2, login);
            preparedStatementUser.setString(3, password);
            preparedStatementClient.setString(1, name);
            preparedStatementClient.setString(2, surname);
            preparedStatementClient.setString(3, login);
            preparedStatementClient.setString(4, password);
            preparedStatementUser.executeUpdate();
            preparedStatementClient.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean addSupplier(String name, String coutry, String make) {
        String insert = "INSERT INTO course.supplier (country, make, name) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, coutry);
            preparedStatement.setString(2, make);
            preparedStatement.setString(3, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getClientData(String login, String password) {
        ResultSet resultSet;
        String list = new String();
        String select = "SELECT * FROM course.clients WHERE login=? and password=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list = resultSet.getString("name") + " " +
                        resultSet.getString("surname");
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static String getManagerData(String login, String password) {
        ResultSet resultSet;
        String list = new String();
        String select = "SELECT * FROM course.managers WHERE login=? and password=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list = resultSet.getString("name") + " " +
                        resultSet.getString("surname");
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static ArrayList<String> getInsurance() {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT * FROM course.insurance";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("price") + " " +
                        resultSet.getString("type"));
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public static ArrayList<String> getSupplier(String make) {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT * FROM course.supplier WHERE make=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, make);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public static ArrayList<String> getCountry(String make) {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);

        String select = "SELECT * FROM course.supplier WHERE make=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, make);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("country"));
            }

        } catch (SQLException e) {
        }
        return list;
    }

    public static boolean addInsurance(String price, String type) {
        String insert = "INSERT INTO course.insurance (type, price) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, type);
            preparedStatement.setInt(2, Integer.valueOf(price));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean markAccepted(int orderNumber) {
        String update = "UPDATE course.order SET order_accepted=1 where number=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1, orderNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean markCompleted(int orderNumber) {
        String update = "UPDATE course.order SET order_completed=1 where number=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1, orderNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;

    }*/
}