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

public class Gui_CfNV extends JFrame{
    private JTextArea chatArea;
    private JTextField messageField;
    private PrintWriter writer;
    private JTextArea orderTextArea;

    public Gui_CfNV(String orderDetails) {
        // Thiết lập JFrame
        setTitle("Coffee Shop - Nhân viên");
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
        JButton takeButton = new JButton("Nhận Order");
        customizeButton(takeButton);
        buttonPanel.add(takeButton);

        // Thêm ActionListener vào nút Menu
        takeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mở cửa sổ gui_menu khi nút Menu được nhấn
                new Gui_Order(orderDetails).setVisible(true);
                dispose();
            }
        });

        // Nút Phản hồi
        JButton feedbackButton = new JButton("Phản hồi");
        customizeButton(feedbackButton);
        buttonPanel.add(feedbackButton);

        // Thêm ActionListener vào nút Phản hồi
        feedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFeedbackServer(); // Khởi động server phản hồi
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

        // Thêm descriptionPanel vào mainPanel
        mainPanel.add(layeredPane, BorderLayout.CENTER);

        // Thêm header và phần nội dung chính vào JFrame
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
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

    private static void startFeedbackServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Feedback Server is running on port 12345");

            // Chờ kết nối từ phía khách hàng
            Socket socket = serverSocket.accept();
            System.out.println("Feedback Client connected: " + socket);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Đọc và hiển thị thông báo phản hồi
            String feedback;
            if ((feedback = reader.readLine()) != null) {
                JOptionPane.showMessageDialog(null, "Phản hồi từ nhân viên:\n" + feedback);
            }

            // Đóng kết nối
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui_CfNV("Thông tin đơn hàng mẫu").setVisible(true);
            }
        });
    }

}
