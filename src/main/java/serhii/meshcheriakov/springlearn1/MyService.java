package serhii.meshcheriakov.springlearn1;

import org.jspecify.annotations.Nullable;
import serhii.meshcheriakov.springlearn1.model.Order;
import org.springframework.stereotype.Service;
import serhii.meshcheriakov.springlearn1.model.OrderStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MyService {
    private final Map<Long, Order> orders;
    private final AtomicLong idCounter;

    public MyService() {
        orders = new HashMap<>();
        idCounter = new AtomicLong();
    }

    public Order getOrderById(Long id) {
        if (!orders.containsKey(id)) {
            throw new NoSuchElementException("Order not found! Id = " + id);
        } else {
            return orders.get(id);
        }
    }

    public List<Order> getAllOrders() {
        return orders.values().stream().toList();
    }

    public Order addOrder(Order newOrder) {
        if (newOrder.id() != null) {
            throw new IllegalArgumentException("Id should not be set!");
        }
        if (newOrder.status() != null) {
            throw new IllegalArgumentException("Status should not be set!");
        }
        var order = new Order(
          idCounter.incrementAndGet(),
                newOrder.user_id(),
                newOrder.product_id(),
                newOrder.amount_to_be_paid(),
                OrderStatus.IN_PROCESS
        );
        orders.put(order.id(), order);
        return order;
    }

    public void deleteOrder(Long id) {
        if (!orders.containsKey(id)) {
            throw new NoSuchElementException("Order not found! Id = " + id);
        }
        var order = orders.get(id);
        if (order.status() == OrderStatus.IN_TRANSIT) {
            throw new IllegalArgumentException("Order status should not be IN_TRANSIT!");
        }
        orders.remove(id);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        if (!orders.containsKey(id)) {
            throw new NoSuchElementException("Order not found! Id = " + id);
        }
        var order = orders.get(id);
        if (order.status() != OrderStatus.IN_PROCESS) {
            throw new IllegalArgumentException("Order status should be IN_PROCESS!");
        }
        var newOrder = new Order(
                order.id(),
                updatedOrder.user_id(),
                updatedOrder.product_id(),
                updatedOrder.amount_to_be_paid(),
                OrderStatus.IN_PROCESS
        );
        orders.put(id, newOrder);
        return newOrder;
    }
}
