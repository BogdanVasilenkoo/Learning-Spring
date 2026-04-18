package serhii.meshcheriakov.springlearn1;

import jakarta.persistence.EntityNotFoundException;
import serhii.meshcheriakov.springlearn1.entity.OrderEntity;
import serhii.meshcheriakov.springlearn1.model.Order;
import org.springframework.stereotype.Service;
import serhii.meshcheriakov.springlearn1.model.OrderStatus;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MyService {
    private final MyRepository repository;

    public MyService(MyRepository repository) {
        this.repository = repository;
    }

    public Order getOrderById(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Order not found! Id = " + id);
        }
        var foundOrder = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found! Id = " + id));
        return toDomainOrder(foundOrder);
    }

    public List<Order> getAllOrders() {
        List<OrderEntity> allOrdersEntities = repository.findAll();

        return allOrdersEntities.stream()
                .map(this::toDomainOrder)
                .toList();
    }

    public Order addOrder(Order newOrder) {
        var orderToCreate = new OrderEntity(
                null,
                newOrder.user_id(),
                newOrder.product_id(),
                newOrder.amount_to_be_paid(),
                OrderStatus.IN_PROCESS
        );
        var savedOrder = repository.save(orderToCreate);
        return toDomainOrder(savedOrder);
    }

    public void deleteOrder(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Order not found! Id = " + id);
        }
        repository.deleteById(id);
    }

//    @param orderToUpdate
//    is an Order type value which must be saved to DB
    public Order updateOrder(Long id, Order orderToUpdate) {
        var foundOrder = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found! Id = " + id));
        if (foundOrder.getStatus() != OrderStatus.IN_PROCESS) {
            throw new IllegalArgumentException("Order status should be IN_PROCESS!");
        }
        OrderEntity orderToSave = new OrderEntity(
                id,
                orderToUpdate.user_id(),
                orderToUpdate.product_id(),
                orderToUpdate.amount_to_be_paid(),
                OrderStatus.IN_PROCESS
        );
        repository.save(orderToSave);
        return toDomainOrder(orderToSave);
    }

    private Order toDomainOrder(OrderEntity foundOrder) {
        return new Order(
                foundOrder.getId(),
                foundOrder.getUser_id(),
                foundOrder.getProduct_id(),
                foundOrder.getAmount_to_be_paid(),
                foundOrder.getStatus()
        );
    }
}
