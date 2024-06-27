package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    private static final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;databaseName=CoffeeShop";
    private static final String DATABASE_USER = "sa";
    private static final String DATABASE_PASSWORD = "123123";

    public List<String> getMenuItems() {
        List<String> items = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT ItemName FROM MenuItems")) {

            while (resultSet.next()) {
                items.add(resultSet.getString("ItemName"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }

    public List<Double> getMenuPrices() {
        List<Double> prices = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT Price FROM MenuItems")) {

            while (resultSet.next()) {
                prices.add(resultSet.getDouble("Price"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return prices;
    }
}

