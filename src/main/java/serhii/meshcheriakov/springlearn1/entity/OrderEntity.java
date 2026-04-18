package serhii.meshcheriakov.springlearn1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import serhii.meshcheriakov.springlearn1.model.OrderStatus;

@Table(name = "orders")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "product_id")
    private Long product_id;

    @Column(name = "amount_to_be_paid")
    private Integer amount_to_be_paid;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

}
