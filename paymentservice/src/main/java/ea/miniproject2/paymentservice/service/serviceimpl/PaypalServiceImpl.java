package ea.miniproject2.paymentservice.service.serviceimpl;

import ea.miniproject2.paymentservice.model.Paypal;
import ea.miniproject2.paymentservice.repository.PaypalRepository;
import ea.miniproject2.paymentservice.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaypalServiceImpl implements PaypalService {
    @Autowired
    private PaypalRepository paypalRepository;
    @Override
    public Paypal savePaypal(Paypal paypal) {
        return paypalRepository.save(paypal);
    }
}
