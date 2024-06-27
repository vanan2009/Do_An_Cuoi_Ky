package Controller;

import Model.CustomerModel;
import Model.DBConnection;
import View.CustomerView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerController {
    private CustomerModel model;
    private CustomerView view;
    private Connection connection;

    public CustomerController(CustomerModel model, CustomerView view, Connection connection) {
        this.model = model;
        this.view = view;
        this.connection = connection;

        view.getSaveButton().addActionListener(e -> saveCustomerInformation());
        view.getMenuButton().addActionListener(e -> openMenu());
        view.getContactButton().addActionListener(e -> openContact());

        // Initialize database connection
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=CoffeeShop", "sa", "123123");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi kết nối database.");
            e.printStackTrace();
        }
    }

    private void openMenu() {
        JOptionPane.showMessageDialog(view, "Đang mở menu...");
    }

    private void openContact() {
        JOptionPane.showMessageDialog(view, "Đang mở liên hệ...");
    }

    private void saveCustomerInformation() {
        String name = view.getCustomerNameField().getText();
        String phoneNumber = view.getPhoneNumberField().getText();
        int tableNumber = Integer.parseInt(view.getTableNumberField().getText());

        // Validate phone number
        if (!model.isValidPhoneNumber()) {
            JOptionPane.showMessageDialog(view, "Số điện thoại không hợp lệ. Vui lòng nhập 10 chữ số.");
            return;
        }

        // Save customer information to database
        String sql = "INSERT INTO Customers (Name, PhoneNumber, TableNumber) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, phoneNumber);
            statement.setInt(3, tableNumber);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(view, "Thông tin khách hàng được lưu thành công..");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi lưu thông tin khách hàng.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerView view = new CustomerView();
            try {
                Connection connection = DBConnection.getConnection();
                CustomerModel model = new CustomerModel("", "", 0);
                new CustomerController(model, view, connection);
                view.setVisible(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(view, "Lỗi kết nối database.");
                e.printStackTrace();
            }
        });
    }
}
