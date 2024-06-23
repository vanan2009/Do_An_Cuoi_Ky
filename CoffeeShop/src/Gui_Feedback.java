import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Gui_Feedback extends JFrame {
    private JTextField feedbackField;
    private PrintWriter writer;

    public Gui_Feedback() {
        setTitle("Phản hồi từ nhân viên");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        feedbackField = new JTextField();
        mainPanel.add(feedbackField, BorderLayout.CENTER);

        JButton sendButton = new JButton("Gửi");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendFeedback(feedbackField.getText());
                feedbackField.setText("");
            }
        });
        mainPanel.add(sendButton, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void sendFeedback(String message) {
        try {
            Socket socket = new Socket("localhost", 12346); // Kết nối tới localhost và port của Gui_CfUsers
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(message);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui_Feedback().setVisible(true);
            }
        });
    }
}
