package Controller;

import java.util.List;

import View.Receipt;
import Model.OrderModel;

public class ReceiptController {
    private Receipt view;
    private OrderModel model;

    public ReceiptController(Receipt view, OrderModel model) {
        this.view = view;
        this.model = model;
        initialize();
    }

    private void initialize() {
        List<String> items = model.getMenuItems();
        List<Double> prices = model.getMenuPrices();

        view = new Receipt(items, prices);
    }
}
