package Controller;

import Model.Item;
import Model.MenuModel;
import View.MenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MenuController {
    private MenuModel model;
    private MenuView view;

    public MenuController(MenuModel model, MenuView view) {
        this.model = model;
        this.view = view;

        // Set listener for order button
        view.setOrderButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOrder();
            }
        });

        // Load menu items from model and display on view
        try {
            List<Item> coffeeItems = model.getItemsByCategory("Coffee");
            List<Item> smoothieItems = model.getItemsByCategory("Smoothie");
            List<Item> otherDrinkItems = model.getItemsByCategory("Other Drinks");

            view.addMenuSection("Coffee", coffeeItems);
            view.addMenuSection("Smoothie", smoothieItems);
            view.addMenuSection("Other Drinks", otherDrinkItems);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error loading menu items from database.");
            ex.printStackTrace();
        }
    }

    public void handleOrder() {
        // Implement order handling logic here
        if (model.getSelectedItems().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Quý khách vui lòng chọn món!");
        } else {
            int choice = JOptionPane.showConfirmDialog(view,
                    "Quý khách có xác nhận order không ạ?",
                    "Xác nhận đơn hàng",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                showReceipt();
            } else {
                model.getSelectedItems().clear();
            }
        }
    }

    private void showReceipt() {
        StringBuilder sb = new StringBuilder();
        for (Item item : model.getSelectedItems()) {
            sb.append(item.getName()).append(" - ").append(item.getPrice()).append("\n");
        }
        sb.append("Tổng thành tiền: ").append(model.calculateTotal()).append("đ");

        JOptionPane.showMessageDialog(view, sb.toString());
    }
}
