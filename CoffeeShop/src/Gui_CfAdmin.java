import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Gui_CfAdmin extends JFrame {
    public Gui_CfAdmin() {
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
        ImageIcon logoIcon = resizeImageIcon("F:\\Downloads\\Java\\IntelliJ\\CoffeeShop\\img\\logoCoffee.jpg", 70, 70);
        logoLabel.setIcon(logoIcon);
        logoLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // Tạo panel cho các nút ở giữa
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(70, 130, 180)); // Màu nền cho panel nút
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5)); // Sử dụng FlowLayout để căn giữa các nút và có khoảng cách giữa chúng

        // Nút Thêm
        JButton addButton = new JButton("Thêm");
        customizeButton(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddOptions();
            }
        });
        buttonPanel.add(addButton);

        // Nút Sửa
        JButton fixButton = new JButton("Sửa");
        customizeButton(fixButton);
        fixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFixOptions();
            }
        });
        buttonPanel.add(fixButton);

        // Nút Xóa
        JButton deleteButton = new JButton("Xóa");
        customizeButton(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeleteOptions();
            }
        });
        buttonPanel.add(deleteButton);

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

    private void showAddOptions() {
        String[] options = {"Thêm nhân viên", "Thêm món mới"};
        int choice = JOptionPane.showOptionDialog(this, "Chọn tùy chọn:", "Thêm",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            showAddEmployeeDialog();
        } else if (choice == 1) {
            showAddItemDialog();
        }
    }

    private void showFixOptions() {
        String[] options = {"Sửa thông tin nhân viên", "Sửa thông tin món"};
        int choice = JOptionPane.showOptionDialog(this, "Chọn tùy chọn:", "Sửa",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            showFixEmployeeDialog();
        } else if (choice == 1) {
            showFixItemDialog();
        }
    }

    private void showDeleteOptions() {
        String[] options = {"Xóa nhân viên", "Xóa món"};
        int choice = JOptionPane.showOptionDialog(this, "Chọn tùy chọn:", "Xóa",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            showDeleteEmployeeDialog();
        } else if (choice == 1) {
            showDeleteItemDialog();
        }
    }

    private void showAddEmployeeDialog() {
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JPasswordField();

        Object[] message = {
                "Username:", usernameField,
                "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Thêm nhân viên", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (!username.isEmpty() && !password.isEmpty()) {
                // Code để thêm nhân viên vào cơ sở dữ liệu hoặc hệ thống
                JOptionPane.showMessageDialog(this, "Nhân viên đã được thêm thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin!");
            }
        }
    }

    private void showAddItemDialog() {
        JTextField itemNameField = new JTextField();
        JTextField itemPriceField = new JTextField();

        Object[] message = {
                "Tên món:", itemNameField,
                "Đơn giá:", itemPriceField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Thêm món mới", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String itemName = itemNameField.getText();
            String itemPrice = itemPriceField.getText();
            if (!itemName.isEmpty() && !itemPrice.isEmpty()) {
                // Code để thêm món mới vào cơ sở dữ liệu hoặc hệ thống
                JOptionPane.showMessageDialog(this, "Món mới đã được thêm thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin!");
            }
        }
    }

    private void showFixEmployeeDialog() {
        JTextField usernameField = new JTextField();
        JTextField newPasswordField = new JPasswordField();

        Object[] message = {
                "Username:", usernameField,
                "Mật khẩu mới:", newPasswordField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Sửa thông tin nhân viên", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String newPassword = newPasswordField.getText();
            if (!username.isEmpty() && !newPassword.isEmpty()) {
                // Code để sửa thông tin nhân viên trong cơ sở dữ liệu hoặc hệ thống
                JOptionPane.showMessageDialog(this, "Thông tin nhân viên đã được sửa thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin!");
            }
        }
    }

    private void showFixItemDialog() {
        JTextField itemNameField = new JTextField();
        JTextField newItemPriceField = new JTextField();

        Object[] message = {
                "Tên món:", itemNameField,
                "Đơn giá mới:", newItemPriceField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Sửa thông tin món", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String itemName = itemNameField.getText();
            String newItemPrice = newItemPriceField.getText();
            if (!itemName.isEmpty() && !newItemPrice.isEmpty()) {
                // Code để sửa thông tin món trong cơ sở dữ liệu hoặc hệ thống
                JOptionPane.showMessageDialog(this, "Thông tin món đã được sửa thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin!");
            }
        }
    }

    private void showDeleteEmployeeDialog() {
        JTextField usernameField = new JTextField();

        Object[] message = {
                "Username:", usernameField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Xóa nhân viên", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            if (!username.isEmpty()) {
                // Code để xóa nhân viên trong cơ sở dữ liệu hoặc hệ thống
                JOptionPane.showMessageDialog(this, "Nhân viên đã được xóa thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin!");
            }
        }
    }

    private void showDeleteItemDialog() {
        JTextField itemNameField = new JTextField();

        Object[] message = {
                "Tên món:", itemNameField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Xóa món", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String itemName = itemNameField.getText();
            if (!itemName.isEmpty()) {
                // Code để xóa món trong cơ sở dữ liệu hoặc hệ thống
                JOptionPane.showMessageDialog(this, "Món đã được xóa thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui_CfAdmin().setVisible(true);
            }
        });
    }
}
