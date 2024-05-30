package upc.edu.artcollab.api.monetization.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.monetization.domain.model.aggregates.Commision;
import upc.edu.artcollab.api.monetization.domain.model.commands.CreateCommisionCommand;
import upc.edu.artcollab.api.monetization.domain.model.commands.CreateSubscriptionCommand;
import upc.edu.artcollab.api.monetization.domain.services.CommisionCommandService;
import upc.edu.artcollab.api.monetization.infrastructure.persistence.jpa.CommisionRepository;

import java.util.Optional;

@Service
public class CommisionCommandServiceImpl implements CommisionCommandService {

    private final CommisionRepository commisionRepository;

    public CommisionCommandServiceImpl(CommisionRepository commisionRepository) {
        this.commisionRepository = commisionRepository;
    }

    @Override
    public Optional<Commision> handle(CreateCommisionCommand command) {
        var commision = new Commision(command);
        var createdCommision = commisionRepository.save(commision);
        return Optional.of(createdCommision);
    }

    @Override
    public Optional<Commision> update(Commision commision) {
        var updatedCommision = commisionRepository.save(commision);
        return Optional.of(updatedCommision);
    }

    @Override
    public void delete(Commision commision) {
         commisionRepository.delete(commision);
    }


}
