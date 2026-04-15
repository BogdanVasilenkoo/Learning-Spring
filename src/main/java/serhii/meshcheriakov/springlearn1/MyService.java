package serhii.meshcheriakov.springlearn1;

import serhii.meshcheriakov.springlearn1.model.Order;
import org.springframework.stereotype.Service;
import serhii.meshcheriakov.springlearn1.model.OrderStatus;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class MyService {
    private final Map<Long, Order> orders = Map.of(
            1L, new Order(
                    1L,
                    100L,
                    40L,
                    399,
                    OrderStatus.IN_PROCESS
            ),
            2L, new Order(
                    2L,
                    85L,
                    40L,
                    2889,
                    OrderStatus.CANCELLED
            ),
            3L, new Order(
                    3L,
                    85L,
                    12L,
                    99,
                    OrderStatus.IN_TRANSIT
            )
    );

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
}
