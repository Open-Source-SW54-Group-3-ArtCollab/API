package upc.edu.artcollab.api.monetization.application.internal.commandservices;


import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.monetization.domain.model.aggregates.Subscription;
import upc.edu.artcollab.api.monetization.domain.model.commands.CreateSubscriptionCommand;
import upc.edu.artcollab.api.monetization.domain.model.commands.DeleteSubscriptionCommand;
import upc.edu.artcollab.api.monetization.domain.model.commands.UpdateSubscriptionCommand;
import upc.edu.artcollab.api.monetization.domain.services.SubscriptionCommandService;
import upc.edu.artcollab.api.monetization.infrastructure.persistence.jpa.SubscriptionRepository;

import java.util.Optional;

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionCommandServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }
    @Override
    public Optional<Subscription> handle(CreateSubscriptionCommand command) {
        var subscription = new Subscription(command);
        var subscriptionCreated = subscriptionRepository.save(subscription);
        return Optional.of(subscriptionCreated);
    }

    @Override
    public Optional<Subscription> handle(DeleteSubscriptionCommand command) {
        Optional<Subscription> subscriptionToDelete = subscriptionRepository.getSubscriptionById(command.id());
        subscriptionRepository.delete(subscriptionToDelete.get());
        return Optional.of(subscriptionToDelete.get());
    }

    @Override
    public Optional<Subscription> handle(Long id,UpdateSubscriptionCommand command) {
        Optional<Subscription> subscriptionToUpdate = subscriptionRepository.getSubscriptionById(id);
        subscriptionToUpdate.get().setActive(command.isActive());
        subscriptionRepository.save(subscriptionToUpdate.get());
        return Optional.of(subscriptionToUpdate.get());
    }
}
