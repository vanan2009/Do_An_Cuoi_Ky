package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoginView extends JFrame {
    private JButton customerButton;
    private JButton staffButton;

    public LoginView() {
        // Setup JFrame
        setTitle("Đăng nhập");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("F:\\Downloads\\Java\\IntelliJ\\CoffeeShop\\img\\loginImg.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(500, 350);
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Chào mừng đến với Coffee Shop");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.WHITE);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 0));

        customerButton = new JButton("Khách hàng");
        customerButton.setPreferredSize(new Dimension(160, 50));
        customerButton.setFont(new Font("Arial", Font.PLAIN, 18));
        customerButton.setForeground(Color.ORANGE);
        customerButton.setBorderPainted(true);
        customerButton.setFocusPainted(false);
        customerButton.setContentAreaFilled(false);
        buttonPanel.add(customerButton);

        staffButton = new JButton("Nhân viên");
        staffButton.setPreferredSize(new Dimension(120, 50));
        staffButton.setFont(new Font("Arial", Font.PLAIN, 18));
        staffButton.setForeground(Color.ORANGE);
        staffButton.setBorderPainted(true);
        staffButton.setFocusPainted(false);
        staffButton.setContentAreaFilled(false);
        buttonPanel.add(staffButton);

        mainPanel.add(buttonPanel);
        add(mainPanel);
    }

    public JButton getCustomerButton() {
        return customerButton;
    }

    public JButton getStaffButton() {
        return staffButton;
    }

    public void showLoginView(boolean isVisible) {
        // TODO Auto-generated method stub
        setVisible(isVisible);
    }

}
