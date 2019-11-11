package ea.miniproject2.paypalservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ea.miniproject2.paypalservice.model.Orders;
import ea.miniproject2.paypalservice.model.Paypal;
import ea.miniproject2.paypalservice.service.PaypalService;
import ea.miniproject2.paypalservice.service.serviceimpl.TokenDecoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@RestController
public class PaypalController {

    @Autowired
    private TokenDecoderService tokenDecoderService;
    @Autowired
    private PaypalService paypalService;


    @PostMapping("/payment/paypal")
    public String paymentModePaypal(@RequestHeader(name="Authorization") String token,@RequestHeader(name="secret") String secret, @RequestBody Paypal paypal) throws UnsupportedEncodingException, JsonProcessingException {
        if(secret.equals("paypal_secret_key")) {
            HashMap<String, String> dataHash = tokenDecoderService.decode(token);
            Orders orders = null;
            if (dataHash.get("role").equals("ROLE_USER")) {
                orders = paypalService.getOrder(token).getBody();
                return "Payment processed for Order Id" + orders.getId() + "with amount " + orders.getTotal();
            }
        }
        return "Sorry cannot make payment";

    }


}
