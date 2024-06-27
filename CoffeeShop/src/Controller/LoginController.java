package Controller;

import javax.swing.*;

import View.CustomerLoginView;
import View.LoginView;
import View.StaffLoginView;

public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;

        view.getCustomerButton().addActionListener(e -> openCustomerLogin());
        view.getStaffButton().addActionListener(e -> openStaffLogin());
    }

    private void openCustomerLogin() {
        SwingUtilities.invokeLater(() -> new CustomerLoginView().setVisible(true));
    }

    private void openStaffLogin() {
        SwingUtilities.invokeLater(() -> new StaffLoginView().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView view = new LoginView();
            new LoginController(view);
            view.setVisible(true);
        });
    }
}