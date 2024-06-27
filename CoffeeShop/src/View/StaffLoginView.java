package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StaffLoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public StaffLoginView() {
        setTitle("Nhân viên đăng nhập");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Tài khoản:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Mật khẩu:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Đăng nhập");
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Kết nối CSDL và xác thực đăng nhập
                try (Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=CoffeeShop;user=sa;password=123123;encrypt=true;trustServerCertificate=true")) {
                    String sql = "SELECT * FROM Staff WHERE Username = ? AND Password = ?";
                    try (PreparedStatement stmt = con.prepareStatement(sql)) {
                        stmt.setString(1, username);
                        stmt.setString(2, password);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            String role = rs.getString("Role");
                            if ("admin".equals(role)) {
                                // Đăng nhập thành công với vai trò admin
                                JOptionPane.showMessageDialog(StaffLoginView.this, "Đăng nhập thành công với vai trò Admin!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                new AdminView().setVisible(true);
                            } else {
                                // Đăng nhập thành công với vai trò nhân viên
                                JOptionPane.showMessageDialog(StaffLoginView.this, "Đăng nhập thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                new StaffView(null).setVisible(true);
                            }
                            dispose(); // Đóng cửa sổ đăng nhập sau khi đăng nhập thành công
                        } else {
                            // Đăng nhập thất bại
                            JOptionPane.showMessageDialog(StaffLoginView.this, "Tài khoản hoặc mật khẩu không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(StaffLoginView.this, "Lỗi kết nối CSDL: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        // Thiết lập kết nối JDBC trước khi hiển thị cửa sổ đăng nhập
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không tìm thấy JDBC Driver!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Hiển thị cửa sổ đăng nhập
        SwingUtilities.invokeLater(() -> {
            StaffLoginView loginView = new StaffLoginView();
            loginView.setVisible(true);
        });
    }
}
