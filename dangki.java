package Sign_Up;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Sign_In.dangnhap;

public class dangki extends JFrame {

    public dangki() {

        JFrame jf = new JFrame("Đăng Ký");
        // Title
        ImageIcon icon = new ImageIcon();

        JLabel jlten = new JLabel("ĐĂNG KÍ ");
        jlten.setBounds(260, 20, 300, 40);
        jlten.setForeground(Color.RED);
        jlten.setFont(new Font("Times New Roman", Font.BOLD, 24));

        JPanel jptao = new JPanel();
        jptao.setBackground(Color.ORANGE);
        jptao.setBounds(150, 20, 300, 50);
        jptao.add(jlten);

        // Username label
        JLabel jluser = new JLabel("Tên Người Dùng");
        jluser.setBounds(120, 100, 150, 20);
        jluser.setForeground(Color.WHITE);
        jluser.setFont(new Font("Times New Roman", Font.BOLD, 18));

        JPanel jpuser = new JPanel();
        jpuser.setBackground(Color.GRAY);
        jpuser.setBounds(120, 120, 150, 30);
        jpuser.add(jluser);

        // Password label
        JLabel jlpass = new JLabel("Mật Khẩu");
        jlpass.setBounds(120, 160, 150, 20);
        jlpass.setForeground(Color.WHITE);
        jlpass.setFont(new Font("Times New Roman", Font.BOLD, 18));

        JPanel jppass = new JPanel();
        jppass.setBackground(Color.GRAY);
        jppass.setBounds(120, 180, 150, 30);
        jppass.add(jlpass);

        // Confirm password label
        JLabel jlConfirmPass = new JLabel("Xác Nhận MậtKhẩu");
        jlConfirmPass.setBounds(120, 220, 200, 20);
        jlConfirmPass.setForeground(Color.WHITE);
        jlConfirmPass.setFont(new Font("Times New Roman", Font.BOLD, 18));

        JPanel jpConfirmPass = new JPanel();
        jpConfirmPass.setBackground(Color.GRAY);
        jpConfirmPass.setBounds(120, 240, 200, 30);
        jpConfirmPass.add(jlConfirmPass);

        // Email label
        JLabel jlEmail = new JLabel("Email");
        jlEmail.setBounds(120, 280, 150, 20);
        jlEmail.setForeground(Color.WHITE);
        jlEmail.setFont(new Font("Times New Roman", Font.BOLD, 18));

        JPanel jpEmail = new JPanel();
        jpEmail.setBackground(Color.GRAY);
        jpEmail.setBounds(120, 300, 150, 30);
        jpEmail.add(jlEmail);

        // Username text field
        JTextField jtuser = new JTextField();
        jtuser.setBounds(300, 120, 150, 30);
        jtuser.setBackground(Color.WHITE);

        // Password field
        JPasswordField jppasss = new JPasswordField();
        jppasss.setBounds(300, 180, 150, 30);
        jppasss.setBackground(Color.WHITE);

        // Confirm password field
        JPasswordField jpConfirmPassField = new JPasswordField();
        jpConfirmPassField.setBounds(300, 240, 150, 30);
        jpConfirmPassField.setBackground(Color.WHITE);

        // Email text field
        JTextField jtEmail = new JTextField();
        jtEmail.setBounds(300, 300, 150, 30);
        jtEmail.setBackground(Color.WHITE);

        // Register button
        ImageIcon iconn = new ImageIcon();
        JButton jbRegister = new JButton("ĐĂNG KÍ");
        jbRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Check if password and confirm password match
                if (jppasss.getText().equals(jpConfirmPassField.getText())) {
                    // Registration successful
                    JFrame jfok = new JFrame("Successfully Registered");

                    JLabel Jlbok = new JLabel("Đăng Kí Thành Công");
                    Jlbok.setBounds(100, 20, 200, 30);
                    Jlbok.setBackground(Color.gray);
                    Jlbok.setFont(new Font("Times New Roman", Font.BOLD, 20));

                    JButton btnok = new JButton("OK");
                    btnok.setBounds(150, 90, 100, 30);
                    btnok.setBackground(Color.YELLOW);
                    btnok.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jfok.dispose();
                            jf.dispose();
                        }
                    });

                    jfok.add(Jlbok);
                    jfok.add(btnok);
                    jfok.setSize(400, 180);
                    jfok.setDefaultCloseOperation(jfok.EXIT_ON_CLOSE);
                    jfok.setLayout(null);
                    jfok.setVisible(true);
                    jfok.setLocationRelativeTo(null);
                } else {
                    // Registration error
                    JFrame rturn = new JFrame("Error!");

                    JLabel Jlberror = new JLabel("Mật Khẩu Không Khớp!");
                    Jlberror.setBounds(30, 20, 350, 30);
                    Jlberror.setBackground(Color.gray);
                    Jlberror.setFont(new Font("Times New Roman", Font.BOLD, 20));

                    JButton btnok1 = new JButton("OK");
                    btnok1.setBounds(150, 80, 100, 30);
                    btnok1.setBackground(Color.YELLOW);
                    btnok1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            rturn.dispose();
                        }
                    });

                    rturn.add(Jlberror);
                    rturn.add(btnok1);
                    rturn.setSize(400, 180);
                    rturn.setDefaultCloseOperation(rturn.EXIT_ON_CLOSE);
                    rturn.setLayout(null);
                    rturn.setVisible(true);
                    rturn.setLocationRelativeTo(null);
                }
            }
        });

        jbRegister.setIcon(iconn);
        jbRegister.setBounds(150, 350, 160, 35);
        jbRegister.setBackground(Color.pink);
        jbRegister.setFont(new Font("Times New Roman", Font.BOLD, 20));

        // Return button
        ImageIcon iconnn = new ImageIcon();
        JButton jbreturn = new JButton("TRỞ LẠI");
        jbreturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new dangnhap();
            }
        });

        jbreturn.setIcon(iconnn);
        jbreturn.setBounds(350, 350, 160, 35);
        jbreturn.setBackground(Color.pink);
        jbreturn.setFont(new Font("Times New Roman", Font.BOLD, 20));

        jf.add(jptao);
        jf.add(jpuser);
        jf.add(jtuser);
        jf.add(jppass);
        jf.add(jppasss);
        jf.add(jpConfirmPass);
        jf.add(jpConfirmPassField);
        jf.add(jpEmail);
        jf.add(jtEmail);
        jf.add(jbRegister);
        jf.add(jbreturn);

        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.setSize(600, 450);
        jf.setLayout(null);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new dangki();
    }
}
