package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Item;
import Model.MenuModel;
import Controller.MenuController;

public class MenuView extends JFrame {
    private JPanel menuPanel;
    private JButton orderButton;

    public MenuView() {
        setTitle("Menu");
        setSize(500, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(new EmptyBorder(40, 20, 20, 20));

        JLabel menuLabel = new JLabel("MENU");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 24));
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(menuLabel);
        
        loadMenuItems();

        add(new JScrollPane(menuPanel));
        setVisible(true);
    }

    private void loadMenuItems() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=CoffeeShop";
        String user = "sa";
        String password = "123123";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT ItemName, Price FROM MenuItems")) {

            while (resultSet.next()) {
                String itemName = resultSet.getString("ItemName");
                double price = resultSet.getDouble("Price");

                JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JCheckBox checkBox = new JCheckBox(itemName + " - $" + price);
                itemPanel.add(checkBox);
                menuPanel.add(itemPanel);
            }

            setOrderButtonActionListener(e -> {
                List<String> selectedItems = new ArrayList<>();
                Component[] components = menuPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof JPanel) {
                        Component[] panelComponents = ((JPanel) component).getComponents();
                        for (Component panelComponent : panelComponents) {
                            if (panelComponent instanceof JCheckBox) {
                                JCheckBox checkBox = (JCheckBox) panelComponent;
                                if (checkBox.isSelected()) {
                                    selectedItems.add(checkBox.getText());
                                }
                            }
                        }
                    }
                }
                System.out.println("Selected items: " + selectedItems);
            });

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối database.");
            ex.printStackTrace();
        }
    }

    public void addMenuSection(String sectionTitle, List<Item> items) {
        JLabel sectionLabel = new JLabel(sectionTitle);
        sectionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        sectionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(sectionLabel);

        JPanel sectionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 50, 5, 50);

        for (Item item : items) {
            JCheckBox checkBox = new JCheckBox(item.getName());
            JLabel priceLabel = new JLabel(String.valueOf(item.getPrice()));

            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.WEST;
            sectionPanel.add(checkBox, gbc);

            gbc.gridx = 1;
            sectionPanel.add(priceLabel, gbc);

            gbc.gridx = 2;
            gbc.anchor = GridBagConstraints.EAST;
            sectionPanel.add(new JPanel(), gbc); // Panel rỗng để căn chỉnh layout
        }

        menuPanel.add(sectionPanel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 40)));
    }

    public void setOrderButtonActionListener(ActionListener actionListener) {
        if (orderButton == null) {
            orderButton = new JButton("Order");
            orderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuPanel.add(orderButton);
        }
        orderButton.addActionListener(actionListener);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuView::new);
    }
}
