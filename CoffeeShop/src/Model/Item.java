package Model;

public class Item {
    private String name;
    private String category;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Item(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "Tên mặt hàng='" + name + '\'' +
                ", Loại='" + category + '\'' +
                ", Giá tiền=" + price +
                '}';
    }
}
