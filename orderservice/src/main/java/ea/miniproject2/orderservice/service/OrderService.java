package ea.miniproject2.orderservice.service;

import ea.miniproject2.orderservice.model.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Orders saveOrder(Orders orders, String token);
    Optional<Orders> getOrderById(Long id);
    List<Orders> getAllOrdersByUser(String username);
    Optional<Orders> findByUsername(String username);
}
