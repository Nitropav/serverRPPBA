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

    public static boolean updateLoyalty(String ID, String sale, String boundary) {
        String updateProd = "UPDATE rppba.loyalty SET L_SALE = ?, L_BOUNDARY = ? WHERE L_ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateProd);
            preparedStatement.setString(1, sale);
            preparedStatement.setString(2, boundary);
            preparedStatement.setInt(3, Integer.parseInt(ID));
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

    public static boolean addFilling(String idProduct, int count) {
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
        ResultSet string1;
        int kol = 0;
        String kolvo = "SELECT R_KOL FROM rppba.residual WHERE ID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(kolvo);
            statement.setString(1, idProduct);
            string1 = statement.executeQuery();
            while (string1.next()) {
                kol = string1.getInt("R_KOL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        if (count <= kol) {
            String insert = "INSERT INTO rppba.filling(O_ID, ID, F_KOL) VALUES (?, ?, ?) ";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insert);
                preparedStatement.setString(1, string);
                preparedStatement.setString(2, idProduct);
                preparedStatement.setInt(3, count);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
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

    public static ArrayList<String> getProductionInfa(){
        ResultSet resultSet;
        ArrayList<String> ordersList = new ArrayList<>(0);
        String select = "select f.o_id, p.model, sum(f.f_kol) sc from filling f, product p where f.id=p.id group by p.model";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getString("O_ID")).append(" ").
                        append(resultSet.getString("MODEL")).append(" ").
                        append(resultSet.getString("SC")).append(" ");
                ordersList.add(order.toString());
            }
        } catch (SQLException e) {
        }
        return ordersList;

    }

    public static boolean shipOrders(int id) {
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

    public static boolean rezervOrders(int id) {
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

    public static boolean notRezervOrders(int id) {
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

    public static List<String> getRezervOrders() {
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

    public static ArrayList<String> getClientInfa(){
        ResultSet resultSet;
        ArrayList<String> clientList = new ArrayList<>(0);

        String select = "select c.c_id, c.o_fname, c.o_lname, c.o_tel, c.o_mail, l.L_SALE, l.L_NAME from client c, loyalty l where c.l_id=l.l_id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder client = new StringBuilder();
                client.append(resultSet.getString("C_ID")).append(" ").
                        append(resultSet.getString("O_FNAME")).append(" ").
                        append(resultSet.getString("O_LNAME")).append(" ").
                        append(resultSet.getString("O_TEL")).append(" ").
                        append(resultSet.getString("O_MAIL")).append(" ").
                        append(resultSet.getString("L_NAME")).append(" ").
                        append(resultSet.getString("L_SALE")).append(" ");
                clientList.add(client.toString());
            }
        } catch (SQLException e) {

        }
        return clientList;
    }

    public static ArrayList<String> getProductInfa(){
        ResultSet resultSet;
        ArrayList<String> productList = new ArrayList<>(0);

        String select = "select p.ID, p.MODEL, p.PRICE, p.TYP, p.SHELL, p.KEREL from rppba.product p";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder client = new StringBuilder();
                client.append(resultSet.getString("ID")).append(" ").
                        append(resultSet.getString("MODEL")).append(" ").
                        append(resultSet.getString("PRICE")).append(" ").
                        append(resultSet.getString("TYP")).append(" ").
                        append(resultSet.getString("SHELL")).append(" ").
                        append(resultSet.getString("KEREL")).append(" ");
                productList.add(client.toString());
            }
        } catch (SQLException e) {

        }
        return productList;
    }

    public static ArrayList<String> getLoyaltyAll(){
        ResultSet resultSet;
        ArrayList<String> loyaltyList = new ArrayList<>(0);

        String select = "select l.L_ID, l.L_NAME, l.L_SALE, l.L_BOUNDARY from rppba.loyalty l";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder client = new StringBuilder();
                client.append(resultSet.getString("L_ID")).append(" ").
                        append(resultSet.getString("L_NAME")).append(" ").
                        append(resultSet.getString("L_SALE")).append(" ").
                        append(resultSet.getString("L_BOUNDARY")).append(" ");
                loyaltyList.add(client.toString());
            }
        } catch (SQLException e) {

        }
        return loyaltyList;
    }

    public static ArrayList<String> getResidualInformation(){
        ResultSet resultSet;
        ArrayList<String> residualList = new ArrayList<>(0);

        String select = "select p.model, r.R_KOL from product p, residual r where p.id=r.id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder residual = new StringBuilder();
                residual.append(resultSet.getString("MODEL")).append(" ").
                        append(resultSet.getString("R_KOL")).append(" ");
                residualList.add(residual.toString());
            }
        } catch (SQLException e) {

        }
        return residualList;
    }

    public static List<String> getDateResidual() {
        ResultSet resultSet;
        List<String> date = new ArrayList<>(0);

        String select = "SELECT DAT FROM s13dat WHERE D_KOD=1 AND DAT > current_date ";
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

    public static boolean addDateOfOrders(String date) {
        String update = "UPDATE rppba.orders SET DAT=? WHERE O_ID=(SELECT MAX(filling.O_ID) from rppba.filling)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, date);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean loyaltyUpdate(){
        String update = "UPDATE rppba.client SET l_id=l_id+1 WHERE l_id<5 AND c_id IN (SELECT t1.c_id from (SELECT c.c_id, c.l_id, l.l_boundary bo FROM client c, loyalty l WHERE c.l_id=l.l_id) t1, (SELECT c_id, SUM(o_cost) sc FROM rppba.orders WHERE MONTH(dat)=MONTH(date_add(current_date, interval -1 day)) AND YEAR(dat)=YEAR(current_date) GROUP BY c_id) t2 WHERE t1.c_id=t2.c_id AND t1.bo<t2.sc)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}