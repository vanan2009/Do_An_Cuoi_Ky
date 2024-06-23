import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class Receipt extends JFrame {
    public Receipt(List<String> items, List<String> prices) {
        setTitle("Biên lai");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel receiptPanel = new JPanel();
        receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
        receiptPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("BIÊN LAI ĐƠN HÀNG");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        receiptPanel.add(titleLabel);

        receiptPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Hiển thị thông tin món và giá tiền
        for (int i = 0; i < items.size(); i++) {
            JLabel itemLabel = new JLabel(items.get(i) + " - " + prices.get(i));
            itemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            receiptPanel.add(itemLabel);
        }

        receiptPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Tổng thành tiền
        JLabel totalPriceLabel = new JLabel("Tổng thành tiền: " + calculateTotalPrice(prices));
        totalPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        receiptPanel.add(totalPriceLabel);

        receiptPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Nút Thanh Toán
        JButton paymentButton = new JButton("Thanh Toán");
        paymentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        paymentButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(Receipt.this, "Thanh toán thành công! Quay lại giao diện chính.");
            new Gui_CfUsers();
            dispose();
        });
        receiptPanel.add(paymentButton);

        add(new JScrollPane(receiptPanel));
    }

    private String calculateTotalPrice(List<String> prices) {
        int total = 0;
        for (String price : prices) {
            // Chuyển đổi giá tiền từ String sang số để tính tổng
            total += Integer.parseInt(price.replaceAll("\\D+", ""));
        }
        return total + "đ";
    }
}
