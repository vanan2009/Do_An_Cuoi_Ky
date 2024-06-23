import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Gui_Order extends JFrame {
    private JTextArea orderTextArea;

    public Gui_Order(String orderDetails) {
        setTitle("Chi tiết đơn hàng");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Thông tin đơn hàng");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        orderTextArea = new JTextArea();
        orderTextArea.setEditable(false);
        orderTextArea.setText(orderDetails);
        JScrollPane scrollPane = new JScrollPane(orderTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton confirmButton = new JButton("Xác Nhận");
        confirmButton.addActionListener(e -> {
            // Xử lý khi nhân viên xác nhận đơn hàng
            JOptionPane.showMessageDialog(Gui_Order.this, "Đã xác nhận đơn hàng!");
            dispose(); // Đóng cửa sổ Gui_Order sau khi xác nhận
            new Gui_CfNV(orderDetails).setVisible(true); // Mở Gui_CfNV với thông tin đơn hàng
        });
        buttonPanel.add(confirmButton);

        JButton closeButton = new JButton("Đóng");
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}
