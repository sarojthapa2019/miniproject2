package ea.miniproject2.paymentservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ea.miniproject2.paymentservice.service.PaymentService;
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


    @PostMapping("/payment")
    public String paymentModePaypal(@RequestHeader(name="Authorization") String token, @RequestHeader(name="secretkey") String mode  , @RequestBody Object object) throws UnsupportedEncodingException, JsonProcessingException {
        HashMap<String, String> dataHash= tokenDecoderService.decode(token);

        if(dataHash.get("role").equals("ROLE_USER")) {
            if(mode.equals("bank_secret_key")){
		System.out.println("iniside payment controller, calling bankservice");
               return paymentService.callBankService(token, object, mode).getBody();
            }
            if(mode.equals("cc_secret_key")){
               return paymentService.callCreditCardService(token, object, mode).getBody();
            }
            if(mode.equals("paypal_secret_key")){
               return paymentService.callBankService(token, object, mode).getBody();
            }
        }
        return "Sorry cannot make payment";

    }


}
