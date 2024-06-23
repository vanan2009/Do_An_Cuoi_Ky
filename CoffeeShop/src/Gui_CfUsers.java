import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;

public class Gui_CfUsers extends JFrame{
    private JTextArea chatArea;
    private JTextField messageField;
    private PrintWriter writer;

    public Gui_CfUsers() {
        // Thiết lập JFrame
        setTitle("Coffee Shop");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1000, 50));
        headerPanel.setBackground(new Color(70, 130, 180));

        // Logo ở góc trái
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = resizeImageIcon("F:\\Downloads\\Java\\IntelliJ\\CoffeeShop\\img\\logoCoffee.jpg",70,70);
        logoLabel.setIcon(logoIcon);
        logoLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // Tạo panel cho các nút ở giữa
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(70, 130, 180)); // Màu nền cho panel nút
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5)); // Sử dụng FlowLayout để căn giữa các nút và có khoảng cách giữa chúng

        // Nút Menu
        JButton menuButton = new JButton("Menu");
        customizeButton(menuButton);
        buttonPanel.add(menuButton);

        // Thêm ActionListener vào nút Menu
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mở cửa sổ gui_menu khi nút Menu được nhấn
                new Gui_Menu();
                dispose();
            }
        });

        // Nút Liên hệ
        JButton contactButton = new JButton("Liên hệ");
        customizeButton(contactButton);
        buttonPanel.add(contactButton);

        // Thêm ActionListener vào nút Liên hệ
        contactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessageToServer("Yêu cầu hỗ trợ từ khách hàng"); // Gửi yêu cầu đến nhân viên
            }
        });

        // Thêm panel chứa các nút vào header
        headerPanel.add(buttonPanel, BorderLayout.CENTER);

        // Tạo phần nội dung chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Tạo JPanel chứa hình ảnh và mô tả
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 700));

        // Hình ảnh của quán cà phê
        JLabel cafeImageLabel = new JLabel();
        ImageIcon cafeImageIcon = resizeImageIcon("F:\\Downloads\\Java\\IntelliJ\\CoffeeShop\\img\\backGround.jpg", 1000, 700);
        cafeImageLabel.setIcon(cafeImageIcon);
        cafeImageLabel.setBounds(0, -85, 1000, 700);
        layeredPane.add(cafeImageLabel, Integer.valueOf(0)); // Đặt hình ảnh ở layer 0 (dưới cùng)

        // Panel chứa mô tả về quán cà phê
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setPreferredSize(new Dimension(100, 100));
        descriptionPanel.setBackground(new Color(240, 240, 240));

        // Bo viền góc tròn cho descriptionPanel
        Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
        int arcRadius = 15;
        Border roundedBorder = BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10));
        descriptionPanel.setBorder(BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createLineBorder(Color.WHITE)));


        // Phần mô tả về quán cà phê
        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setText("Mô tả về quán cà phê...\nĐây là một quán cà phê tuyệt vời với không gian thoải mái và cà phê ngon.");
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(new Color(240, 240, 240));
        descriptionPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);
        descriptionPanel.setBounds(70, 100, 220, 400);
        layeredPane.add(descriptionPanel, Integer.valueOf(1)); // Đặt descriptionPanel ở layer 1 (trên cùng)

        // Thêm descriptionPanel vào mainPanel
        mainPanel.add(layeredPane, BorderLayout.CENTER);

        // Thêm header và phần nội dung chính vào JFrame
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void sendMessageToServer(String message) {
        try {
            Socket socket = new Socket("localhost", 12345); // Kết nối tới localhost và port của Gui_CfNV
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(message);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    // Phương thức tùy chỉnh nút
    private void customizeButton(JButton button) {
        button.setPreferredSize(new Dimension(120, 40));
        button.setBorder(new LineBorder(Color.WHITE, 2));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(70, 130, 180));
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui_CfUsers().setVisible(true);
            }
        });
    }

}
