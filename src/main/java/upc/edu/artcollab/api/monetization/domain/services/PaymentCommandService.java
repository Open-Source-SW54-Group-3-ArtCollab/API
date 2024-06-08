package upc.edu.artcollab.api.monetization.domain.services;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.monetization.domain.model.commands.CreatePaymentCommand;

@Service
public interface PaymentCommandService {
    void createPayment(CreatePaymentCommand command);


}
