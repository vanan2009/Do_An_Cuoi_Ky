package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class Receipt extends JFrame {
    private JLabel titleLabel;
    private JPanel receiptPanel;
    private JButton paymentButton;

    public Receipt(List<String> items, List<Double> prices) {
        setTitle("Biên lai");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
        setupReceiptPanel(items, prices);
        addComponentsToFrame();

        setVisible(true);
    }

    private void initializeComponents() {
        titleLabel = new JLabel("BIÊN LAI ĐƠN HÀNG");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        receiptPanel = new JPanel();
        receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
        receiptPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        paymentButton = new JButton("Thanh Toán");
        paymentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void setupReceiptPanel(List<String> items, List<Double> prices) {
        receiptPanel.add(titleLabel);
        receiptPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Display item and price information
        for (int i = 0; i < items.size(); i++) {
            JLabel itemLabel = new JLabel(items.get(i) + " - " + prices.get(i));
            itemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            receiptPanel.add(itemLabel);
        }

        receiptPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Calculate total price
        JLabel totalPriceLabel = new JLabel("Tổng thành tiền: " + calculateTotalPrice(prices));
        totalPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        receiptPanel.add(totalPriceLabel);

        receiptPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Payment button action
        paymentButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(Receipt.this, "Thanh toán thành công! Quay lại giao diện chính.");
            dispose(); // Close the receipt window after payment
        });
        receiptPanel.add(paymentButton);
    }

    private void addComponentsToFrame() {
        add(new JScrollPane(receiptPanel));
    }

    private String calculateTotalPrice(List<Double> prices) {
        double total = 0;
        for (Double price : prices) {
            total += price;
        }
        return String.format("%.2f đ", total); // Format total price
    }
}
