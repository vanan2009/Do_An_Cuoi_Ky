package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class StaffView extends JFrame {
    private JButton takeButton;
    private JButton feedbackButton;
    private JTextArea orderTextArea;

    public StaffView(String orderDetails) {
        // Setup JFrame
        setTitle("Coffee Shop - Nhân viên");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1000, 50));
        headerPanel.setBackground(new Color(70, 130, 180));

        // Logo
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = resizeImageIcon("F:\\Downloads\\Java\\IntelliJ\\CoffeeShop\\img\\logoCoffee.jpg", 70, 70);
        logoLabel.setIcon(logoIcon);
        logoLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // Buttons in header
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(70, 130, 180));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5));

        takeButton = new JButton("Nhận Order");
        customizeButton(takeButton);
        buttonPanel.add(takeButton);

        feedbackButton = new JButton("Phản hồi");
        customizeButton(feedbackButton);
        buttonPanel.add(feedbackButton);

        headerPanel.add(buttonPanel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Image and description
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 700));

        JLabel cafeImageLabel = new JLabel();
        ImageIcon cafeImageIcon = resizeImageIcon("F:\\Downloads\\Java\\IntelliJ\\CoffeeShop\\img\\backGround.jpg", 1000, 700);
        cafeImageLabel.setIcon(cafeImageIcon);
        cafeImageLabel.setBounds(0, -85, 1000, 700);
        layeredPane.add(cafeImageLabel, Integer.valueOf(0));


        mainPanel.add(layeredPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        // Order details
        orderTextArea = new JTextArea(orderDetails);
        orderTextArea.setWrapStyleWord(true);
        orderTextArea.setLineWrap(true);
        orderTextArea.setEditable(false);
        orderTextArea.setBackground(new Color(240, 240, 240));

        setVisible(true);
    }

    public JButton getTakeButton() {
        return takeButton;
    }

    public JButton getFeedbackButton() {
        return feedbackButton;
    }

    private ImageIcon resizeImageIcon(String path, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(path));
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void customizeButton(JButton button) {
        button.setPreferredSize(new Dimension(120, 40));
        button.setBorder(new LineBorder(Color.WHITE, 2));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(70, 130, 180));
        button.setFocusPainted(false);
    }
}

