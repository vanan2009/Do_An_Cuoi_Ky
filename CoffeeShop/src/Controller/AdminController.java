package Controller;

import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.AdminModel;
import View.AdminView;

public class AdminController {
    private AdminModel model;
    private AdminView view;

    public AdminController(AdminModel model, AdminView view) {
        this.model = model;
        this.view = view;

        view.addAddButtonActionListener(new AddButtonListener());
        view.addFixButtonActionListener(new FixButtonListener());
        view.addDeleteButtonActionListener(new DeleteButtonListener());
    }

    class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showAddEmployeeDialog(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    Object[] data = (Object[]) e.getSource();

                    switch (command) {
                        case "addEmployee":
                            String username = (String) data[0];
                            String password = (String) data[1];
                            String fullName = (String) data[2];
                            String phoneNumber = (String) data[3];
                            String role = (String) data[4];
                            model.addStaff(username, password, fullName, phoneNumber, role);
                            break;
                        case "addItem":
                            String name = (String) data[0];
                            String category = (String) data[1];
                            double price = Double.parseDouble((String) data[2]);
                            model.addItem(name, category, price);
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }


    class FixButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showFixEmployeeDialog(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    if (command.equals("fixEmployee")) {
                        Object[] data = (Object[]) e.getSource();
                        String username = (String) data[0];
                        String newPassword = (String) data[1];
                        model.updateStaff(username, newPassword);
                    } else if (command.equals("fixItem")) {
                        Object[] data = (Object[]) e.getSource();
                        String name = (String) data[0];
                        String newPrice = (String) data[1];
                        model.updateItem(name, Double.parseDouble(newPrice));
                    }
                }
            });
        }
    }

    class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showDeleteEmployeeDialog(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    if (command.equals("deleteEmployee")) {
                        String username = (String) e.getSource();
                        model.deleteStaff(username);
                    } else if (command.equals("deleteItem")) {
                        String name = (String) e.getSource();
                        model.deleteItem(name);
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdminModel model = new AdminModel();
                AdminView view = new AdminView();
                new AdminController(model, view);
                view.setVisible(true);
            }
        });
    }
}

