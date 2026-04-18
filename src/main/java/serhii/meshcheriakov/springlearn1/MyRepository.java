package serhii.meshcheriakov.springlearn1;

import org.springframework.data.jpa.repository.JpaRepository;
import serhii.meshcheriakov.springlearn1.entity.OrderEntity;

public interface MyRepository extends JpaRepository<OrderEntity, Long> {

}
