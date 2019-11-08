package ea.miniproject2.paymentservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ea.miniproject2.paymentservice.model.*;
import ea.miniproject2.paymentservice.service.BankService;
import ea.miniproject2.paymentservice.service.CreditCardService;
import ea.miniproject2.paymentservice.service.PaymentService;
import ea.miniproject2.paymentservice.service.PaypalService;
import ea.miniproject2.paymentservice.service.serviceimpl.TokenDecoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@RestController
public class PaymentController {

    @Autowired
    private TokenDecoderService tokenDecoderService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private BankService bankService;
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private PaypalService paypalService;

    @PostMapping("/payment/bank")
    public String paymentModeBank(@RequestHeader(name="Authorization") String token, @RequestBody Bank bank) throws UnsupportedEncodingException, JsonProcessingException {
        HashMap<String, String> dataHash= tokenDecoderService.decode(token);
        Orders orders = null;
        if(dataHash.get("role").equals("ROLE_USER")) {
             orders = paymentService.getOrder(token).getBody();
            return "Payment processed for Order Id "+ orders.getId() +" with amount "+orders.getTotal();
        }
        return "Sorry cannot make payment";

    }

    @PostMapping("/payment/paypal")
    public String paymentModePaypal(@RequestHeader(name="Authorization") String token, @RequestBody Paypal paypal) throws UnsupportedEncodingException, JsonProcessingException {
        HashMap<String, String> dataHash= tokenDecoderService.decode(token);
        Orders orders = null;
        if(dataHash.get("role").equals("ROLE_USER")) {
            orders = paymentService.getOrder(token).getBody();
            return "Payment processed for Order Id"+ orders.getId() +"with amount "+orders.getTotal();
        }
        return "Sorry cannot make payment";

    }

    @PostMapping("/payment/cc")
    public String paymentModeCC(@RequestHeader(name="Authorization") String token, @RequestBody CreditCard cc) throws UnsupportedEncodingException, JsonProcessingException {
        HashMap<String, String> dataHash= tokenDecoderService.decode(token);
        Orders orders = null;
        if(dataHash.get("role").equals("ROLE_USER")) {
            orders = paymentService.getOrder(token).getBody();
            return "Payment processed for Order Id"+ orders.getId() +"with amount "+orders.getTotal();
        }
        return "Sorry cannot make payment";

    }

}
