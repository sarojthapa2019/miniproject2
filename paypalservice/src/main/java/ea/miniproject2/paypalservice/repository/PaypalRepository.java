package ea.miniproject2.paypalservice.repository;

import ea.miniproject2.paypalservice.model.Paypal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaypalRepository extends JpaRepository<Paypal, Long> {
}
