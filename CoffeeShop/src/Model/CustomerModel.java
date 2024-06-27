package Model;

public class CustomerModel {
    private String name;
    private String phoneNumber;
    private int tableNumber;

    public CustomerModel(String name, String phoneNumber, int tableNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.tableNumber = tableNumber;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public int getTableNumber() { return tableNumber; }
    public void setTableNumber(int tableNumber) { this.tableNumber = tableNumber; }

    // Validate phone number
    public boolean isValidPhoneNumber() {
        return phoneNumber.matches("\\d{10}");
    }
}
