package server;

import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) {
        final int portNumber = 12345;
        System.out.println("Khởi động máy chủ trên cổng " + portNumber);

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Chờ khách hàng kết nối...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Máy khách được kết nối: " + socket);
                // Handle client connection here
                new Thread(new HandleClient(socket)).start();
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi chạy máy chủ: " + e.getMessage());
        }
    }

    private static class HandleClient implements Runnable {
        private Socket clientSocket;

        public HandleClient(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Nhận được tin nhắn từ khách hàng: " + message);
                    // Process received message here if needed
                    writer.println("Máy chủ nhận được: " + message);
                }
            } catch (IOException e) {
                System.err.println("Khách hàng xử lý lỗi: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
