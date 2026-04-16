package serhii.meshcheriakov.springlearn1;

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
}
