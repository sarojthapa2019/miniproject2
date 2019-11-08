package ea.miniproject2.orderservice.repository;

import ea.miniproject2.orderservice.model.CartEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartEntryRepository extends JpaRepository<CartEntry, Long> {

    List<CartEntry> getByUsername(String username);
}
