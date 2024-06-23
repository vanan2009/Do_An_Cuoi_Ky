import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Gui_Login extends JFrame {
    public Gui_Login() {
        // Thiết lập JFrame
        setTitle("Đăng nhập");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo panel chính với hình nền
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Đọc hình ảnh từ đường dẫn cụ thể
                    Image backgroundImage = ImageIO.read(new File("F:\\Downloads\\Java\\IntelliJ\\CoffeeShop\\img\\loginImg.jpg"));
                    // Vẽ hình ảnh nền từ góc (0,0) của JPanel mainPanel
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(500, 350); // Kích thước của JPanel mainPanel
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setOpaque(false); // Đặt mainPanel trong suốt để hình nền có thể hiển thị qua


        // Tiêu đề
        JLabel titleLabel = new JLabel("Chào mừng đến với Coffee Shop");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.WHITE); // Đổi màu chữ thành trắng để nổi bật trên nền

        // Thêm khoảng cách trước tiêu đề
        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        mainPanel.add(titleLabel);

        // Thêm khoảng cách sau tiêu đề
        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Tạo panel chứa các button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Đặt buttonPanel trong suốt để hình nền có thể hiển thị qua
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 0)); // Giữa các button có khoảng cách 40px

        // Tạo button Khách hàng
        JButton customerButton = new JButton("Khách hàng");
        customerButton.setPreferredSize(new Dimension(160, 50));
        customerButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Thiết lập font cho button
        customerButton.setForeground(Color.ORANGE); // Đổi màu chữ thành trắng
        customerButton.setBorderPainted(true);
        customerButton.setFocusPainted(false); // Loại bỏ đường viền khi focus
        customerButton.setContentAreaFilled(false); // Loại bỏ phần nền của button
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mở cửa sổ gui_menu khi nút Khách hàng được nhấn
                new Gui_CfUsers().setVisible(true);
                dispose(); // Đóng cửa sổ login
            }
        });
        buttonPanel.add(customerButton);

        // Tạo button Nhân viên
        JButton staffButton = new JButton("Nhân viên");
        staffButton.setPreferredSize(new Dimension(120, 50));
        staffButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Thiết lập font cho button
        staffButton.setForeground(Color.ORANGE); // Đổi màu chữ thành trắng
        staffButton.setBorderPainted(true);
        staffButton.setFocusPainted(false); // Loại bỏ đường viền khi focus
        staffButton.setContentAreaFilled(false); // Loại bỏ phần nền của button
        staffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mở cửa sổ gui_cf khi nút Nhân viên được nhấn
                String orderDetails = null;
                new Gui_CfNV(orderDetails).setVisible(true);
                dispose(); // Đóng cửa sổ login
            }
        });
        buttonPanel.add(staffButton);

        // Thêm panel chứa button vào panel chính
        mainPanel.add(buttonPanel);

        // Thêm panel chính vào JFrame
        add(mainPanel);

        // Hiển thị JFrame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui_Login();
            }
        });
    }
}
