package ea.miniproject2.paymentservice.service.serviceimpl;

import ea.miniproject2.paymentservice.model.Orders;
import ea.miniproject2.paymentservice.model.Payment;
import ea.miniproject2.paymentservice.repository.PaymentRepository;
import ea.miniproject2.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> findAllPayment() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }
    @Override
    public ResponseEntity<Orders> getOrder(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+token );
        ResponseEntity<Orders> response = restTemplate.exchange("http://localhost:8082/getorder/{id}", HttpMethod.GET, new HttpEntity<>("parameters", headers),
                new ParameterizedTypeReference<Orders>() {
                },1);
        return response;
    }
}
