package serhii.meshcheriakov.springlearn1.model;

public record Order(
    Long id,
    Long user_id,
    Long product_id,
    Integer amount_to_be_paid,
    OrderStatus status
) {
}
