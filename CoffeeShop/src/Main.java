import javax.swing.SwingUtilities;

import View.CustomerLoginView;
import View.LoginView;
import View.StaffLoginView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            loginView.showLoginView(true);

            loginView.getCustomerButton().addActionListener(e -> {
                CustomerLoginView customerLoginView = new CustomerLoginView();
                customerLoginView.setVisible(true);
            });

            loginView.getStaffButton().addActionListener(e -> {
                StaffLoginView staffLoginView = new StaffLoginView();
                staffLoginView.setVisible(true);
            });
        });
    }
}
