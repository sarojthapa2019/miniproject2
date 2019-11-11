package ea.miniproject2.paymentservice.service.serviceimpl;

import ea.miniproject2.paymentservice.model.Payment;
import ea.miniproject2.paymentservice.repository.PaymentRepository;
import ea.miniproject2.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${BANK_SERVICE:#{null}}")
    private String bankUrl;
    @Value("${CC_SERVICE:#{null}}")
    private String ccUrl;
    @Value("${PAYPAL_SERVICE:#{null}}")
    private String paypalUrl;
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
    public  ResponseEntity<String> callBankService(String token, Object object, String secret) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Authorization", "Bearer "+token );
        headers.add("Content-Type", "application/json");
        headers.add("secret", secret);
        HttpEntity<Object> formEntity = new HttpEntity<Object>(object, headers);
        ResponseEntity<String> response = null;
        final String uri = String.format("http://%s/payment/bank",bankUrl);
        try{
            response = restTemplate.exchange(uri, HttpMethod.POST, formEntity,
                    new ParameterizedTypeReference<String>() {
                    });
        }
       catch (Exception e){

            e.printStackTrace();
       }
        finally {
            return response;
        }

    }

    @Override
    public ResponseEntity<String> callPaypalService(String token, Object object, String secret) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Authorization", "Bearer "+token );
        headers.add("Content-Type", "application/json");
        headers.add("secret", secret);
        HttpEntity<Object> formEntity = new HttpEntity<Object>(object, headers);
        ResponseEntity<String> response = null;
        final String uri = String.format("http://%s/payment/paypal",paypalUrl);
        try{
            response = restTemplate.exchange(uri, HttpMethod.POST, formEntity,
                    new ParameterizedTypeReference<String>() {
                    });
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return response;
        }
    }

    @Override
    public ResponseEntity<String> callCreditCardService(String token, Object object, String secret) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Authorization", "Bearer "+token );
        headers.add("Content-Type", "application/json");
        headers.add("secret", secret);

        HttpEntity<Object> formEntity = new HttpEntity<Object>(object, headers);
        ResponseEntity<String> response = null;
        final String uri = String.format("http://%s/payment/cc",ccUrl);
        try{
            response = restTemplate.exchange(uri, HttpMethod.POST, formEntity,
                    new ParameterizedTypeReference<String>() {
                    });
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return response;
        }
    }

}
