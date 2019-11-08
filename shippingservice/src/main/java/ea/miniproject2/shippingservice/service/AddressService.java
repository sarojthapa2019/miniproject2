package ea.miniproject2.shippingservice.service;

import ea.miniproject2.shippingservice.model.Address;

import java.util.Optional;

public interface AddressService {
    Address saveAddres(Address address);
    Optional<Address> getAddressByUsername(String username);
}
