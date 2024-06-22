package Sign_In;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class dangnhap extends JFrame 
{
	String user = "An";
	String pass = "2009";

	public dangnhap() 
	{

		JFrame jf = new JFrame("Login");
		// phần login
		ImageIcon icon = new ImageIcon();

		JLabel jlten = new JLabel("ĐĂNG NHẬP ");
		jlten.setBounds(260, 40, 300, 400);
		jlten.setForeground(Color.RED);
		jlten.setFont(new Font("Times New Roman", Font.BOLD, 24));

		JPanel jptao = new JPanel();
		jptao.setBackground(Color.ORANGE);
		jptao.setBounds(150, 20, 250, 50);

		// phần username
		JLabel jluser = new JLabel("Tên Người Dùng");
		jluser.setBounds(120, 120, 150, 20);
		jluser.setForeground(Color.WHITE);
		jluser.setFont(new Font("Times New Roman", Font.BOLD, 18));

		JPanel jpuser = new JPanel();
		jpuser.setBackground(Color.GRAY);
		jpuser.setBounds(120, 100, 150, 30);
		jpuser.add(jluser);

		// phần password
		JLabel jlpass = new JLabel("Mật Khẩu");
		jlpass.setBounds(120, 140, 150, 20);
		jlpass.setForeground(Color.WHITE);
		jlpass.setFont(new Font("Times New Roman", Font.BOLD, 18));

		JPanel jppass = new JPanel();
		jppass.setBackground(Color.GRAY);
		jppass.setBounds(120, 140, 150, 30);
		jppass.add(jlpass);

		// tao text user
		JTextField jtuser = new JTextField();
		jtuser.setBounds(300, 100, 150, 30);
		jtuser.setBackground(Color.WHITE);

		// taopass
		JPasswordField jppasss = new JPasswordField();
		jppasss.setBounds(300, 140, 150, 30);
		jppasss.setBackground(Color.WHITE);

		// taobutonĐangNhap

		ImageIcon iconn = new ImageIcon();
		JButton jblogin = new JButton("ĐĂNG NHẬP");

		jblogin.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// TODO Auto-generated method stub

				if (jtuser.getText().equalsIgnoreCase(user) && jppasss.getText().equals(pass)) 
				{

					JFrame jfok = new JFrame("Successsfully");

					JLabel Jlbok = new JLabel("Đăng Nhập Thành Công");
					Jlbok.setBounds(100, 20, 200, 30);
					Jlbok.setBackground(Color.gray);
					Jlbok.setFont(new Font("Times New Roman", Font.BOLD, 20));

					JButton btnok = new JButton("OK");
					btnok.setBounds(150, 90, 100, 30);
					btnok.setBackground(Color.YELLOW);
					btnok.addActionListener(new ActionListener() 
					{

						@Override
						public void actionPerformed(ActionEvent e) 
						{
							// TODO Auto-generated method stub
							jfok.dispose();
							jf.dispose();
							
							// jfok.disable();
						}
					});
					jfok.add(Jlbok);
					jfok.add(btnok);

					jfok.setSize(400, 180);
					jfok.setDefaultCloseOperation(jfok.EXIT_ON_CLOSE);
					jfok.setLayout(null);
					jfok.setVisible(true);
					jfok.setLocationRelativeTo(null);
					
					JFrame createFrame = new Create();
					createFrame.setVisible(true);
				} 
				else 
				{
					// create container "rturn" return container "Login"
					JFrame rturn = new JFrame("Error!");

					JLabel Jlberror = new JLabel("Xin Thử Lại!");
					Jlberror.setBounds(30, 20, 350, 30);
					Jlberror.setBackground(Color.gray);
					Jlberror.setFont(new Font("Times New Roman", Font.BOLD, 20));

					JButton btnok1 = new JButton("OK");
					btnok1.setBounds(150, 80, 100, 30);
					btnok1.setBackground(Color.YELLOW);
					btnok1.addActionListener(new ActionListener() 
					{

						@Override
						public void actionPerformed(ActionEvent arg0) 
						{
							// TODO Auto-generated method stub
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

		jblogin.setIcon(iconn);
		jblogin.setBounds(50, 200, 160, 35);
		jblogin.setBackground(Color.pink);
		jblogin.setFont(new Font("Times New Roman", Font.BOLD, 20));

		// buttonreturn
		ImageIcon iconnn = new ImageIcon();
		JButton jbreturn = new JButton("TRỞ LẠI");
		jbreturn.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				dispose();
				
			}
		});
		jbreturn.setIcon(iconnn);
		jbreturn.setBounds(380, 200, 160, 35);
		jbreturn.setBackground(Color.pink);
		jbreturn.setFont(new Font("Times New Roman", Font.BOLD, 20));

		jlten.setIcon(icon);
		jptao.add(jlten);
		jf.add(jptao);
		jf.add(jppass);
		jf.add(jpuser);
		jf.add(jtuser);
		jf.add(jppasss);
		jf.add(jblogin);
		jf.add(jbreturn);

		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setSize(600, 300);

		jf.setLayout(null);
		jf.setVisible(true);
		jf.setLocationRelativeTo(null);

	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new dangnhap();
	}

}

