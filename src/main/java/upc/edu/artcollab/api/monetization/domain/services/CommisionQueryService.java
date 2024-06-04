/**
 * @summary
 * import GetCommisionByIdQuery and GetCommisionsByAmmountGreatherThanQuery class from domain model queries
 * This file contains the CommisionQueryService interface.
 *
 */

package upc.edu.artcollab.api.monetization.domain.services;

import upc.edu.artcollab.api.monetization.domain.model.aggregates.Commision;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetCommisionByIdQuery;
import upc.edu.artcollab.api.monetization.domain.model.queries.GetCommisionsByAmountGreatherThanQuery;

import java.util.List;
import java.util.Optional;

public interface CommisionQueryService {
    Optional<Commision> handle(GetCommisionByIdQuery query);
    List <Commision> handle(GetCommisionsByAmountGreatherThanQuery query);
    List<Commision> getAll();
}
