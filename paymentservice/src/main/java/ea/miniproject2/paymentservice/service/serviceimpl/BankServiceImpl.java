package ea.miniproject2.paymentservice.service.serviceimpl;

import ea.miniproject2.paymentservice.model.Bank;
import ea.miniproject2.paymentservice.repository.BankRepository;
import ea.miniproject2.paymentservice.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankRepository bankRepository;
    @Override
    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }
}
