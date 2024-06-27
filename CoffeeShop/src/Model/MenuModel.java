package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuModel {
    private Connection connection;
    private List<Item> menuItems;
    private List<Item> selectedItems;

    public MenuModel(Connection connection) {
        this.connection = connection;
        menuItems = new ArrayList<>();
        selectedItems = new ArrayList<>();
    }

    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM MenuItems";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String itemName = rs.getString("ItemName"); // Tên cột trong bảng MenuItems
                String category = rs.getString("Category"); // Tên cột trong bảng MenuItems
                double price = rs.getDouble("Price"); // Tên cột trong bảng MenuItems
                items.add(new Item(itemName, category, price));
            }
        }

        return items;
    }

    public void selectItem(Item item) {
        selectedItems.add(item);
    }

    public void deselectItem(Item item) {
        selectedItems.remove(item);
    }

    public List<Item> getSelectedItems() {
        return selectedItems;
    }

    public int calculateTotal() {
        int total = 0;
        for (Item item : selectedItems) {
            total += item.getPrice();
        }
        return total;
    }

    public List<Item> getItemsByCategory(String category) throws SQLException {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * FROM Items WHERE Category = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Name");
                double price = rs.getDouble("Price");
                Item item = new Item(name, price);
                itemList.add(item);
            }
        }
        return itemList;
    }
}
