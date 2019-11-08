package ea.miniproject2.shippingservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ea.miniproject2.shippingservice.exception.ResourceException;
import ea.miniproject2.shippingservice.model.Address;
import ea.miniproject2.shippingservice.service.AddressService;
import ea.miniproject2.shippingservice.service.serviceimpl.TokenDecoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Optional;

@RestController
public class AddressController {

    @Autowired
    private TokenDecoderService tokenDecoderService;
    @Autowired
    private AddressService addressService;

    @PostMapping("/address")
    public Address saveAddress(@RequestHeader(name="Authorization") String token, @RequestBody Address address) throws UnsupportedEncodingException, JsonProcessingException {
        HashMap<String, String> dataHash= tokenDecoderService.decode(token);
        if(dataHash.get("role").equals("ROLE_USER")) {
            address.setUsername(dataHash.get("sub"));
            return   addressService.saveAddres(address);
        }
       return null;
    }

    @GetMapping("/address")
    public ResponseEntity<Address> getAddress(@RequestHeader(name="Authorization") String token) throws UnsupportedEncodingException, JsonProcessingException {
        HashMap<String, String> dataHash= tokenDecoderService.decode(token);
        if(dataHash.get("role").equals("ROLE_USER")) {
            Optional<Address> address = addressService.getAddressByUsername(dataHash.get("sub"));
            if(address.isPresent()){
                return ResponseEntity.accepted().body(address.get());
            }
        }
        throw new ResourceException(HttpStatus.NOT_FOUND, "We were unable to find the previously saved shipping address. </br>" +
                "Please save an address first");
    }

}
