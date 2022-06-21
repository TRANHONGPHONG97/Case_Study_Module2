package vn.thphong.manager.services;

import vn.thphong.manager.model.OrderItem;
import vn.thphong.manager.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderItemService implements IOrderItemService {


    private final static String path = "data/order-item.csv";
    private static OrderItemService instance;

    private OrderItemService() {

    }

    public static OrderItemService getInstance() {
        if (instance == null) {
            instance = new OrderItemService();
        }
        return instance;
    }

    @Override
    public List<OrderItem> findAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        List<String> records = CSVUtils.readFile(path);
        for (String record : records) {
            orderItems.add(new OrderItem(record));
        }
        return orderItems;
    }

    @Override
    public void add(OrderItem newOrderItem) {
        List<OrderItem> orderItems = findAll();
        orderItems.add(newOrderItem);
        CSVUtils.writeFile(path, orderItems);
    }

    @Override
    public void update(OrderItem newOrderItem) {
        List<OrderItem> orderItems = findAll();
        CSVUtils.writeFile(path, orderItems);
    }

    @Override
    public OrderItem getOrderItemById(int id) {
        List<OrderItem> orderItems = findAll();
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getId() == id)
                return orderItem;
        }
        return null;
    }
}
