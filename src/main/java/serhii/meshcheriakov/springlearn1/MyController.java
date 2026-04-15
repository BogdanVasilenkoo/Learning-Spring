package serhii.meshcheriakov.springlearn1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import serhii.meshcheriakov.springlearn1.model.Order;

import java.util.List;

@RestController
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Long id) {
        return myService.getOrderById(id);
    }

    @GetMapping()
    public List<Order> getAllOrders() {
        return myService.getAllOrders();
    }
}
