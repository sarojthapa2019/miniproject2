package ea.miniproject2.orderservice.repository;

import ea.miniproject2.orderservice.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByUsername(String username);
//    @Query(value = "SELECT  o from Orders o where o.username =:username order by o.id desc offset ")
//    Optional<Orders> findByUsernameAndOrderByIdDesc(String username);
    Optional<Orders> findFirstByUsernameOrderByIdDesc(String username);

}
