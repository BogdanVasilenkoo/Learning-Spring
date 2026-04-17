package serhii.meshcheriakov.springlearn1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serhii.meshcheriakov.springlearn1.model.Order;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/order")
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Long id) {
        logger.info("getOrderById");
        return myService.getOrderById(id);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        logger.info("getAllOrders");
        return ResponseEntity.ok(myService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order newOrder) {
        logger.info("addOrder");
        return ResponseEntity.status(201)
                .body(myService.addOrder(newOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        logger.info("deleteOrder");
        try {
            myService.deleteOrder(id);
        } catch (NoSuchElementException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(404).build();
        } catch (IllegalArgumentException e) {
            logger.info(e.getMessage());
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.status(200)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        logger.info("updateOrder");
        return ResponseEntity.ok(myService.updateOrder(id, updatedOrder));
    }
}
