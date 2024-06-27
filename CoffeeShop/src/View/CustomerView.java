package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class CustomerView extends JFrame {
    private JTextField customerNameField;
    private JTextField phoneNumberField;
    private JTextField tableNumberField;
    private JButton menuButton;
    private JButton contactButton;
    private JButton saveButton;

    public CustomerView() {
        setTitle("Coffee Shop");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
        addComponentsToFrame();

        setVisible(true);
    }

    private void addComponentsToFrame() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1000, 50));
        headerPanel.setBackground(new Color(70, 130, 180));

        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = resizeImageIcon("F:\\Downloads\\Java\\IntelliJ\\CoffeeShop\\img\\logoCoffee.jpg", 70, 70);
        logoLabel.setIcon(logoIcon);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.add(logoLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(70, 130, 180));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5));

        menuButton = new JButton("Menu");
        customizeButton(menuButton);
        buttonPanel.add(menuButton);

        contactButton = new JButton("Liên hệ");
        customizeButton(contactButton);
        buttonPanel.add(contactButton);

        saveButton = new JButton("Lưu");
        customizeButton(saveButton);
        buttonPanel.add(saveButton);

        headerPanel.add(buttonPanel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 700));

        JLabel cafeImageLabel = new JLabel();
        ImageIcon cafeImageIcon = resizeImageIcon("F:\\Downloads\\Java\\IntelliJ\\CoffeeShop\\img\\backGround.jpg", 1000, 700);
        cafeImageLabel.setIcon(cafeImageIcon);
        cafeImageLabel.setBounds(0, -85, 1000, 700);
        layeredPane.add(cafeImageLabel, Integer.valueOf(0));

        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setPreferredSize(new Dimension(100, 70));
        descriptionPanel.setBackground(new Color(240, 240, 240));

        Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
        int arcRadius = 15;
        Border roundedBorder = BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10));
        descriptionPanel.setBorder(BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createLineBorder(Color.WHITE)));

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setText("Đây là một quán cà phê tuyệt vời với không gian thoải mái và cà phê ngon. Bạn có thể trải nghiệm cảm giác thư giản thoải mái ở nơi đây.");
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(new Color(240, 240, 240));
        descriptionPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);
        descriptionPanel.setBounds(70, 100, 220, 400);
        layeredPane.add(descriptionPanel, Integer.valueOf(1));

        mainPanel.add(layeredPane, BorderLayout.CENTER);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        loginPanel.setOpaque(false);

        customerNameField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        tableNumberField = new JTextField(20);

        loginPanel.add(new JLabel("Tên khách hàng:"));
        loginPanel.add(customerNameField);
        loginPanel.add(new JLabel("Số điện thoại:"));
        loginPanel.add(phoneNumberField);
        loginPanel.add(new JLabel("Số bàn:"));
        loginPanel.add(tableNumberField);

        mainPanel.add(loginPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    public JButton getMenuButton() {
        return menuButton;
    }

    public JButton getContactButton() {
        return contactButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTextField getCustomerNameField() {
        return customerNameField;
    }

    public JTextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public JTextField getTableNumberField() {
        return tableNumberField;
    }

    private void initializeComponents() {
        customerNameField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        tableNumberField = new JTextField(20);
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
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(70, 130, 180));
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerView view = new CustomerView();
            view.setVisible(true);
        });
    }
}
