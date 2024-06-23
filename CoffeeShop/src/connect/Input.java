package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Input {
    public static Connection connecting() {
        Connection con = null;
        try {
            // Load JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish connection
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=CoffeeShop;user=sa;password=123123;encrypt=true;trustServerCertificate=true");
            System.out.println("Connected");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error Code: " + e.getErrorCode());
        }
        return con;
    }

    public static void disconnecting(Connection con) {
        try {
            if (con != null) {
                con.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error Code: " + e.getErrorCode());
        }
    }

    public static void main(String[] args) {
        Connection con = Input.connecting();
        Input.disconnecting(con);
    }
}
