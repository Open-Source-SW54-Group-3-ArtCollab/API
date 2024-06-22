package upc.edu.artcollab.api.monetization.application.internal.commandservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.monetization.domain.model.aggregates.Commision;
import upc.edu.artcollab.api.monetization.domain.model.commands.CreateCommisionCommand;
import upc.edu.artcollab.api.monetization.domain.model.commands.DeleteCommisionCommand;
import upc.edu.artcollab.api.monetization.domain.model.commands.UpdateCommisionCommand;
import upc.edu.artcollab.api.monetization.domain.model.exceptions.InvalidCommisionException;
import upc.edu.artcollab.api.monetization.domain.services.CommisionCommandService;
import upc.edu.artcollab.api.monetization.infrastructure.persistence.jpa.CommisionRepository;

import java.util.Optional;

/**
 * CommisionCommandServiceImpl
 *  <p>
 *       This class implements the CommisionCommandService interface and provides the implementation for the
 *        methods declared in the interface.
 *  </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
@Service
public class CommisionCommandServiceImpl implements CommisionCommandService {

    private final CommisionRepository commisionRepository;

    public CommisionCommandServiceImpl(CommisionRepository commisionRepository) {
        this.commisionRepository = commisionRepository;
    }

    @Override
    public Optional<Commision> handle(CreateCommisionCommand command) {
        var commision = new Commision(command);
        if(command.amount() <= 0){
            throw new InvalidCommisionException("Amount must be greater than 0");
        }
        var createdCommision = commisionRepository.save(commision);
        return Optional.of(createdCommision);
    }

    @Override
    public Optional<Commision> handle(Long id, UpdateCommisionCommand command) {
          Optional<Commision>commisionToUpdate = commisionRepository.findById(id);
          commisionToUpdate.get().setAmount(command.amount());
          commisionToUpdate.get().setContent(command.content());
          commisionRepository.save(commisionToUpdate.get());
          return Optional.of(commisionToUpdate.get());
    }

    @Override
    public Optional<Commision> handle(DeleteCommisionCommand command) {
        Optional<Commision> commisionToDelete = commisionRepository.findById(command.id());
        commisionRepository.delete(commisionToDelete.get());
        return Optional.of(commisionToDelete.get());
    }


}
