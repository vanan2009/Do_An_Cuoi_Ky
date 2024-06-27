package Model;

import java.util.List;

public class ReceiptModel {
    private List<String> items;
    private List<String> prices;

    public ReceiptModel(List<String> items, List<String> prices) {
        this.items = items;
        this.prices = prices;
    }

    public List<String> getItems() {
        return items;
    }

    public List<String> getPrices() {
        return prices;
    }

    public String calculateTotalPrice() {
        int total = 0;
        for (String price : prices) {
            total += Integer.parseInt(price.replaceAll("\\D+", ""));
        }
        return total + "đ";
    }
}
