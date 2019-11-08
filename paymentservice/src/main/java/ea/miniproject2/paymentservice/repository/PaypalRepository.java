package ea.miniproject2.paymentservice.repository;

import ea.miniproject2.paymentservice.model.Paypal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaypalRepository extends JpaRepository<Paypal, Long> {
}
