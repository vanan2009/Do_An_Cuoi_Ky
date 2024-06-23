import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Gui_Menu extends JFrame {
    private List<String> selectedItems = new ArrayList<>();
    private List<String> selectedPrices = new ArrayList<>();

    public Gui_Menu() {
        // Thiết lập JFrame
        setTitle("Menu");
        setSize(500, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(new EmptyBorder(40, 20, 20, 20));

        JLabel menuLabel = new JLabel("MENU");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 24));
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(menuLabel);

        // Thêm khoảng cách
        menuPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Tiêu đề COFFEE
        JLabel coffeeLabel = new JLabel("Coffee:");
        coffeeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        coffeeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa tiêu đề
        menuPanel.add(coffeeLabel);

        // Các loại coffee với giá tiền và ô tích
        String[] coffees = {"Cà phê đen", "Bạc xỉu", "Cà phê muối", "Cà phê sữa"};
        String[] coffeePrices = {"20.000đ", "25.000đ", "30.000đ", "35.000đ"};
        menuPanel.add(createMenuItems(coffees, coffeePrices));

        // Thêm khoảng cách
        menuPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Tiêu đề Sinh tố
        JLabel smoothieLabel = new JLabel("Sinh tố:");
        smoothieLabel.setFont(new Font("Arial", Font.BOLD, 18));
        smoothieLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa tiêu đề
        menuPanel.add(smoothieLabel);

        // Các loại sinh tố với giá tiền và ô tích
        String[] smoothies = {"Sinh tố bơ", "Sinh tố xoài", "Sinh tố cam", "Sinh tố mít"};
        String[] smoothiePrices = {"30.000đ", "35.000đ", "30.000đ", "35.000đ"};
        menuPanel.add(createMenuItems(smoothies, smoothiePrices));

        // Thêm khoảng cách
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Tiêu đề các loại thức uống khác
        JLabel otherDrinkLabel = new JLabel("Các loại thức uống khác:");
        otherDrinkLabel.setFont(new Font("Arial", Font.BOLD, 18));
        otherDrinkLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa tiêu đề
        menuPanel.add(otherDrinkLabel);

        // Các loại thức uống khác với giá tiền và ô tích
        String[] otherDrinks = {"Bánh quy", "Bánh kem", "Bánh táo", "Siro dâu"};
        String[] otherDrinkPrices = {"10.000đ", "15.000đ", "20.000đ", "15.000đ"};
        menuPanel.add(createMenuItems(otherDrinks, otherDrinkPrices));

        // Thêm nút Order
        JButton orderButton = new JButton("Order");
        orderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedItems.isEmpty()) {
                    JOptionPane.showMessageDialog(Gui_Menu.this, "Quý khách vui lòng chọn món!");
                } else {
                    int choice = JOptionPane.showConfirmDialog(Gui_Menu.this,
                            "Quý khách có xác nhận order không ạ?",
                            "Xác nhận đơn hàng",
                            JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        showReceipt();
                    } else {
                        selectedItems.clear(); // Đặt lại danh sách đã chọn
                        selectedPrices.clear(); // Đặt lại danh sách giá tiền đã chọn
                    }
                }
            }
        });
        menuPanel.add(orderButton);

        add(new JScrollPane(menuPanel));
        setVisible(true);
    }

    private JPanel createMenuItems(String[] items, String[] prices) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 50, 5, 50);

        for (int i = 0; i < items.length; i++) {
            JCheckBox checkBox = new JCheckBox(items[i]);
            JLabel priceLabel = new JLabel(prices[i]);

            int finalI = i;
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkBox.isSelected()) {
                        selectedItems.add(items[finalI]);
                        selectedPrices.add(prices[finalI]);
                    } else {
                        selectedItems.remove(items[finalI]);
                        selectedPrices.remove(prices[finalI]);
                    }
                }
            });

            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.WEST;
            panel.add(checkBox, gbc);

            gbc.gridx = 1;
            panel.add(priceLabel, gbc);

            gbc.gridx = 2;
            gbc.anchor = GridBagConstraints.EAST;
            panel.add(new JPanel(), gbc); // Ô trống để căn chỉnh layout
        }

        return panel;
    }

    private void showReceipt() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < selectedItems.size(); i++) {
            sb.append(selectedItems.get(i)).append(" - ").append(selectedPrices.get(i)).append("\n");
        }
        sb.append("Tổng thành tiền: ").append(calculateTotal()).append("đ");

        Gui_Order orderWindow = new Gui_Order(sb.toString());
        orderWindow.setVisible(true);
        // Không đóng cửa sổ menu ở đây, vì sẽ được đóng khi xác nhận đơn hàng trong Gui_Order
    }

    private String calculateTotal() {
        int total = 0;
        for (String price : selectedPrices) {
            String numericString = price.replaceAll("[^\\d]", ""); // Lấy phần số từ chuỗi giá tiền
            total += Integer.parseInt(numericString);
        }
        return String.valueOf(total);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui_Menu();
            }
        });
    }
}
