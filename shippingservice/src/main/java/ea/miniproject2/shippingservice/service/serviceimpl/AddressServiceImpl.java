package ea.miniproject2.shippingservice.service.serviceimpl;

import ea.miniproject2.shippingservice.model.Address;
import ea.miniproject2.shippingservice.repository.AddressRepository;
import ea.miniproject2.shippingservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Address saveAddres(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Optional<Address> getAddressByUsername(String username) {
        return addressRepository.findByUsername(username);
    }
}
