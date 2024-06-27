package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AdminView extends JFrame {
    private JButton addButton, fixButton, deleteButton;

    public AdminView() {
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
        ImageIcon logoIcon = resizeImageIcon("C:\\Users\\dwtdwt\\eclipse-workspace\\JavaCK\\src\\main\\java\\img\\logoCoffee.jpg", 70, 70);
        logoLabel.setIcon(logoIcon);
        logoLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // Tạo panel cho các nút ở giữa
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(70, 130, 180));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5));

        // Nút Thêm
        addButton = new JButton("Thêm");
        customizeButton(addButton);
        buttonPanel.add(addButton);

        // Nút Sửa
        fixButton = new JButton("Sửa");
        customizeButton(fixButton);
        buttonPanel.add(fixButton);

        // Nút Xóa
        deleteButton = new JButton("Xóa");
        customizeButton(deleteButton);
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
        layeredPane.add(cafeImageLabel, Integer.valueOf(0));

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

    private void customizeButton(JButton button) {
        button.setPreferredSize(new Dimension(120, 40));
        button.setBorder(new LineBorder(Color.WHITE, 2));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(70, 130, 180));
        button.setFocusPainted(false);
    }

    public void addAddButtonActionListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addFixButtonActionListener(ActionListener listener) {
        fixButton.addActionListener(listener);
    }

    public void addDeleteButtonActionListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void showAddEmployeeDialog(ActionListener actionListener) {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField fullNameField = new JTextField();
        JTextField phoneNumberField = new JTextField();
        JTextField roleField = new JTextField();

        Object[] message = {
                "Username:", usernameField,
                "Password:", passwordField,
                "Tên nhân viên:", fullNameField,
                "Số điện thoại:", phoneNumberField,
                "Role:", roleField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Thêm nhân viên", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            actionListener.actionPerformed(new ActionEvent(
                    new Object[]{usernameField.getText(), new String(passwordField.getPassword()), fullNameField.getText(), phoneNumberField.getText(), roleField.getText()},
                    ActionEvent.ACTION_PERFORMED, "addEmployee"
            ));
        }
    }


    public void showFixEmployeeDialog(ActionListener actionListener) {
        JTextField usernameField = new JTextField();
        JPasswordField newPasswordField = new JPasswordField();

        Object[] message = {
                "Username:", usernameField,
                "Mật khẩu mới:", newPasswordField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Sửa thông tin nhân viên", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            actionListener.actionPerformed(new ActionEvent(
                    new Object[]{usernameField.getText(), new String(newPasswordField.getPassword())},
                    ActionEvent.ACTION_PERFORMED, "fixEmployee"
            ));
        }
    }


    public void showDeleteEmployeeDialog(ActionListener actionListener) {
        JTextField usernameField = new JTextField();

        Object[] message = {
                "Username:", usernameField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Xóa nhân viên", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            actionListener.actionPerformed(new ActionEvent(
                    usernameField.getText(),
                    ActionEvent.ACTION_PERFORMED, "deleteEmployee"
            ));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Tạo và hiển thị AdminView
                AdminView adminView = new AdminView();
                adminView.setVisible(true);

                // Đăng ký các ActionListener cho các button
                adminView.addAddButtonActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        adminView.showAddEmployeeDialog(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Xử lý khi người dùng thêm nhân viên
                                Object[] data = (Object[]) e.getSource();
                                String username = (String) data[0];
                                String password = (String) data[1];
                                String fullName = (String) data[2];
                                String phoneNumber = (String) data[3];
                                String role = (String) data[4];

                                // Thực hiện thêm nhân viên vào cơ sở dữ liệu hoặc xử lý khác
                                System.out.println("Thêm nhân viên:");
                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("Full Name: " + fullName);
                                System.out.println("Phone Number: " + phoneNumber);
                                System.out.println("Role: " + role);
                            }
                        });
                    }
                });

                adminView.addFixButtonActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        adminView.showFixEmployeeDialog(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Xử lý khi người dùng sửa thông tin nhân viên
                                Object[] data = (Object[]) e.getSource();
                                String username = (String) data[0];
                                String newPassword = (String) data[1];

                                // Thực hiện sửa thông tin nhân viên trong cơ sở dữ liệu hoặc xử lý khác
                                System.out.println("Sửa thông tin nhân viên:");
                                System.out.println("Username: " + username);
                                System.out.println("New Password: " + newPassword);
                            }
                        });
                    }
                });

                adminView.addDeleteButtonActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        adminView.showDeleteEmployeeDialog(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Xử lý khi người dùng xóa nhân viên
                                String username = (String) e.getSource();

                                // Thực hiện xóa nhân viên khỏi cơ sở dữ liệu hoặc xử lý khác
                                System.out.println("Xóa nhân viên:");
                                System.out.println("Username: " + username);
                            }
                        });
                    }
                });
            }
        });
    }
}
