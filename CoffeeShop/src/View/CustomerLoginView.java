package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerLoginView extends JFrame {
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField tableField;

    public CustomerLoginView() {
        setTitle("Khách hàng đăng nhập");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Tên khách hàng:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Số điện thoại:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("Số bàn:"));
        tableField = new JTextField();
        panel.add(tableField);

        JButton loginButton = new JButton("Đăng nhập");
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String table = tableField.getText();

                if (name.isEmpty() || phone.isEmpty() || table.isEmpty() || !phone.matches("\\d{10}") || !table.matches("\\d+")) {
                    JOptionPane.showMessageDialog(CustomerLoginView.this, "Vui lòng nhập đầy đủ và chính xác thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Simulate opening a new window for the customer
                    new Thread(() -> new CustomerView().setVisible(true)).start();
                }
            }
        });

        add(panel);
    }
}

