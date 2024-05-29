package upc.edu.artcollab.api.monetization.application.internal.queryservices;

import org.springframework.stereotype.Service;
import upc.edu.artcollab.api.monetization.domain.model.aggregates.Commision;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetCommisionByIdQuery;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetCommisionsByAmountGreatherThanQuery;
import upc.edu.artcollab.api.monetization.domain.services.CommisionQueryService;
import upc.edu.artcollab.api.monetization.infrastructure.persistence.jpa.CommisionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommisionQueryServiceImpl implements CommisionQueryService {

    private final CommisionRepository commisionRepository;

    public CommisionQueryServiceImpl(CommisionRepository commisionRepository) {
        this.commisionRepository = commisionRepository;
    }
    @Override
    public Optional<Commision> handle(GetCommisionByIdQuery query) {
        return commisionRepository.findById(query.id());
    }

    @Override
    public List<Commision> handle(GetCommisionsByAmountGreatherThanQuery query) {
        return commisionRepository.findCommisionByAmountGreaterThan(query.amount());
    }

    @Override
    public List<Commision> getAll() {
        return commisionRepository.getAll();
    }
}
